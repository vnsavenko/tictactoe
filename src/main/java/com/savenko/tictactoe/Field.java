package com.savenko.tictactoe;

public class Field {
    public static final int CROSS = 3;
    public static final int ZERO = 0;
    public static final int EMPTY = -1;


    private int board[][] = new int[3][3];

    //Уставновить значение поля с заданными координатами

    //Инициализировать игровую доску (значениями -1)
    public Field() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               board[i][j] = EMPTY;
            }
        }
    }
    //Вставить символ на доску, если его там нет
    public void setItem(int i, int j, int val) {
        board[i][j] = val;
    }

    // Получить символ с доски
    public int getItem(int i, int j){
        return board[i][j];
    }

    // получить доску целиком
    public int[][] getBoard() {
        return board;
    }
//Нарисовать игровое поле

    public void drawField() {

        System.out.println();
        int shift = 1;
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                String c ="";
                switch (board[i][j]) {
                    case EMPTY:
                        c = "" + (shift + j);
                        break;
                    case CROSS:
                        c = "X";
                        break;
                    case ZERO:
                        c = "O";
                        break;
                }
                System.out.print(c + " ");
            }
            shift += 3;
            System.out.println();
        }

    }

    //Проверить наличие выигрышной комбинации
    public boolean checkWinner(){
        //проверить ряды
        checkRows();
        //проверить столбцы
        checkCols();
        //проверить диагонали
        checkDiag();
        System.out.println("ПРоверка выигрыша");
    return true;
    }


//проверка рядов на выигрышн комбинации
    private boolean checkRows() {
        return true;
    }

    //проверка столбцов на выигрышн комбинации
    private boolean checkCols() {
    return true;
    }

    //проверка диагоналей на выигрышн комбинации
    private boolean checkDiag() {
    return true;
    }


}

