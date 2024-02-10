package oz.example.springdatajdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oz.example.springdatajdbc.database.DbAccess;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class DbAccessTest {

    private final DbAccess dbAccess;

    @Autowired
    ApplicationContext applicationContext;

//    @Autowired
//JdbcMappingContext mappingContext;

    @Autowired
    public DbAccessTest(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @Test
    void test() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        System.out.println("OK: " +dbAccess.listDepts());
        System.out.println("OK: " +dbAccess.listDeptsByLocation("DALLAS"));
    }
}
