package sample;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import static sample.Home.AXIS_LEN;

public class ImplicitFunctionGraph extends Graph{

    EquationEvaluation equationEvaluation ;
    public String function;
    float values[][][];
    float points;
    ImplicitFunctionGraph(String function)
    {
        this.function=function;
        equationEvaluation = new EquationEvaluation(function);
        points = (float) AXIS_LEN;
        values = new float[(int) points+2][(int) points+2][(int) points+2];
    }
    void evaluate()
    {
        for (int i=0;i<points;i++)
        {
            for (int j=0;j<points;j++)
            {
                for (int k=0;k<points;k++)
                {
                    float x = i - (points/2);
                    x=x/10;
                    float y = j - (points/2);
                    y=y/10;
                    float z = k - (points/2);
                    z=z/10;
                    values[i][j][k]= equationEvaluation.evaluate(x,y,z);
                }
            }
        }
        if(equationEvaluation.invalid||equationEvaluation.parathensisMismatch)
        {
            Main.data.implicitGraphWindow.warning.setText("Invalid Expression Or Parentheses Imbalanced");
            Main.data.implicitGraphWindow.warning.setVisible(true);
            Main.data.implicitGraphWindow.warning.setTextFill(Color.RED);
            return;
        }

        plot();


        Main.data.implicitGraphWindow.window.close();
        Main.data.leftMenu.function[Main.data.index].setBackground(new Background(new BackgroundFill(color,null,null)));
        Main.data.leftMenu.function[Main.data.index].setText(function);


    }

