package org.edu.controller.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.commonutil.Response;
import org.edu.service.front.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("前台显示")
@RestController
@RequestMapping("/service_edu")
public class IndexController {

    @Autowired
    private FrontService frontService;


    @ApiOperation("首页")
    @GetMapping("/index")
    public Response getIndexData(Integer courseNum,Integer teacherNum){
        Response response = frontService.indexDisplay(courseNum, teacherNum);
        return response;
    }
}
