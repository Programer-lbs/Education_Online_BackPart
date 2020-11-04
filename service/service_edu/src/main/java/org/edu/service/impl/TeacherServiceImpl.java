package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.EduTeacher;
import org.edu.bean.QueryTeacherVo;
import org.edu.commonutil.OssTemplate;
import org.edu.commonutil.Response;
import org.edu.mapper.TeacherMapper;
import org.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private OssTemplate ossTemplate;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<EduTeacher> listTeachers() {
        return teacherMapper.selectList(null);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherMapper.deleteById(id);
    }

    @Override
    public Page<EduTeacher> listTeacherCondition(QueryTeacherVo queryTeacherVo,Integer current,Integer limit ) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(queryTeacherVo.getName())){
            queryWrapper.like("name",queryTeacherVo.getName());
        }
        if(!StringUtils.isEmpty(queryTeacherVo.getIntro())){
            queryWrapper.like("intro",queryTeacherVo.getIntro());
        }
        if(!StringUtils.isEmpty(queryTeacherVo.getLevel())){
            queryWrapper.eq("level",queryTeacherVo.getLevel());
        }
        Page<EduTeacher> page = new Page<>(current,limit);
         teacherMapper.selectPage(page, queryWrapper);
       log.info("result",page.getRecords());
        return  page;
    }

    @Override
    public EduTeacher getOneById(Long id) {
        EduTeacher teacher = teacherMapper.selectById(id);
        return teacher;
    }

    @Override
    public void updateTeacher(EduTeacher eduTeacher) {
        teacherMapper.updateById(eduTeacher);
    }

    @Override
    public void saveTeacher(EduTeacher eduTeacher) {
        teacherMapper.insert(eduTeacher);
    }

    @Override
    public String uploadAvatar(MultipartFile file) throws IOException {
      String fileName = UUID.randomUUID().toString().
              replaceAll("-","")+"_"+file.getOriginalFilename();
      InputStream inputStream = file.getInputStream();
      String filePath = ossTemplate.upload(fileName,inputStream);
      return filePath;
    }
}
