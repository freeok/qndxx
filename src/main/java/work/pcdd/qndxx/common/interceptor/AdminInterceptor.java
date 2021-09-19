package work.pcdd.qndxx.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import work.pcdd.qndxx.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 普通管理员拦截器
 *
 * @author AD
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("admin");
        boolean flag = student != null && (Objects.equals(student.getRole(), "admin") || Objects.equals(student.getRole(), "sa"));
        System.out.println("普通管理员拦截器执行，是否放行？" + flag);
        /*if (!flag) {
            response.sendRedirect("/");
        }*/
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
