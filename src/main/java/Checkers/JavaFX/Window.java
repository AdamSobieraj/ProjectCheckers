package Checkers.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Window {

    public static Label labelCurrentTurn = new Label();

    public static HBox addHBox(Button buttonCurrent, Button buttonClose ) {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: #336699;");

        buttonCurrent.setText("Game Restart");
        buttonCurrent.setPrefSize(100, 20);

        buttonClose.setText("Close");
        buttonClose.setPrefSize(100, 20);

        Label mainLable = new Label("Current player turn: ");
        mainLable.setPrefSize(350,20);
        mainLable.setAlignment(Pos.TOP_RIGHT);
        mainLable.setFont(new Font("Arial", 30));

        labelCurrentTurn.setText("WHITE");
        labelCurrentTurn.setPrefSize(200,20);
        labelCurrentTurn.setAlignment(Pos.TOP_LEFT);
        labelCurrentTurn.setFont(new Font("Arial", 30));

        hbox.getChildren().addAll(buttonCurrent, buttonClose, mainLable, labelCurrentTurn);

        return hbox;
    }

    public static void addStackPane(HBox hb) {

        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0,Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT
        // alignment of all nodes
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));

        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
    }

    /*
     * Creates a horizontal flow pane with eight icons in four rows
     */
    public static FlowPane addFlowPane() {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");
        return flow;
    }

    public static StackPane addBottomStackPane(){
        StackPane bottom = new StackPane();
        bottom.setPrefHeight(20);
        bottom.setStyle("-fx-background-color: DAE6F3;");
        return bottom;
    }
}
