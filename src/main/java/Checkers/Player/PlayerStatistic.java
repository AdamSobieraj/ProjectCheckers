package Checkers.Player;

import Checkers.BoardElements.PieceType;

public class PlayerStatistic {

    private static int whiteKillCount;
    private static int blackKillCount;
    private static int whiteMoveCount;
    private static int blackMoveCount;
    private static PieceType currentPlayerTurn;
    private static int turnCounter =0;

    public static int getTurnCounter() {
        return turnCounter;
    }
    public static int getWhiteKillCount() {
        return whiteKillCount;
    }

    public static int getBlackKillCount() {
        return blackKillCount;
    }

    public static int getWhiteMoveCount() {
        return whiteMoveCount;
    }

    public static int getBlackMoveCount() {
        return blackMoveCount;
    }

    public static PieceType getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public static void setWhiteKillCount(int whiteKillCount) {
        PlayerStatistic.whiteKillCount = whiteKillCount;
    }

    public static void setBlackKillCount(int blackKillCount) {
        PlayerStatistic.blackKillCount = blackKillCount;
    }

    public static void setWhiteMoveCount(int whiteMoveCount) {
        PlayerStatistic.whiteMoveCount = whiteMoveCount;
    }

    public static void setBlackMoveCount(int blackMoveCount) {
        PlayerStatistic.blackMoveCount = blackMoveCount;
    }

    public static void setCurrentPlayerTurn(PieceType currentPlayerTurn) {
        PlayerStatistic.currentPlayerTurn = currentPlayerTurn;
    }

    public static void setTurnCounter(int turnCounter) {
        PlayerStatistic.turnCounter = turnCounter;
    }

    public static void turnLogic(){
        if(turnCounter == 0){
            currentPlayerTurn = PieceType.WHITE;
            turnCounter++;
        }else if (turnCounter>0 && currentPlayerTurn.equals(PieceType.WHITE)){
            currentPlayerTurn = PieceType.BLACK;
            turnCounter++;
        }else if (turnCounter>0 && currentPlayerTurn.equals(PieceType.BLACK)){
            currentPlayerTurn = PieceType.WHITE;
            turnCounter++;
        }
    }

    public static String displayCurrentTurn(){
        String turn;

        if ( turnCounter==0 ){
            turn = "WHITE";
        }else if (currentPlayerTurn.equals(PieceType.WHITE) && turnCounter >0) {
            turn = "WHITE";
        }
        else {
            turn = "BLACK";
        }
        return turn;
    }

}
