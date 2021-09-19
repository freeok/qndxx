package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AD
 */
@Data
@NoArgsConstructor
@ApiModel(description = "系统参数表")
public class Param {
    @ApiModelProperty("公告信息")
    private String notice;
}
