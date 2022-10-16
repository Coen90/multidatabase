package com.database.multidatabase.db1.repository;

import com.database.multidatabase.db1.domain.Dept1;
import com.database.multidatabase.db1.domain.Emp1;
import com.database.multidatabase.db2.domain.Dept2;
import com.database.multidatabase.db2.domain.Emp2;
import com.database.multidatabase.db2.repository.DeptRepository2;
import com.database.multidatabase.db2.repository.EmpRepository2;
import com.database.multidatabase.service.DeptService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Commit
@SpringBootTest
class TestDb {

    @Autowired
    EmpRepository1 empRepository1;
    @Autowired
    DeptRepository1 deptRepository1;
    @Autowired
    EmpRepository2 empRepository2;
    @Autowired
    DeptRepository2 deptRepository2;
    @Autowired
    DeptService deptService;

    @BeforeEach
    @Transactional
    public void pre() {
        empRepository1.deleteAll();
        empRepository2.deleteAll();
        deptRepository1.deleteAll();
        deptRepository2.deleteAll();

        insert_test();
        insert_db2();
    }

    @Test
    void trTest() {
        try {
            deptService.tranXATest();
        } catch (Exception e) {

        }
        Assertions.assertThat(deptRepository1.findById(50L)).isNotPresent();
    }

    @Test
    void trNonTest() {
        try {
            deptService.tranNonXATest();
        } catch (Exception e) {

        }
        Assertions.assertThat(deptRepository1.findById(50L)).isPresent();
    }

    @Test
    void insert_test() {
        List<Dept1> list = new ArrayList<>();
        Dept1 dept10 = new Dept1(10L, "ACCOUNTING", "NEW YORK");
        list.add(dept10);
        Dept1 dept15 = new Dept1(15L, "TEST", "TEST");
        list.add(dept15);
        Dept1 dept20 = new Dept1(20L, "RESEARCH", "DALLAS");
        list.add(dept20);
        Dept1 dept25 = new Dept1(25L, "SALES", "CHICAGO");
        list.add(dept25);
        Dept1 dept30 = new Dept1(30L, "OPERATIONS", "BOSTON");
        list.add(dept30);
        deptRepository1.saveAll(list);
        List<Emp1> empList = new ArrayList<Emp1>();
        Emp1 emp;
        emp = new Emp1(7839L, "KING"  , "PRESIDENT", null, getTimestamp("1981-11-17 00:00:00.000"), 5000, null, 10L); empList.add(emp);
        emp = new Emp1(7698L, "BLAKE" , "MANAGER"  , 7839, getTimestamp("1981-01-05 00:00:00.000"), 2850, null, 30L); empList.add(emp);
        emp = new Emp1(7782L, "CLARK" , "MANAGER"  , 7839, getTimestamp("1981-09-06 00:00:00.000"), 2450, null, 10L); empList.add(emp);
        emp = new Emp1(7566L, "JONES" , "MANAGER"  , 7839, getTimestamp("1981-02-04 00:00:00.000"), 2975, null, 20L); empList.add(emp);
        emp = new Emp1(7788L, "SCOTT" , "ANALYST"  , 7566, getTimestamp("1987-07-13 00:00:00.000"), 3000, null, 20L); empList.add(emp);
        emp = new Emp1(7902L, "FORD"  , "ANALYST"  , 7566, getTimestamp("1981-03-12 00:00:00.000"), 3000, null, 20L); empList.add(emp);
        emp = new Emp1(7369L, "SMITH" , "CLERK"    , 7566, getTimestamp("1980-12-17 00:00:00.000"),  800, null, 20L); empList.add(emp);
        emp = new Emp1(7499L, "ALLEN" , "SALESMAN" , 7698, getTimestamp("1981-02-20 00:00:00.000"), 1600,  300, 30L); empList.add(emp);
        emp = new Emp1(7521L, "WARD"  , "SALESMAN" , 7698, getTimestamp("1981-02-22 00:00:00.000"), 1250,  500, 30L); empList.add(emp);
        emp = new Emp1(7654L, "MARTIN", "SALESMAN" , 7698, getTimestamp("1981-09-28 00:00:00.000"), 1250, 1400, 30L); empList.add(emp);
        emp = new Emp1(7844L, "TURNER", "SALESMAN" , 7698, getTimestamp("1981-09-08 00:00:00.000"), 1500,    0, 30L); empList.add(emp);
        emp = new Emp1(7876L, "ADAMS" , "CLERK"    , 7698, getTimestamp("1971-07-13 00:00:00.000"), 1100, null, 20L); empList.add(emp);
        emp = new Emp1(7900L, "JAMES" , "CLERK"    , 7698, getTimestamp("1981-03-12 00:00:00.000"),  950, null, 30L); empList.add(emp);
        emp = new Emp1(7934L, "MILLER", "CLERK"    , 7782, getTimestamp("1982-01-23 00:00:00.000"), 1300, null, 10L); empList.add(emp);
        empRepository1.saveAll(empList);

    }


