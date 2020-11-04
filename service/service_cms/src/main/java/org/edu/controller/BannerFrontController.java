package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.CrmBanner;
import org.edu.commonutil.Response;
import org.edu.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("前台banner模块")
@RestController
@RequestMapping("/edu_cms")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation("前台显示未删除banner")
    @GetMapping("/banner/show")
    public Response bannerShow(){
        List<CrmBanner> crmBanners = bannerService.bannerShow();
        return Response.ok().data("items",crmBanners);
    }

}
