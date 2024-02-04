package oz.example.springdatajdbc.model;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class Emp {

    @Id
    public int empNo;
    public String name;
    public String job;
    public Integer mgr;
    public Timestamp hireDate;
    public Integer sal;
    public Integer comm;
    public Integer deptNo;


    @Override
    public String toString() {
        return "Emp{" +
            "empNo=" + empNo +
            ", name='" + name + '\'' +
            ", job='" + job + '\'' +
            ", mgr=" + mgr +
            ", hireDate=" + hireDate +
            ", sal=" + sal +
            ", comm=" + comm +
            ", deptNo=" + deptNo +
            '}';
    }
}
