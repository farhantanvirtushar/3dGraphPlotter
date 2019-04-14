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

public class ExplicitGraphWindow {
    Stage window;
    Scene scene;
    GridPane gridPane;
    TextField input;
    Label warning;
    Label f;
    Label setColor;
    Button plot;
    ColorPicker colorPicker;

    Label howToWrite;

    ExplicitGraphWindow (Stage window)
    {

        this.window = window;

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);

        scene = new Scene(gridPane,450,450);


        howToWrite = new Label("use sqrt( ) for '√'. For example : √x ≈ sqrt(x) \n use '^' for power. For example : 'x^2' ");
        howToWrite.setTextFill(Color.RED);

        input = new TextField();
        f = new Label(" z = ");
        setColor = new Label("Select Color");
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GRAY);
        plot = new Button("Plot");
        warning = new Label("");
        warning.setVisible(false);

        gridPane.add(howToWrite,1,0);
        gridPane.add(f,0,1);
        gridPane.add(input,1,1);
        gridPane.add(warning,1,2);
        gridPane.add(setColor,1,3);
        gridPane.add(colorPicker,1,4);
        gridPane.add(plot,1,5);

        plot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String function = input.getText();
                Color color = colorPicker.getValue();
                Main.data.addExplicitGraph(function,color);
            }
        });

    }

}
