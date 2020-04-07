package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.entity.Question;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuestionMapper extends BaseMapper<Question> {
}
