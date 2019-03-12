package com.savenko.tictactoe;

import java.util.Scanner;

public class GameLogic {

    private static final String GREETING ="Привет. Вы играете крестиками. Компьютер будет играть ноликами";
    private static final String  GAME_RULES_1 = "Вот игровое поле, нужно вводить координаты ячейки, куда будете ставить Х .";
    private static final String  GAME_RULES_2 = "Координаты ячейки вводить цифрами от 0 до 2 через пробел, затем нажать enter.";

    public static int MOVE = 0;//счетчик ходов, возможно достаточно private?

    Field f; // ссылка на игровое поле

    public GameLogic(){
        f = new Field(); //создаем игровое поле
        System.out.println(GREETING); // выводим сообщения перед стартом
        System.out.println(GAME_RULES_1);
        System.out.println(GAME_RULES_2);
        f.drawField();//рисуем игровое поле


    }

    public void playGame(){
     for(;GameLogic.MOVE<9;)//проверка на кол-во ходов меньше 9
        {

            if(humanMove()) {    //ход игрока  + проверка выигрышной комбинации
                System.out.println("Выиграли крестики");
                break;
            }


            if(pcMove()){  //ход компьютера + проверка выигрышной комбинации
                System.out.println("Выиграли нолики");
                break;
            }

        }



    }

    private int[] getCoordinates(){  // считываем координаты с клавиатуры
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        return new int[]{x, y};
}

    private boolean humanMove(){//ход игрока

        System.out.println("Сейчас Ваш ход");
        System.out.println("Введите коорд пустой клетки в виде пары чисел, разделенных пробелом");

         int[] xy;

                 do {xy = getCoordinates();
                 if(f.getItem(xy[0], xy[1]) != -1){
                     System.out.println("эта ячейка занята. Попытайтесь еще");
                 }
                 }
            while (f.getItem(xy[0], xy[1]) != -1);

            f.setItem(xy[0], xy[1], Field.CROSS);
        ++GameLogic.MOVE;
            f.drawField();
            return checkWinner();
    }

    private boolean pcMove() {

        int x, y;
        do {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
        }
        while (f.getItem(x, y) != -1);


        System.out.println();
        System.out.println("PC moves:");
        f.setItem(x, y, Field.ZERO);
        ++GameLogic.MOVE;
        f.drawField();
        return checkWinner();

    }

    private boolean checkWinner() {

        //int[] checksum = new int[9];
        int[][] board = f.getBoard();
        int diag = 0;//сумма в гл диагонали
        int otherdiag = 0;//сумма в другой диагонали

        for (int i = 0; i < 3; i++) {

            int sum = 0;// сумма значений в столбцах
            int str = 0;// сумма значений в строках

            for (int j = 0; j < 3; j++) {
                sum+= board[j][i];//сумма столбцов
                str+= board[i][j];//сумма строк
                if (i == j ) diag+= board[i][j];//сумма главной диагонали
                if (i + j == 2) otherdiag+= board[i][j];// сумма другой диагонали

            }

            if((sum == 9)||(sum==0)||(str==9)||(str==0))
                return true;
        }
        if((diag==9)||(diag==0)||(otherdiag==9)||(otherdiag==0)) return true;

        return false;
    }







/*
-2 создаем доску
-1 отрисовываем доску
выводим сообщение, что первыми ходят крестики,
в цикле:
0.  просим ввести координаты ячейки для крестика
1. принимаем с клавиатуры координаты, проверяем, что ячейка пуста, ставим крестик. Если непуста, сообщаем об это и шаг 1.
2. отрисовываем доску
3.  просим ввести координаты ячейки для крестика
4. принимаем с клавиатуры координаты, проверяем, что ячейка пуста, ставим крестик. Если непуста, сообщаем об это и шаг 3.
5. отрисовываем доску.
6. проверяем доску на выигрыш. если да то выводим кто выиграл. переходим к шагу 8
7 проверяем доску на количество свободных мест. если их нет и выигрыш не состоялся, то выводим сообщение о ничье
8. спрашиваем, играть ли заново, если да, то на шаг "-2", если нет, то выход


  */

}
