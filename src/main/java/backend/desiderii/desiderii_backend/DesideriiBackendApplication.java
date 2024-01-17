package backend.desiderii.desiderii_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
@MapperScan("backend.desiderii.desiderii_backend.mapper")
public class DesideriiBackendApplication {
    private static final Logger logger = LoggerFactory.getLogger(DesideriiBackendApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(DesideriiBackendApplication.class, args);

        logger.info("backend.desiderii 启动...\n");
    }
}
