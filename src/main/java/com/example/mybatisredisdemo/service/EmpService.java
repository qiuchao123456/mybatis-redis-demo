package com.example.mybatisredisdemo.service;

import com.example.mybatisredisdemo.entity.Emp;

import java.util.List;

/**
 * @author QC
 * @DATA 2021-7-23
 */
public interface EmpService {

    void add(Emp emp);

    Emp getEmpById(Integer id);

    List<Emp> selectForPage1(int currentPage, int pageSize);


}
