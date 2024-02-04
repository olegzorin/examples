package oz.example.springdatajdbc.model;

import org.springframework.data.annotation.Id;

public class Dept {
    @Id
    public int deptNo;
    public String deptName;
    public String location;
}
