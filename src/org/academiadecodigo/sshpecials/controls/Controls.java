package org.academiadecodigo.sshpecials.controls;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.sshpecials.GameController;
import org.academiadecodigo.sshpecials.gameobjects.Player;

public class Controls implements KeyboardHandler {

    /*
    * Variable Declaration
    * */

    //Keyboard
    private Keyboard keyboard;

    //Player holder
    private Player player;

    //Game controller
    GameController gameController;

    //------------------------------------------------------------------------

    /*
     * Getters and Setters
     * */

    //Set a player to the controls
    public void setPlayer(Player player){
        this.player = player;
    }

    //Set a Game Controller to the controls

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }


    //------------------------------------------------------------------------
    /*
     * Methods
     * */

    //Keyboard initializer
    public void init(){

        //Assign a keyboard instance
        this.keyboard = new Keyboard(this);

        //Move Left Control
        KeyboardEvent pressA = new KeyboardEvent();
        pressA.setKey(KeyboardEvent.KEY_A);
        pressA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Move Right Control
        KeyboardEvent pressD = new KeyboardEvent();
        pressD.setKey(KeyboardEvent.KEY_D);
        pressD.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Shoot Control
        KeyboardEvent pressSpace = new KeyboardEvent();
        pressSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Quit Control
        KeyboardEvent pressQ = new KeyboardEvent();
        pressQ.setKey(KeyboardEvent.KEY_Q);
        pressQ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Quit Control
        KeyboardEvent pressS = new KeyboardEvent();
        pressS.setKey(KeyboardEvent.KEY_S);
        pressS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Instructions Control
        KeyboardEvent pressI = new KeyboardEvent();
        pressI.setKey(KeyboardEvent.KEY_I);
        pressI.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Instructions Control
        KeyboardEvent pressB = new KeyboardEvent();
        pressB.setKey(KeyboardEvent.KEY_B);
        pressB.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //Listeners for the keys implemented above
        keyboard.addEventListener(pressA);
        keyboard.addEventListener(pressD);
        keyboard.addEventListener(pressSpace);
        keyboard.addEventListener(pressQ);
        keyboard.addEventListener(pressS);
        keyboard.addEventListener(pressI);
        keyboard.addEventListener(pressB);


    }

    //Keyboard Events while Key Pressed
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){

        switch(keyboardEvent.getKey()){

            case KeyboardEvent.KEY_D:
                player.moveRight();
                break;

            case KeyboardEvent.KEY_A:
                player.moveLeft();
                break;

            case KeyboardEvent.KEY_SPACE:
                if(gameController.isOnMenu()){
                    break;

                }
                player.shoot();
                break;

            case KeyboardEvent.KEY_Q:
                System.exit(-1);
                break;

            case KeyboardEvent.KEY_S:
                gameController.setOnMenu();
                break;

            case KeyboardEvent.KEY_I:
                if(gameController.isOnMenu()) {
                    gameController.drawInstructions();
                }
                break;

            case KeyboardEvent.KEY_B:
                if(gameController.isOnMenu()) {
                    gameController.deleteInstructions();
                }
                break;
        }
    }

    //Keyboard Events while Key Release
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent){

    }

}
