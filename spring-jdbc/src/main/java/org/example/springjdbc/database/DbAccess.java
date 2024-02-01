package org.example.springjdbc.database;

import org.example.springjdbc.mapper.EmpRowMapper;
import org.example.springjdbc.model.Emp;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DbAccess {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DbAccess(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> listDepts() {
        return jdbcTemplate.getJdbcTemplate().queryForList("select dept_no, dept_name from dept");
    }

    public Map<String, Object> getDept(int deptNo) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("deptNo", deptNo);
        return jdbcTemplate.queryForMap("select dept_no, dept_name, location from dept where dept_no = :deptNo", parameterSource);
    }

    public List<Emp> listEmps(Integer deptNo) {
        List<String> columns = List.of("emp_no", "emp_name", "job");
        StringBuilder queryBuilder = new StringBuilder("select " + String.join(",", columns) + " from emp");
        if (deptNo != null) queryBuilder.append(" where dept_no = ").append(deptNo);
        return jdbcTemplate.query(queryBuilder.toString(), new EmpRowMapper(columns));
    }

    public Emp getEmp(int empNo) {
        List<String> columns = List.of("emp_no", "emp_name", "job", "mgr", "hire_date", "sal", "comm", "dept_no");
        String query = "select " + String.join(",", columns) + " from emp where emp_no = :empNo";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("empNo", empNo);
        return jdbcTemplate.queryForObject(query, parameterSource, new EmpRowMapper(columns));
    }

}
