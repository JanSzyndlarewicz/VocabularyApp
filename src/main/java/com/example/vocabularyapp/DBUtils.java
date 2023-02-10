package com.example.vocabularyapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        assert root != null;
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void checkPairExists(ActionEvent actionEvent, String word1, String word2){
        java.sql.Connection connection= null;
        PreparedStatement psCheckPairExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vocabularyapp", "root", "1111");
            psCheckPairExists = connection.prepareStatement("SELECT * FROM vocabulary WHERE word1 = ? AND word2 = ?");
            psCheckPairExists.setString(1, word1);
            psCheckPairExists.setString(2, word2);
            resultSet = psCheckPairExists.executeQuery();

            if(!resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Wrong answer");
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Good answer");
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckPairExists != null){
                try {
                    psCheckPairExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addWords(ActionEvent actionEvent, String word1, String word2){
        Connection connection= null;
        PreparedStatement psCheckPairExists = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;
        Boolean isPair;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vocabularyapp", "root", "1111");
            psCheckPairExists = connection.prepareStatement("SELECT * FROM vocabulary WHERE word1 = ? AND word2 = ?");
            psCheckPairExists.setString(1, word1);
            psCheckPairExists.setString(2, word2);
            resultSet = psCheckPairExists.executeQuery();

            isPair = resultSet.isBeforeFirst();

            if(!isPair){
                psInsert = connection.prepareStatement("INSERT INTO vocabulary (word1, word2) VALUES(?, ?)");
                psInsert.setString(1, word1);
                psInsert.setString(2, word2);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Words added favorably");
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This pair of words already exists");
                alert.show();
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
              try {
                  resultSet.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckPairExists != null){
                try {
                    psCheckPairExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
