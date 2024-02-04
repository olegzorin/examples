package oz.example.springjdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import oz.example.springjdbc.model.Dept;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptRowMapper implements RowMapper<Dept> {

    public DeptRowMapper() {
    }

    @Override
    public Dept mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Dept dept = new Dept();
        dept.deptNo = rs.getInt("dept_no");
        dept.name = rs.getString("dept_name");
        dept.location = rs.getString("location");
        return dept;
    }

}
