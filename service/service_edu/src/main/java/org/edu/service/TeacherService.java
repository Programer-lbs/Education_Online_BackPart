package org.edu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.EduTeacher;
import org.edu.bean.QueryTeacherVo;
import org.edu.commonutil.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeacherService {

    //查询所有教师
    List<EduTeacher> listTeachers();

    //删除教师
    void deleteTeacher(Long id);

    //按条件分页查询
    Page<EduTeacher> listTeacherCondition(QueryTeacherVo queryTeacherVo,Integer current,Integer limit);

    //查询单个教师
    EduTeacher getOneById(Long id);

    //修改教师信息
    void updateTeacher(EduTeacher eduTeacher);

    //新增教师信息
    void saveTeacher(EduTeacher eduTeacher);

    //头像上传
    String uploadAvatar(MultipartFile file) throws IOException;
}
