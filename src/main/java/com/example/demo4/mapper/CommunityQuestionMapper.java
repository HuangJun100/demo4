package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.dto.QuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Mapper
public interface CommunityQuestionMapper {

    /**
     * 问题和坐着的分页
     * @param page
     * @return
     */
    public IPage<QuestionDto> getQuesList(Page page);

    /**
     * 通过ID查询问题及作者
     * @param id
     * @return
     */
    public QuestionDto getQuestionById(Long id);
}
