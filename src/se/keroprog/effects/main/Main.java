package se.keroprog.effects.main;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Flake> flakes = new ArrayList();
    private static int floor = 28, width = 65;
    private static Structure house = new Structure(5, width - 15, 20, floor, Terminal.Color.RED);
    private static Structure ground = new Structure(-1, width, floor -1, floor+1, Terminal.Color.GREEN);

    private static ArrayList<Structure> structureList = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {


        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);

        structureList.add(house);
        structureList.add(ground);



        while (true){

            awaitInput(terminal);
        }



    }

    public static void addFlake(){
        Flake tempFlake = new Flake((int) (Math.random()*width));
        flakes.add(tempFlake);

    }

    public static void awaitInput(Terminal terminal) throws InterruptedException {
        Key key;
        do{
            Thread.sleep(5);
            key = terminal.readInput();

            if(key != null){

                terminal.clearScreen();

                for (Structure s :
                        structureList) {

                    s.draw(terminal);
                }

                addFlake();

                for (Flake f : flakes) {
                    f.draw(terminal);
                    if(f.canFall(flakes, structureList)){

                        f.fall();
                    } else{
                        if(f.canSlide(flakes, structureList)){
                            f.fall();
                        }
                    }
                }
            }
        }
        while(key == null);

    }

    public static int getFloor() {
        return floor;
    }

    public static int getWidth() {
        return width;
    }
}
