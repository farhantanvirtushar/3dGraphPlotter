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

public class ImplicitGraphWindow{
    Stage window;
    Scene scene;
    GridPane gridPane;
    TextField input;
    Label warning;
    Label f;
    Label zero;
    Label setColor;
    Button plot;
    ColorPicker colorPicker;
    ImplicitGraphWindow(Stage window)
    {
        this.window = window;

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);

        scene = new Scene(gridPane,400,400);


        input = new TextField();
        f = new Label(" f(x,y,z) = ");
        zero = new Label(" = 0");
        setColor = new Label("Select Color");
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GRAY);
        plot = new Button("Plot");
        warning = new Label("");
        warning.setVisible(false);


        gridPane.add(f,0,0);
        gridPane.add(input,1,0);
        gridPane.add(zero,2,0);
        gridPane.add(warning,1,1);
        gridPane.add(setColor,1,2);
        gridPane.add(colorPicker,1,3);
        gridPane.add(plot,1,4);

        plot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String function = input.getText();
                Color color = colorPicker.getValue();
                Main.data.addImplicitGraph(function,color);
            }
        });
    }
}
