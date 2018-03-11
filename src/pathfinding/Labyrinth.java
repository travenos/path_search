/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import java.util.ArrayList;

/**
 *
 * @author alexey
 */
public class Labyrinth implements Cloneable {

    private ArrayList<Corridor> corridors = new <Corridor>ArrayList();
    double[][] matrix = new double[0][0];

    @Override
    public Labyrinth clone() throws CloneNotSupportedException {
        Labyrinth obj;
        obj = (Labyrinth) super.clone();
        obj.corridors = (ArrayList<Corridor>) this.corridors.clone();
        obj.matrix = (double[][]) this.matrix.clone();
        return obj;
    }

    public int size() {
        return corridors.size();
    }

    public boolean isEmpty() {
        return corridors.isEmpty();
    }

    //Добавить коридор
    //public void add(int xStart, int yStart, int xFinish, int yFinish) {
    public void add(ArrayList<int[]> coordArray) {
        //Создание связей между коридорами
        int xStart = coordArray.get(0)[0];
        int yStart = coordArray.get(0)[1];
        int xFinish = coordArray.get(coordArray.size() - 1)[0];
        int yFinish = coordArray.get(coordArray.size() - 1)[1];
        int N = intercectNumber();
        int start = N;
        int finish = N;
        for (int i = 0; i <= size() - 1; i++) {
            int x = getCorridor(i).getXstart();
            int y = getCorridor(i).getYstart();
            if (x == xStart && y == yStart) {
                start = getCorridor(i).getStartPoint();
            }
            if (x == xFinish && y == yFinish) {
                finish = getCorridor(i).getStartPoint();
            }
            x = getCorridor(i).getXfinish();
            y = getCorridor(i).getYfinish();
            if (x == xStart && y == yStart) {
                start = getCorridor(i).getFinishPoint();
            }
            if (x == xFinish && y == yFinish) {
                finish = getCorridor(i).getFinishPoint();
            }
        }
        if (start == finish && finish == N) {
            finish++;
        }
        Corridor cor = new Corridor(coordArray, start, finish);
        corridors.add(cor);
        if (start >= N || finish >= N) {
            int newN = N;
            if (start >= N) {
                newN++;
            }
            if (finish >= N) {
                newN++;
            }
            double[][] matrix2 = new double[newN][newN];
            for (int i = 0; i < N; i++) {
                System.arraycopy(matrix[i], 0, matrix2[i], 0, N);
                matrix2[N][i] = Double.POSITIVE_INFINITY;
                matrix2[i][N] = Double.POSITIVE_INFINITY;
                if (newN - N == 2) {
                    matrix2[N + 1][i] = Double.POSITIVE_INFINITY;
                    matrix2[i][N + 1] = Double.POSITIVE_INFINITY;
                }
            }
            matrix = matrix2;
        }
        matrix[start][finish] = cor.getWeight();
        matrix[finish][start] = matrix[start][finish];
    }

    //Удалить последний коридор    
    public void removeLast() {
        int N = size() - 1;
        if (N >= 0) {/*
             for (int i = 0; i <= N - 1; i++) {
             getCorridor(i).removeNeighbour(N);
             }*/

            corridors.remove((int) N);
        }
    }

    public void removePoint(int p) {
        Corridor cor;
        for (int j = 0; j <= size() - 1; j++) {
            cor = getCorridor(j);
            if (cor.getStartPoint() > p) {
                cor.setStartPoint(cor.getStartPoint() - 1);
            }
            if (cor.getFinishPoint() > p) {
                cor.setFinishPoint(cor.getFinishPoint() - 1);
            }
        }
    }

    //Количество точек начала-окончания коридоров
    public int intercectNumber() {
        return matrix.length;
    }

    //Получить i-й коридор
    public Corridor getCorridor(int i) {
        return corridors.get(i);
    }

    public int[] getStartCoord(int i) {
        int[] coord = new int[2];
        coord[0] = getCorridor(i).getXstart();
        coord[1] = getCorridor(i).getYstart();
        return coord;
    }

    public int[] getFinishCoord(int i) {
        int[] coord = new int[2];
        coord[0] = getCorridor(i).getXfinish();
        coord[1] = getCorridor(i).getYfinish();
        return coord;
    }

