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
public class Bishop extends Piece {

    public Bishop(int xLoc, int yLoc, int pieceColor) {
        super(xLoc, yLoc, pieceColor);

        pieceNum = 1;

        pieceValue = 3;
    }

    public boolean move(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces) || !canMove(xLoc,yLoc,enemyPieces,alliedPieces)) {
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

    public boolean canMove(int xLoc, int yLoc, ArrayList<Piece> enemyPieces, ArrayList<Piece> alliedPieces) {

        if (!onBoard(xLoc, yLoc) || alliedPieceThere(xLoc, yLoc, alliedPieces)) {
            return false;
        }

        int xdiff = xLoc - pieceLocation[0];
        int ydiff = yLoc - pieceLocation[1];

        if ((ydiff - xdiff == 0) || (ydiff + xdiff == 0)) {

            //checking if the move is blocked by a piece
            if (xdiff > 1) {
                
                for (int i = xdiff - 1; i > 0; i--) {
                    if (enemyPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), enemyPieces) != -1 ||
                            alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), alliedPieces)){
                        return false;
                    }
                }
                
            } else if (xdiff < -1){
                
                for (int i = xdiff + 1; i < 0; i++) {
                    if (enemyPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), enemyPieces) != -1 ||
                            alliedPieceThere(xLoc - i, yLoc - (ydiff / Math.abs(ydiff)), alliedPieces)){
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

}
