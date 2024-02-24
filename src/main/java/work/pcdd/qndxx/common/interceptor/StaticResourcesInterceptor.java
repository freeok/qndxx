package work.pcdd.qndxx.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 静态资源拦截器，拦截指定的静态资源
 *
 * @author pcdd
 */
@Slf4j
public class StaticResourcesInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("{} 静态资源拦截器执行", request.getRequestURI());
        response.sendRedirect("/");
        return false;
    }

}
