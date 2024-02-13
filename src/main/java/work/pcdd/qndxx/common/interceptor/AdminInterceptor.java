package work.pcdd.qndxx.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import work.pcdd.qndxx.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 普通管理员拦截器
 *
 * @author pcdd
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("admin");
        boolean flag = student != null && (Objects.equals(student.getRole(), "admin") || Objects.equals(student.getRole(), "sa"));
        log.info("{} 管理员拦截器执行，{}", request.getRequestURL(), flag ? "允许访问" : "拒绝访问");
        return flag;
    }

}
