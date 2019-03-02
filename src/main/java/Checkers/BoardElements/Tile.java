package Checkers.BoardElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import Checkers.CheckersApp;

public class Tile extends Rectangle {

    private Piece piece;

    //veryfication if it is not out
    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x , int y) {
        setWidth(CheckersApp.TILE_SIZE);
        setHeight(CheckersApp.TILE_SIZE);

        relocate(x*CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);

        setFill( light ? Color.GRAY : Color.valueOf("#00303f"));
    }
}