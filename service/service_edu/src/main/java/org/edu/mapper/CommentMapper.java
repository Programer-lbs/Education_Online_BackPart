package org.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.edu.bean.EduComment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<EduComment> {
}
