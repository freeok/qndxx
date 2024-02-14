package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级表
 *
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "班级表")
public class Clazz implements Serializable {

    @Schema(name = "班级名")
    private String clazzName;

    @Schema(name = "季数")
    private String season;

    @Schema(name = "期数")
    private String period;

    @Schema(name = "系统启动状态")
    private Boolean isEnable;

    @Schema(name = "创建日期")
    private Date createdAt;

}
