/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

/**
 *
 * @author Kamal
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public boolean equals(Position p) {
        return ((this.x == p.x)
                && (this.y == p.y));
    }

    @Override
    public String toString() {
        return String.format("{%d,%d}", this.x, this.y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
