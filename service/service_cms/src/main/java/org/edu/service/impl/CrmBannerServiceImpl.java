package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.CrmBanner;
import org.edu.commonutil.OssTemplate;
import org.edu.exception.BannerException;
import org.edu.mapper.CrmBannerMapper;
import org.edu.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CrmBannerServiceImpl  implements CrmBannerService {

    @Autowired
    private CrmBannerMapper bannerMapper;

    @Autowired
    private OssTemplate ossTemplate;

    @Override
    public void saveBanner(CrmBanner banner) {
        int affect = bannerMapper.insert(banner);
        if(affect!=1){
            throw new BannerException("添加banner失败");
        }
    }

    @Override
    public void updateBanner(CrmBanner banner) {
        int affect = bannerMapper.updateById(banner);
        if(affect!=1){
            throw new BannerException("修改banner失败");
        }
    }

    @Override
    public void deleteBanner(Long bannerId) {
        int affect = bannerMapper.deleteById(bannerId);
        if(affect!=1){
            throw new BannerException("删除banner失败");
        }
    }

    @Override
    public String uploadBannerImg(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+"_"+file.getOriginalFilename();
        String imageUrl = ossTemplate.upload(fileName, file.getInputStream());
        return imageUrl;
    }

    @Cacheable(value = "banner",key = "'adminBanner'")
    @Override
    public Page<CrmBanner> getBannersByPage(Integer page, Integer pageSize) {
       Page<CrmBanner> pages = new Page<>(page,pageSize);
       bannerMapper.selectPage(pages, null);
       return pages;
    }

    @Cacheable(value = "banner",key = "'frontBanner'")
    @Override
    public List<CrmBanner> bannerShow() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        List<CrmBanner> crmBanners = bannerMapper.selectList(queryWrapper);
        return crmBanners;
    }
}
