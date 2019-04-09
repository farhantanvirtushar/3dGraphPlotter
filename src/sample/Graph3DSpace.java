package sample;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Graph3DSpace extends Group {
    Rotate r;
    Transform t;
    Graph3DSpace()
    {
        t = new Rotate();
        rotateByX(135);
        rotateByZ(45);
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
