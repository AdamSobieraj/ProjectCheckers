package Checkers;

import Checkers.BoardElements.Piece;
import Checkers.BoardElements.PieceType;
import Checkers.BoardElements.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Checkers.CheckersApp.*;

public class PieceTest {

    @Before
    public void setUp ()  {
        System.out.println("This is test bench");

        CheckersApp checkersApp = new CheckersApp();
        Tile tile;

        for (int y =0; y <HEIGHT ; y++) {
            for (int x = 0; x < WIDTH; x++){
                tile = new Tile((x + y)%2==0, x , y);
                checkersApp.board[x][y] = tile;
                tileGroup.getChildren().add(tile);
                //White fields
                Piece piece = null;
                //populate with Pieces
                if (y==1 && x==2 ){
                    piece = checkersApp.pieceCreatr(PieceType.BLACK, x ,y);
                }
                if (y==2 && x==3 ) {
                    piece = checkersApp.pieceCreatr(PieceType.WHITE, x ,y);
                }
                // check for null
                if(piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
    }

    @Test
    public void testPieceMoveDone(){

        //Given
        PieceType pieceType = PieceType.WHITE;
        Piece piece = new Piece(pieceType, 2, 1);
        //When
        int x = 3;
        int y = 2;
        piece.moveDone(x,y);
        //Then
         Assert.assertEquals(300.0, piece.getMouseButtonOldPozX(), 0.01);
         Assert.assertEquals(200.0, piece.getMouseButtonOldPozY(), 0.01);
    }
    @Test
    public void testPieceMoveDoneNoMove(){

        //Given
        PieceType pieceType = PieceType.WHITE;
        Piece piece = new Piece(pieceType, 0, 0);
        //When
        int x = 0;
        int y = 0;
        piece.moveDone(x,y);
        //Then
        Assert.assertEquals(0.0, piece.getMouseButtonOldPozX(), 0.01);
        Assert.assertEquals(0.0, piece.getMouseButtonOldPozY(), 0.01);
    }




}