    @Test
    void insert_db2() {


        List<Dept2> list2 = new ArrayList<>();
        Dept2 dept210 = new Dept2(10L, "ACCOUNTING", "NEW YORK");list2.add(dept210);
        Dept2 dept215 = new Dept2(15L, "TEST", "TEST");list2.add(dept215);
        Dept2 dept220 = new Dept2(20L, "RESEARCH", "DALLAS");list2.add(dept220);
        Dept2 dept225 = new Dept2(25L, "SALES", "CHICAGO");list2.add(dept225);
        Dept2 dept230 = new Dept2(30L, "OPERATIONS", "BOSTON");list2.add(dept230);
        deptRepository2.saveAll(list2);

        List<Emp2> empList = new ArrayList<Emp2>();
        Emp2 emp;
        emp = new Emp2(7839L, "KING"  , "PRESIDENT", null, getTimestamp("1981-11-17 00:00:00.000"), 5000, null, 10L); empList.add(emp);
        emp = new Emp2(7698L, "BLAKE" , "MANAGER"  , 7839, getTimestamp("1981-01-05 00:00:00.000"), 2850, null, 30L); empList.add(emp);
        emp = new Emp2(7782L, "CLARK" , "MANAGER"  , 7839, getTimestamp("1981-09-06 00:00:00.000"), 2450, null, 10L); empList.add(emp);
        emp = new Emp2(7566L, "JONES" , "MANAGER"  , 7839, getTimestamp("1981-02-04 00:00:00.000"), 2975, null, 20L); empList.add(emp);
        emp = new Emp2(7788L, "SCOTT" , "ANALYST"  , 7566, getTimestamp("1987-07-13 00:00:00.000"), 3000, null, 20L); empList.add(emp);
        emp = new Emp2(7902L, "FORD"  , "ANALYST"  , 7566, getTimestamp("1981-03-12 00:00:00.000"), 3000, null, 20L); empList.add(emp);
        emp = new Emp2(7369L, "SMITH" , "CLERK"    , 7566, getTimestamp("1980-12-17 00:00:00.000"),  800, null, 20L); empList.add(emp);
        emp = new Emp2(7499L, "ALLEN" , "SALESMAN" , 7698, getTimestamp("1981-02-20 00:00:00.000"), 1600,  300, 30L); empList.add(emp);
        emp = new Emp2(7521L, "WARD"  , "SALESMAN" , 7698, getTimestamp("1981-02-22 00:00:00.000"), 1250,  500, 30L); empList.add(emp);
        emp = new Emp2(7654L, "MARTIN", "SALESMAN" , 7698, getTimestamp("1981-09-28 00:00:00.000"), 1250, 1400, 30L); empList.add(emp);
        emp = new Emp2(7844L, "TURNER", "SALESMAN" , 7698, getTimestamp("1981-09-08 00:00:00.000"), 1500,    0, 30L); empList.add(emp);
        emp = new Emp2(7876L, "ADAMS" , "CLERK"    , 7698, getTimestamp("1971-07-13 00:00:00.000"), 1100, null, 20L); empList.add(emp);
        emp = new Emp2(7900L, "JAMES" , "CLERK"    , 7698, getTimestamp("1981-03-12 00:00:00.000"),  950, null, 30L); empList.add(emp);
        emp = new Emp2(7934L, "MILLER", "CLERK"    , 7782, getTimestamp("1982-01-23 00:00:00.000"), 1300, null, 10L); empList.add(emp);
        empRepository2.saveAll(empList);

    }

    public Timestamp getTimestamp(String timeStamp){
        LocalDateTime localDateTime = LocalDateTime.parse(timeStamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return Timestamp.valueOf(localDateTime);
    }


}