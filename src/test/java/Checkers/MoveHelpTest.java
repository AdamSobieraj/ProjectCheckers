package Checkers;

import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Checkers.BoardElements.Piece;
import Checkers.BoardElements.PieceType;
import Checkers.BoardElements.Tile;
import Checkers.Movment.MoveHelp;
import Checkers.Player.PlayerStatistic;

import static Checkers.CheckersApp.*;

public class MoveHelpTest {

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

    //Test MoveHelp
    @Test
    public void testBoardHighliteAvailibleMove(){

        //Given
        MoveHelp moveHelp = new MoveHelp();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setCurrentPlayerTurn(PieceType.BLACK);
        //When
        moveHelp.highliteAvailibleMove(true, 2,1);
        //Then
        Assert.assertEquals(Color.GREEN, CheckersApp.board[4][3].getFill());
    }

    @Test
    public void testBoardHighliteAvailibleMoveKill(){

        //Given
        MoveHelp moveHelp = new MoveHelp();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setCurrentPlayerTurn(PieceType.BLACK);
        //When
        moveHelp.highliteAvailibleMove(true, 2,1);
        //Then
        Assert.assertEquals(Color.GREEN, CheckersApp.board[3][0].getFill());
    }

}
