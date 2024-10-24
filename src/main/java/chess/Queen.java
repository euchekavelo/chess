package chess;

public class Queen extends ChessPiece {

    public Queen(String color) {
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

        int differenceLines = Math.abs(line - toLine);
        int differenceColumns = Math.abs(column - toColumn);

        return (differenceLines == differenceColumns && differenceLines >= 1)
                || (differenceLines == 0 || differenceColumns == 0);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}