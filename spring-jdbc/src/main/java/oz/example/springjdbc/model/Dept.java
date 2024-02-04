package oz.example.springjdbc.model;

import java.util.Objects;

public class Dept {
    public int deptNo;
    public String name;
    public String location;

    @Override
    public String toString() {
        return "{deptNo=" + deptNo + ", name=" + name + ", location=" + location + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Dept d) {
            return (deptNo == d.deptNo) && Objects.equals(name, d.name) && Objects.equals(location, d.location);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = deptNo;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
