package work.pcdd.qndxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author pcdd
 * created by 2021/1/6
 */
@SpringBootApplication
@MapperScan("work.pcdd.qndxx.mapper")
@EnableOpenApi
public class QndxxApplication {

    public static void main(String[] args) {
        SpringApplication.run(QndxxApplication.class, args);
    }

}
