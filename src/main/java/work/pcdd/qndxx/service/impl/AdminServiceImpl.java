package work.pcdd.qndxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.common.vo.ResultCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.AdminMapper;
import work.pcdd.qndxx.service.AdminService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author AD
 * @date
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result login(String stuId, String pwd, HttpSession session) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(pwd);
        List<Student> list = adminMapper.login(student);
        if (list.size() == 1 && Objects.equals(list.get(0).getStuId(), stuId)
                && Objects.equals(list.get(0).getPwd(), pwd)) {
            session.setAttribute("admin", list.get(0));
            return Result.success();
        }
        return Result.failure(ResultCode.USER_LOGIN_ERROR);
    }

    @Override
    public Result findAllByClazzName(String clazzName, int start, int limit) {
        // 分页助手，设置起始页和每页显示的条数
        PageHelper.startPage(start, limit);
        List<Student> list = adminMapper.findAllByClazzName(clazzName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return Result.success0(list, pageInfo.getTotal());
    }

    @Override
    public Result findSubmitted(String clazzName, int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Map<String, Object>> list = adminMapper.findSubmitted(clazzName);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return Result.success0(list, pageInfo.getTotal());
    }

    @Override
    public Result findUnpaid(String clazzName, int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Student> list = adminMapper.findUnpaid(clazzName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return Result.success0(list, pageInfo.getTotal());
    }

    @Override
    public Result findSubmittedCount(String clazzName) {
        return Result.success(adminMapper.findSubmittedCount(clazzName));
    }

    @Override
    public Result findUnpaidCount(String clazzName) {
        return Result.success(adminMapper.findUnpaidCount(clazzName));
    }

    @Override
    public Result updPwd(String oldPwd, String newPwd, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        String stuId = admin.getStuId();
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(oldPwd);
        // 旧密码错误，拒绝修改
        if (adminMapper.login(student).isEmpty()) {
            return Result.failure(ResultCode.ADMIN_OLD_PASSWORD_ERROR);
        }
        student.setPwd(newPwd);
        if (adminMapper.updPwd(student) == 1) {
            return Result.success();
        }

        return Result.failure(ResultCode.ADMIN_UPDATE_PASSWORD_FAIL);
    }


}
