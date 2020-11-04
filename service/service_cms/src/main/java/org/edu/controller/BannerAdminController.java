package org.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.CrmBanner;
import org.edu.commonutil.Response;
import org.edu.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("banner模块")
@RequestMapping("/edu_cms")
@RestController
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation("添加banner")
    @PostMapping("/banner")
    public Response saveBanner(@RequestBody CrmBanner banner){
        bannerService.saveBanner(banner);
        return Response.ok();
    }


    @ApiOperation("删除banner信息")
    @DeleteMapping("/banner/{bannerId}")
    public Response deleteBanner(@PathVariable("bannerId")Long bannerId){
        bannerService.deleteBanner(bannerId);
        return Response.ok();
    }

    @ApiOperation("修改banner信息")
    @PutMapping("/banner")
    public Response updateBanner(@RequestBody CrmBanner banner){
        bannerService.updateBanner(banner);
        return Response.ok();
    }

    @ApiOperation("上传banner图片")
    @PostMapping("/banner/upload")
    public Response uploadBannerImage(MultipartFile file)  {
        try {
            String imgUrl = bannerService.uploadBannerImg(file);
            return Response.ok().data("imageUrl",imgUrl);
        }catch (IOException e){
            return Response.fail().data("error",e.getMessage());
        }
    }

    @ApiOperation("后台分页显示所有banner")
    @GetMapping("/banners/{page}/{pageSize}")
    public Response getAll(@PathVariable("page")Integer page,
                           @PathVariable("pageSize")Integer pageSize){
        Page<CrmBanner> bannersPage = bannerService.getBannersByPage(page, pageSize);
        return Response.ok().data("items",bannersPage);
    }
}
