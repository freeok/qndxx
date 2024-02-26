package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织表
 *
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "组织表")
public class Organize implements Serializable {

    @Schema(name = "组织 ID")
    private Integer id;

    @Schema(name = "组织名")
    private String organizeName;

    @Schema(name = "季数")
    private Integer season;

    @Schema(name = "期数")
    private Integer period;

    @Schema(name = "系统启动状态")
    private Boolean isEnable;

    @Schema(name = "创建日期")
    private Date createdAt;

}
