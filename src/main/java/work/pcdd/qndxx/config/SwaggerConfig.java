package work.pcdd.qndxx.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pcdd
 * created by 2021/9/19 16:55
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户 API")
                .pathsToMatch("/student/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("管理员 API")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title("qndxx")
                .description("青年大学习截图上交系统")
                .version("2.0.0");
        return new OpenAPI().info(info);
    }

}
