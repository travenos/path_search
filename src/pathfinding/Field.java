/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;
import java.awt.Color;
import java.io.Serializable;
/**
 *
 * @author alexey
 */

public class Field implements Serializable{
    
    private int cells [][]; //Содержимое клеток
    private int width; //Количество столбцов
    private int height; //Количество строк
    
    public static final Color colorGrey = new Color(238, 238, 238);
    
    public static final Color cellColors[]={Color.white, Color.black, colorGrey, Color.blue,
    Color.magenta, Color.orange,  Color.yellow, Color.green, Color.red};
    /* Белая - свободная, чёрная - препятствие, серая - дверь, синяя - труднопроходимая,
    фиолетовая - начальная, оранжевая - конечная, жёлтая - путь от двери до двери,
    зелёная - текущий путь, ?красная - изученная точка?
    */
    public Field(int w, int h){
        newField(w,h);
    }
    
    private void newField(int w, int h){
        width=w;
        height=h;
        cells=new int[width][height];
        clear();
    }
    
    public void changeSize(int w, int h){
        int cellsOld[][]=cells;
        int oldW=width;
        int oldH=height;
        width=w;
        height=h;
        newField(w,h);
        int maxW,maxH;
        if (width<oldW){
            maxW=width;
        }else{
            maxW=oldW;
        }
        if (height<oldH){
            maxH=height;
        }else{
            maxH=oldH;
        }
        for (int i=0;i<maxW;i++){
            System.arraycopy(cellsOld[i], 0, cells[i], 0, maxH);
        }
    }
    
    public void setCellType(int x, int y, Color type){
        cells[x][y]=findColor(type);
    }
    
    public void setCellType(int x, int y, int type){
        if (x>=0 && x<width && y>=0 && y<height){
            cells[x][y]=type;
        }
    }
    
    private int findColor(Color c){
        for (int i=0;i<cellColors.length;i++){
            if (cellColors[i].equals(c))
                return i;
        }
        return 0;
    }
    
    public Color getCellType(int x, int y){
        if (x>=0 && x<width && y>=0 && y<height){
            return cellColors[cells[x][y]];
        }
        return cellColors[1];
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public boolean hasLeftWall(int x, int y){
        if (x>=1 && x<=width-1){
            return (cells[x-1][y]==1);
        }
        else{
            return true;
        }
    }
    
    public boolean hasRightWall(int x, int y){
        if (x>=0 && x<=width-2){
            return (cells[x+1][y]==1);
        }
        else{
            return true;
        }
    }
    
    public boolean hasUpWall(int x, int y){
        if (y>=1 && y<=height-1){
            return (cells[x][y-1]==1);
        }
        else{
            return true;
        }
    }
    
    public boolean hasDownWall(int x, int y){
        if (y>=0 && y<=height-2){
            return (cells[x][y+1]==1);
        }
        else{
            return true;
        }
    }
    
    //Вес клетки
    public int getWeight(int x, int y){
        int weight;
        switch(cells[x][y]){
            case 1:
                //weight=getCellCount()+1; //Если клетка непроходимая, она имеет очень большой вес
                //weight=Double.POSITIVE_INFINITY;
                weight=-1;
                break;
            case 3:
                weight=3; //Труднопроходимая клетка
                break;
            default:
                weight=1;
        }
        return weight;
    }
    
    //Количество клеток
    public int getCellCount(){
        return width*height;
    }
            
    //Очистить поле
    public final void clear(){
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                cells[i][j]=0;
                }
            }
    }
    
}
