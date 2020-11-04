package org.edu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.CrmBanner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CrmBannerService {

    //添加banner
    void saveBanner(CrmBanner banner);

    //修改banner
    void updateBanner(CrmBanner banner);

    //删除banner
    void deleteBanner(Long bannerId);

    //上传banner图片
    String uploadBannerImg(MultipartFile file) throws IOException;

    //获取所有banner
    Page<CrmBanner> getBannersByPage(Integer page, Integer pageSize);

    //前台显示banner(未删除)
    List<CrmBanner> bannerShow();

}
