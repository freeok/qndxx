package work.pcdd.qndxx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "用户表")
public class User implements Serializable {

    @Schema(name = "用户 ID")
    @TableId
    private String userId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "密码")
    private String pwd;

    @Schema(name = "角色")
    private String role;

    @Schema(name = "组织名")
    private String organizeName;

    @Schema(name = "组织 ID")
    private Integer organizeId;

    private String oldId;

}
