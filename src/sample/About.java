package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class About{
    Stage window;
    Label label1;
    About()
    {
        window = new Stage();
        window.setTitle("About");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene scene = new Scene(vBox,500,250);
        label1 = new Label();
        label1.setText("This software is developed as an undergraduate project of \n" +
                "Department Of Computer Science And Engineering University Of Dhaka\n" );
        Label developerInfo = new Label("Developers");
        developerInfo.setTextFill(Color.BLUE);
        Label developer1 = new Label("Md. Farhan Tanvir\n Email : farhantanvir.2340.csedu@gmail.com");
        Label developer2 = new Label("Fahmida Haque\n Email : iamshanta1998@gmail.com    ");
        vBox.getChildren().addAll(label1,developerInfo,developer1,developer2);

        window.setScene(scene);
        window.show();
    }
}
