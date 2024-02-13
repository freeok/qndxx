package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.entity.Clazz;
import work.pcdd.qndxx.mapper.ClazzMapper;
import work.pcdd.qndxx.service.ClazzService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    @Override
    public Result getClazz(String clazzName, HttpSession session) {
        Clazz clazz = clazzMapper.getClazz(clazzName);
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
