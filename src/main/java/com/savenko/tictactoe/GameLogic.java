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

    private boolean humanMove(){//ход игрока + проверка на выигрыш

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
            return checkWin(); // проверка на выигрыш
    }

    private boolean pcMove() {//ход компьютера + проверка на выигрыш

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
        return checkWin();//проверка на выигрыш

    }

    private boolean checkWin(){//проверка на выигрыш

        return checkRow()||checkCol()||checkDiags();

        }

    private boolean checkCol(){// проверка комбинации в колонках
        int[][] board = f.getBoard();

        for (int i = 0; i < 3; i++) {
            int sum = 0;

            for (int j = 0; j < 3; j++) {
                sum+= board[j][i];//сумма столбцов
            }

            if (((sum == 9) || (sum == 0))) return true;
        }
        return false;
    }

    private boolean checkRow(){// проверка в рядах

        int[][] board = f.getBoard();

        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum+= board[i][j];//сумма строк
            }
        if (((sum == 9) || (sum == 0))) return true;
        }
       return false;
    }

    private boolean checkDiags(){// проверка в диагоналях

        int[][] board = f.getBoard();
        int diag = 0;
        int otherdiag = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j ) diag+= board[i][j];//сумма главной диагонали
                if (i + j == 2) otherdiag+= board[i][j];// сумма другой диагонали
            }
        }

        if((diag==9)||(diag==0)||(otherdiag==9)||(otherdiag==0)) return true;
        return false;
    }

}
