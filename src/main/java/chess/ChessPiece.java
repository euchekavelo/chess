package chess;

public abstract class ChessPiece {

    private final String color;
    private boolean check = true;

    protected boolean isCheck() {
        return check;
    }

    protected void setCheck(boolean check) {
        this.check = check;
    }

    protected ChessPiece(String color) {
        this.color = color;
    }

    protected String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
