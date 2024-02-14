package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.R;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.StudentMapper;
import work.pcdd.qndxx.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Override
    public R unSafeLogin(String stuId, HttpSession session) {
        Student student = studentMapper.findById(stuId);
        if (student != null && Objects.equals(student.getStuId(), stuId)) {
            session.setAttribute("student", student);
            return R.success();
        }
        return R.failure(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public R addStudent(Student student) {
        // 添加学生前判断学号是否存在，若存在，则拒绝添加
        if (studentMapper.findById(student.getStuId()) != null) {
            return R.failure(RCode.USER_HAS_EXISTED);
        }
        return R.success(studentMapper.addStudent(student));
    }

    @Override
    public R delStudentById(String stuId) {
        return R.success(studentMapper.delStudentById(stuId));
    }

    @Override
    public R findByName(Student student) {
        List<Student> list = studentMapper.findByName(student);
        return R.success0(list, (long) list.size());
    }

    @Override
    public R updStudentById(Student student) {
        return R.success(studentMapper.updStudentById(student));
    }

}
