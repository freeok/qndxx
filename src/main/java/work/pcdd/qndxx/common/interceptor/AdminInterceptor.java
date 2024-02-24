package work.pcdd.qndxx.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import work.pcdd.qndxx.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 普通管理员拦截器
 *
 * @author pcdd
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("admin");
        boolean isLogin = user != null && (Objects.equals(user.getRole(), "admin") || Objects.equals(user.getRole(), "sa"));
        log.info("{} 管理员拦截器执行，{}", request.getRequestURL(), isLogin ? "允许访问" : "未登录，拒绝访问");
        if (!isLogin) {
            response.sendRedirect("/console/login");
        }
        return isLogin;
    }

}
