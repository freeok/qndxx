package work.pcdd.qndxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.pcdd.qndxx.entity.Image;

/**
 * @author pcdd
 */
public interface ImageMapper extends BaseMapper<Image> {

    /**
     * 增加一条图片上传记录
     *
     * @param image 上传记录表对象
     * @return 影响的行数
     */
    int add(Image image);

    /**
     * 根据组织删除upload表记录
     *
     * @return 影响的行数
     */
    int delete(Integer organizeId);

}
