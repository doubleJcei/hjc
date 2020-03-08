package com.example.demo1.service;

import com.example.demo1.entity.work;

import java.util.List;

public interface workService {

    StringBuffer selectAll();

    String submit(String works);

    int addOne(work work);

    int delOneById(int id);

    int updOne(work work);
}
