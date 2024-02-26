package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pcdd
 */
@Data
@Schema(description = "上传记录表")
public class Upload implements Serializable {

    @Schema(name = "id")
    private Integer id;

    @Schema(name = "用户id")
    private String userId;

    @Schema(name = "图片路径")
    private String imgKey;

    @Schema(name = "图片大小（单位kb）")
    private Double size;

    @Schema(name = "图片扩展名")
    private String extName;

    @Schema(name = "上传时间")
    private Date uploadTime;

}
