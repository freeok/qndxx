package work.pcdd.qndxx.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * 上传情况页 > 表格
 *
 * @author pcdd
 */
@Data
public class UploadDetailsVO {

    private String userId;

    private String username;

    /**
     * 是否上交
     */
    private String hasUploaded;

    private Date createdAt;

}
