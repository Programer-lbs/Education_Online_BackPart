package org.edu.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.EduTeacher;
import org.edu.mapper.TeacherMapper;
import org.edu.service.front.FrontTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FrontTeacherServiceImpl implements FrontTeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Map<String, Object> listTeacher(Integer start, Integer limit, String keyword) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
           wrapper.or().like("name",keyword).or().like("intro",keyword).or().like("career",keyword);
        }
        wrapper.orderByDesc("sort");
        Page<EduTeacher> eduTeacherPage = new Page<>(start,limit);
        teacherMapper.selectPage(eduTeacherPage, wrapper);
        if(eduTeacherPage.getRecords()==null){
            return null;
        }
        Map<String,Object> maps = new HashMap<>();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        long current = eduTeacherPage.getCurrent();
        boolean next = eduTeacherPage.hasNext();
        boolean previous = eduTeacherPage.hasPrevious();
        maps.put("records",records);
        maps.put("total",total);
        maps.put("current",current);
        maps.put("next",next);
        maps.put("previous",previous);
        return maps;
    }
}
