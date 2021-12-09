package com.example.mybatisredisdemo.mapper;

import com.example.mybatisredisdemo.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author QC
 * @DATA  2021-7-23
 */
@Mapper
public interface EmpMapper{
    Emp selectByPrimaryKey(Integer id);
    int insert(Emp emp);
    List<Emp> selectForPage1(int currentPage, int pageSize);
}
