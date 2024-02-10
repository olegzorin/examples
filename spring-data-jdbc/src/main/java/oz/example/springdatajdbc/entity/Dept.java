package oz.example.springdatajdbc.entity;

import org.springframework.data.annotation.Id;

public class Dept {
    @Id
    public int deptNo;
    public String deptName;
    public String location;

    @Override
    public String toString() {
        return "Dept{" +
            "deptNo=" + deptNo +
            ", deptName='" + deptName + '\'' +
            ", location='" + location + '\'' +
            '}';
    }
}
