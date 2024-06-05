package level1_nextstep.example;

import level1_nextstep.annotation.Inject;
import level1_nextstep.annotation.Service;

@Service
public class MyQnaService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Inject
    public MyQnaService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }
}
