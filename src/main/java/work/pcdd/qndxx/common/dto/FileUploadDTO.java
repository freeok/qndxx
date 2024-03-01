package work.pcdd.qndxx.common.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pcdd
 */
@Data
public class FileUploadDTO {

    /**
     * 变量名一定为 file
     */
    private MultipartFile file;
    private String userId;
    private String username;
    private String organizeName;
    /**
     * 图片类型：upload1、upload2 分别表示朋友圈截图，首页截图
     */
    private String type;

}
