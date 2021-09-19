package work.pcdd.qndxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.entity.Clazz;
import work.pcdd.qndxx.mapper.ClazzMapper;
import work.pcdd.qndxx.service.ClazzService;

import javax.servlet.http.HttpSession;

/**
 * @author AD
 */
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public Result getClazz(String clazzName, HttpSession session) {
        Clazz clazz = clazzMapper.getClazz(clazzName).get(0);
        session.setAttribute("clazz", clazz);
        return Result.success(clazz);
    }

    @Override
    public Result updateIssue(Clazz clazz) {
        return Result.success(clazzMapper.updateIssue(clazz));
    }

    @Override
    public Result updateIsEnable(Clazz clazz, HttpSession session) {
        return Result.success(clazzMapper.updateIsEnable(clazz));
    }
}
