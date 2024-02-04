package oz.example.springjdbc.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oz.example.springjdbc.AppConfig;
import oz.example.springjdbc.model.Dept;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class DbAccessTest {

    private final DbAccess dbAccess;

    @Autowired
    public DbAccessTest(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @Test
    void depts() {
        List<Dept> depts = dbAccess.listDepts();
        assertNotNull(depts);
        assertFalse(depts.isEmpty());
        depts.forEach(System.out::println);

        Dept testDept = dbAccess.getDept(depts.getFirst().deptNo);
        assertNotNull(testDept);
        assertEquals(depts.getFirst(), testDept);
    }
}
