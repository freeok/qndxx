package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Clazz getClazz(String clazzName, HttpSession session) {
        Clazz clazz = clazzMapper.getClazz(clazzName);
        session.setAttribute("clazz", clazz);
        return clazz;
    }

    @Override
    public Integer updateIssue(Clazz clazz) {
        return clazzMapper.updateIssue(clazz);
    }

    @Override
    public Integer updateIsEnable(Clazz clazz, HttpSession session) {
        return clazzMapper.updateIsEnable(clazz);
    }

}
