package oz.example.springdatajdbc.restful;


import oz.example.springdatajdbc.database.DbAccess;
import org.springframework.web.bind.annotation.*;

@RestController
public class RsController {

    private final DbAccess dbAccess;

    public RsController(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @GetMapping(value = "/", produces = "application/json")
    public Result hello() {
        return Result.ok("Greetings from Spring Boot!");
    }

    @GetMapping(value = "/depts", produces = "application/json")
    public Result depts() {
        return Result.of(dbAccess::listDepts);
    }
/*
    @GetMapping(value = "/depts/{deptNo}", produces = "application/json")
    public Result dept(
        @PathVariable("deptNo") int deptNo
    ) {
        return Result.of(() -> dbAccess.getDept(deptNo));
    }

    @GetMapping(value = "/emps", produces = "application/json")
    public Result emps(
        @RequestParam(value = "deptNo", required = false) Integer deptNo
    ) {
        return Result.of(() -> dbAccess.listEmps(deptNo));
    }

    @GetMapping(value = "/emps/{empNo}", produces = "application/json")
    public Result getEmp(
        @PathVariable("empNo") int empNo
    ) {
        return Result.of(() -> dbAccess.getEmp(empNo));
    }

    @PostMapping(value = "/emps", consumes = "application/json", produces = "application/json")
    public Result addEmp(
        @RequestBody List<Emp> emps
    ) {
        return Result.of(() -> dbAccess.addEmps(emps));
    }

    @DeleteMapping(value = "/emps", produces = "application/json")
    public Result deleteEmp(
        @RequestParam("empNo") int[] empNos
    ) {
        return Result.of(() -> dbAccess.deleteEmps(empNos));
    }
*/
}
