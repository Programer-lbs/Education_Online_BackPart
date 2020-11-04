package org.edu.service.front;

import org.edu.commonutil.Response;

public interface FrontService  {

    //前台热门课程与热门讲师显示
    Response indexDisplay(Integer courseNum,Integer teacherNum);
}
