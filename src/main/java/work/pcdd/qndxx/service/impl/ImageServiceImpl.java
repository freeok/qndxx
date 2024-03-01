package work.pcdd.qndxx.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.entity.Organize;
import work.pcdd.qndxx.common.dto.FileUploadDTO;
import work.pcdd.qndxx.mapper.ImageMapper;
import work.pcdd.qndxx.service.ImageService;
import work.pcdd.qndxx.service.OrganizeService;
import work.pcdd.qndxx.util.R;
import work.pcdd.qndxx.util.UploadUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author pcdd
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    private final ImageMapper imageMapper;
    private final OrganizeService organizeService;

    /**
     * 截图上传
     */
    @Override
    public R upload(FileUploadDTO dto) {
        MultipartFile mf = dto.getFile();
        // 若文件不存在，则拒绝上传
        if (mf.isEmpty()) {
            return R.fail(RCode.FILE_NOT_FOUND);
        }

        // 判断上传到哪个目录
        String dirName = "upload1".equals(dto.getType()) ? "朋友圈截图" : "首页截图";
        // 获取文件大小(B => KB)，保留两位小数
        BigDecimal size = NumberUtil.round(mf.getSize() / 1024.0, 2);
        log.info("fileSize:" + size.doubleValue() + "KB");
        // 获取文件名(文件主名 + 扩展名)
        String fileName = mf.getOriginalFilename();
        log.info("fileName:" + fileName);
        // 获取文件扩展名
        String extension = FileUtil.extName(fileName);
        // 获取指定文件在当前项目的绝对路径
        String filePath = UploadUtils.getRealPath("image", dto.getOrganizeName(), dirName, fileName);
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

        // 保存上传记录
        Image image = new Image();
        image.setUserId(dto.getUserId());
        image.setImgKey(relativePath);
        image.setSize(size.doubleValue());
        image.setExtName(extension);
        image.setCreatedAt(new Date());
        imageMapper.add(image);

        return R.ok("上传成功");
    }

    /**
     * 截图下载
     *
     * @param resp         response对象
     * @param organizeName 组织名
     */
    @SneakyThrows
    @Override
    public void download(HttpServletResponse resp, String organizeName) {
        // 指定压缩哪一个目录（目录名即为组织名）
        String filePath = UploadUtils.IMG_REAL_PATH + organizeName;
        log.info("filePath:" + filePath);
        ZipUtil.zip(filePath);

        // 设置下载响应头
        resp.setContentType("application/octet-stream;charset=utf-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(organizeName + ".zip", "UTF-8"));

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
    public void reset(Integer organizeId) {
        Organize organize = organizeService.getById(organizeId);
        imageMapper.delete(organize.getId());
        String path = UploadUtils.IMG_REAL_PATH + organize.getOrganizeName();
        log.info("delete:" + path);
        // 删除指定组织的图片目录
        FileUtil.del(path);
        // 删除指定组织的压缩包
        FileUtil.del(path + ".zip");
    }

}
