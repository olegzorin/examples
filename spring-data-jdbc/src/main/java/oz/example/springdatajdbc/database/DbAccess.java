package oz.example.springdatajdbc.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;
import oz.example.springdatajdbc.entity.Dept;
import oz.example.springdatajdbc.entity.Emp;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbAccess {

    private final DeptRepository deptRepository;
    private final EmpRepository empRepository;
    private final JdbcAggregateTemplate template;
    public DbAccess(DeptRepository deptRepository, EmpRepository empRepository, JdbcAggregateTemplate template) {
        this.deptRepository = deptRepository;
        this.empRepository = empRepository;
        this.template = template;
    }

    public List<Dept> listDepts() {
        ArrayList<Dept> depts = new ArrayList<>();
        deptRepository.findAll().forEach(depts::add);
        return depts;
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

    public void insertDept(Dept dept) {
        template.insert(dept);
    }

    public void updateDept(Dept dept) {
        deptRepository.save(dept);
    }

    public void putEmp(Emp emp) {
        empRepository.save(emp);
    }
}
