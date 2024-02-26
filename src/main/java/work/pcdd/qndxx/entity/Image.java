package work.pcdd.qndxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pcdd
 */
@Data
@Schema(description = "上传记录表")
public class Image implements Serializable {

    @Schema(name = "上传记录 ID")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(name = "图片路径")
    private String imgKey;

    @Schema(name = "图片大小(KB)")
    private Double size;

    @Schema(name = "图片扩展名")
    private String extName;

    @Schema(name = "上传时间")
    private Date createdAt;

    @Schema(name = "用户 ID")
    private String userId;

}
