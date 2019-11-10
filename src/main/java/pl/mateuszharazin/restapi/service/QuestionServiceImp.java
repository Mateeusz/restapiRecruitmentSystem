package pl.mateuszharazin.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszharazin.restapi.model.Question;
import pl.mateuszharazin.restapi.repository.QuestionRepository;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public boolean isQuestionAlreadyExist(Question question) {

        boolean isQuestionExist = false;

        Question existing = questionRepository.findByQuestionBody(question.getQuestionBody());
        if (existing != null) {
            isQuestionExist = true;
        }

        return isQuestionExist;
    }
}
