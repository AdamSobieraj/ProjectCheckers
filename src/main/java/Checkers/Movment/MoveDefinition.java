package Checkers.Movment;

import Checkers.BoardElements.Piece;

public class MoveDefinition {

    private MoveIdent type;
    private Piece piece;

    public MoveIdent getType() {
        return type;
    }

    public Piece getPiece() {
        return piece;
    }

    public MoveDefinition(MoveIdent type) {
        this(type, null);
    }

    public MoveDefinition(MoveIdent type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
}
