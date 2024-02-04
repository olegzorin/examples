package oz.example.springjdbc.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import oz.example.springjdbc.mapper.DeptRowMapper;
import oz.example.springjdbc.mapper.EmpRowMapper;
import oz.example.springjdbc.model.Dept;
import oz.example.springjdbc.model.Emp;

import java.util.List;

@Repository
public class DbAccess {

    private final JdbcTemplate jdbcTemplate;

    public DbAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Dept> listDepts() {
        return jdbcTemplate.query("select dept_no, dept_name, location from dept", new DeptRowMapper());
    }

    public Dept getDept(int deptNo) {
        String query = "select dept_no, dept_name, location from dept where dept_no = :deptNo";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("deptNo", deptNo);
        return new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(query, parameterSource, new DeptRowMapper());
    }

    public List<Emp> listEmps(Integer deptNo) {
        List<String> columns = List.of("emp_no", "emp_name", "job", "dept_no");
        StringBuilder queryBuilder = new StringBuilder("select " + String.join(",", columns) + " from emp");
        if (deptNo != null) queryBuilder.append(" where dept_no = ").append(deptNo);
        return jdbcTemplate.query(queryBuilder.toString(), new EmpRowMapper(columns));
    }

    public Emp getEmp(int empNo) {
        List<String> columns = List.of("emp_no", "emp_name", "job", "mgr", "hire_date", "sal", "comm", "dept_no");
        String query = "select " + String.join(",", columns) + " from emp where emp_no = :empNo";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("empNo", empNo);
        return new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(query, parameterSource, new EmpRowMapper(columns));
    }

    @Transactional
    public void addEmps(List<Emp> emps) {
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "insert into emp(emp_no,emp_name,job,mgr,hire_date,sal,comm,dept_no) "+
            "values (:empNo,:name,:job,:mgr,:hireDate,:sal,:comm,:deptNo)";
        emps.forEach(emp -> npjt.update(sql, new SimplePropertySqlParameterSource(emp)));
    }

    @Transactional
    public void deleteEmps(int[] empNos) {
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "delete from emp where emp_no = :empNo";
        for (int empNo : empNos) {
            npjt.update(sql, new MapSqlParameterSource().addValue("empNo", empNo));
        }
    }

}
