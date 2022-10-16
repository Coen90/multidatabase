package com.database.multidatabase.db1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "empList")
@Entity
@Table(name = "dept")
@NoArgsConstructor
public class Dept1 {

    public Dept1(Long deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    @Id
    private Long deptno;
    private String dname;
    private String loc;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Emp1> empList;
}
