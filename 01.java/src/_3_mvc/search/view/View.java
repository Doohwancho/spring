package _3_mvc.search.view;

import _3_mvc.search.controller.PersonDetailsAction;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class View {

    //controller
    private final Action searchPersonAction;
    private Action personDetailsAction;

    //model
    private final Document searchInput;
    private DefaultListModel searchResultListModel;
    private DefaultListSelectionModel searchResultSelectionModel;


    public View(Action searchPersonAction, PersonDetailsAction personDetailsAction, Document searchInput, DefaultListModel searchResultListModel, DefaultListSelectionModel searchResultSelectionModel) {
        this.searchPersonAction = searchPersonAction;
        this.personDetailsAction = personDetailsAction;
        this.searchInput = searchInput;
        this.searchResultListModel = searchResultListModel;
        this.searchResultSelectionModel = searchResultSelectionModel;
        init();
    }
    public void init(){
        JFrame mainFrame = new JFrame("MVC example");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(640, 300);
        mainFrame.setLocationRelativeTo(null);

        Container contentPane = mainFrame.getContentPane();

        JPanel searchInputPanel = new JPanel();
        searchInputPanel.setLayout(new BorderLayout());

        //add event listener with controller
        JTextField searchField = new JTextField(searchInput, null, 0);
        searchInputPanel.add(searchField, BorderLayout.CENTER);
        searchField.addActionListener(searchPersonAction);

        //add event listener with controller
        JButton searchButton = new JButton(searchPersonAction);
        searchInputPanel.add(searchButton, BorderLayout.EAST);

        JList searchResultList = new JList();
        searchResultList.setModel(searchResultListModel); //model
        searchResultList.setSelectionModel(searchResultSelectionModel); //model

        JPanel searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BorderLayout());
        JScrollPane scrollableSearchResult = new JScrollPane(searchResultList);
        searchResultPanel.add(scrollableSearchResult, BorderLayout.CENTER); //model

        JPanel selectionOptionsPanel = new JPanel();

        //add event listener with controller
        JButton showPersonDetailsButton = new JButton(personDetailsAction);
        selectionOptionsPanel.add(showPersonDetailsButton);

        contentPane.add(searchInputPanel, BorderLayout.NORTH);
        contentPane.add(searchResultPanel, BorderLayout.CENTER);
        contentPane.add(selectionOptionsPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }
}
