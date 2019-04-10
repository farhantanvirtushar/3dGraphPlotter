package sample;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
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
        leftMenu = new LeftMenu(graph3DSpace);

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


        double totalPoints = AXIS_LEN*2;
        float points[][] = new float[410][410];
        for(int i=0;i<=totalPoints;i++)
        {
            for(int j=0;j<=totalPoints;j++)
            {
                float x= (float) ((i-(totalPoints/2))/2);
                x=(float)(x/20.0);
                float y=(float) ((j-(totalPoints/2))/2);
                y=(float)(y/20.0);
                float z = (float)(20*(Math.sin(x*y)));
                points[i][j]=z;
            }
        }

        TriangleMesh surface = new TriangleMesh();
        surface.getTexCoords().addAll(0,0);

        int k=0;
        for(int i=0;i<totalPoints;i++)
        {
            for(int j=0;j<totalPoints;j++)
            {
                float x1= (float) ((i-(totalPoints/2))/2);
                float y1= (float) ((j-(totalPoints/2))/2);
                float z1 = points[i][j];

                float x2= (float) (x1+0.5);
                float y2= y1;
                float z2 = points[i+1][j];

                float x3= x1;
                float y3= (float) (y1+0.5);
                float z3 = points[i][j+1];

                float x4= (float) (x1+0.5);
                float y4= (float) (y1+0.5);
                float z4 = points[i+1][j+1];


                surface.getPoints().addAll(
                        x1,    y1,    z1,
                        x2,    y2,    z2,
                        x3,    y3,    z3,
                        x4,    y4,    z4
                );

                surface.getFaces().addAll(
                        k+0,0,  k+1,0,  k+2,0,
                        k+2,0,  k+1,0,  k+0,0,
                        k+1,0,  k+2,0,  k+3,0,
                        k+3,0,  k+2,0,  k+1,0

                );
                k+=4;

            }

        }
        MeshView graphSurface = new MeshView(surface);
        graphSurface.setDrawMode(DrawMode.FILL);
        graphSurface.setMaterial(new PhongMaterial(Color.GRAY));
        graph3DSpace.getChildren().addAll(graphSurface);


    }

}
