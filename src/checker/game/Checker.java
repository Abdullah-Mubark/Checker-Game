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
public final class Checker {
    private CheckerType type;
    private Position position;

    public Checker(CheckerType type, Position position){
        setType(type);
        setPosition(position);
    }

    public CheckerType getType(){
        return type;
    }
    public Position getPosition(){
        return position;
    }
    public void setType(CheckerType type){
        this.type = type;
    }
    public void setPosition(Position position){
        this.position = position;
    }
}