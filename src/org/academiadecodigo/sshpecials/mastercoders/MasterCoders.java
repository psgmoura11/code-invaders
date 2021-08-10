package org.academiadecodigo.sshpecials.mastercoders;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.sshpecials.controls.Sound;
import org.academiadecodigo.sshpecials.gameobjects.Player;
import org.academiadecodigo.sshpecials.gameobjects.bullets.Bullet;
import org.academiadecodigo.sshpecials.gameobjects.enemies.Enemy;

public class MasterCoders {

    /*
     * Variable Declaration
     * */

    //Master Coder Image
    private Picture masterCoderImage;

    //Dying Sound
    private Sound dyingSound;

    //Master Coder Health
    private int Hp;

    //------------------------------------------------------------------------
    /*
     * Constructor
     * */
    public MasterCoders(int x){

        this.masterCoderImage = new Picture(x,420,"academia.png");
        masterCoderImage.draw();
        this.dyingSound = new Sound("/mcDeath.wav");
        this.Hp = 3;

    }



    /*
     * Getters and Setters
     * */

    //Get master coder dying sound
    public Sound getDyingSound(){

        return dyingSound;

    }

    //Get master coder image
    public Picture getMasterCoderImage(){

        return this.masterCoderImage;

    }

    //Get HP
    public int getHp(){

        return this.Hp;

    }

    //Set hp --
    public void getDamaged(){

        this.Hp--;

    }



    //------------------------------------------------------------------------
    /*
     * Methods
     * */

    //Delete master coder draw
    public void deleteImage(){

        this.masterCoderImage.delete();

    }

    //Draws master coder image
    public void drawMasterCoderImage(){

        this.masterCoderImage.draw();

    }

}
