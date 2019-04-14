package sample;


import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Graph3DSpace extends Group {
    Rotate r;
    Transform t;
    MeshView meshView[];
    boolean isDrawn[];
    Graph3DSpace()
    {

        meshView=new MeshView[5];
        isDrawn = new boolean[5];
        for(int i=0;i<5;i++)
        {
            isDrawn[i]=false;
        }
        t = new Rotate();
        rotateByX(120);
        rotateByZ(40);

    }
    void add(MeshView mView , int i)
    {
        if(isDrawn[i])
        {
            this.getChildren().removeAll(meshView[i]);
        }
        meshView[i]=mView;
        isDrawn[i]=true;
        this.getChildren().add(mView);
    }
    void rotateByX(int angle)
    {
        r = new Rotate(angle,Rotate.X_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

    void rotateByY(int angle)
    {
        r = new Rotate(angle,Rotate.Y_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

    void rotateByZ(int angle)
    {
        r = new Rotate(angle,Rotate.Z_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }
}
