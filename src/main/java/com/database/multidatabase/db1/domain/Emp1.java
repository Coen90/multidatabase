package com.database.multidatabase.db1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = "empList")
@Entity
@Table(name = "emp")
@NoArgsConstructor
public class Emp1 {

    public Emp1(Long empno, String ename, String job, Integer mgr, Timestamp hiredate, Integer sal, Integer comm, Long deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        Dept1 dept = new Dept1();
        dept.setDeptno(deptno);
        this.dept = dept;
    }

    @Id
    private Long empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Timestamp hiredate;
    private Integer sal;
    private Integer comm;

    @ManyToOne
    @JoinColumn(name="deptno", nullable=false)
    private Dept1 dept;

}
