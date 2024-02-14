package work.pcdd.qndxx.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片表
 *
 * @author pcdd
 */
@Data
@NoArgsConstructor
@Schema(name = "图片表")
public class Image implements Serializable {

    @Schema(name = "图片绝对路径")
    private String imgKey;

    @Schema(name = "图片大小（单位kb）")
    private Double imgSize;

    @Schema(name = "图片扩展名")
    private String imgExtension;

}
