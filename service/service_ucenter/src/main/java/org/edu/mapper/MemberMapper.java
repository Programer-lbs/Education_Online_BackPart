package org.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.bean.UcenterMember;

public interface MemberMapper extends BaseMapper<UcenterMember> {

    //选择某一天的注册人数
    Integer getRegisterNumOneDay(@Param("date") String date);
}
