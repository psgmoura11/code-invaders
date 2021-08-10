package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.sshpecials.GameController;
import org.academiadecodigo.sshpecials.controls.Sound;
import org.academiadecodigo.sshpecials.gameobjects.Player;
import org.academiadecodigo.sshpecials.gameobjects.bullets.Bullet;
import org.academiadecodigo.sshpecials.mastercoders.MasterCoders;

public abstract class Enemy {

    /*
     * Variable Declaration
     * */

    //Enemy image
    private Picture enemyImage;

    //Direction of movement
    private boolean moveLeft;

    //Enemy bullet array
    private Bullet[] bullets;

    //Enemy Health
    private int Hp;

    //Points awarded for killing
    private int pointsAwarded;

    //Enemy shooting sound
    private Sound shootingSound;

    //Enemy dying sound
    private Sound dyingSound;

    //Enemy shooting probability
    private int shootingProb;

    //------------------------------------------------------------------------
    /*
     * Constructors
     * */

    public Enemy(){

        shootingSound = new Sound("/enemyShootingSound.wav");
        dyingSound = new Sound("/enemyDyingSound.wav");

    }

    //------------------------------------------------------------------------

    /*
     * Getters and Setters
     * */

    //Set shooting probability
    public void setShootingProb(int shootingProb) {
        this.shootingProb = shootingProb;
    }

    //Get shooting probability
    public int getShootingProb() {
        return shootingProb;
    }

    //Get enemy dying sound
    public Sound getDyingSound(){

        return dyingSound;

    }

    //Get enemy image
    public Picture enemyImage(){

        return this.enemyImage;

    }

    //Set enemy image
    public void setEnemyImage(Picture enemyImage){

        this.enemyImage = enemyImage;

    }

    //Delete enemy draw
    public void deleteImage(){

        for(int i = 0; i < bullets.length; i++){

            if(bullets[i] == null){continue;}

            bullets[i].getBulletImage().delete();
            bullets[i] = null;

        }

        this.enemyImage.delete();

    }

    //Draws enemy image
    public void drawEnemyImage(){

        this.enemyImage.draw();

    }

    //Set move left
    public void changeDirection(boolean left){

        this.moveLeft = left;

    }

    //Get HP
    public int getHp(){

        return this.Hp;

    }

    //Set hp --
    public void getDamaged(){

        this.Hp--;

    }

    //Set hp
    public void setHp(int Hp){

        this.Hp = Hp;

    }

    //enemy get bullets
    public Bullet[] getBullets(){

        return this.bullets;

    }

    //Enemies awarded points setter
    public void setPointsAwarded(int points){

        this.pointsAwarded = points;

    }

    //Get awarded points
    public int getPointsAwarded(){

        return this.pointsAwarded;

    }

    //------------------------------------------------------------------------
    /*
     * Methods
     * */

    //Enemy initializer
    public void init(int bullets){

        this.moveLeft = true;
        this.bullets = new Bullet[bullets];

    }

    //Movement methods
    //Enemy auto movement
    public void moveEnemy(){

        if(moveLeft){

            this.enemyImage.translate(-1,0);

        }

        if(!moveLeft){

            this.enemyImage.translate(1,0);

        }

    }

    //Enemy Collisions
    //Check if enemy reaches lower boundary
    public boolean enemyReachesLowerBoundary(Rectangle lowerLimit){

        if(this.enemyImage.getMaxY() > lowerLimit.getY()){

            return true;

        }

        return false;

    }

    public void enemyBulletCheckPlayerCollision(Player player){

        for(int i = 0; i < bullets.length;i++){

            if(bullets[i] == null){continue;}

            if(bullets[i].checkPlayerCollision(player)){

                bullets[i].getBulletImage().delete();
                bullets[i] = null;
                player.loseLife();

            }

        }


    }

    //Destroy bullet on bottom
    public void bulletDestroyBottom(){

        for(int i = 0; i < bullets.length;i++){
            if(bullets[i] == null){continue;}

            if(bullets[i].checkBottomCollision()){

                bullets[i].getBulletImage().delete();
                bullets[i] = null;

            }

        }


    }

    //Enemy Shoot methods
    //Enemy shoot
    public void enemyShoot(){

        for(int i = 0; i < bullets.length; i++) {

            if (bullets[i] != null) {

                continue;

            }

            bullets[i] = new Bullet(this);
            bullets[i].shoot();
            shootingSound.play(true);

        }
    }

    //Check enemy bullet collision with master coders
    public void enemyBulletCheckMasterCodersCollision(MasterCoders[] masterCoders){

        for(int i = 0; i < bullets.length;i++){

            if(bullets[i] == null){continue;}

            for(int j = 0; j < masterCoders.length; j++) {
                if(bullets[i] == null){continue;}
                if(masterCoders[j] == null){continue;}

                if (bullets[i].checkMasterCoderCollision(masterCoders[j])) {

                    bullets[i].getBulletImage().delete();
                    bullets[i] = null;
                    masterCoders[j].getDamaged();

                    if(masterCoders[j].getHp() < 1){

                        masterCoders[j].deleteImage();
                        masterCoders[j].getDyingSound().play(true);
                        masterCoders[j] = null;

                    }


                }
            }
        }

    }


}
