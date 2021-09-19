package work.pcdd.qndxx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片表
 *
 * @author AD
 */
@Data
@NoArgsConstructor
@ApiModel(description = "图片表")
public class Image {
    @ApiModelProperty("图片绝对路径")
    private String imgKey;

    @ApiModelProperty("图片大小（单位kb）")
    private Double imgSize;

    @ApiModelProperty("图片扩展名）")
    private String imgExtension;
}
