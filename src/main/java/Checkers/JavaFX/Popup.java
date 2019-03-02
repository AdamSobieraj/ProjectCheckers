package Checkers.JavaFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Checkers.Player.PlayerStatistic;

public class Popup {
    private static String winner;
    private static Button buttonClose;
    private static Stage popupwindow=new Stage();

    public static void setButtonClose(Button buttonClose) {
        Popup.buttonClose = buttonClose;
    }

    public static void display()
    {

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        winner = (PlayerStatistic.getWhiteKillCount() == 12)? "WHITE": "BLACK";
        popupwindow.setTitle("End Game java App" );


        Label label1= new Label("The Game is Finish, the Winner Is: "+ winner);
        Button button1= new Button("Close this pop up window");
        button1.setOnAction(e -> popupwindow.close());

        buttonClose.setText("Close");

        VBox layout= new VBox(10);

        layout.getChildren().addAll(label1, button1,buttonClose );
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static void popupClose(){
        popupwindow.close();
    }
}