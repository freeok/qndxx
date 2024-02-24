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
@Schema(name = "用户表")
public class User implements Serializable {

    @Schema(name = "用户 ID")
    private String userId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "密码")
    private String pwd;

    @Schema(name = "角色")
    private String role;

    @Schema(name = "班级名")
    private String clazzName;

}
