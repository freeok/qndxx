package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.R;
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
    public R getClazz(String clazzName, HttpSession session) {
        Clazz clazz = clazzMapper.getClazz(clazzName);
        session.setAttribute("clazz", clazz);
        return R.success(clazz);
    }

    @Override
    public R updateIssue(Clazz clazz) {
        return R.success(clazzMapper.updateIssue(clazz));
    }

    @Override
    public R updateIsEnable(Clazz clazz, HttpSession session) {
        return R.success(clazzMapper.updateIsEnable(clazz));
    }

}
