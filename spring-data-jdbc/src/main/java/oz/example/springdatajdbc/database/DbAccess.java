package oz.example.springdatajdbc.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import oz.example.springdatajdbc.entity.Dept;
import oz.example.springdatajdbc.entity.Emp;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbAccess {

    private final DeptRepository deptRepository;
    private final EmpRepository empRepository;

    public DbAccess(DeptRepository deptRepository, EmpRepository empRepository) {
        this.deptRepository = deptRepository;
        this.empRepository = empRepository;
    }

    public List<Dept> listDepts() {
        return deptRepository.findByDeptName("SALES");
    }

    public List<Dept> listDeptsByLocation(String location) {
        return deptRepository.findByLocation(location);
    }

    public List<Emp> listEmps(Integer deptNo) {
        if (deptNo != null) return empRepository.getByDeptNo(deptNo);
        ArrayList<Emp> emps = new ArrayList<>();
        empRepository.findAll().forEach(emps::add);
        return emps;
    }

    public List<Emp> listEmpsByMinSalary(int salary) {
        return empRepository.findBySalGreaterThanEqual(salary);
    }
}
