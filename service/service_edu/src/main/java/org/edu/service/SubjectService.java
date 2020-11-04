package org.edu.service;


import org.edu.bean.EduSubject;
import org.edu.bean.EduSubjects;
import java.util.List;

public interface SubjectService {

    //添加类别
    void saveSubject(EduSubject subject);

    //按级获取所有课程
    List<EduSubjects> listAll();

    //获取单个课程
    EduSubject getOneById(Integer id);

    //获取所有课程
    List<EduSubject> all();

    //判断课程分类信息是否存在
    EduSubject existSubject(String title,Integer pid);

}
