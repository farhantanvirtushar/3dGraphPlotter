package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ParametricGraphWindow {

    Stage window;
    Scene scene;
    GridPane gridPane;
    TextField inputX;
    TextField inputY;
    TextField inputZ;
    Label instruction;
    Label warning;
    Label x;
    Label y;
    Label z;
    Label setColor;
    Button plot;
    ColorPicker colorPicker;

    Label howToWrite;

    ParametricGraphWindow(Stage window)
    {


        this.window = window;

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);

        scene = new Scene(gridPane,450,450);


        howToWrite = new Label("use sqrt( ) for '√'. For example : √x ≈ sqrt(x) \n use '^' for power. For example : 'x^2' ");
        howToWrite.setTextFill(Color.RED);

        instruction = new Label("Use u , v as parameter");

        inputX = new TextField();
        inputY = new TextField();
        inputZ = new TextField();

        x = new Label(" x(u,v) = ");
        y = new Label(" y(u,v) = ");
        z = new Label(" z(u,v) = ");
        setColor = new Label("Select Color");
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GRAY);
        plot = new Button("Plot");
        warning = new Label("");
        warning.setVisible(false);


        gridPane.add(howToWrite,1,0);
        gridPane.add(instruction,1,1);
        gridPane.add(x,0,2);
        gridPane.add(inputX,1,2);
        gridPane.add(y,0,3);
        gridPane.add(inputY,1,3);
        gridPane.add(z,0,4);
        gridPane.add(inputZ,1,4);
        gridPane.add(warning,1,5);
        gridPane.add(setColor,1,6);
        gridPane.add(colorPicker,1,6);
        gridPane.add(plot,1,8);

        plot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String functionX = inputX.getText();
                String functionY = inputY.getText();
                String functionZ = inputZ.getText();
                Color color = colorPicker.getValue();
                Main.data.addParametricGraph(functionX,functionY,functionZ,color);
            }
        });

    }
}
