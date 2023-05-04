/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author shale
 */
public class Queen extends Piece {

    public Queen(int xLoc, int yLoc, int pieceColor) {
        super(xLoc, yLoc, pieceColor);

        pieceNum = 4;

        pieceValue = 9;
    }

    public int[][] spacesBetween(int xLoc, int yLoc) {

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if (xdiff == ydiff) {//if its acting like a bishop
            int[][] locs = new int[xdiff - 1][2];

            if (xdiff > 1) {

                for (int i = xdiff - 1; i > 0; i--) {

                    locs[i - 1][0] = xLoc - i;
                    locs[i - 1][1] = yLoc - (ydiff / Math.abs(ydiff)) * i;

                }

            } else if (xdiff < -1) {

                for (int i = xdiff + 1; i < 0; i++) {
                    locs[-1 * (i + 1)][0] = xLoc - i;
                    locs[-1 * (i + 1)][1] = yLoc - (ydiff / Math.abs(ydiff)) * i;
                }

            }
            return locs;
            
        } else {// if its acting like a rook
            
            int[][] locs;

            if (xdiff != 0) {
                locs = new int[xdiff - 1][2];
                for (int i = xdiff - xdiff / Math.abs(xdiff); i != 0; i -= xdiff / Math.abs(xdiff)) {
                    locs[Math.abs(i - xdiff / Math.abs(xdiff))][0] = xLoc - i;
                    locs[Math.abs(i - xdiff / Math.abs(xdiff))][1] = yLoc;
                }
            } else {
                locs = new int[ydiff - 1][2];
                for (int i = ydiff - ydiff / Math.abs(ydiff); i != 0; i -= ydiff / Math.abs(ydiff)) {
                    locs[Math.abs(i - ydiff / Math.abs(ydiff))][0] = xLoc;
                    locs[Math.abs(i - ydiff / Math.abs(ydiff))][1] = yLoc - i;
                }
            }
            return locs;
        }

    }

    // you didn't see anything (O_-)
    private boolean bishopMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces) || !canMove(xLoc, yLoc, enemyPieces, alliedPieces)) {
            return false;
        }

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if ((ydiff - xdiff == 0) || (ydiff + xdiff == 0)) {

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }

            pieceLocation[0] = xLoc;
            pieceLocation[1] = yLoc;
            moveCount++;
            return true;
        }

        return false;
    }

    private boolean bishopCanMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces)) {
            return false;
        }

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if ((ydiff - xdiff == 0) || (ydiff + xdiff == 0)) {

            //checking if the move is blocked by a piece
            if (xdiff > 1) {

                for (int i = xdiff - 1; i > 0; i--) {
                    if (enemyPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), enemyPieces) != -1
                            || alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)) * i, alliedPieces)) {
                        return false;
                    }
                }

            } else if (xdiff < -1) {

                for (int i = xdiff + 1; i < 0; i++) {
                    if (enemyPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), enemyPieces) != -1
                            || alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)) * i, alliedPieces)) {
                        return false;
                    }
                }

            }

            return true;
        }

        return false;
    }

    private boolean rookMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces) || !canMove(xLoc, yLoc, enemyPieces, alliedPieces)) {
            return false;
        }

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if (xdiff != 0 && ydiff != 0) {
            return false;
        } else if (ydiff != 0) {

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }

            pieceLocation[1] = yLoc;
            moveCount++;
            return true;
        } else if (xdiff != 0) {

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }

            pieceLocation[0] = xLoc;
            moveCount++;
            return true;
        }

        return false;
    }

    private boolean rookCanMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces)) {
            return false;
        }

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if (xdiff != 0 && ydiff != 0) {
            return false;
        }

        if (xdiff != 0) {
            for (int i = xdiff - xdiff / Math.abs(xdiff); i != 0; i -= xdiff / Math.abs(xdiff)) {
                if (enemyPieceThere(xLoc - i, yLoc, enemyPieces) != -1
                        || alliedPieceThere(xLoc - i, yLoc, alliedPieces)) {
                    return false;
                }
            }
        } else if (ydiff != 0) {
            for (int i = ydiff - ydiff / Math.abs(ydiff); i != 0; i -= ydiff / Math.abs(ydiff)) {
                if (enemyPieceThere(xLoc, yLoc - i, enemyPieces) != -1
                        || alliedPieceThere(xLoc, yLoc - i, alliedPieces)) {
                    return false;
                }
            }
        }

        if (ydiff != 0) {

            return true;
        } else if (xdiff != 0) {

            return true;
        }
        return false;
    }

    public boolean move(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {
        if (bishopMove(xLoc, yLoc, enemyPieces, alliedPieces) || rookMove(xLoc, yLoc, enemyPieces, alliedPieces)) {
            return true;
        }
        return false;
    }

    public boolean canMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {
        if (bishopCanMove(xLoc, yLoc, enemyPieces, alliedPieces) || rookCanMove(xLoc, yLoc, enemyPieces, alliedPieces)) {
            return true;
        }
        return false;
    }
}
