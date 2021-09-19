package work.pcdd.qndxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.common.vo.ResultCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.StudentMapper;
import work.pcdd.qndxx.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author AD
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Result unSafeLogin(String stuId, HttpSession session) {
        Student student = studentMapper.findById(stuId);
        System.out.println(student);

        if (student != null && Objects.equals(student.getStuId(), stuId)) {
            session.setAttribute("student", student);
            return Result.success();
        }
        return Result.failure(ResultCode.USER_LOGIN_ERROR);
    }

    @Override
    public Result addStudent(Student student) {
        // 添加学生前判断学号是否存在，若存在，则拒绝添加
        if (studentMapper.findById(student.getStuId()) != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
        return Result.success(studentMapper.addStudent(student));
    }

    @Override
    public Result delStudentById(String stuId) {
        return Result.success(studentMapper.delStudentById(stuId));
    }

    @Override
    public Result findByName(Student student) {
        List<Student> list = studentMapper.findByName(student);
        return Result.success0(list, (long) list.size());
    }

    @Override
    public Result updStudentById(Student student) {
        return Result.success(studentMapper.updStudentById(student));
    }
}
