package com.example.demo1.dao;

import com.example.demo1.entity.work;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface workDao {

    //work selectAll();

    /**
     * 查询所有字段
     * @return
     */
    List<work> selectAll(Integer type);

    int addOne(work work);

    int delOneById(int id);

    int updOne(work work);

}
