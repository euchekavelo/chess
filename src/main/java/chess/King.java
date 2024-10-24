package chess;

public class King extends ChessPiece {

    public King(String color) {
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

        return (differenceLines == 0 && differenceColumns == 1) || (differenceLines == 1 && differenceColumns == 0)
                || (differenceLines == 1 && differenceColumns == 1);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        ChessPiece[][] chessPieces = board.getBoard();

        for (int i = 0; i < chessPieces.length; i++) {
            for (int j = 0; j < chessPieces[i].length; j++) {
                if (chessPieces[i][j] == this || chessPieces[i][j] == null) {
                    continue;
                }

                if (!(chessPieces[i][j].getColor().equals(this.getColor()))
                        && (chessPieces[i][j].canMoveToPosition(board, i, j, line, column))) {
                    return true;
                }
            }
        }

        return false;
    }
}
