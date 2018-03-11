/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alexey
 */
public class Corridor  implements Serializable{

    private int startN; //Вершина, к которой примыкает коридор в начале
    private int finishN; //Вершина, к которой примыкает коридор в конце

    private ArrayList<int[]> cells=new <int[]>ArrayList();
    private int weight=0;

    //Конструктор
    public Corridor(ArrayList<int[]> coordArray, int start, int finish) {
        cells=(ArrayList<int[]>)coordArray.clone();
        for (int i=0;i<cells.size();i++){
            weight+=cells.get(i)[2];
        }
        weight--;
        startN = start;
        finishN = finish;
    }

    //Задание новых координат конца - вычисление новых угла и длины
    public final void setFinishCoord(int x, int y, int w) {
        cells.add(new int[]{x,y,w});
        weight+=w;
    }

    public int[] getCell(int i){
        return cells.get(i).clone();
    }

    //Получение координат начала
    public int getXstart() {
        return cells.get(0)[0];
    }

    public int getYstart() {
        return cells.get(0)[1];
    }

    //Получение координат конца
    public int getXfinish() {
        return cells.get(getLength()-1)[0];
    }

    public int getYfinish() {
        return cells.get(getLength()-1)[1];        
    }

    //Получить длину данного коридора
    public int getLength() {
        return cells.size();
    }
    
    public int getWeight() {
        return weight;
    }

    public int getStartPoint() {
        return startN;
    }

    public int getFinishPoint() {
        return finishN;
    }

    //Задать номер вершины, к которой примыкает коридор
    public void setFinishPoint(int point) {
        finishN = point;
    }

    public void setStartPoint(int point) {
        startN = point;
    }
}
