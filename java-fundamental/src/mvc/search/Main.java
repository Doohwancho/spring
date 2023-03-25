package mvc.search;

import mvc.search.controller.PersonDetailsAction;
import mvc.search.controller.SearchPersonAction;
import mvc.search.controller.service.PersonService;
import mvc.search.controller.service.PersonServiceMock;
import mvc.search.view.View;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * a. 의존성의 방향
 *
 * view -> controller -> model
 *
 *
 * b. logic
 *
 * view에서 Action(controller)를 event listener에 등록해주고,
 * event fire -> controller에서 model을 변경 -> 변경된 model의 값이 view에 반영된다.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * model
         */
        //model1
        DefaultListSelectionModel searchResultSelectionModel = new DefaultListSelectionModel();
        searchResultSelectionModel
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //model2
        DefaultListModel searchResultListModel = new DefaultListModel();

        //model3
        Document searchInput = new PlainDocument();

        /**
         * controller
         */
        //Service
        PersonService personService = new PersonServiceMock();

        //Controller - PersonDetailsAction
        PersonDetailsAction personDetailsAction = new PersonDetailsAction(searchResultSelectionModel, searchResultListModel);

        //Controller - SearchPersonAction
        Action searchPersonAction = new SearchPersonAction(searchInput, searchResultListModel, personService);

        /**
         * view
         */
        //View
        View view = new View(searchPersonAction, personDetailsAction, searchInput, searchResultListModel, searchResultSelectionModel);
    }
}

