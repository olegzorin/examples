package org.example.springjdbc.restful;


import org.example.springjdbc.database.DbAccess;
import org.example.springjdbc.model.Emp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RsController {

    private final DbAccess dbAccess;

    public RsController(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @GetMapping("/")
    public String hello() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/depts")
    public String depts() {
        try {
            List<Map<String, Object>> res = dbAccess.listDepts();
            return "Depts: " + res;
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    @GetMapping("/depts/{deptNo}")
    public Map<String, Object> dept(
        @PathVariable("deptNo") int deptNo
    ) {
        return dbAccess.getDept(deptNo);
    }

    @GetMapping(value = "/emps", produces = "application/json")
    public List<Emp> emps(
        @RequestParam(value = "deptNo", required = false) Integer deptNo
    ) {
        return dbAccess.listEmps(deptNo);
    }

    @GetMapping(value = "/emps/{empNo}", produces = "application/json")
    public Emp emp(
        @PathVariable("empNo") int empNo
    ) {
        return dbAccess.getEmp(empNo);
    }

}
