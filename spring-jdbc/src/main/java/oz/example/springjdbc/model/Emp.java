package oz.example.springjdbc.model;

import java.sql.Timestamp;

public class Emp {

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
