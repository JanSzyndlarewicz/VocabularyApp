package com.example.vocabularyapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddVocabularyController implements Initializable {
    @FXML
    private Button button_goBack;
    @FXML
    private Button button_add;
    @FXML
    private TextField tf_polish;
    @FXML
    private TextField tf_english;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "vocabulary-view.fxml", "Log in");
            }
        });

        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.addWords(event, tf_polish.getText(), tf_english.getText());
            }
        });


    }
}
