package Checkers;

import Checkers.BoardElements.Piece;
import Checkers.BoardElements.PieceType;
import Checkers.BoardElements.Tile;
import Checkers.Movment.MoveDefinition;
import Checkers.Movment.MoveIdent;
import Checkers.Movment.MovmentLogic;
import Checkers.Player.PlayerStatistic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Checkers.CheckersApp.*;

public class MovementLogicTest {

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

    //Test for movment Logic
    @Test
    public void testMovmentLogicNone(){

        //Given
        MovmentLogic movmentLogic = new MovmentLogic();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setCurrentPlayerTurn(PieceType.BLACK);
        //When
        int x = 3;
        int y = 3;
        Piece piece = new Piece(PieceType.BLACK, x ,y);
        MoveDefinition testMoveType = movmentLogic.tryToMove(piece , x, y);
        //Then
        Assert.assertEquals(MoveIdent.NONE,testMoveType.getType() );
    }

    @Test
    public void testMovmentLogicNormal(){

        //Given
        MovmentLogic movmentLogic = new MovmentLogic();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setCurrentPlayerTurn(PieceType.BLACK);
        //When
        int x = 1;
        int y = 2;
        double oldX = 200.0;
        double oldY = 100.0;
        Piece piece = new Piece(PieceType.BLACK, x ,y);
        piece.setMouseButtonOldPozX(oldX);
        piece.setMouseButtonOldPozY(oldY);
        MoveDefinition testMoveType = movmentLogic.tryToMove(piece , x, y);
        //Then
        Assert.assertEquals(MoveIdent.NORMAL,testMoveType.getType() );
    }

    @Test
    public void testMovmentLogicKill(){

        //Given
        MovmentLogic movmentLogic = new MovmentLogic();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setCurrentPlayerTurn(PieceType.BLACK);
        //When
        int x = 4;
        int y = 3;
        double oldX = 200.0;
        double oldY = 100.0;
        Piece piece = new Piece(PieceType.BLACK, x ,y);
        piece.setMouseButtonOldPozX(oldX);
        piece.setMouseButtonOldPozY(oldY);
        MoveDefinition testMoveType = movmentLogic.tryToMove(piece , x, y);
        //Then
        Assert.assertEquals(MoveIdent.KILL,testMoveType.getType() );
    }

    @Test
    public void testMovmentLogicToBoard(){

        //Given
        MovmentLogic movmentLogic = new MovmentLogic();
        //When
        double inPixelsX = 200.0;
        int resoult = movmentLogic.toBoard(inPixelsX);
        //Then
        Assert.assertEquals(2,resoult );
    }
}
