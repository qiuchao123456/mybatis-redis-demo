package com.example.mybatisredisdemo.service.impl;

import com.example.mybatisredisdemo.entity.Emp;
import com.example.mybatisredisdemo.mapper.EmpMapper;
import com.example.mybatisredisdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author QC
 * @date 2021-7-23
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    public RedisTemplate redisTemplate;

    @Resource
    private EmpMapper empMapper;

    @Override
    public void add(Emp emp){
        empMapper.insert(emp);
    }

    @Override
    public Emp getEmpById(Integer id) {
        //先从缓存获取数据，如果有则直接返回
//                       如果无，则查询mysql，并将数据设置到缓存
        Emp emp;
        String key = "user:" + id;
        Object userObj = redisTemplate.opsForValue().get(key);
        if (userObj == null){
            System.out.println("————》查询数据库---");
            //查数据库
            emp = empMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(key,emp,1, TimeUnit.SECONDS);
        }else {
            emp = (Emp) userObj;
            System.out.println("————》查询缓存~~~");
        }
        return emp;
    }

    @Override
    public List<Emp> selectForPage1(int currentPage, int pageSize) {
        String key = "user: " + currentPage;
        String key2 = "user:" + pageSize;
        Object userObj = redisTemplate.opsForValue().get(key);
        Object userObj2 = redisTemplate.opsForValue().get(key2);
        if (userObj == null&&userObj2==null){
            System.out.println("————》查询数据库---");
            //查数据库
            List<Emp> emp = empMapper.selectForPage1(1,3);
            redisTemplate.opsForValue().set(key,emp);
        }else {
            System.out.println("————》查询缓存~~~");
        }
        return empMapper.selectForPage1((currentPage-1)*pageSize,pageSize);
    }

}
