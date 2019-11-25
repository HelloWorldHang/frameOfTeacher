package wang.doug.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("wang.doug.frame.dao")
public class ConsoleApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsoleApplication.class, args);

    }
}
