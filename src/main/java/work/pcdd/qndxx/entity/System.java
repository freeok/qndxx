package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "系统参数表")
public class System implements Serializable {

    @Schema(name = "公告信息")
    private String notice;

    @Schema(name = "系统版本")
    private String version;

}
