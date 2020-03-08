package com.example.demo1.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo1.dao.workDao;
import com.example.demo1.entity.work;
import com.example.demo1.service.workService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class workServiceImpl implements workService {

    private static final Logger log = LoggerFactory.getLogger(workServiceImpl.class);

    @Autowired
    private workDao wd;

    @Override
    public StringBuffer selectAll() {
        List<work> workList = wd.selectAll(0);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (int i=0 ; i<workList.size() ; i++){
                required(stringBuffer , workList.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("workServiceImpl:selectAll()");
        }
        return stringBuffer;
    }

    /**
     * 检查提交
     *
     * */
    @Override
    public String submit(String works) {
        List<work> workList = (List<work>) JSONObject.parseObject(works);
        Pattern pattern;
        for (int i=0 ; i<workList.size() ; i++){
            if (workList.get(i).getTname().equals("email")){
                String mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
                String mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
                String mailRegex=mailName+"@"+mailDomain;
                pattern=Pattern.compile(mailRegex);
                Matcher m = pattern.matcher(workList.get(i).getValue());
                if (m.find()==false){
                    return "邮箱格式不对";
                }
            }
            if (workList.get(i).getTname().equals("name")){
                pattern = Pattern.compile("^(?!.*\\..*\\.)[\\u4e00-\\u9fa5]([\\u4e00-\\u9fa5\\.]*|[A-Za-z\\.]*)$");
                Matcher m = pattern.matcher(workList.get(i).getValue());
                if (m.find()==false){
                    return "姓名格式不对";
                }
            }
        }
        return "ok";
    }

    public void required(StringBuffer stringBuffer , work work){
        if (work.getDisplay()==1){
            noRequired(stringBuffer , work);
            return;
        }
        stringBuffer.append("<div> <span>*"+work.getName()+
                "</span> <"+work.getTagType()+" type='"+work.getType()+"' required='required' name='"+work.getName()+"' ></"+work.getTagType()+"> </div>");
    }

    public void noRequired(StringBuffer stringBuffer , work work){
        stringBuffer.append("<div> <span>*"+work.getName()+
                "</span> <"+work.getTagType()+" type='"+work.getType()+" name='"+work.getName()+"' ></"+work.getTagType()+"> </div>");
    }

    @Override
    public int addOne(work work) {
        return wd.addOne(work);
    }

    @Override
    public int delOneById(int id) {
        return wd.delOneById(id);
    }

    @Override
    public int updOne(work work) {
        return wd.updOne(work);
    }
}
