package work.pcdd.qndxx.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.common.vo.ResultCode;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.entity.Upload;
import work.pcdd.qndxx.mapper.AdminMapper;
import work.pcdd.qndxx.mapper.ImageMapper;
import work.pcdd.qndxx.service.ImageService;
import work.pcdd.qndxx.util.UploadUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author AD
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 截图上传
     *
     * @param id        学号
     * @param name      学生姓名
     * @param par       图片类型：upload1、upload2分别表示朋友圈截图，首页截图
     * @param clazzName 班级名
     * @param mf        MultipartFile对象
     */
    @Override
    public Result upload(String id, String name, String par, String clazzName, MultipartFile mf) {
        log.info("========================upload start========================");
        // 若文件不存在，则拒绝上传
        if (mf.isEmpty()) {
            return Result.failure(ResultCode.FILE_NOT_FOUND);
        }

        // 判断上传到哪个目录
        String dirName = "upload1".equals(par) ? "朋友圈截图" : "首页截图";
        // 获取文件大小(B => KB)，保留两位小数
        BigDecimal size = NumberUtil.round(mf.getSize() / 1024.0, 2);
        log.info("fileSize:" + size.doubleValue() + "KB");
        // 获取文件名(文件主名 + 扩展名)
        String fileName = mf.getOriginalFilename();
        log.info("fileName:" + fileName);
        // 获取文件扩展名
        String extension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        // 获取指定文件在当前项目的绝对路径
        String filePath = UploadUtils.getRealPath("image", clazzName, dirName, fileName);
        log.info("filePath:" + filePath);
        // 相对路径
        String relativePath = filePath.substring(filePath.indexOf("uploads"));
        // 这里将\替换成了/ 目的在于便于springmvc访问路径，
        relativePath = "/" + relativePath.replaceAll("\\\\", "/");
        log.info("相对路径：" + relativePath);

        try {
            File file = new File(filePath);
            // 上传文件
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Upload upload = new Upload();
        upload.setStuId(id);
        upload.setImgKey(relativePath);
        upload.setUploadTime(new Date());
        // 将上传者的学号、文件路径、上传时间保存到upload表
        imageMapper.addUpload(upload);

        Image image = new Image();
        image.setImgKey(relativePath);
        image.setImgSize(size.doubleValue());
        image.setImgExtension(extension);
        // 将上传图片的路径、图片大小、图片扩展名保存到image表
        imageMapper.addImage(image);

        log.info("========================upload end========================");
        return Result.success();
    }

    /**
     * 截图下载
     *
     * @param req       request对象
     * @param resp      response对象
     * @param clazzName 班级名
     */
    @Override
    public Result download(HttpServletRequest req, HttpServletResponse resp, String clazzName) {
        log.info("========================download start========================");
        // 若该班级无人上交，拒绝下载
        if (adminMapper.findSubmitted(clazzName).isEmpty()) {
            return Result.failure(ResultCode.DOWNLOAD_REFUSE);
        }

        // 指定压缩哪一个目录（目录名即为班级名）
        String zipPath = UploadUtils.IMG_REAL_PATH + clazzName;
        log.info("zipPath:" + zipPath);
        ZipUtil.zip(zipPath);

        // 指定压缩文件名
        String zipName = clazzName + ".zip";

        // 实现文件下载 设置响应头,zipName设置要下载的zip的名称
        resp.setContentType("application/force-download");
        try {
            // 防止文件名中文乱码
            resp.setHeader("Content-Disposition", "attachment;filename=" + new String(zipName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 先使用文件输入流 将文件读到内存中 再使用输出流 将文件输出给用户
        File file = new File(zipPath + ".zip");
        if (!file.exists()) {
            return Result.failure(ResultCode.FILE_NOT_FOUND);
        }

        try (InputStream fis = new FileInputStream(file)) {
            // 准备一个缓冲区
            byte[] bytes = new byte[(int) file.length()];
            // 将文件读入缓冲区中
            fis.read(bytes);
            // 获得响应的输出流
            ServletOutputStream sos = resp.getOutputStream();
            sos.write(bytes);
            sos.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        log.info("========================download end========================");
        return Result.success();
    }

    @Override
    public Result deleteUpload(String clazzName) {
        imageMapper.deleteUpload(clazzName);
        String path = UploadUtils.IMG_REAL_PATH + clazzName;
        log.info("deleteUpload:" + path);
        // 删除指定班级的图片目录
        FileUtil.del(path);
        // 删除指定班级的压缩包
        FileUtil.del(path + ".zip");
        return Result.success();
    }

    @Override
    public Result isUploaded(String stuId) {
        List<Upload> list = imageMapper.isUploaded(stuId);
        return Result.success(list);
    }

}
