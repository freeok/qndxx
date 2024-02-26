package work.pcdd.qndxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrganizeServiceImpl extends ServiceImpl<OrganizeMapper, Organize> implements OrganizeService {

}
