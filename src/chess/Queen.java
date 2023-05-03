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
                            || alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), alliedPieces)) {
                        return false;
                    }
                }

            } else if (xdiff < -1) {

                for (int i = xdiff + 1; i < 0; i++) {
                    if (enemyPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), enemyPieces) != -1
                            || alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), alliedPieces)) {
                        return false;
                    }
                }

            }

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }

            return true;
        }

        return false;
    }
    
    private boolean rookMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces)) {
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
            for (int i = xdiff - xdiff/Math.abs(xdiff); i != 0 ; i -= xdiff/Math.abs(xdiff)) {
                if (enemyPieceThere(xLoc - i, yLoc, enemyPieces) != -1
                        || alliedPieceThere(xLoc - i, yLoc, alliedPieces)) {
                    return false;
                }
            }
        } else if (ydiff != 0) {
            for (int i = ydiff - ydiff/Math.abs(ydiff); i != 0 ; i -= ydiff/Math.abs(ydiff)) {
                if (enemyPieceThere(xLoc, yLoc - i, enemyPieces) != -1
                        || alliedPieceThere(xLoc, yLoc - i, alliedPieces)) {
                    return false;
                }
            }
        } 
        
        else if (ydiff != 0) {

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }
            return true;
        } 
        else if (xdiff != 0) {

            if (enemyPieceThere(xLoc, yLoc, enemyPieces) != -1) {
                enemyPieces.remove(enemyPieceThere(xLoc, yLoc, enemyPieces));
            }
            return true;
        }
        return false;
    }

    public boolean move(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces){
        if (bishopMove(xLoc, yLoc, enemyPieces, alliedPieces) || rookMove(xLoc, yLoc, enemyPieces, alliedPieces))
            return true;
        return false;
    }
    
    public boolean canMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces){
        if (bishopCanMove(xLoc, yLoc, enemyPieces, alliedPieces) || rookCanMove(xLoc, yLoc, enemyPieces, alliedPieces))
            return true;
        return false;
    }
}