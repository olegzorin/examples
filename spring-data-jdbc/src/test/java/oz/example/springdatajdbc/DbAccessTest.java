package oz.example.springdatajdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oz.example.springdatajdbc.database.DbAccess;
import oz.example.springdatajdbc.entity.Dept;

import java.sql.SQLOutput;
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
    void beans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Test
    void dept() {
//        System.out.println("Dallas:");
//        dbAccess.listDeptsByLocation("DALLAS").forEach(System.out::println);
//        System.out.println("put:");
        Dept dept = new Dept();
        dept.deptNo = 50;
        dept.deptName = "TEST_2";
        dept.location = "LOC";
        dbAccess.updateDept(dept);
        dbAccess.listDepts().forEach(System.out::println);
    }

    @Test
    void emp() {
        System.out.println("Dept 10");
        dbAccess.listEmps(10).forEach(System.out::println);
        System.out.println("All depts");
        dbAccess.listEmps(null).forEach(System.out::println);
        System.out.println("Salary >= 1000");
        dbAccess.listEmpsByMinSalary(1000).forEach(System.out::println);
    }
}
