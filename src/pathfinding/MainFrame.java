/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static java.lang.Thread.sleep;
import javax.swing.JToggleButton;

/**
 *
 * @author alexey
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    final Core core; //Все методы логики
    int cellSize = 10; //Размер клетки
    final Color borderColor = Field.colorGrey;
    final JToggleButton[] buttons = new JToggleButton[6];
    int buttonNumber = 0;
    boolean mousePressed = false;
    Thread waveThread=null;

    public MainFrame(Core parent) {
        initComponents();
        core = parent;
        buttonsToArray();
        enableCellTypes(false);
        buttonSave.setEnabled(false);
        buttonRepaint.setEnabled(false);
        visualCheckBox.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        canvas1 = new java.awt.Canvas();
        jToolBar1 = new javax.swing.JToolBar();
        buttonNew = new javax.swing.JButton();
        buttonOpen = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonRepaint = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        freeButton = new javax.swing.JToggleButton();
        obstacleButton = new javax.swing.JToggleButton();
        doorButton = new javax.swing.JToggleButton();
        difficultButton = new javax.swing.JToggleButton();
        startButton = new javax.swing.JToggleButton();
        finnishButton = new javax.swing.JToggleButton();
        graphButton = new javax.swing.JButton();
        waysButton = new javax.swing.JButton();
        graphRadioButton = new javax.swing.JRadioButton();
        waveRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        fieldButton = new javax.swing.JButton();
        heightEdit = new javax.swing.JFormattedTextField();
        widthEdit = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cellSizeEdit = new javax.swing.JFormattedTextField();
        visualCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        canvas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                canvas1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                canvas1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvas1MouseClicked(evt);
            }
        });
        canvas1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                canvas1MouseDragged(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        buttonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/new.png"))); // NOI18N
        buttonNew.setToolTipText("Новый лабиринт");
        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonNew);

        buttonOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/open.png"))); // NOI18N
        buttonOpen.setToolTipText("Открыть лабиринт");
        buttonOpen.setFocusable(false);
        buttonOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonOpen);

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/save.png"))); // NOI18N
        buttonSave.setToolTipText("Сохранить лабиринт");
        buttonSave.setFocusable(false);
        buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonSave);

        buttonRepaint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/repaint.png"))); // NOI18N
        buttonRepaint.setToolTipText("Заново нарисовать лабиринт");
        buttonRepaint.setFocusable(false);
        buttonRepaint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonRepaint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonRepaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRepaintActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonRepaint);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        freeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/free.png"))); // NOI18N
        freeButton.setSelected(true);
        freeButton.setToolTipText("Свободное пространство");
        freeButton.setFocusable(false);
        freeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        freeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        freeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freeButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(freeButton);

        obstacleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/obstacle.png"))); // NOI18N
        obstacleButton.setToolTipText("Стена (препятствие)");
        obstacleButton.setFocusable(false);
        obstacleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        obstacleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        obstacleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obstacleButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(obstacleButton);

        doorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/door.png"))); // NOI18N
        doorButton.setToolTipText("Дверной проём");
        doorButton.setFocusable(false);
        doorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doorButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(doorButton);

        difficultButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/difficult.png"))); // NOI18N
        difficultButton.setToolTipText("Труднопроходимое место");
        difficultButton.setFocusable(false);
        difficultButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        difficultButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        difficultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difficultButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(difficultButton);

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/start.png"))); // NOI18N
        startButton.setToolTipText("Начальная точка");
        startButton.setFocusable(false);
        startButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(startButton);

        finnishButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pathfinding/icons/finnish.png"))); // NOI18N
        finnishButton.setToolTipText("Конечная точка");
        finnishButton.setFocusable(false);
        finnishButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        finnishButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        finnishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finnishButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(finnishButton);

        graphButton.setText("Построить граф");
        graphButton.setEnabled(false);
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        waysButton.setText("Найти пути");
        waysButton.setEnabled(false);
        waysButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waysButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(graphRadioButton);
        graphRadioButton.setSelected(true);
        graphRadioButton.setText("С использованием графа");
        graphRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(waveRadioButton);
        waveRadioButton.setText("Волновой алгоритм");
        waveRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waveRadioButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Размер поля:");

        fieldButton.setText("Построить поле");
        fieldButton.setEnabled(false);
        fieldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldButtonActionPerformed(evt);
            }
        });

        heightEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        heightEdit.setText("40");

        widthEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        widthEdit.setText("65");

        jLabel2.setText("Ширина");

        jLabel3.setText("Высота");

        jLabel4.setText("Размер ячейки");

        cellSizeEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        cellSizeEdit.setText("10");

        visualCheckBox.setText("Визуализация волны");
        visualCheckBox.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(widthEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(heightEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cellSizeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(graphButton)
                            .addComponent(waysButton)
                            .addComponent(fieldButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(visualCheckBox)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(graphRadioButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(waveRadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(graphButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(waysButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(widthEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(heightEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldButton)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(cellSizeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(graphRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(waveRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(visualCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewActionPerformed
        int[] size = getFieldParam();
        core.newField(size[0], size[1]);
        clearField();
        enableCellTypes(true);
        buttonSave.setEnabled(true);
        buttonRepaint.setEnabled(true);
    }//GEN-LAST:event_buttonNewActionPerformed

    private void buttonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenActionPerformed
        boolean result = core.openFile();
        if (result) {
            enableCellTypes(true);
            buttonSave.setEnabled(true);
            buttonRepaint.setEnabled(true);
            cellSize = Integer.parseInt(cellSizeEdit.getText());
            int w = core.getFieldWidth();
            int h = core.getFieldHeight();
            widthEdit.setText(String.valueOf(w));
            heightEdit.setText(String.valueOf(h));
            canvas1.setSize(w * cellSize - 1, h * cellSize - 1);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(30);
                        core.drawField();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                }
            };
            thread.start();
        }

    }//GEN-LAST:event_buttonOpenActionPerformed

    private void freeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freeButtonActionPerformed
        buttonSelected(0);
    }//GEN-LAST:event_freeButtonActionPerformed

    private void obstacleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obstacleButtonActionPerformed
        buttonSelected(1);
    }//GEN-LAST:event_obstacleButtonActionPerformed

    private void difficultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difficultButtonActionPerformed
        buttonSelected(3);
    }//GEN-LAST:event_difficultButtonActionPerformed

    private void finnishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finnishButtonActionPerformed
        buttonSelected(5);
    }//GEN-LAST:event_finnishButtonActionPerformed

    private void canvas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvas1MousePressed
        mousePressed = true;
    }//GEN-LAST:event_canvas1MousePressed

    private void canvas1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvas1MouseReleased
        mousePressed = false;
    }//GEN-LAST:event_canvas1MouseReleased

    private void canvas1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvas1MouseDragged
        if (!mousePressed) {
            return;
        }
        if (evt.getX() > canvas1.getWidth() || evt.getY() > canvas1.getHeight()) {
            return;
        }
        canvas1MouseClicked(evt);
    }//GEN-LAST:event_canvas1MouseDragged

    private void canvas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvas1MouseClicked
        if (!core.fieldExists()) {
            return;
        }
        int coord[] = getXY(evt.getX(), evt.getY());
        core.writeCell(coord[0], coord[1]);
        if (buttonNumber == 2) {

        }
        fillCell(coord[0], coord[1], Field.cellColors[buttonNumber]);
    }//GEN-LAST:event_canvas1MouseClicked


    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        core.saveToFile();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        buttonSelected(4);
    }//GEN-LAST:event_startButtonActionPerformed

    private void buttonRepaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRepaintActionPerformed
        core.removeGraph();
        canvas1.setSize(core.getFieldWidth() * cellSize - 1, core.getFieldHeight() * cellSize - 1);
        formWindowStateChanged(null);
        core.drawField();
    }//GEN-LAST:event_buttonRepaintActionPerformed

    private void doorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doorButtonActionPerformed
        buttonSelected(2);
    }//GEN-LAST:event_doorButtonActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        core.createGraph();
    }//GEN-LAST:event_graphButtonActionPerformed

    private void waysButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waysButtonActionPerformed
        if (waveThread!=null){
            waveThread.stop();
            waveThread=null;
        }
        long startTime=System.currentTimeMillis();
        if (graphRadioButton.isSelected()) {
            core.findWay();
        } else {
            boolean visualize = visualCheckBox.isSelected();
            
            if (visualize){
                waveThread=new Thread(){
                    @Override
                    public void run(){
                        core.findCellWay(true);
                    }
                };
                waveThread.start();
                return;
            }
            else{
                core.findCellWay(false);
            }
        }
        long stopTime=System.currentTimeMillis();
        //System.out.print("Время поиска: ");
        //System.out.println(stopTime-startTime);
    }//GEN-LAST:event_waysButtonActionPerformed

    private void fieldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldButtonActionPerformed
        int[] size = getFieldParam();
        core.changeFieldSize(size[0], size[1]);
        //core.drawField();
    }//GEN-LAST:event_fieldButtonActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(30);
                    core.drawField();
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        };
        thread.start();
    }//GEN-LAST:event_formWindowStateChanged

    private void waveRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waveRadioButtonActionPerformed
        visualCheckBox.setEnabled(true);
        System.out.println();
    }//GEN-LAST:event_waveRadioButtonActionPerformed

    private void graphRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphRadioButtonActionPerformed
        visualCheckBox.setEnabled(false);
        System.out.println();
    }//GEN-LAST:event_graphRadioButtonActionPerformed

    private int[] getFieldParam() {
        cellSize = Integer.parseInt(cellSizeEdit.getText());
        int w = Integer.parseInt(widthEdit.getText());
        int h = Integer.parseInt(heightEdit.getText());
        canvas1.setSize(w * cellSize - 1, h * cellSize - 1);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(50);
                    core.drawField();
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        };
        thread.start();
        return new int[]{w, h};
    }

    //Очистить поле, нарисовать серую сетку
    public void clearField(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) canvas1.getGraphics();
        g.setPaint(Color.WHITE);
        int w = width * cellSize;
        int h = height * cellSize;
        g.fillRect(x * cellSize, y * cellSize, w, h);
        int iw = (width + x) * cellSize;
        int ih = (height + y) * cellSize;
        g.setColor(borderColor);
        //Рисуем строки
        for (int i = (y + 1) * cellSize - 1; i <= ih; i += cellSize) {
            g.drawLine(x * cellSize, i, x * cellSize + w - 1, i);
        }
        //Рисуем столбцы
        for (int i = (x + 1) * cellSize - 1; i <= iw; i += cellSize) {
            g.drawLine(i, y * cellSize, i, y * cellSize + h - 1);
        }
    }

    public void clearField() {
        int w = canvas1.getWidth() / cellSize + 1;
        int h = canvas1.getHeight() / cellSize + 1;
        clearField(0, 0, w, h);
    }

    //Получить координаты ячейки по позиции мыши
    private int[] getXY(int mouseX, int mouseY) {
        int coord[] = new int[2];
        coord[0] = mouseX / cellSize;
        coord[1] = mouseY / cellSize;
        return coord;
    }

    //Все кнопки - элементы массива
    private void buttonsToArray() {
        buttons[0] = freeButton;
        buttons[1] = obstacleButton;
        buttons[2] = doorButton;
        buttons[3] = difficultButton;
        buttons[4] = startButton;
        buttons[5] = finnishButton;
    }

    //Нажатие на кнопку на панели инструментов
    private void buttonSelected(int b) {
        buttons[b].setSelected(true);
        core.setInputType(b);
        for (int i = 0; i <= buttons.length - 1; i++) {
            if (i != b) {
                buttons[i].setSelected(false);
            }
        }
        buttonNumber = b;
    }

    public void fillCell(int x, int y, Color color) {
        Graphics2D g = (Graphics2D) canvas1.getGraphics();
        g.setPaint(color);
        int startX = x * cellSize;
        int startY = y * cellSize;
        if (color == Color.orange || color == Color.magenta) {
            g.fillOval(startX, startY, cellSize - 1, cellSize - 1);
            return;
        }
        if (color == Color.white) {
            clearField(x, y, 1, 1);
            return;
        }
        g.fillRect(startX, startY, cellSize, cellSize);
    }

    private void enableCellTypes(boolean enable) {
        for (int i = 0; i <= buttons.length - 1; i++) {
                buttons[i].setEnabled(enable);
        }
        graphButton.setEnabled(enable);
        waysButton.setEnabled(enable);
        fieldButton.setEnabled(enable);
    }

    public void drawNumber(int x, int y, int number) {
        int xs = x * cellSize;
        int ys = (y + 1) * cellSize;
        Graphics2D g = (Graphics2D) canvas1.getGraphics();
        g.setPaint(Color.black);
        Font font = new Font("Times New Roman", Font.PLAIN, cellSize);
        g.setFont(font);
        g.drawString(String.valueOf(number), xs, ys);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonOpen;
    private javax.swing.JButton buttonRepaint;
    private javax.swing.JButton buttonSave;
    private java.awt.Canvas canvas1;
    private javax.swing.JFormattedTextField cellSizeEdit;
    private javax.swing.JToggleButton difficultButton;
    private javax.swing.JToggleButton doorButton;
    private javax.swing.JButton fieldButton;
    private javax.swing.JToggleButton finnishButton;
    private javax.swing.JToggleButton freeButton;
    private javax.swing.JButton graphButton;
    private javax.swing.JRadioButton graphRadioButton;
    private javax.swing.JFormattedTextField heightEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToggleButton obstacleButton;
    private javax.swing.JToggleButton startButton;
    private javax.swing.JCheckBox visualCheckBox;
    private javax.swing.JRadioButton waveRadioButton;
    private javax.swing.JButton waysButton;
    private javax.swing.JFormattedTextField widthEdit;
    // End of variables declaration//GEN-END:variables
}
