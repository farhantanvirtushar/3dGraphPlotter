package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SelectGraphTypeWindow{

    Label graphType;
    Button explicitFunction;
    Button implicitFunction;
    Button parametricFunction;
    Stage window;
    Scene scene;
    SelectGraphTypeWindow()
    {

        VBox options = new VBox();
        options.setAlignment(Pos.CENTER);
        options.setSpacing(20);
        graphType = new Label("Select Graph Type");
        graphType.setTextFill(Color.BLUE);
        graphType.setScaleX(2);
        graphType.setScaleY(2);
        explicitFunction = new Button("Explicit Function \n z = f(x,y)");
        implicitFunction = new Button("Implicit Function\nf(x,y,z) = 0");
        parametricFunction = new Button(" Parametric Function\nx = f(t) , y = g(t) , z = h(t)");

        explicitFunction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ExplicitGraphWindow explicitGraphWindow = new ExplicitGraphWindow(window);
                Main.data.explicitGraphWindow = explicitGraphWindow;
                window.setScene(explicitGraphWindow.scene);
                window.setTitle("Explicit Function");
            }
        });
        implicitFunction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ImplicitGraphWindow implicitGraphWindow = new ImplicitGraphWindow(window);
                Main.data.implicitGraphWindow = implicitGraphWindow;
                window.setScene(implicitGraphWindow.scene);
                window.setTitle("Implicit Function");
            }
        });
        options.getChildren().addAll(graphType,explicitFunction,implicitFunction,parametricFunction);

        scene = new Scene(options,400,400);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("Select Graph Type");
        window.show();
    }
}
