package oz.example.springdatajdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Date;

public class Emp {

    @Id
    public int empNo;
    @Column("emp_name")
    public String name;
    public String job;
    public Integer mgr;
    public Date hireDate;
    public Integer sal;
    public Integer comm;
    public Integer deptNo;


    @Override
    public String toString() {
        return "Emp{" +
            "empNo=" + empNo +
            ", name=" + name +
            ", job=" + job +
            ", mgr=" + mgr +
            ", hireDate=" + hireDate +
            ", sal=" + sal +
            ", comm=" + comm +
            ", deptNo=" + deptNo +
            '}';
    }
}
