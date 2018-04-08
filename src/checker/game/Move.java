/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

import java.util.ArrayList;

/**
 *
 * @author Kamal
 */
public class Move {

    private Position intialpos;
    private Position finalpos;
    private boolean isCapture;

    public Move(Position intialpos, Position finalpos) {
        this.intialpos = intialpos;
        this.finalpos = finalpos;
    }

    public Move(Position intialpos, Position finalpos, boolean isCapture) {
        this.intialpos = intialpos;
        this.finalpos = finalpos;
        this.isCapture = isCapture;
    }

    public boolean ExistsInList(ArrayList<Move> moves) {
        for (int i = 0; i < moves.size(); i++) {
            if (this.equals(moves.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s --> %s", this.intialpos.toString(), this.finalpos.toString());
    }

    public boolean equals(Move move) {
        return (this.intialpos.equals(move.intialpos)
                && this.finalpos.equals(move.finalpos));
    }

    public Position getIntialpos() {
        return intialpos;
    }

    public void setIntialpos(Position intialpos) {
        this.intialpos = intialpos;
    }

    public Position getFinalpos() {
        return finalpos;
    }

    public void setFinalpos(Position finalpos) {
        this.finalpos = finalpos;
    }

    public boolean getIsCapture() {
        return isCapture;
    }

    public void setIsCapture(boolean isCapture) {
        this.isCapture = isCapture;
    }

}
