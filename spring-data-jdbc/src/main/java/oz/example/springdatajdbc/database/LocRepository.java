package oz.example.springdatajdbc.database;

import org.springframework.data.repository.CrudRepository;
import oz.example.springdatajdbc.entity.Loc;

public interface LocRepository extends CrudRepository<Loc, String> {
}
