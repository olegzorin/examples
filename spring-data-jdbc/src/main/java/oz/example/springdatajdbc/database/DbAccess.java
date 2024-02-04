package oz.example.springdatajdbc.database;

import org.springframework.stereotype.Service;
import oz.example.springdatajdbc.database.crud.DeptRepository;
import oz.example.springdatajdbc.model.Dept;

import java.util.List;

@Service
public class DbAccess {

//    private final DeptRepository deptRepository;

//    public DbAccess(DeptRepository deptRepository) {
//        this.deptRepository = deptRepository;
//    }

    public DbAccess() {
    }

    public List<Dept> listDepts() {
        return List.of(new Dept() {{ deptNo=1; deptName="name";}});
    }
}
