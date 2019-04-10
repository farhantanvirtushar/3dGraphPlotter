package sample;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftMenu extends Group {
    Button function[] = new Button[5];
    VBox vBox;

    LeftMenu()
    {
        vBox = new VBox();
        for(int i=0;i<function.length;i++)
        {
            function[i] = new Button("Add New Graph");
            function[i].setPrefSize(200,100);
            String id = "f"+i;
            function[i].setId(id);
            function[i].setVisible(true);
            vBox.getChildren().add(function[i]);
        }
        function[0].setVisible(true);
        this.getChildren().add(vBox);
    }

}
