package sample;

import javafx.scene.paint.Color;

public class Data{

    public Graph graph[];
    public Graph3DSpace graph3DSpace;
    int i;
    Data()
    {
        graph = new Graph[5];
        i=0;
    }

    void addExplicitGraph(String function, Color color)
    {
        ExplicitFunctionGraph explicitFunction = new ExplicitFunctionGraph(function);
        graph[i] = explicitFunction;
        graph[i].setColor(color);
        graph[i].evaluate();
        i++;
    }

    void addImplicitGraph()
    {
        //graph[i]=new ImplicitFunctionGraph();
    }
    void addParametricGraph()
    {
        //graph[i]=new ParametricFunctionGraph();
    }
}
