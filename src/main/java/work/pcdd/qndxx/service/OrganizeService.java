package work.pcdd.qndxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.pcdd.qndxx.entity.Organize;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface OrganizeService extends IService<Organize> {

    /**
     * 根据组织查询organize表
     */
    Organize getOne(Integer organizeId, HttpSession session);

    /**
     * 根据组织修改期数
     */
    Integer updateIssue(Organize organize);

    /**
     * 根据组织修改系统状态
     */
    Integer updateIsEnable(Organize organize, HttpSession session);

}
