package work.pcdd.qndxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.common.dto.FileUploadDTO;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpServletResponse;

/**
 * @author pcdd
 */
public interface ImageService extends IService<Image> {

    /**
     * 上传图片
     */
    R upload(FileUploadDTO dto);

    /**
     * 根据组织名下载压缩包
     *
     * @param resp response对象
     */
    void download(HttpServletResponse resp, String organizeName);

    /**
     * 根据组织删除upload表记录
     */
    void reset(Integer organizeId);

}
