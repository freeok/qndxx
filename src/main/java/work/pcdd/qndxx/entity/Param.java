package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pcdd
 */
@Data
@NoArgsConstructor
@ApiModel(description = "系统参数表")
public class Param implements Serializable {

    @ApiModelProperty("公告信息")
    private String notice;

}
