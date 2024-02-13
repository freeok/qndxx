package work.pcdd.qndxx.mapper;

import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.entity.Upload;

import java.util.List;

/**
 * @author pcdd
 */
public interface ImageMapper {

    /**
     * 增加一条图片上传记录
     *
     * @param upload 上传记录表对象
     * @return 影响的行数
     */
    int addUpload(Upload upload);

    /**
     * 增加一条上传图片信息
     *
     * @param image 图片表对象
     * @return 影响的行数
     */
    int addImage(Image image);

    /**
     * 根据班级删除upload表记录
     *
     * @param clazzName 班级名
     * @return 影响的行数
     */
    int deleteUpload(String clazzName);

    List<Upload> isUploaded(String stuId);

}
