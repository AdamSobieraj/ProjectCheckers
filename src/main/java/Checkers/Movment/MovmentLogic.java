package Checkers.Movment;

import Checkers.BoardElements.Piece;
import Checkers.BoardElements.PieceType;

import static Checkers.CheckersApp.TILE_SIZE;
import static Checkers.CheckersApp.board;

public class MovmentLogic {

    private int x0;
    private int y0;

    //convertion of pixel cordinates to board cordinates
    public static int toBoard(double pixel){
        return (int)(pixel + TILE_SIZE/2)/TILE_SIZE;
    }

    public MoveDefinition tryToMove(Piece piece, int newXX, int newYY){

        if (board[newXX][newYY].hasPiece() || (newXX + newYY)%2 == 0){
            return new MoveDefinition(MoveIdent.NONE);
        }
        x0 = toBoard(piece.getMouseButtonOldPozX());
        y0 = toBoard(piece.getMouseButtonOldPozY());

        //Move Distinguisching // move direction identyfication going up or down
        if (        Math.abs(newXX - x0)==1 && //Movment for Piece normal
                (   piece.getType().equals(PieceType.WHITE)||piece.getType().equals(PieceType.BLACK))){//blokada ruch w tył
            return new MoveDefinition(MoveIdent.NORMAL);
        }else if (  (Math.abs(newXX - x0)>=0 && (Math.abs(newXX - x0)<=7 ) && //Movment for Piece normal
                (   piece.getType().equals(PieceType.WHITE_KING)||piece.getType().equals(PieceType.BLACK_KING)))){//Movment for King

            int dirX = ((x0 - newXX)>0)? -1: 1;
            int dirY = ((y0 - newYY)>0)? -1: 1;
            int tmpY = y0;
            int tmpX = 0;

            if(dirX==1){
                for (int i=x0; i<=(newXX-x0); i=i+dirX){
                    if((i>x0&& i<newXX)){

                        tmpX = i;
                        tmpY = tmpY+dirY;

                        //need to have piece and piece different type
                        if (board[tmpX][tmpY].hasPiece() && board[tmpX][tmpY].getPiece().getType() != piece.getType()){
                            return new MoveDefinition(MoveIdent.KILL, board[tmpX][tmpY].getPiece());
                        }
                    }
                }
            }else {
                for (int i=x0; i>=(newXX-x0); i=i+dirX){
                    if((i<x0 && i>newXX)){
                        tmpX = i;
                        tmpY = tmpY + dirY;

                        //need to have piece and piece different type
                        if (board[tmpX][tmpY].hasPiece() && board[tmpX][tmpY].getPiece().getType() != piece.getType()){
                            return new MoveDefinition(MoveIdent.KILL, board[tmpX][tmpY].getPiece());
                        }
                    }
                }
            }
            return new MoveDefinition(MoveIdent.NORMAL);
        }else if (( Math.abs(newXX - x0)==2) &&
                    Math.abs(newYY -y0) == Math.abs(piece.getType().moveDir *2)){//Kill Movement - 2 steps
            int x1 = x0 + (newXX - x0)/2;//getting element between
            int y1 = y0 + (newYY - y0)/2;
            //need to have piece and piece different type
            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()){
                return new MoveDefinition(MoveIdent.KILL, board[x1][y1].getPiece());
            }
        }
        return new MoveDefinition(MoveIdent.NONE);
    }
}
