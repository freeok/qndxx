package work.pcdd.qndxx.mapper;

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
    int add(Upload upload);

    /**
     * 根据组织删除upload表记录
     *
     * @param organizeName 组织名
     * @return 影响的行数
     */
    int delete(String organizeName);

    List<Upload> exists(String userId);

}
