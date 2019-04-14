package sample;

import javafx.scene.paint.Color;

public class Data{

    public Graph3DSpace graph3DSpace;
    public LeftMenu leftMenu;

    public ExplicitGraphWindow explicitGraphWindow;
    public ImplicitGraphWindow implicitGraphWindow;
    int index;
    Data()
    {
        index=0;
    }

    void addExplicitGraph(String function, Color color)
    {
        ExplicitFunctionGraph explicitFunction = new ExplicitFunctionGraph(function);
        explicitFunction.setColor(color);
        explicitFunction.evaluate();
    }

    void addImplicitGraph(String function , Color color)
    {
       ImplicitFunctionGraph implicitFunctionGraph = new ImplicitFunctionGraph(function);
       implicitFunctionGraph.setColor(color);
       implicitFunctionGraph.evaluate();
    }
    void addParametricGraph()
    {
        //graph[i]=new ParametricFunctionGraph();
    }
}
