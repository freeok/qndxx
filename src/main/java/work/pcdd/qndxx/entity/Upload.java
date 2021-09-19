package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author AD
 */
@Data
@ApiModel(description = "上传记录表")
public class Upload {
    @ApiModelProperty("学号")
    private String stuId;

    @ApiModelProperty("图片路径")
    private String imgKey;

    @ApiModelProperty("上传时间")
    private Date uploadTime;
}
