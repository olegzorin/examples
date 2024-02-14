package oz.example.springdatajdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.Set;

public class Dept {
    @Id
    public int deptNo;
    public String deptName;
//    public String location;
    public boolean deleted;

    /*
     * In contrast to other ORM frameworks, e.g., Hibernate or Spring Data JPA,
     * Spring Data JDBC can’t automatically fetch the referenced entity object.
     * To get the referenced Loc, you need to use the LocRepository to fetch it
     * from the database.
     */
    @Column("location")
    public AggregateReference<Loc,String> loc;
    public Set<Emp> emps;

    @Override
    public String toString() {
        return "Dept{" +
            "deptNo=" + deptNo +
            ", deptName=" + deptName +
//            ", location=" + location +
            ", loc=" + loc +
            ", emps=" + emps +
            '}';
    }
}
