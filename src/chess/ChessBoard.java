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
public class ChessBoard {

    ArrayList<Piece> white;
    ArrayList<Piece> black;

    public ChessBoard() {

        white = new ArrayList();
        black = new ArrayList();

        for (int i = 1; i <= 8; i++) {
            white.add(new Pawn(i, 2, 1));
        }

        white.add(new Rook(1, 1, 1));
        white.add(new Rook(8, 1, 1));

        white.add(new Knight(2, 1, 1));
        white.add(new Knight(7, 1, 1));

        white.add(new Bishop(3, 1, 1));
        white.add(new Bishop(6, 1, 1));

        white.add(new Queen(4, 1, 1));

        white.add(new King(5, 1, 1));

        for (int i = 1; i <= 8; i++) {
            black.add(new Pawn(i, 7, 1));
        }

        black.add(new Rook(1, 8, 1));
        black.add(new Rook(8, 8, 1));

        black.add(new Knight(2, 8, 1));
        black.add(new Knight(7, 8, 1));

        black.add(new Bishop(3, 8, 1));
        black.add(new Bishop(6, 8, 1));

        black.add(new Queen(4, 8, 1));

        black.add(new King(5, 8, 1));
    }

    public String toString() {
        String str = "";
        boolean flag;

        for (int k = 1; k <= 8; k++) {
            for (int i = 1; i <= 8; i++) {
                str += "|";
                flag = false;
                for (int j = 0; j < black.size(); j++) {
                    if (black.get(j).getPieceLocation()[0] == i && black.get(j).getPieceLocation()[1] == k) {
                        switch (black.get(j).getPieceNum()) {
                            case 0:
                                str += "Pn";
                                break;
                            case 1:
                                str += "Bp";
                                break;
                            case 2:
                                str += "Kn";
                                break;
                            case 3:
                                str += "Rk";
                                break;
                            case 4:
                                str += "Qn";
                                break;
                            case 5:
                                str += "Kg";
                                break;
                        }
                        flag = true;
                    }
                }
                for (int j = 0; j < white.size(); j++) {

                    if (white.get(j).getPieceLocation()[0] == i && white.get(j).getPieceLocation()[1] == k) {
                        switch (white.get(j).getPieceNum()) {
                            case 0:
                                str += "Pn";
                                break;
                            case 1:
                                str += "Bp";
                                break;
                            case 2:
                                str += "Kn";
                                break;
                            case 3:
                                str += "Rk";
                                break;
                            case 4:
                                str += "Qn";
                                break;
                            case 5:
                                str += "Kg";
                                break;
                        }
                        flag = true;
                    }
                }
                if (!flag) {
                    str += "  ";
                }
            }
            str += "|\n";
        }
        return str;
    }

}
