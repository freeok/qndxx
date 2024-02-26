package work.pcdd.qndxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author pcdd
 */
public interface ImageService extends IService<Image> {

    /**
     * 上传图片
     *
     * @param id           账号
     * @param name         用户姓名
     * @param par          图片类型：upload1、upload2分别表示朋友圈截图，首页截图
     * @param organizeName 组织名
     * @param mf           MultipartFile对象
     * @return 图片的绝对路径，大小
     */
    R upload(String id, String name, String par, String organizeName, MultipartFile mf);

    /**
     * 根据组织名下载压缩包
     *
     * @param req  request对象
     * @param resp response对象
     */
    void download(HttpServletRequest req, HttpServletResponse resp, String organizeName);

    /**
     * 根据组织删除upload表记录
     */
    void reset(Integer organizeId);

}
