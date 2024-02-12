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
     * setViewName设置真实路径（必须正确）
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 用户首页
        registry.addViewController("/").setViewName("user/index");
        registry.addViewController("/index").setViewName("user/index");
        // 用户上传界面
        registry.addViewController("/upload/moments").setViewName("user/upload");
        registry.addViewController("/upload/home").setViewName("user/upload");
        // 用户登录界面
        registry.addViewController("/login").setViewName("user/login");

        // 管理员登录页面
        registry.addViewController("/console/login-1").setViewName("admin/login-1");
        registry.addViewController("/console/login-2").setViewName("admin/login-2");
        registry.addViewController("/console/login-3").setViewName("admin/login-3");
        // 后台首页
        registry.addViewController("/console").setViewName("public-admin/manager");

        // 普通管理员首页
        registry.addViewController("/console/admin/welcome").setViewName("admin/welcome-1");
        // 截图上交情况页面
        registry.addViewController("/console/admin/table-upload").setViewName("admin/table-upload");
        // 截图下载页面
        registry.addViewController("/console/admin/download-image").setViewName("admin/download-image");
        // 班级管理页面
        registry.addViewController("/console/admin/table-class").setViewName("admin/table-class");
        registry.addViewController("/console/admin/table/add").setViewName("admin/table/add");
        registry.addViewController("/console/admin/table/edit").setViewName("admin/table/edit");
        // 系统管理页面
        registry.addViewController("/console/admin/setting-system").setViewName("admin/setting-system");

        // 管理员基本资料页面
        registry.addViewController("/console/admin-info").setViewName("public-admin/admin-info");
        // 管理员修改密码页面
        registry.addViewController("/console/admin-password").setViewName("public-admin/admin-password");

        // 404 505页面
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");

        // 菜单管理
        /*registry.addViewController("/menu").setViewName("page/menu");
        // 网站设置
        registry.addViewController("/setting").setViewName("page/setting");
        // 普通表单
        registry.addViewController("/form").setViewName("page/form");
        // 分布表单
        registry.addViewController("/form-step").setViewName("page/form-step");*/
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
                // 拦截所有
                .addPathPatterns("/**")
                // 排除静态资源
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/lib/**")
                .excludePathPatterns("/favicon.ico")
                // 排除页面
                .excludePathPatterns("/")
                .excludePathPatterns("/index")
                .excludePathPatterns("/login")
                // 防止用户注销时，刷新管理员页面被拦截
                .excludePathPatterns("/console/**")
                // 排除api
                .excludePathPatterns("/student/login/**")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/clazz/**")
                .excludePathPatterns("/captcha/**");

        // 普通管理员拦截器
        registry.addInterceptor(adminInterceptor())
                // api
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**")
                .excludePathPatterns("/admin/logout")

                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/login/*")
                .excludePathPatterns("/student/isLogin")
                .excludePathPatterns("/student/logout")

                .addPathPatterns("/clazz/**")
                // 用户进入上传界面时会调用，故排除
                .excludePathPatterns("/clazz/*")

                .addPathPatterns("image/**")

                // 页面
                .addPathPatterns("/console/admin/**")
                .addPathPatterns("/console/*")
                .excludePathPatterns("/console/login-1");


        // 静态资源拦截器
        registry.addInterceptor(staticResourcesInterceptor())
                .addPathPatterns("/uploads/**");
    }

    /**
     * 配置静态访问资源
     *
     * @param registry
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
