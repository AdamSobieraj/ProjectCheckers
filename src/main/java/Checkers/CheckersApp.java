package Checkers;

import Checkers.Player.PlayerStatistic;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Checkers.BoardElements.Piece;
import Checkers.BoardElements.PieceType;
import Checkers.BoardElements.Tile;
import Checkers.GameControll.GameEndControll;
import Checkers.Movment.MoveDefinition;
import Checkers.Movment.MoveHelp;
import Checkers.Movment.MoveIdent;
import Checkers.Movment.MovmentLogic;

import static Checkers.Player.PlayerStatistic.turnLogic;
import static Checkers.JavaFX.Popup.*;
import static Checkers.JavaFX.Window.*;

public class CheckersApp extends Application {

    private Pane root = new Pane();
    //Dimensions
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final int PIECE_COUNT = 12;
    MovmentLogic movmentLogic = new MovmentLogic();

    //Board tile
    public static Tile[][] board = new Tile[WIDTH][HEIGHT];

    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();
    Tile tile;

    protected Parent createContent(){
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        //Tile Creation
        for (int y =0; y <HEIGHT ; y++) {
            for (int x = 0; x < WIDTH; x++){
                tile = new Tile((x + y)%2==0, x , y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);
                //White fields
                Piece piece = null;
                //populate with Pieces
                if (y<=2 && ((x + y)%2!=0) ){
                    piece = pieceCreatr(PieceType.BLACK, x ,y);
                }
                if (y>=5 && ((x + y)%2!=0) ){
                    piece = pieceCreatr(PieceType.WHITE, x ,y);
                }
                // check for null
                if(piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btnPopupCloseGame = new Button();
        Button btnCloseGame = new Button();
        Button btnNewGame = new Button();
        BorderPane border = new BorderPane();

        setButtonClose(btnPopupCloseGame);
        HBox hbox = addHBox(btnNewGame, btnCloseGame);
        border.setTop(hbox);
        border.setLeft(addFlowPane());//empty
        border.setRight(addFlowPane());//empty
        border.setBottom(addBottomStackPane());//empty
        addStackPane(hbox);         // Add stack to HBox in top region
        border.setCenter(createContent());
        Scene scene = new Scene(border);
        primaryStage.setTitle("Checkres Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnNewGame.setOnAction( e->{

            root.getChildren().clear();
            tileGroup.getChildren().clear();
            pieceGroup.getChildren().clear();
            PlayerStatistic.setTurnCounter(0);
            PlayerStatistic.setBlackKillCount(0);
            PlayerStatistic.setWhiteKillCount(0);
            labelCurrentTurn.setText(PlayerStatistic.displayCurrentTurn());
            createContent();
        });
        btnCloseGame.setOnAction(e -> {
            primaryStage.close();
        });

        btnPopupCloseGame.setOnAction(e -> {
            primaryStage.close();
            popupClose();
        });
    }

    public Piece pieceCreatr( PieceType type ,int x ,int y){
        Piece piece = new Piece(type, x ,y);

        //Show moves that are possible
        piece.setOnMouseClicked(e -> {
            int clickPosX = MovmentLogic.toBoard(piece.getLayoutX());
            int clickPosY = MovmentLogic.toBoard(piece.getLayoutY());
            MoveHelp.resetHighlite();
            MoveHelp.highliteAvailibleMove(true,clickPosX, clickPosY);
        });

        //movable
        piece.setOnMouseReleased(e -> {
            int newX = MovmentLogic.toBoard(piece.getLayoutX());//position obtain
            int newY = MovmentLogic.toBoard(piece.getLayoutY());//there is hange of layout

            MoveDefinition result;
            //Initialize first move
            if (PlayerStatistic.getTurnCounter()==0){
                turnLogic();
            }

            //prievious cordinate //clear board
            int x0 = MovmentLogic.toBoard(piece.getMouseButtonOldPozX());
            int y0 = MovmentLogic.toBoard(piece.getMouseButtonOldPozY());

            //Return to previous color
            board[x0][y0].setFill(Color.valueOf("#00303f"));

            //add turn logic
            if((newX <0 || newY <0 || newX >= WIDTH || newY >=HEIGHT)){
                result = new MoveDefinition(MoveIdent.NONE);
            }else if ( board[x0][y0].getPiece().getType().equals(PlayerStatistic.getCurrentPlayerTurn())){
                result =  movmentLogic.tryToMove(piece, newX, newY);
            }else {
                result = new MoveDefinition(MoveIdent.NONE);
            }

            switch (result.getType()){
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    turnLogic();
                    piece.moveDone(newX, newY);
                    if (board[x0][y0].getPiece().getType() == PieceType.WHITE){
                        piece.setType(PieceType.WHITE_KING);
                        System.out.println("Test");
                    }
                    board[x0][y0].setPiece(null);//delate
                    board[newX][newY].setPiece(piece);//create
                    labelCurrentTurn.setText(PlayerStatistic.displayCurrentTurn());
                    break;
                case KILL:
                    turnLogic();
                    //kill count
                    if (board[x0][y0].getPiece().getType().equals(PieceType.WHITE)){
                        PlayerStatistic.setWhiteKillCount(PlayerStatistic.getWhiteKillCount()+1);
                    }else {
                        PlayerStatistic.setBlackKillCount(PlayerStatistic.getBlackKillCount()+1);
                    }

                    piece.moveDone(newX, newY);
                    board[x0][y0].setPiece(null);//delate
                    board[newX][newY].setPiece(piece);//create

                    Piece killedPiece = result.getPiece();//element created
                    board[MovmentLogic.toBoard(killedPiece.getMouseButtonOldPozX())][MovmentLogic.toBoard(killedPiece.getMouseButtonOldPozY())].setPiece(null);
                    pieceGroup.getChildren().remove(killedPiece);
                    labelCurrentTurn.setText(PlayerStatistic.displayCurrentTurn());

                    // if the count of killed enemy reaches Tile total count
                    if (    (PlayerStatistic.getWhiteKillCount() == PIECE_COUNT )||
                            (PlayerStatistic.getBlackKillCount() == PIECE_COUNT )){
                        GameEndControll.onEndGameLogic();
                        display();
                    }
                    break;
            }
        });
        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
