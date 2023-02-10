package com.example.vocabularyapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VocabularyController implements Initializable {
    @FXML
    private Button button_goBack;
    @FXML
    private Button button_check;
    @FXML
    private Button button_addNewWords;
    @FXML
    private TextField tf_word1;
    @FXML
    private TextField tf_word2;
    @FXML
    private Label score;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "vocabulary-view.fxml", "Log in");
            }
        });

        button_check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.checkPairExists(event, tf_word1.getText(), tf_word2.getText());
            }
        });

        button_addNewWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "addVocabulary-view.fxml", "Add new vocabulary");
            }
        });
    }
}
