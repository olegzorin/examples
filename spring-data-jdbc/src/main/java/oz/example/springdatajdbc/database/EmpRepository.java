package oz.example.springdatajdbc.database;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import oz.example.springdatajdbc.entity.Emp;

import java.util.List;

@Repository
public interface EmpRepository extends CrudRepository<Emp, Integer> {

    // Do not use method name beginning with 'find...' to avoid confusing the framework
    @Query("SELECT * from emp where dept_no = :deptNo")
    List<Emp> getByDeptNo(@Param("deptNo") int deptNo);

    List<Emp> findBySalGreaterThanEqual(int sal);

}
