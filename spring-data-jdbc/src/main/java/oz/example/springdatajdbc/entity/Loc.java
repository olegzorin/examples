package oz.example.springdatajdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("locations")
public class Loc {
    @Id
    public String name;
    public String country;
    public boolean deleted;

    @Override
    public String toString() {
        return "Loc{" +
            "name=" + name +
            ", country=" + country +
            ", deleted=" + deleted +
            '}';
    }
}
