package Checkers.BoardElements;

public enum PieceType {
    BLACK_KING(2),
    BLACK(1),
    WHITE(-1),
    WHITE_KING(-2);

    public final int moveDir;

    PieceType(int moveDir){
        this.moveDir = moveDir;
    }
}
