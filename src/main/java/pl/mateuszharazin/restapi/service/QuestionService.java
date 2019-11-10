package pl.mateuszharazin.restapi.service;

import pl.mateuszharazin.restapi.model.Question;

public interface QuestionService {

    public void saveQuestion(Question question);
    public boolean isQuestionAlreadyExist(Question question);
}
