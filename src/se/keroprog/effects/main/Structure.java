package se.keroprog.effects.main;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Kristoffer on 9/19/2016.
 */
public class Structure {

    int xBoundsMin, xBoundsMax, yBoundsMin, yBoundsMax;
    Terminal.Color color;

//    private static final int[][] POSITIONS = {
//            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,1,1,1,0,0,0},
//            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0},
//            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0},
//            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0},
//    };

    private int floor;

    public Structure(int xMin, int xMax, int yMin, int yMax, Terminal.Color color){

        this.xBoundsMin = xMin;
        this.xBoundsMax = xMax;
        this.yBoundsMin = yMin;
        this.yBoundsMax = yMax;
        this.color = color;



    }

    public boolean doesBlock(int inX, int inY){

        if(inY < yBoundsMax && inY > yBoundsMin && inX > xBoundsMin && inX < xBoundsMax) {
            return true;
        }

        return false;
    }



    public void draw(Terminal terminal){

        terminal.applyForegroundColor(color);
        for (int i = 0; i < Main.getFloor()+1; i++) {
            for (int j = 0; j < Main.getWidth(); j++) {
                if (doesBlock(j,i)){
                    terminal.moveCursor(j,i);
                    terminal.putCharacter('X');
                }
            }
        }

    }

}
