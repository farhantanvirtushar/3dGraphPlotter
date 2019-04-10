package sample;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;


public class Home extends GridPane {

    SubScene graphScene;
    SubScene menuScene;

    double AXIS_LEN=200;
    double AXIS_RADIUS=0.5;

    Graph3DSpace graph3DSpace;
    LeftMenu leftMenu;

    public Camera camera;
    Home()
    {
        graph3DSpace = new Graph3DSpace();
        leftMenu = new LeftMenu();

        Cylinder xAxis = new Cylinder(AXIS_RADIUS,AXIS_LEN);
        Cylinder yAxis = new Cylinder(AXIS_RADIUS,AXIS_LEN);
        Cylinder zAxis = new Cylinder(AXIS_RADIUS,AXIS_LEN);

        xAxis.setMaterial(new PhongMaterial(Color.RED));
        yAxis.setMaterial(new PhongMaterial(Color.GREEN));
        zAxis.setMaterial(new PhongMaterial(Color.BLUE));

        xAxis.getTransforms().add(new Rotate(90,Rotate.Z_AXIS));
        zAxis.getTransforms().add(new Rotate(90,Rotate.X_AXIS));

        Sphere xHead = new Sphere(3);
        Sphere yHead = new Sphere(3);
        Sphere zHead = new Sphere(3);

        xHead.translateXProperty().set(xHead.getTranslateX()+(AXIS_LEN/2));
        yHead.translateYProperty().set(yHead.getTranslateY()+(AXIS_LEN/2));
        zHead.translateZProperty().set(zHead.getTranslateZ()+(AXIS_LEN/2));

        xHead.setMaterial(new PhongMaterial(Color.RED));
        yHead.setMaterial(new PhongMaterial(Color.GREEN));
        zHead.setMaterial(new PhongMaterial(Color.BLUE));

        graph3DSpace.getChildren().addAll(xAxis,yAxis,zAxis,xHead,yHead,zHead);

        menuScene = new SubScene(leftMenu,200,Main.HEIGHT);
        graphScene = new SubScene(graph3DSpace,Main.WIDTH-200,Main.HEIGHT,true, SceneAntialiasing.BALANCED);

        camera = new PerspectiveCamera(true);
        graphScene.setCamera(camera);

        camera.setNearClip(1);
        camera.setFarClip(3000);

        camera.translateXProperty().set(0);
        camera.translateYProperty().set(0);
        camera.translateZProperty().set(-400);

        this.add(menuScene,0,0);
        this.add(graphScene,1,0);


    }

}
