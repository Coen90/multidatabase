package com.database.multidatabase.db2.domain;

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
public class Dept2 {

    public Dept2(Long deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    @Id
    private Long deptno;
    private String dname;
    private String loc;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Emp2> empList;
}
