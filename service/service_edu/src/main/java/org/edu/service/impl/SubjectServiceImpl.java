package org.edu.service.impl;

import org.edu.bean.EduSubject;
import org.edu.bean.EduSubjects;
import org.edu.mapper.SubjectMapper;
import org.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;


    //TODO
    @Override
    public void saveSubject(EduSubject subject) {
        subjectMapper.insert(subject);
    }

    @Override
    public List<EduSubjects> listAll() {
        List<EduSubject> eduSubjects = subjectMapper.selectList(null);

        //设置一级分类
        List<EduSubjects> oneCategory = new ArrayList<>();

        for(EduSubject subject : eduSubjects){
            if(subject.getParentId()==0){
                EduSubjects eduSubjects1 = new EduSubjects();
                eduSubjects1.setSubject(subject);
                oneCategory.add(eduSubjects1);
            }
        }
        for(EduSubjects subjects: oneCategory){
            for(EduSubject subject : eduSubjects){
                if(subject.getParentId()!=0){
                    if(subjects.getSubject().getId()==subject.getParentId()){
                        subjects.getSubjectList().add(subject);
                    }
                }
            }
        }
        return oneCategory;
    }

    @Override
    public EduSubject getOneById(Integer id) {
        EduSubject subject = subjectMapper.selectById(id);
        return subject;
    }

    @Override
    public List<EduSubject> all() {
        return subjectMapper.selectList(null);
    }

    @Override
    public EduSubject existSubject(String title, Integer pid) {
        return subjectMapper.existSubject(title,pid);
    }

}
