package oz.example.springjdbc.mapper;

import oz.example.springjdbc.model.Emp;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpRowMapper implements RowMapper<Emp> {

    private final List<String> columns;

    public EmpRowMapper(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public Emp mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Emp emp = new Emp();
        if (columns.contains("emp_no")) emp.empNo = rs.getInt("emp_no");
        if (columns.contains("emp_name")) emp.name = rs.getString("emp_name");
        if (columns.contains("job")) emp.job = rs.getString("job");
        if (columns.contains("mgr")) emp.mgr = rs.getInt("mgr");
        if (columns.contains("sal")) emp.sal = rs.getInt("sal");
        if (columns.contains("comm")) emp.comm = rs.getInt("comm");
        if (columns.contains("dept_no")) emp.deptNo = rs.getInt("dept_no");
        if (columns.contains("hire_date")) emp.hireDate = rs.getTimestamp("hire_date");
        return emp;
    }

}
