package work.pcdd.qndxx.service;

import work.pcdd.qndxx.entity.Organize;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface OrganizeService {

    /**
     * 根据组织查询organize表
     *
     * @param organizeName 组织名
     * @return 执行结果
     */
    Organize getOne(String organizeName, HttpSession session);

    /**
     * 根据组织修改期数
     *
     * @param organize 组织bean
     * @return 执行结果
     */
    Integer updateIssue(Organize organize);

    /**
     * 根据组织修改系统状态
     *
     * @param organize 组织bean
     * @return 执行结果
     */
    Integer updateIsEnable(Organize organize, HttpSession session);

}