    //Поиск наикратчайшего пути при помощи алгоритма Дейкстры
    public ArrayList<int[]> findShortestWay(int from, int to) {
        int N = matrix.length;
        Waybill bill = new Waybill();
        if (from < 0 || from >= N || to < 0 || to >= N) {  //Проверка корректности аргументов
            return null;
        }

        if (from == to) {
            ArrayList<int[]> task = new <int[]>ArrayList();
            return task;
        }

        int curV = from; //Вершина, в которой мы находимся в данный момент
        double curDist;
        bill.add(curV, 0, -1);

        boolean flag = false;
        for (int j = 0; j <= N - 1; j++) {
            curDist = bill.getDistance(curV);
            for (int i = 0; i <= N - 1; i++) {
                if (Double.isFinite(matrix[curV][i])) { //Если к вершине путь существует
                    double length = curDist + matrix[curV][i];
                    if (bill.findVertex(i) == -1) { //Вершины в списке нет, её нужно добавить
                        bill.add(i, length, curV);
                    } else { //Вершина уже есть
                        if (length < bill.getDistance(i)) { //Если есть более короткий путь
                            bill.changeDist(i, length);
                            ArrayList<Integer> way = bill.getWay(curV);
                            way.add(curV);
                            bill.changeWay(i, way);
                        }
                    }
                }
            }
            bill.setChecked(curV); //Помечаем вершину
            curV = bill.findMinUnchecked(); //Выбираем новую вершину с минимальным весом
            if (curV == to) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.print("ERROR!!! ");
            System.out.print(from);
            System.out.print(" ");
            System.out.println(to);
        }
        return createTask(bill, curV);
    }

    public ArrayList<int[]> findShortestWay(int[] fromCoord, int[] toCoord) {
        int start = getPoint(fromCoord[0], fromCoord[1]);
        int finish = getPoint(toCoord[0], toCoord[1]);
        return findShortestWay(start, finish);
    }

    //Получить коридор, имеющий начало в start и конец в finish
    public Corridor connects(int start, int finish) {
        int i = connectsN(start, finish);
        if (i < 0) {
            return null; //Подходящий коридор не найден
        }
        return getCorridor(i);
    }

    private int connectsN(int start, int finish) {
        Corridor cor;
        for (int i = 0; i <= size() - 1; i++) {
            cor = getCorridor(i);
            if (cor.getStartPoint() == start && cor.getFinishPoint() == finish) {
                return i;
            }
        }
        return -1; //Подходящий коридор не найден
    }

    //Представление графа в виде матрицы смежности. Вершины - перекрёстки, дуги - коридоры
   /* public void createMatr() { //Потом сделать private
     int N = intercectNumber();
     matrix = new double[N][N]; //Матрица смежности
     Corridor cor;
     for (int i = 0; i <= N - 1; i++) {
     for (int j = 0; j <= N - 1; j++) {
     cor = connects(i, j);
     if (cor != null) {
     matrix[i][j] = cor.getWeight();
     } else {
     cor = connects(j, i);
     if (cor != null) {
     matrix[i][j] = cor.getWeight();
     } else {
     matrix[i][j] = Double.POSITIVE_INFINITY;
     }
     }
     }
     }
     }*/
    //Создать путь
    private ArrayList<int[]> createTask(Waybill bill, int v) {
        ArrayList<int[]> task = new <int[]>ArrayList();
        ArrayList<Integer> arr = (ArrayList<Integer>) bill.getWay(v).clone();
        arr.add(v);
        Corridor cor;
        boolean direction;
        for (int i = 1; i <= arr.size() - 2; i++) {

            cor = connects(arr.get(i), arr.get(i + 1));
            direction = true;
            if (cor == null) {
                cor = connects(arr.get(i + 1), arr.get(i));
                direction = false;
            }
            if (direction) {
                for (int j = 1; j < cor.getLength() - 1; j++) {
                    task.add(cor.getCell(j));
                }
            } else {
                for (int j = cor.getLength() - 2; j > 0; j--) {
                    task.add(cor.getCell(j));
                }
            }

        }
        return task;
    }

    //Получить номер вершины с координатами x, y
    public int getPoint(int x, int y) {
        for (int i = 0; i <= size() - 1; i++) {
            if (getCorridor(i).getXstart() == x && getCorridor(i).getYstart() == y) {
                return getCorridor(i).getStartPoint();
            }
            if (getCorridor(i).getXfinish() == x && getCorridor(i).getYfinish() == y) {
                return getCorridor(i).getFinishPoint();
            }
        }
        return -1;
    }

    public void clear() {
        corridors.clear();
    }
}
