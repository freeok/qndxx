package work.pcdd.qndxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import work.pcdd.qndxx.common.interceptor.AdminInterceptor;
import work.pcdd.qndxx.common.interceptor.StaticResourcesInterceptor;
import work.pcdd.qndxx.common.interceptor.UserInterceptor;

/**
 * @author pcdd
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 设置html访问路径
     * addViewController设置映射路径（任意）
     * setViewName设置真实路径（必须正确，templates为根目录）
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 用户首页
        registry.addViewController("/").setViewName("user/index");
        registry.addViewController("/index").setViewName("user/index");
        // 用户上传
        registry.addViewController("/upload/moments").setViewName("user/upload");
        registry.addViewController("/upload/home").setViewName("user/upload");
        // 用户登录
        registry.addViewController("/login").setViewName("user/login");

        // 管理员登录
        registry.addViewController("/console/login").setViewName("admin/login");
        // 后台首页
        registry.addViewController("/console").setViewName("admin/index");
        // 数据一览
        registry.addViewController("/menu/home").setViewName("admin/menu/home");
        // 上交情况
        registry.addViewController("/menu/upload-details").setViewName("admin/menu/upload-details");
        // 截图下载
        registry.addViewController("/menu/download").setViewName("admin/menu/download");
        // 组织管理
        registry.addViewController("/menu/class-mgmt").setViewName("admin/menu/class-mgmt");
        // 系统管理
        registry.addViewController("/menu/settings").setViewName("admin/menu/settings");

        registry.addViewController("/common/add").setViewName("admin/common/add");
        registry.addViewController("/common/edit").setViewName("admin/common/edit");

        // 管理员基本资料
        registry.addViewController("/admin-info").setViewName("admin/admin-info");
        // 管理员修改密码
        registry.addViewController("/admin-password").setViewName("admin/admin-password");

        // 404 505页面
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");
    }

    /**
     * 注册登录拦截器
     */
    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Bean
    public StaticResourcesInterceptor staticResourcesInterceptor() {
        return new StaticResourcesInterceptor();
    }

    /**
     * 添加拦截器到springmvc拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户拦截器
        registry.addInterceptor(userInterceptor())
                // 页面
                .addPathPatterns("/upload/**");

        // 管理员拦截器
        registry.addInterceptor(adminInterceptor())
                // api
                .addPathPatterns("/admin/**")
                // 页面
                .addPathPatterns("/menu/*")
                .addPathPatterns("/common/**")
                .addPathPatterns("/admin-info")
                .addPathPatterns("/admin-password")
                // 静态资源
                .addPathPatterns("/api/**");

        // 静态资源拦截器
        registry.addInterceptor(staticResourcesInterceptor())
                // 不允许通过 url 直接访问的静态资源
                .addPathPatterns("/uploads/**");
    }

    /**
     * 配置静态访问资源
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
    }*/

    /**
     * 允许跨域
     */
   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }*/

}
