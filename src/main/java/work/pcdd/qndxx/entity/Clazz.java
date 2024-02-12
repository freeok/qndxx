package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级表
 *
 * @author AD
 */
@Data
@NoArgsConstructor
@ApiModel(description = "班级表")
public class Clazz implements Serializable {
    @ApiModelProperty("班级名")
    private String clazzName;

    @ApiModelProperty("季数")
    private String season;

    @ApiModelProperty("期数")
    private String period;

    @ApiModelProperty("系统启动状态")
    private Boolean isEnable;

    @ApiModelProperty("创建日期")
    private Date createdAt;
}
