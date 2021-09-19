package work.pcdd.qndxx.mapper;

import org.springframework.stereotype.Repository;
import work.pcdd.qndxx.entity.Clazz;

import java.util.List;

/**
 * @author AD
 */
@Repository
public interface ClazzMapper {
    /**
     * 根据班级查询clazz表
     *
     * @param clazzName 班级名
     * @return Clazz bean
     */
    List<Clazz> getClazz(String clazzName);

    /**
     * 根据班级修改期数
     *
     * @param clazz 班级bean
     * @return 影响的行数
     */
    int updateIssue(Clazz clazz);

    /**
     * 根据班级修改系统状态
     *
     * @param clazz 班级bean
     * @return 影响的行数
     */
    int updateIsEnable(Clazz clazz);


}
