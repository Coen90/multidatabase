package com.database.multidatabase.service;

import com.database.multidatabase.db1.domain.Dept1;
import com.database.multidatabase.db1.repository.DeptRepository1;
import com.database.multidatabase.db2.repository.DeptRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final DeptRepository1 deptRepository1;
    private final DeptRepository2 deptRepository2;

    @Override
    @Transactional
    public void tranXATest() {

        Dept1 dept50 = new Dept1(50L, "CCCC", "cccc");
        deptRepository1.save(dept50); // Transaction으로 묶여서 세이브 안됨
        deptRepository2.deleteAll();

    }

    @Override
    public void tranNonXATest() {

        Dept1 dept50 = new Dept1(50L, "CCCC", "cccc");
        deptRepository1.save(dept50); // Transaction으로 안묶여서 세이브됨
        deptRepository2.deleteAll();

    }
}
