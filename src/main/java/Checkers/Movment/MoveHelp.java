package Checkers.Movment;

import javafx.scene.paint.Color;

import Checkers.Player.PlayerStatistic;

import static Checkers.CheckersApp.*;

public class MoveHelp {

    private static int x;
    private static int y;
    private static Color currentOption;

    public static void resetHighlite(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if((i + j)%2!=0){
                    board[i][j].setFill(Color.valueOf("#00303f"));
                }
            }
        }
    }

    public static void highliteAvailibleMove(boolean highlite, int xx, int yy){
            x = xx;
            y = yy;

        if (highlite){
            currentOption = Color.GREEN;
        }else {
            currentOption = Color.valueOf("#00303f");
        }

        if(board[x][y].getPiece().getType().equals(PlayerStatistic.getCurrentPlayerTurn())) {

            if (x==0 && !(y==board.length-1)){
                highliteLogicMoveOnX(false);
            }else if (y==board.length -1&& !(x==0)){
                highliteLogicMoveOnY(true);
            }else if (y==0 && !(x==board.length -1 )){
                highliteLogicMoveOnY(false);
            }else if (x==board.length -1 && !(y==0) ){
                highliteLogicMoveOnX(true);
            }else if (x==0 && y==board.length-1){
                highliteLogicMoveCorner(true);
            }else if (x==board.length-1 && y==0){
                highliteLogicMoveCorner(false);
            }else if (x>0 && x< board.length && y>0 && y<board.length){
                movesInTheMiddle();
            }

        }
    }

    private static void highliteLogicMoveOnX(boolean orientationX){

        int xCondition;
        int yCondition = 1;
        xCondition = (orientationX == true) ? (-1):( 1);

        if ( !board[x + xCondition][y + yCondition].hasPiece()){//empty
            board[x + xCondition][y + yCondition].setFill(currentOption);
        }
        if ( !board[x + xCondition][y - yCondition].hasPiece()){//empty
            board[x + xCondition][y - yCondition].setFill(currentOption);
        }
        if (board[x + xCondition][y + yCondition].hasPiece()){
            if (    !board[x + xCondition][y + yCondition].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                    !board[x + xCondition*2][y + yCondition*2].hasPiece()  ){//black element
                board[x + xCondition*2][y + yCondition*2].setFill(currentOption);
            }
        }
        if (board[x + xCondition][y - yCondition].hasPiece()){
            if (    !board[x + xCondition][y - yCondition].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                    !board[x + xCondition*2][y - yCondition*2].hasPiece()  ){//black element
                board[x + xCondition*2][y - yCondition*2].setFill(currentOption);
            }
        }
        board[x][y].setFill(currentOption);
    }

    private static void highliteLogicMoveOnY(boolean orientationY){

        int xCondition = 1;
        int yCondition;

        yCondition = (orientationY == true) ? (-1):( 1);

        if ( !board[x + xCondition][y + yCondition].hasPiece()){//empty
            board[x + xCondition][y + yCondition].setFill(currentOption);
        }
        if ( !board[x - xCondition][y + yCondition].hasPiece()){//empty
            board[x - xCondition][y + yCondition].setFill(currentOption);
        }
        if (board[x + xCondition][y + yCondition].hasPiece()){
            if (    !board[x + xCondition][y + yCondition].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                    !board[x + xCondition*2][y + yCondition*2].hasPiece()  ){//black element
                board[x + xCondition*2][y + yCondition*2].setFill(currentOption);
            }
        }
        if (board[x - xCondition][y + yCondition].hasPiece()){
            if (    !board[x - xCondition][y + yCondition].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                    !board[x - xCondition*2][y + yCondition*2].hasPiece()  ){//black element
                board[x + xCondition*2][y - yCondition*2].setFill(currentOption);
            }
        }
        board[x][y].setFill(currentOption);
    }

    private static void highliteLogicMoveCorner(boolean orientationUp){

        int xCondition;
        int yCondition;
        xCondition = (orientationUp == true) ? ( 1):(-1);
        yCondition = (orientationUp == true) ? (-1):( 1);

        if ( !board[x + xCondition][y + yCondition].hasPiece()){//empty
            board[x + xCondition][y + yCondition].setFill(currentOption);
        }

        if (board[x + xCondition][y + yCondition].hasPiece()){
            if (    !board[x + xCondition][y + yCondition].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                    !board[x + xCondition*2][y + yCondition*2].hasPiece()  ){//black element
                board[x + xCondition*2][y + yCondition*2].setFill(currentOption);
            }
        }
        board[x][y].setFill(currentOption);
    }

    private static void movesInTheMiddle(){

        for (int i = -1; i <= 1; i=i+2) {
            for (int j = -1; j <= 1; j=j+2) {
                if (!board[x + i][y + j].hasPiece()) {
                    board[x + i][y + j].setFill(currentOption);
                }
                if ( board[x + i][y + j].hasPiece()){
                    if (    !board[x + i][y + j].getPiece().getType().equals(board[x][y].getPiece().getType())&&
                            !board[x + (2*i)][y + (2*j)].hasPiece()  ){//black element
                        board[x + (2*i)][y + (2*j)].setFill(currentOption);
                    }
                }


            }
        }
    }

}

