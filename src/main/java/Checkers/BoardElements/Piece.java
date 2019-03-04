package Checkers.BoardElements;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;

import static Checkers.CheckersApp.TILE_SIZE;

public class Piece extends StackPane {

    private static final String dotsURL = "kor.png";

    private static final double DIMENSION_ELIPS_1 = 0.3125;
    private static final double DIMENSION_ELIPS_2 = 0.26;

    public void setType(PieceType type) {
        this.type = type;
    }

    Image dots = null;
    private PieceType type;

    //For movement cordinates
    private double mouseButtonPozX;
    private double mouseButtonPozY;
    private double mouseButtonOldPozX;
    private double mouseButtonOldPozY;



    public void setMouseButtonOldPozX(double mouseButtonOldPozX) {
        this.mouseButtonOldPozX = mouseButtonOldPozX;
    }

    public void setMouseButtonOldPozY(double mouseButtonOldPozY) {
        this.mouseButtonOldPozY = mouseButtonOldPozY;
    }

    public double getMouseButtonOldPozX() {
        return mouseButtonOldPozX;
    }

    public double getMouseButtonOldPozY() {
        return mouseButtonOldPozY;
    }

    private Image imageOpen(){

        try {
            dots = new Image(dotsURL);
        }catch (NullPointerException  e){
            System.out.println("Unable to retrieve Image!!!");
        }
        return dots;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;
        moveDone(x, y);

        drawElement();

        //Move section Lambda
        setOnMousePressed( e ->{
            mouseButtonPozX = e.getSceneX();
            mouseButtonPozY = e.getSceneY();
        });
        //drag Lambda
        setOnMouseDragged(e ->{
            relocate(e.getSceneX() - mouseButtonPozX + mouseButtonOldPozX,
                     e.getSceneY() - mouseButtonPozY + mouseButtonOldPozY);
        });

    }

    //move function
    public void moveDone(int x, int y){
        mouseButtonOldPozX = x * TILE_SIZE;
        mouseButtonOldPozY = y * TILE_SIZE;
        relocate(mouseButtonOldPozX, mouseButtonOldPozY);
    }

    public void drawElement(){
        //Piece Creation BLACK/WHITE
        Ellipse pieceBG = new Ellipse(TILE_SIZE * DIMENSION_ELIPS_1, TILE_SIZE * DIMENSION_ELIPS_2);
        pieceBG.setFill(Color.GRAY);
        pieceBG.setStroke(Color.GRAY);
        pieceBG.setStrokeWidth(TILE_SIZE * 0.03);

        //Centring
        pieceBG.setTranslateX((TILE_SIZE - TILE_SIZE * DIMENSION_ELIPS_1 * 2)/2);
        pieceBG.setTranslateY((TILE_SIZE - TILE_SIZE * DIMENSION_ELIPS_2 * 2)/2 + TILE_SIZE*0.07);

        //Piece Bottom
        Ellipse ellipse = new Ellipse(TILE_SIZE * DIMENSION_ELIPS_1, TILE_SIZE * DIMENSION_ELIPS_2);

        if (type == PieceType.BLACK_KING || type == PieceType.WHITE_KING){
            ellipse.setFill(new ImagePattern(imageOpen()) );
        }else {
            ellipse.setFill(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        }
        ellipse.setStroke(Color.GRAY);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        //Centring
        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * DIMENSION_ELIPS_1 * 2)/2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * DIMENSION_ELIPS_2 * 2)/2);

        getChildren().addAll(pieceBG, ellipse);
    }

    public PieceType getType() {
        return type;
    }

    public void abortMove(){
        relocate(mouseButtonOldPozX, mouseButtonOldPozY);
    }
}
