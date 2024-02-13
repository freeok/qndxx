package work.pcdd.qndxx.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.common.vo.ResultCode;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.entity.Upload;
import work.pcdd.qndxx.mapper.ImageMapper;
import work.pcdd.qndxx.service.ImageService;
import work.pcdd.qndxx.util.UploadUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author pcdd
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    /**
     * 截图上传
     *
     * @param id        学号
     * @param name      学生姓名
     * @param type      图片类型：upload1、upload2分别表示朋友圈截图，首页截图
     * @param clazzName 班级名
     * @param mf        MultipartFile对象
     */
    @Override
    public Result upload(String id, String name, String type, String clazzName, MultipartFile mf) {
        // 若文件不存在，则拒绝上传
        if (mf.isEmpty()) {
            return Result.failure(ResultCode.FILE_NOT_FOUND);
        }

        // 判断上传到哪个目录
        String dirName = "upload1".equals(type) ? "朋友圈截图" : "首页截图";
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
        relativePath = "/" + relativePath.replace("\\", "/");
        log.info("相对路径：" + relativePath);

        try {
            // 上传文件
            mf.transferTo(new File(filePath));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        Image image = new Image();
        image.setImgKey(relativePath);
        image.setImgSize(size.doubleValue());
        image.setImgExtension(extension);
        // 将上传图片的路径、图片大小、图片扩展名保存到image表
        imageMapper.addImage(image);

        Upload upload = new Upload();
        upload.setStuId(id);
        upload.setImgKey(relativePath);
        upload.setUploadTime(new Date());
        // 将上传者的学号、文件路径、上传时间保存到upload表
        imageMapper.addUpload(upload);

        return Result.success();
    }

    /**
     * 截图下载
     *
     * @param req       request对象
     * @param resp      response对象
     * @param clazzName 班级名
     */
    @SneakyThrows
    @Override
    public void download(HttpServletRequest req, HttpServletResponse resp, String clazzName) {
        // 指定压缩哪一个目录（目录名即为班级名）
        String filePath = UploadUtils.IMG_REAL_PATH + clazzName;
        log.info("filePath:" + filePath);
        ZipUtil.zip(filePath);

        // 设置下载响应头
        resp.setContentType("application/octet-stream;charset=utf-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(clazzName + ".zip", "UTF-8"));

        try (InputStream in = Files.newInputStream(new File(filePath + ".zip").toPath());
             ServletOutputStream out = resp.getOutputStream()) {
            int len;
            // 缓冲区大小 4 KB
            byte[] buffer = new byte[4096];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
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
