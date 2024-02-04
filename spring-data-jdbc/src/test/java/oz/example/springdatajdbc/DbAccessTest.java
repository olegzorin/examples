package oz.example.springdatajdbc;

//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import oz.example.springdatajdbc.database.DbAccess;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfiguration.class)
public class DbAccessTest {

    private final DbAccess dbAccess;

    @Autowired
    public DbAccessTest(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

//    @Test
    void test() {
        System.out.println("OK: " +dbAccess);
    }
}
