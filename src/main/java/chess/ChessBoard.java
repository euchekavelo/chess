package chess;

public class ChessBoard {

    public final ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    private String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                if ((board[startLine][startColumn] instanceof Rook || board[startLine][startColumn] instanceof King
                        || board[startLine][startColumn] instanceof Pawn) && board[startLine][startColumn].isCheck()) {
                    board[startLine][startColumn].setCheck(false);
                }

                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    //--------------------------------------
    public ChessPiece[][] getBoard() {
        return board;
    }

    public boolean castling0() {
        int line = nowPlayer.equals("White") ? 0 : 7;
        ChessPiece king = board[line][4];
        ChessPiece rook = board[line][0];

        boolean canKingParticipateInCastling = king instanceof King && king.isCheck()
                && ((King) king).isUnderAttack(this, line, 4)
                && ((King) king).isUnderAttack(this, line, 2);

        boolean canRookParticipateInCastling = rook instanceof Rook && rook.isCheck();
        boolean areCellsEmpty = board[line][1] == null && board[line][2] == null && board[line][3] == null;

        if (!canKingParticipateInCastling || !canRookParticipateInCastling || !areCellsEmpty) {
            return false;
        }

        king.setCheck(false);
        board[line][2] = king;
        board[line][4] = null;
        rook.setCheck(false);
        board[line][3] = rook;
        board[line][0] = null;

        nowPlayer = nowPlayerColor().equals("White") ? "Black" : "White";

        return true;
    }

    public boolean castling7() {
        int line = nowPlayer.equals("White") ? 0 : 7;
        ChessPiece king = board[line][4];
        ChessPiece rook = board[line][7];

        boolean canKingParticipateInCastling = king instanceof King && king.isCheck()
                && ((King) king).isUnderAttack(this, line, 4)
                && ((King) king).isUnderAttack(this, line, 6);

        boolean canRookParticipateInCastling = rook instanceof Rook && rook.isCheck();
        boolean areCellsEmpty = board[line][5] == null && board[line][6] == null;

        if (!canKingParticipateInCastling || !canRookParticipateInCastling || !areCellsEmpty) {
            return false;
        }

        king.setCheck(false);
        board[line][6] = king;
        board[line][4] = null;
        rook.setCheck(false);
        board[line][5] = rook;
        board[line][7] = null;

        nowPlayer = nowPlayerColor().equals("White") ? "Black" : "White";

        return true;
    }
}
