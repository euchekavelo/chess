package chess;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
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
        boolean isPathClear = isPathClear(chessPieces, line, toLine, column, toColumn);

        return differenceLines == differenceColumns && isPathClear &&
                (chessPiece == null || !this.getColor().equals(chessPiece.getColor()));
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    private boolean isPathClear(ChessPiece[][] chessPieces, int line, int toLine, int column, int toColumn) {
        if (line < toLine && column < toColumn) {
            while (line != toLine - 1 && column != toColumn - 1) {
                line++;
                column++;
                if (chessPieces[line][column] != null) {
                    return false;
                }
            }
        }

        if (line > toLine && column < toColumn) {
            while ((line != toLine + 1) && (column != toColumn - 1)) {
                line--;
                column++;
                if (chessPieces[line][column] != null) {
                    return false;
                }
            }
        }

        if (line < toLine && column > toColumn) {
            while ((line != toLine - 1) && (column != toColumn + 1)) {
                line++;
                column--;
                if (chessPieces[line][column] != null) {
                    return false;
                }
            }
        }

        if (line > toLine && column > toColumn) {
            while ((line != toLine + 1) && (column != toColumn + 1)) {
                line--;
                column--;
                if (chessPieces[line][column] != null) {
                    return false;
                }
            }
        }

        return true;
    }
}
