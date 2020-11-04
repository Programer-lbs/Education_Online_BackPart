package org.edu.service.front;

import java.util.Map;

public interface FrontTeacherService {

    //条件查询讲师
    Map<String,Object>  listTeacher(Integer start,Integer limit,String keyword);

}
