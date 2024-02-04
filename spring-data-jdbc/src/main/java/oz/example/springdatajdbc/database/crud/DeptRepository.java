package oz.example.springdatajdbc.database.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import oz.example.springdatajdbc.model.Dept;

import java.util.List;

@Repository
public interface DeptRepository extends CrudRepository<Dept, Integer> {

    List<Dept> findByDeptName(String deptName);
}
