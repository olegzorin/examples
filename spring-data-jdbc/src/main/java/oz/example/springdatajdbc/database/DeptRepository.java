package oz.example.springdatajdbc.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import oz.example.springdatajdbc.entity.Dept;

import java.util.List;

@Repository
public interface DeptRepository extends CrudRepository<Dept, Integer> {

    List<Dept> findByDeptName(String deptName);

    List<Dept> findByLocation(String location);
}
