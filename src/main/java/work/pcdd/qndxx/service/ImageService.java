package work.pcdd.qndxx.service;

import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.util.R;
import work.pcdd.qndxx.entity.Upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author pcdd
 */
public interface ImageService {

    /**
     * 上传图片
     *
     * @param id        学号
     * @param name      学生姓名
     * @param par       图片类型：upload1、upload2分别表示朋友圈截图，首页截图
     * @param clazzName 班级名
     * @param mf        MultipartFile对象
     * @return 图片的绝对路径，大小
     */
    R upload(String id, String name, String par, String clazzName, MultipartFile mf);

    /**
     * 根据班级名下载压缩包
     *
     * @param req  request对象
     * @param resp response对象
     * @return 执行结果
     */
    void download(HttpServletRequest req, HttpServletResponse resp, String clazzName);

    /**
     * 根据班级删除upload表记录
     *
     * @return 执行结果
     */
    void deleteUpload(String clazzName);

    /**
     * 根据学号查询upload表字段
     *
     * @param stuId
     * @return 执行结果
     */
    List<Upload> isUploaded(String stuId);

}
