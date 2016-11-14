package se.keroprog.effects.main;


import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;

/**
 * Created by Kristoffer on 9/19/2016.
 */
public class Flake {

    private int x, y;
    private char skin = 'X';
    private Terminal.Color color = Terminal.Color.WHITE;

    public Flake(int x){

        this.x = x;
        this.y = 0;


    }

    public boolean canFall(ArrayList<Flake> flakes, ArrayList<Structure> structureList){

        boolean hit = false;
        for (Flake f: flakes
             ) {
            if(f.x == x && f.y == y+1){
                hit = true;
            }
            for (Structure structure :
                    structureList) {
                if (structure.doesBlock(x,y+1)) {
                    hit = true;
                }
            }
        }
        return !hit;

    }

    public boolean canSlide(ArrayList<Flake> flakes, ArrayList<Structure> structureList){

        boolean hit = false;
        for (Flake f: flakes
                ) {
            if(f.x == x-1 && f.y == y+1){
                hit = true;
            }
            for (Structure structure :
                    structureList) {
                if (structure.doesBlock(x-1,y+1)) {
                    hit = true;
                }
            }
        }
       if(!hit && x > 0) {
           x--;
           return !hit;
       }

       hit = false;
        for (Flake f: flakes
                ) {
            if(f.x == x+1 && f.y == y+1){
                hit = true;
            }
            for (Structure structure :
                    structureList) {
                if (structure.doesBlock(x+1,y+1)) {
                    hit = true;
                }
            }
        }

        if(!hit && x < Main.getWidth()-1) {
            x++;
            return !hit;
        }

        return false;


    }



    public void fall(){

        y++;
    }

    public void draw(Terminal t){
        t.moveCursor(x, y);
        t.applyForegroundColor(color);
        t.putCharacter(skin);
    }
}
