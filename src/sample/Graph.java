package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class Graph {

    public TriangleMesh surface;

    MeshView graphSurface;
    public float z_values[][];
    Color color;
    public double totalPoints;

    Graph()
    {
        totalPoints = (int)(Home.AXIS_LEN*2);
        z_values = new float[(int)totalPoints+10][(int)totalPoints+10];

        surface = new TriangleMesh();
        surface.getTexCoords().addAll(0,0);

        color = Color.GRAY;

    }
    void setColor(Color color)
    {
        this.color = color;
    }

    void evaluate()
    {}
}
