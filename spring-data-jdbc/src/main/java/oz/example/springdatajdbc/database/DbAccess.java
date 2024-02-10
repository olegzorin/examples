package oz.example.springdatajdbc.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import oz.example.springdatajdbc.entity.Dept;

import java.util.List;

//@Service
@Repository
public class DbAccess {

    @Autowired
    private DeptRepository deptRepository;

//    public DbAccess(DeptRepository deptRepository) {
//        this.deptRepository = deptRepository;
//    }

    public List<Dept> listDepts() {
        return deptRepository.findByDeptName("SALES");
    }

    public List<Dept> listDeptsByLocation(String location) {
        return deptRepository.findByLocation(location);
    }
}
