package sample;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Data{

    public Stage mainWindow;
    public Graph3DSpace graph3DSpace;
    public LeftMenu leftMenu;

    public ExplicitGraphWindow explicitGraphWindow;
    public ImplicitGraphWindow implicitGraphWindow;
    public ParametricGraphWindow parametricGraphWindow;
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
    void addParametricGraph(String functionX , String functionY , String functionZ , Color color)
    {
        ParametricFunctionGraph parametricFunctionGraph = new ParametricFunctionGraph(functionX,functionY,functionZ);
        parametricFunctionGraph.setColor(color);
        parametricFunctionGraph.evaluate();
    }

    void clear()
    {
        graph3DSpace.clear();

    }
}
