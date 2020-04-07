package com.example.demo4.service.serviceImp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.dto.QuestionDto;
import com.example.demo4.entity.Question;
import com.example.demo4.mapper.CommunityQuestionMapper;
import com.example.demo4.mapper.QuestionMapper;
import com.example.demo4.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuestionServiceImp
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/18 16:25
 */
@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommunityQuestionMapper communityQuestionMapper;

    @Override
    public Integer addQuestion(Question question) {
        return questionMapper.insert(question);
    }

    @Override
    public IPage<QuestionDto> getQuestionList(Page page) {
        IPage<QuestionDto> questionDtoList = communityQuestionMapper.getQuesList(page);
        return questionDtoList;
    }

    @Override
    public QuestionDto getquestionById(Long id) {
        return communityQuestionMapper.getQuestionById(id);
    }
}
