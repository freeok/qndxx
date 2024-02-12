package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pcdd
 */
@Data
@ApiModel(description = "上传记录表")
public class Upload implements Serializable {

    @ApiModelProperty("学号")
    private String stuId;

    @ApiModelProperty("图片路径")
    private String imgKey;

    @ApiModelProperty("上传时间")
    private Date uploadTime;

}
