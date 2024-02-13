package work.pcdd.qndxx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.AdminMapper;

import java.util.List;

@Slf4j
@SpringBootTest
class QndxxApplicationTests {

    @Autowired
    AdminMapper adminMapper;

    @Test
    void test01() {

    }

    @Test
    void test02() {
        // 分页助手，设置起始页和每页显示的条数
        PageHelper.startPage(3, 3);
        List<Student> list = adminMapper.findAllByClazzName("史莱克学院");
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        log.info(Result.success0(list, pageInfo.getTotal()).toString());
    }

}
