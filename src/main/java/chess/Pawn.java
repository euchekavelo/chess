package chess;

public class Pawn extends ChessPiece {

    private boolean isFinishPosition = false;

    public Pawn(String color) {
        super(color);
    }

    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }

        if (!(chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn))) {
            return false;
        }

        int differenceColumns = Math.abs(column - toColumn);
        int differenceLines = Math.abs(toLine - line);

        if (differenceLines == 2 && differenceColumns == 0 && super.isCheck()) {
            return true;
        }

        return differenceLines == 1 && differenceColumns == 0;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
