package work.pcdd.qndxx.mapper;

import work.pcdd.qndxx.entity.Organize;

/**
 * @author pcdd
 */
public interface OrganizeMapper {

    /**
     * organize
     *
     * @return Organize bean
     */
    Organize getOne(Integer organizeId);

    /**
     * 根据组织修改期数
     *
     * @param organize 组织bean
     * @return 影响的行数
     */
    int updateIssue(Organize organize);

    /**
     * 根据组织修改系统状态
     *
     * @param organize 组织bean
     * @return 影响的行数
     */
    int updateIsEnable(Organize organize);

}
