package com.example.demo1.controller;

import com.example.demo1.entity.work;
import com.example.demo1.service.workService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class workController {

    @Autowired
    private workService workservice;

    @RequestMapping(value = "/work",method = RequestMethod.GET)
    public StringBuffer work(){
        return workservice.selectAll();
    }

    @RequestMapping(value = "submit" , method = RequestMethod.POST)
    public String submit(String works){
        return workservice.submit(works);
    }

    @RequestMapping(value = "/addOne")
    public String addOne(work work){
        int result = workservice.addOne(work);
        if (result==1){
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping(value = "/addOne")
    public String delOneById(int id){
        int result = workservice.delOneById(id);
        if (result==1){
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/addOne")
    public String updOne(work work){
        int result = workservice.updOne(work);
        if (result==1){
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
