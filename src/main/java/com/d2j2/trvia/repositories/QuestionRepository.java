package com.d2j2.trvia.repositories;

import com.d2j2.trvia.entities.MyQuestion;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository <MyQuestion, Long>{
}
