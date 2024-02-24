package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.entity.Organize;
import work.pcdd.qndxx.mapper.OrganizeMapper;
import work.pcdd.qndxx.service.OrganizeService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class OrganizeServiceImpl implements OrganizeService {

    private final OrganizeMapper organizeMapper;

    @Override
    public Organize getOne(String organizeName, HttpSession session) {
        Organize organize = organizeMapper.getOne(organizeName);
        session.setAttribute("organize", organize);
        return organize;
    }

    @Override
    public Integer updateIssue(Organize organize) {
        return organizeMapper.updateIssue(organize);
    }

    @Override
    public Integer updateIsEnable(Organize organize, HttpSession session) {
        return organizeMapper.updateIsEnable(organize);
    }

}
