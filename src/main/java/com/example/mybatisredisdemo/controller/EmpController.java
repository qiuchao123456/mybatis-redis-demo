package com.example.mybatisredisdemo.controller;

import com.example.mybatisredisdemo.entity.Emp;
import com.example.mybatisredisdemo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author QC
 * @DATA 2021-7-23
 */
@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/emp")
    public String addEmp(Emp emp){
        empService.add(emp);
        return "add ok";
    }

    @GetMapping("/emp/{id}")
    public Object getEmpById(@PathVariable("id") Integer id){
        return empService.getEmpById(id);
    }

    @RequestMapping("/emp/List/{value}")
    public List<Emp> select(@RequestParam("currentPage") Integer currentPage,
                            @RequestParam("pageSize") Integer pageSize){
        List<Emp> selectForPage = empService.selectForPage1((currentPage-1)*pageSize, pageSize) ;

        return selectForPage;
    }
}
