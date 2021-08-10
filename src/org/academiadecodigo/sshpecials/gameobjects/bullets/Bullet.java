package org.academiadecodigo.sshpecials.gameobjects.bullets;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.sshpecials.gameobjects.Player;
import org.academiadecodigo.sshpecials.gameobjects.enemies.Enemy;
import org.academiadecodigo.sshpecials.mastercoders.MasterCoders;

public class Bullet {

    /*
     * Variable Declaration
     * */

    //Bullet Image
    private Picture bulletImage;

    //------------------------------------------------------------------------
    /*
     * Constructor
     * */

    //Constructor for player bullets
    public Bullet(Player player){

        this.bulletImage = new Picture(player.centerX() - 4,player.getPlayerImage().getY(),"playerbullet.png");

    }

    //Constructor for enemy bullets
    public Bullet(Enemy enemy){

        this.bulletImage = new Picture(enemy.enemyImage().getX() + enemy.enemyImage().getWidth()/2,enemy.enemyImage().getMaxY(),"enemybullet.png");

    }

    /*
     * Getters and Setters
     * */

    //Get bullet image
    public Picture getBulletImage() {
        return bulletImage;
    }

    // Get contact point of the bullet
    public int contactPoint(){

        return this.bulletImage.getX() + this.bulletImage.getWidth()/2;

    }

    //------------------------------------------------------------------------
    /*
     * Methods
     * */

    //Shoot bullet
    public void shoot(){

        bulletImage.draw();

    }

    //Player bullet move
    public void playerBulletMove(){

        bulletImage.translate(0, -10);

    }

    //Enemy bullet move
    public void enemyBulletMove(){

        bulletImage.translate(0, 5);

    }

    //Player bullet collision with top of the map
    public boolean checkPlayerBulletTopCollision() {

        return bulletImage.getY() < 20 ? true : false;

    }

    // Check bottom collision
    public boolean checkBottomCollision(){

        return bulletImage.getMaxY() > 580 ? true : false;

    }

    //Delete bullet image
    public void hide(){

        bulletImage.delete();

    }

    //Check bullet player collision
    public boolean checkPlayerCollision(Player player){

        if(contactPoint() > player.getPlayerImage().getX()
                && contactPoint() < player.getPlayerImage().getMaxX()
                && bulletImage.getMaxY() > player.getPlayerImage().getY()
                && bulletImage.getMaxY() < player.getPlayerImage().getMaxY()){


            return true;


        }

        return false;

    }

    //Check bullet master coder collision
    public boolean checkMasterCoderCollision(MasterCoders masterCoders){

        if(contactPoint() > masterCoders.getMasterCoderImage().getX()
                && contactPoint() < masterCoders.getMasterCoderImage().getMaxX()
                && bulletImage.getMaxY() > masterCoders.getMasterCoderImage().getY()
                && bulletImage.getMaxY() < masterCoders.getMasterCoderImage().getMaxY()){


            return true;


        }

        return false;

    }





}
