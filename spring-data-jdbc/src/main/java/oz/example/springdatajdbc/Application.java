package oz.example.springdatajdbc;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class Application implements ApplicationContextAware {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
