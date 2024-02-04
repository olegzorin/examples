const endpoint = "http://localhost:8086";

const get = async (path) => fetch(`${endpoint}/${path}`)

const add = async (emps) => fetch(`${endpoint}/emps`, {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify(emps)
});

const del = async (empNos) =>
    fetch(`${endpoint}/emps?empNo=${empNos.join("&empNo=")}`, {method: "DELETE"});

const resp = await
    get("depts");
    // get("emps");
    // get("emps?deptNo=30");
    // add([
    //     {empNo: 10001, name: "Test1", deptNo: 10, job: "user", sal: 101, hireDate: "2024-02-01"},
    //     {empNo: 10002, name: "Test2", deptNo: 10, job: "user", sal: 102, hireDate: "2024-02-02"},
    //     {empNo: 10003, name: "Test3", deptNo: 10, job: "user", sal: 103, hireDate: "2024-02-03"},
    //     {empNo: 10004, name: "Test4", deptNo: 10, job: "user", sal: 104, hireDate: "2024-02-04"}
    // ]);
    // del([10001, 10002, 10003, 10004])
l
console.log(await resp.json());