    void plot()
    {

        for (int i=0;i<points;i++)
        {
            for (int j=0;j<points;j++)
            {
                for (int k=0;k<points;k++)
                {
                    float x = i - (points/2);
                    x=x/10;
                    float y = j - (points/2);
                    y=y/10;
                    float z = k - (points/2);
                    z=z/10;
                    values[i][j][k]=equationEvaluation.evaluate(x,y,z);
                }
            }
        }

        int map1[][][];
        int map2[][][];
        int map3[][][];
        map1 = new int[(int) points+2][(int) points+2][(int) points+2];
        map2 = new int[(int) points+2][(int) points+2][(int) points+2];
        map3 = new int[(int) points+2][(int) points+2][(int) points+2];


        for(int i=0;i<points;i++)
        {
            for (int j=0;j<points;j++)
            {
                for (int k =0;k<points;k++)
                {
                    map1[i][j][k]=0;
                    map2[i][j][k]=0;
                    map3[i][j][k]=0;
                }

            }
        }

        for(int i=1;i<points-1;i++)
        {
            for (int j=1;j<points-1;j++)
            {
                for (int k =1;k<points-1;k++)
                {
                    float v = values[i][j][k];
                    if(v>0)
                    {
                        if((values[i][j][k+1]<0)||(values[i][j][k-1]<0))
                        {
                            map1[i][j][k]=1;
                        }

                    }


                }

            }
        }
        for(int j=1;j<points-1;j++)
        {
            for (int k=1;k<points-1;k++)
            {
                for (int i =1;i<points-1;i++)
                {
                    float v = values[i][j][k];
                    if(v>0)
                    {
                        if((values[i+1][j][k]<0)||(values[i-1][j][k]<0))
                        {
                            map2[i][j][k]=1;
                        }

                    }


                }

            }
        }
        for(int k=1;k<points-1;k++)
        {
            for (int i=1;i<points-1;i++)
            {
                for (int j =1;j<points-1;j++)
                {
                    float v = values[i][j][k];
                    if(v>0)
                    {
                        if((values[i][j+1][k]<0)||(values[i][j-1][k]<0))
                        {
                            map3[i][j][k]=1;

                        }
                    }


                }

            }
        }
        TriangleMesh surface = new TriangleMesh();
        surface.getTexCoords().addAll(0,0);

        int k = 0;
        try {

            for (int i = 0; i < points; i++) {
                for (int j = 0; j < points; j++) {
                    int k1 = 0, k2 = 0, k3 = 0, k4 = 0;
                    while (true) {

                        for (; k1 < points; k1++) {
                            if (map1[i][j][k1] == 1) {
                                k1 += 1;
                                break;
                            }
                        }

                        for (; k2 < points; k2++) {
                            if (map1[i + 1][j][k2] == 1) {
                                k2 += 1;
                                break;
                            }
                        }

                        for (; k3 < points; k3++) {
                            if (map1[i][j + 1][k3] == 1) {
                                k3 += 1;
                                break;
                            }
                        }
                        for (; k4 < points; k4++) {
                            if (map1[i + 1][j + 1][k4] == 1) {
                                k4 += 1;
                                break;
                            }
                        }

                        if ((!(k1 < points)) || (!(k2 < points)) || (!(k3 < points)) || (!(k4 < points))) {
                            break;
                        }
                        float x1 = i - (points / 2);
                        float y1 = j - (points / 2);
                        float z1 = k1 - (points / 2);

                        float x2 = x1 + 1;
                        float y2 = y1;
                        float z2 = k2 - (points / 2);

                        float x3 = x1;
                        float y3 = y1 + 1;
                        float z3 = k3 - (points / 2);

                        float x4 = x1 + 1;
                        float y4 = y1 + 1;
                        float z4 = k4 - (points / 2);

                        surface.getPoints().addAll(
                                x1, y1, z1,
                                x2, y2, z2,
                                x3, y3, z3,
                                x4, y4, z4
                        );

                        surface.getFaces().addAll(
                                k + 0, 0, k + 1, 0, k + 2, 0,
                                k + 2, 0, k + 1, 0, k + 0, 0,
                                k + 1, 0, k + 2, 0, k + 3, 0,
                                k + 3, 0, k + 2, 0, k + 1, 0

                        );
                        k += 4;
                        if(k>1000000)
                        {
                            throw new Exception();
                        }


                    }
                }
            }
        }catch (OutOfMemoryError error)
        {

        }
        catch (Exception e)
        {

        }

        graphSurface = new MeshView(surface);
        graphSurface.setDrawMode(DrawMode.FILL);
        graphSurface.setMaterial(new PhongMaterial(color));
        Main.data.graph3DSpace.add(graphSurface,Main.data.index,0);


        surface = new TriangleMesh();
        surface.getTexCoords().addAll(0,0);

        k=0;
        try{
            for(int i=0;i<points;i++)
            {
                for (int j=0;j<points;j++)
                {
                    int k1=0,k2=0,k3=0,k4=0;
                    while (true)
                    {

                        for(;k1<points;k1++)
                        {
                            if(map2[k1][i][j]==1)
                            {
                                k1+=1;
                                break;
                            }
                        }

                        for(;k2<points;k2++)
                        {
                            if(map2[k2][i+1][j]==1)
                            {
                                k2+=1;
                                break;
                            }
                        }

                        for(;k3<points;k3++)
                        {
                            if(map2[k3][i][j+1]==1)
                            {
                                k3+=1;
                                break;
                            }
                        }
                        for(;k4<points;k4++)
                        {
                            if(map2[k4][i+1][j+1]==1)
                            {
                                k4+=1;
                                break;
                            }
                        }

                        if((!(k1<points)) || (!(k2<points))||(!(k3<points))||(!(k4<points)))
                        {
                            break;
                        }
                        float y1 = i-(points/2);
                        float z1 = j-(points/2);
                        float x1 = k1-(points/2);

                        float y2 = y1+1;
                        float z2 = z1;
                        float x2 = k2-(points/2);

                        float y3 = y1;
                        float z3 = z1 +1;
                        float x3 = k3-(points/2);

                        float y4 = y1+1;
                        float z4 = z1+1;
                        float x4 = k4-(points/2);

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
                        if(k>1000000)
                        {
                            throw new Exception();
                        }

                    }
                }
            }
        }catch (OutOfMemoryError error)
        {

        }
        catch (Exception e)
        {

        }

        graphSurface = new MeshView(surface);
        graphSurface.setDrawMode(DrawMode.FILL);
        graphSurface.setMaterial(new PhongMaterial(color));
        Main.data.graph3DSpace.add(graphSurface,Main.data.index,1);

        surface = new TriangleMesh();
        surface.getTexCoords().addAll(0,0);

        k=0;

        try {
            for(int i=0;i<points;i++)
            {
                for (int j=0;j<points;j++)
                {
                    int k1=0,k2=0,k3=0,k4=0;
                    while (true)
                    {

                        for(;k1<points;k1++)
                        {
                            if(map3[j][k1][i]==1)
                            {
                                k1+=1;
                                break;
                            }
                        }

                        for(;k2<points;k2++)
                        {
                            if(map3[j][k2][i+1]==1)
                            {
                                k2+=1;
                                break;
                            }
                        }

                        for(;k3<points;k3++)
                        {
                            if(map3[j+1][k3][i]==1)
                            {
                                k3+=1;
                                break;
                            }
                        }
                        for(;k4<points;k4++)
                        {
                            if(map3[j+1][k4][i+1]==1)
                            {
                                k4+=1;
                                break;
                            }
                        }

                        if((!(k1<points)) || (!(k2<points))||(!(k3<points))||(!(k4<points)))
                        {
                            break;
                        }
                        float z1 = i-(points/2);
                        float x1 = j-(points/2);
                        float y1 = k1-(points/2);

                        float z2 = z1+1;
                        float x2 = x1;
                        float y2 = k2-(points/2);

                        float z3 = z1;
                        float x3 = x1 +1;
                        float y3 = k3-(points/2);

                        float z4 = z1+1;
                        float x4 = x1+1;
                        float y4 = k4-(points/2);

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
                        if(k>1000000)
                        {
                            throw new Exception();
                        }

                    }
                }
            }
        }catch (OutOfMemoryError error)
        {

        }
        catch (Exception e)
        {

        }

        graphSurface = new MeshView(surface);
        graphSurface.setDrawMode(DrawMode.FILL);
        graphSurface.setMaterial(new PhongMaterial(color));
        Main.data.graph3DSpace.add(graphSurface,Main.data.index,2);
    }


}