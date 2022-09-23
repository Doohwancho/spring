package level1_nextstep.example;

import level1_nextstep.annotation.Controller;
import level1_nextstep.annotation.Inject;

@Controller
public class QnaController {

    private final MyQnaService qnaService;

    @Inject
    public QnaController(MyQnaService qnaService) {
        this.qnaService = qnaService;
    }
}
