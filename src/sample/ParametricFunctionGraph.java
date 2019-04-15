package sample;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;

import static java.lang.Math.abs;

public class ParametricFunctionGraph extends Graph{

    EquationEvaluation equationEvaluationX;
    EquationEvaluation equationEvaluationY;
    EquationEvaluation equationEvaluationZ;

    String functionX,functionY,functionZ;
    float x_values[][];
    float y_values[][];
    float z_values[][];

    ParametricFunctionGraph(String functionX , String functionY , String functionZ )
    {
        this.functionX = functionX;
        this.functionY = functionY;
        this.functionZ = functionZ;

        x_values = new float[(int)totalPoints+10][(int)totalPoints+10];
        y_values = new float[(int)totalPoints+10][(int)totalPoints+10];
        z_values = new float[(int)totalPoints+10][(int)totalPoints+10];

        equationEvaluationX = new EquationEvaluation(functionX);
        equationEvaluationY = new EquationEvaluation(functionY);
        equationEvaluationZ = new EquationEvaluation(functionZ);


    }

    void evaluate()
    {
        for(int i=0;i<=totalPoints;i++)
        {
            for(int j=0;j<=totalPoints;j++)
            {
                float a = (float) ((i-(totalPoints/2))/10)/2;
                float b = (float) ((j-(totalPoints/2))/10)/2;
                x_values[i][j]= 10* equationEvaluationX.evaluate(a,b);

                y_values[i][j]=10*equationEvaluationY.evaluate(a,b);

                z_values[i][j] = 10* equationEvaluationZ.evaluate(a,b);

            }

        }
        singleParameterHandel();

        if(equationEvaluationX.invalid||equationEvaluationX.parathensisMismatch||equationEvaluationY.invalid||equationEvaluationY.parathensisMismatch||equationEvaluationZ.invalid||equationEvaluationZ.parathensisMismatch)
        {
            Main.data.parametricGraphWindow.warning.setText("Invalid Expression Or Parentheses Imbalanced");
            Main.data.parametricGraphWindow.warning.setVisible(true);
            Main.data.parametricGraphWindow.warning.setTextFill(Color.RED);
            return;
        }

        plot();
        Main.data.parametricGraphWindow.window.close();
        Main.data.leftMenu.function[Main.data.index].setBackground(new Background(new BackgroundFill(color,null,null)));
        String text = "x(u,v)="+functionX+"\ny(u,v)="+functionY+"\nz(u,v)="+functionZ;
        Main.data.leftMenu.function[Main.data.index].setText(text);
    }

    void plot()
    {
        int k=0;
        for(int i=0;i<totalPoints;i++)
        {
            for(int j=0;j<totalPoints;j++)
            {
                float x1= x_values[i][j];
                float y1= y_values[i][j];
                float z1= z_values[i][j];

                float x2= x_values[i+1][j];
                float y2= y_values[i+1][j];
                float z2= z_values[i+1][j];

                float x3= x_values[i][j+1];
                float y3= y_values[i][j+1];
                float z3= z_values[i][j+1];

                float x4= x_values[i+1][j+1];
                float y4= y_values[i+1][j+1];
                float z4= z_values[i+1][j+1];


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
        graphSurface = new MeshView(surface);
        graphSurface.setId(""+Main.data.index);
        graphSurface.setDrawMode(DrawMode.FILL);
        graphSurface.setMaterial(new PhongMaterial(color));
        Main.data.graph3DSpace.add(graphSurface,Main.data.index,0);
    }

    void singleParameterHandel()
    {
        int counter = 0;
        float a =(float) 0.25;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(x_values[i][0]-x_values[i][1])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                x_values[i][1]+=a;
            }
        }
        counter=0;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(y_values[i][0]-y_values[i][1])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                y_values[i][1]+=a;
            }
        }
        counter=0;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(z_values[i][0]-z_values[i][1])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                z_values[i][1]+=a;
            }
        }
        counter = 0;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(x_values[0][i]-x_values[1][i])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                x_values[1][i]+=a;
            }
        }
        counter=0;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(y_values[0][i]-y_values[1][i])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                y_values[1][i]+=a;
            }
        }
        counter=0;
        for (int i=0;i<totalPoints;i++)
        {
            if(abs(z_values[0][i]-z_values[1][i])>0.001)
            {
                counter++;
            }
        }
        if(counter==0)
        {
            for (int i=0;i<totalPoints;i++)
            {
                z_values[1][i]+=a;
            }
        }
    }

}
