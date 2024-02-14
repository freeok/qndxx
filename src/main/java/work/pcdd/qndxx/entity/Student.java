package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生表
 *
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "学生表")
public class Student implements Serializable {

    @Schema(name = "学号")
    private String stuId;

    @Schema(name = "学生姓名")
    private String stuName;

    @Schema(name = "密码")
    private String pwd;

    @Schema(name = "角色")
    private String role;

    @Schema(name = "班级名")
    private String clazzName;

}
