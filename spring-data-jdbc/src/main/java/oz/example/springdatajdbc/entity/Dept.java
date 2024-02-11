package oz.example.springdatajdbc.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Dept {
    @Id
    public int deptNo;
    public String deptName;
    public String location;
//    public List<Emp> emps;

    @Override
    public String toString() {
        return "Dept{" +
            "deptNo=" + deptNo +
            ", deptName='" + deptName + '\'' +
            ", location='" + location + '\'' +
            '}';
    }
}
