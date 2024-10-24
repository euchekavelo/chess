package chess;

public class Horse extends ChessPiece {

    public Horse(String color) {
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
        ChessPiece[][] chessPieces = chessBoard.getBoard();
        ChessPiece chessPiece = chessPieces[toLine][toColumn];

        boolean isColumnEmpty = differenceLines == 2 && differenceColumns == 1
                && ((line < toLine
                && chessPieces[line + 1][column] == null
                && chessPieces[line + 2][column] == null)
                || (line > toLine
                && chessPieces[line - 1][column] == null
                && chessPieces[line - 2][column] == null));

        boolean isLineEmpty = differenceColumns == 2 && differenceLines == 1
                && ((column < toColumn
                && chessPieces[line][column + 1] == null
                && chessPieces[line][column + 2] == null)
                || (column > toColumn
                && chessPieces[line][column - 1] == null
                && chessPieces[line][column - 2] == null));

        return isColumnEmpty || isLineEmpty && (chessPiece == null || !chessPiece.getColor().equals(this.getColor()));
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
