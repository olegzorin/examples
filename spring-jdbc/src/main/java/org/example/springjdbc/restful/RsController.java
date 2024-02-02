package org.example.springjdbc.restful;


import org.example.springjdbc.database.DbAccess;
import org.example.springjdbc.model.Emp;
import org.springframework.web.bind.annotation.*;

@RestController
public class RsController {

    private final DbAccess dbAccess;

    public RsController(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @GetMapping("/")
    public Result hello() {
        return Result.ok("Greetings from Spring Boot!");
    }

    @GetMapping("/depts")
    public Result depts() {
        return Result.ok(dbAccess.listDepts());
    }

    @GetMapping("/depts/{deptNo}")
    public Result dept(
        @PathVariable("deptNo") int deptNo
    ) {
        return Result.ok(dbAccess.getDept(deptNo));
    }

    @GetMapping(value = "/emps", produces = "application/json")
    public Result emps(
        @RequestParam(value = "deptNo", required = false) Integer deptNo
    ) {
        return Result.ok(dbAccess.listEmps(deptNo));
    }

    @GetMapping(value = "/emps/{empNo}", produces = "application/json")
    public Result getEmp(
        @PathVariable("empNo") int empNo
    ) {
        return Result.ok(dbAccess.getEmp(empNo));
    }

    @PostMapping(value = "/emps/{empNo}", consumes = "application/json")
    public Result addEmp(
        @PathVariable("empNo") int empNo,
        @RequestBody Emp emp
    ) {
        emp.empNo = empNo;
        try {
            dbAccess.addEmp(emp);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
