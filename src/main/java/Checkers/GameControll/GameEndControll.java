package Checkers.GameControll;

import Checkers.CheckersApp;

import static Checkers.CheckersApp.board;

public class GameEndControll {

    public static void onEndGameLogic(){

        for(int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++){
                board[i][j].setPiece(null);
            }
        }
        CheckersApp.pieceGroup.getChildren().clear();
    }
}
