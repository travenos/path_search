/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author alexey
 */
public class Core {

    public MainFrame mainFrame; //Главная форма
    private Field field = null; //Поле
    private Labyrinth graph = null; //Построенный граф путей к целям
    private Labyrinth way = null;   //Граф с путями к целям
    private ArrayList<ArrayList<int[]>> wayPoints = null; //Список клеток, входящих в пути
    //private Color cellType;
    private int inputType = 0;  //Тип добавляемого объекта
    private ArrayList<int[]> visitedDoors = null;   //Список уже посещённых дверей

    //Поля для волнового алгоритма
    int cells[][];

    private final int sleepTime = 200; //Время сна перед отрисовкой при визуализации

    //Конструктор по умолчанию
    public Core() {
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);

    }

    //Сгенерировать новый лабиринт
    public void newField(int w, int h) {
        field = new Field(w, h);
        cells = new int[w][h];
        graph = null;
        way = null;
        wayPoints = null;
    }

    //Изменить размер поля
    public void changeFieldSize(int w, int h) {
        if (!this.fieldExists()){
            this.newField(w, h);
            return;
        }
        field.changeSize(w, h);
        cells = new int[w][h];
        graph = null;
        way = null;
        System.gc();
    }

    //Задать тип вводимого параметра
    public void setInputType(int t) {
        inputType = t;
    }

    //Добавить в лабиринт стену или объект
    public void writeCell(int x, int y) {
        field.setCellType(x, y, inputType);
    }

    //Сохранить поле в файл
    public void saveToFile() {
        JFileChooser saveDial = new JFileChooser();
        if (saveDial.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            File file = saveDial.getSelectedFile();
            try {
                ObjectOutputStream fin = new ObjectOutputStream(new FileOutputStream(file));
                fin.writeObject(field);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Загрузить поле из файла
    public boolean openFile() {
        JFileChooser openDial = new JFileChooser();
        boolean success = false;
        if (openDial.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            File file = openDial.getSelectedFile();
            try {
                ObjectInputStream fout = new ObjectInputStream(new FileInputStream(file));
                field = (Field) fout.readObject();
                //drawField();
                cells = new int[field.getWidth()][field.getHeight()];
                success = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return success;
    }

    //Удалить все построенные графы
    public void removeGraph() {
        graph = null;
        way = null;
        wayPoints = null;
        System.gc();
    }

    //Нарисовать на канве поле
    public void drawField() {
        if (field == null) {
            return;
        }
        drawField(0, 0, field.getWidth(), field.getHeight());
    }

    public void drawField(int x, int y, int width, int height) {
        if (field == null) {
            return;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (width > field.getWidth()) {
            width = field.getWidth();
        }
        if (height > field.getHeight()) {
            height = field.getHeight();
        }
        mainFrame.clearField(x, y, width, height);
        if (way != null) {
            drawGraph(x, y, width, height, way);
        } else if (graph != null) {
            drawGraph(x, y, width, height, graph);
        }
        if (wayPoints != null) {
            drawWay(x, y, width, height);
        }
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (field.getCellType(i, j) != Color.white) {
                    mainFrame.fillCell(i, j, field.getCellType(i, j));
                }
            }
        }
    }

    //Нанести на канву построенный граф
    private void drawGraph(int x, int y, int width, int height, Labyrinth labyrinth) {
        for (int i = 0; i < labyrinth.size(); i++) {
            Corridor cor = labyrinth.getCorridor(i);
            for (int j = 1; j < cor.getLength() - 1; j++) {
                int[] coord = cor.getCell(j);
                if (coord[0] >= x && coord[1] >= y && coord[0] < x + width && coord[1] < y + height) {
                    mainFrame.fillCell(coord[0], coord[1], Color.yellow);
                }
            }
        }
    }

    //Нанести на канву построенный путь
    private void drawWay(int x, int y, int width, int height) {
        ArrayList<int[]> currentWay;
        for (int i = 0; i < wayPoints.size(); i++) {
            currentWay = wayPoints.get(i);
            if (currentWay != null) {
                for (int j = 0; j < currentWay.size(); j++) {
                    int[] coord = currentWay.get(j);
                    if (coord[0] >= x && coord[1] >= y && coord[0] < x + width && coord[1] < y + height) {
                        mainFrame.fillCell(coord[0], coord[1], Color.green);
                    }
                }
            }
        }
    }

    //Сгенерировано ли поле?
    public boolean fieldExists() {
        boolean exists = (field != null);
        return exists;
    }

    //Построить граф
    public void createGraph() {
        //for (int ooo=0;ooo<51;ooo++){
        if (field == null) {
            return;
        }
        way = null;
        visitedDoors = new <int[]>ArrayList();
        graph = new Labyrinth();
        System.gc();
        
        //long startTime=System.currentTimeMillis();
        int counter = 0;
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                if (field.getCellType(i, j) == Field.colorGrey) {//Если точка является дверью
                    if (counter != 0) { //Первую дверь проверять не обязательно
                        visitedDoors.add(new int[]{i, j});
                        createBranches(i, j, graph, true);
                    }
                    counter++;
                }
            }
        }
        //this.drawField();
        //long stopTime=System.currentTimeMillis();
        //System.out.print(stopTime-startTime);
        //System.out.print(", ");
        
        visitedDoors.clear();
        drawField();
        //System.out.println("Graph is built!");
        //}
    }

    //Поиск пути от данной двери до всех остальных дверей помещения
    private void createBranches(int x, int y, Labyrinth labyrinth, boolean onlyToDoors) {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                cells[i][j] = -1;
            }
        }
        int stepNumber = 0;
        cells[x][y] = stepNumber;
        int a[][];
        ArrayList<int[]> newCells = new <int[]>ArrayList();
        ArrayList<int[]> currentCells = new <int[]>ArrayList();
        ArrayList<int[]> newNewCells;
        currentCells.add(new int[]{x, y, field.getWeight(x, y)});
        do {
            //Переход к следующим ячейкам
            for (int j = 0; j < currentCells.size(); j++) {
                int X = currentCells.get(j)[0];
                int Y = currentCells.get(j)[1];
                //mainFrame.drawNumber(X, Y, cells[X][Y]);//Для отладки

                a = new int[4][2];
                a[0][0] = X - 1;
                a[0][1] = Y; //Слева
                a[1][0] = X + 1;
                a[1][1] = Y; //Справа
                a[2][0] = X;
                a[2][1] = Y + 1; //Снизу
                a[3][0] = X;
                a[3][1] = Y - 1; //Сверху
                for (int i = 0; i < a.length; i++) {
                    Color curC = field.getCellType(a[i][0], a[i][1]);
                    if (curC != Color.black) {
                        if (cells[a[i][0]][a[i][1]] == -1) {
                            int weight = field.getWeight(a[i][0], a[i][1]);
                            cells[a[i][0]][a[i][1]] = stepNumber + weight;
                            if (curC == Field.colorGrey || curC == Color.orange && !onlyToDoors) {
                                if (!isVisitedDoor(a[i][0], a[i][1])) {
                                    addBranch(a[i][0], a[i][1], labyrinth);
                                }
                            } else {
                                int[] b = {a[i][0], a[i][1], weight};
                                newCells.add(b);
                            }
                        }
                    }
                }
            }
            currentCells.clear();
            newNewCells = new <int[]>ArrayList();
            for (int i = 0; i < newCells.size(); i++) {
                newCells.get(i)[2]--;
                if (newCells.get(i)[2] == 0) {
                    currentCells.add(newCells.get(i));
                } else {
                    newNewCells.add(newCells.get(i));
                }
            }
            newCells = newNewCells;
            stepNumber++;
        } while (currentCells.size() > 0 || newCells.size() > 0);
    }

    //Добавить ветвь в граф
    private void addBranch(int x, int y, Labyrinth labyrinth) {
        ArrayList<int[]> list = new <int[]>ArrayList();
        list.add(new int[]{x, y, field.getWeight(x, y)});
        //mainFrame.fillCell(x, y, Color.yellow);//Для отладки
        int step = cells[x][y];
        int a[][] = new int[4][2];
        while (cells[x][y] != 0) {
            a[0][0] = x - 1;
            a[0][1] = y; //Слева
            a[1][0] = x + 1;
            a[1][1] = y; //Справа
            a[2][0] = x;
            a[2][1] = y + 1; //Снизу
            a[3][0] = x;
            a[3][1] = y - 1; //Сверху
            for (int i = 0; i < a.length; i++) {
                if (field.getCellType(a[i][0], a[i][1]) != Color.black) {
                    int curStep = cells[a[i][0]][a[i][1]];
                    if (curStep == step - field.getWeight(x, y)) {
                        x = a[i][0];
                        y = a[i][1];
                        step = curStep;
                        break;
                    }
                }
            }
            list.add(new int[]{x, y, field.getWeight(x, y)});
            //mainFrame.fillCell(x, y, Color.yellow);//Для отладки
        }
        labyrinth.add(list);
    }

    //Посещалась ли уже данная дверь при построении графа?
    private boolean isVisitedDoor(int x, int y) {
        for (int i = 0; i < visitedDoors.size(); i++) {
            int X = visitedDoors.get(i)[0];
            int Y = visitedDoors.get(i)[1];
            if (x == X && y == Y) {
                return true;
            }
        }
        return false;
    }

    //Поиск пути по графу
    public void findWay() {
        //for (int ooo=0;ooo<51;ooo++){
        if (field == null) {
            return;
        }
        if (graph == null) {
            this.createGraph();
        }
        try {
            way = graph.clone();
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        
        ArrayList<int[]> startPoints = new <int[]>ArrayList();
        ArrayList<int[]> finishPoints = new <int[]>ArrayList();
                
        //long startTime=System.currentTimeMillis();
        //Добавляем в граф рёбра, ведущие от начальной и конечной точек
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                //Если точка является началом или концом
                if (field.getCellType(i, j) == Color.magenta || field.getCellType(i, j) == Color.orange) {
                    //visitedDoors.add(new int[]{i, j});
                    if (field.getCellType(i, j) == Color.magenta) {
                        startPoints.add(new int[]{i, j});
                        createBranches(i, j, way, false);
                    } else {
                        finishPoints.add(new int[]{i, j});
                        createBranches(i, j, way, true);
                    }
                }
            }
        }

        wayPoints = new <ArrayList<int[]>>ArrayList();
        for (int i = 0; i < startPoints.size(); i++) {
            for (int j = 0; j < finishPoints.size(); j++) {
                wayPoints.add(way.findShortestWay(startPoints.get(i), finishPoints.get(j)));
            }
        }
        
        //long stopTime=System.currentTimeMillis();
        //System.out.print(stopTime-startTime);
        //System.out.print(", ");
        
        drawField();
        //}
    }

    //Поиск от старта к финишу волновым алгоритмом
    public void findCellWay(boolean visualize) {
        //for (int ooo=0;ooo<51;ooo++){
        wayPoints = new <ArrayList<int[]>>ArrayList();
        
        //long startTime=System.currentTimeMillis();
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                //Если точка является началом
                if (field.getCellType(i, j) == Color.magenta) {
                    waveFind(i, j, visualize);
                }
            }
        }
        
        //long stopTime=System.currentTimeMillis();
        //System.out.print(stopTime-startTime);
        //System.out.print(", ");
        
        drawField();
        //}
    }

    //Поиск конечной точки волновым алгоритмом
    private void waveFind(int x, int y, boolean visualize) {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                cells[i][j] = -1;
            }
        }
        int stepNumber = 0;
        cells[x][y] = stepNumber;
        int a[][];
        ArrayList<int[]> newCells = new <int[]>ArrayList();
        ArrayList<int[]> currentCells = new <int[]>ArrayList();
        ArrayList<int[]> newNewCells;
        currentCells.add(new int[]{x, y, field.getWeight(x, y)});
        do {
            //Переход к следующим ячейкам
            if (visualize) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
            for (int j = 0; j < currentCells.size(); j++) {
                int X = currentCells.get(j)[0];
                int Y = currentCells.get(j)[1];
                if (visualize) {
                    mainFrame.drawNumber(X, Y, cells[X][Y]);//Для визуализации
                }

                a = new int[4][2];
                a[0][0] = X - 1;
                a[0][1] = Y; //Слева
                a[1][0] = X + 1;
                a[1][1] = Y; //Справа
                a[2][0] = X;
                a[2][1] = Y + 1; //Снизу
                a[3][0] = X;
                a[3][1] = Y - 1; //Сверху
                for (int i = 0; i < a.length; i++) {
                    Color curC = field.getCellType(a[i][0], a[i][1]);
                    if (curC != Color.black) {
                        if (cells[a[i][0]][a[i][1]] == -1) {
                            int weight = field.getWeight(a[i][0], a[i][1]);
                            cells[a[i][0]][a[i][1]] = stepNumber + weight;
                            if (curC == Color.orange) {
                                writeWay(a[i][0], a[i][1], visualize);
                                return;
                            } else {
                                int[] b = {a[i][0], a[i][1], weight};
                                newCells.add(b);
                            }
                        }
                    }
                }
            }
            currentCells.clear();
            newNewCells = new <int[]>ArrayList();
            for (int i = 0; i < newCells.size(); i++) {
                newCells.get(i)[2]--;
                if (newCells.get(i)[2] == 0) {
                    currentCells.add(newCells.get(i));
                } else {
                    newNewCells.add(newCells.get(i));
                }
            }
            newCells = newNewCells;
            stepNumber++;
        } while (currentCells.size() > 0 || newCells.size() > 0);
    }

    //Записать путь, полученный при поиске волновым алгоритмом
    private void writeWay(int x, int y, boolean visualize) {
        ArrayList<int[]> list = new <int[]>ArrayList();
        list.add(0, new int[]{x, y, field.getWeight(x, y)});
        if (visualize) {
            try {
                sleep(sleepTime);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
            mainFrame.fillCell(x, y, Color.green);//Для отладки
        }
        int step = cells[x][y];
        int a[][] = new int[4][2];
        while (cells[x][y] != 0) {
            a[0][0] = x - 1;
            a[0][1] = y; //Слева
            a[1][0] = x + 1;
            a[1][1] = y; //Справа
            a[2][0] = x;
            a[2][1] = y + 1; //Снизу
            a[3][0] = x;
            a[3][1] = y - 1; //Сверху
            for (int i = 0; i < a.length; i++) {
                if (field.getCellType(a[i][0], a[i][1]) != Color.black) {
                    int curStep = cells[a[i][0]][a[i][1]];
                    if (curStep == step - field.getWeight(x, y)) {
                        x = a[i][0];
                        y = a[i][1];
                        step = curStep;
                        break;
                    }
                }
            }
            list.add(0, new int[]{x, y, field.getWeight(x, y)});
            if (visualize) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
                mainFrame.fillCell(x, y, Color.green);//Для отладки
            }
        }
        //list.
        wayPoints.add(list);
    }

    //Получить ширину поля
    public int getFieldWidth() {
        if (fieldExists()) {
            return field.getWidth();
        } else {
            return 0;
        }
    }

    //Получить высоту поля
    public int getFieldHeight() {
        if (fieldExists()) {
            return field.getHeight();
        } else {
            return 0;
        }
    }

//Метод main
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Core();
            }
        });
    }
}
