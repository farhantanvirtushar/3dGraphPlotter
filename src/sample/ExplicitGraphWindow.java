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
    Scene scene;
    GridPane gridPane;
    TextField input;
    Label f;
    Label setColor;
    Button plot;
    ColorPicker colorPicker;

    ExplicitGraphWindow ()
    {

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        scene = new Scene(gridPane,400,400);
        input = new TextField();
        f = new Label(" z = ");
        setColor = new Label("Select Color");
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GRAY);
        plot = new Button("Plot");

        gridPane.add(f,0,0);
        gridPane.add(input,1,0);
        gridPane.add(setColor,1,1);
        gridPane.add(colorPicker,1,2);
        gridPane.add(plot,1,3);

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
