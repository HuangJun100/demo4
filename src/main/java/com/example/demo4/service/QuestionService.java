package com.example.demo4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.dto.QuestionDto;
import com.example.demo4.entity.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 添加问题整体
     * @param question
     * @return
     */
    public Integer addQuestion(Question question);

    /**
     * 问题及作者分页
     * @param page
     * @return
     */
    public IPage<QuestionDto> getQuestionList(Page page);

    public QuestionDto getquestionById(Long id);
}
