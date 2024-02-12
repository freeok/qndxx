package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生表
 *
 * @author AD
 */
@Data
@NoArgsConstructor
@ApiModel(description = "学生表")
public class Student implements Serializable {
    @ApiModelProperty("学号")
    private String stuId;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("班级名")
    private String clazzName;
}
