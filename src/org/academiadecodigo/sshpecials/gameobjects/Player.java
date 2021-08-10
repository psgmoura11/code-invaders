package org.academiadecodigo.sshpecials.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.sshpecials.GameController;
import org.academiadecodigo.sshpecials.controls.Sound;
import org.academiadecodigo.sshpecials.gameobjects.bullets.Bullet;
import org.academiadecodigo.sshpecials.gameobjects.enemies.Enemy;
import org.academiadecodigo.sshpecials.mastercoders.MasterCoders;

public class Player {

    /*
     * Variable Declaration
     * */

    //Player Image Holder
    private Picture playerImage;

    //Bullet Array
    private Bullet[] bullets;

    //Player lives
    private int lives;

    //Player Shooting Sound
    private Sound shootingSound;

    //------------------------------------------------------------------------
    /*
     * Constructors
     * */
    // Player Constructor
    public Player(int x, int y){

        //Assign the image to the player
        this.playerImage = new Picture(x/2 + GameController.PADDING - 25,y - 100, "playersprite.png");

        //Initialize bullet array
        this.bullets = new Bullet[3];

        //initialize lifes
        this.lives = 3;

        //Initialise shooting sound
        this.shootingSound = new Sound("/keyboardSound.wav");

    }

    //------------------------------------------------------------------------
    /*
     * Getters and Setters
     * */

    //Get Player image
    public Picture getPlayerImage() {

        return playerImage;

    }

    //Get image center X
    public int centerX(){

        return playerImage.getX() + playerImage.getWidth()/2;

    }

    //Get bullets
    public Bullet[] getBullets(){

        return this.bullets;

    }

    //------------------------------------------------------------------------
    /*
     * Methods
     * */

    //Controls methods
    //Player moves left
    public void moveLeft(){

        if(playerImage.getX() < 20){
            return;
        }

        playerImage.translate(-10,0);

    }

    //Player moves right
    public void moveRight(){

        if(playerImage.getMaxX() > 790){

            return;

        }

        playerImage.translate(10,0);

    }

    //Player Shoots
    public void shoot(){

        for(int i = 0; i < bullets.length; i++) {

            if (bullets[i] != null) {

                continue;

            }

            bullets[i] = new Bullet(this);
            bullets[i].shoot();
            shootingSound.play(true);
            break;
        }

    }

    //Image related methods
    //Draw player
    public void drawPlayer(){

            this.playerImage.draw();

    }

    //Bullet related methods
    //Bullet destroy
    public void destroyBullet(int x){

        bullets[x].hide();
        bullets[x] = null;

    }

    //Check collisions between bullet and enemies
    public void checkCollisionsAndDestroy(Enemy[] enemies, GameController gameController){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            for(int j = 0; j < bullets.length; j++){

                if(bullets[j] == null){continue;}

                //if bullet contact point x is bigger than enemies minimum x
                if(enemies[i].enemyImage().getX() < bullets[j].contactPoint()
                        //and if bullet contact point x is less than enemies maximum x
                        && enemies[i].enemyImage().getMaxX() > bullets[j].contactPoint()
                        //and if bullet y is bigger than enemies y
                        && enemies[i].enemyImage().getY() < bullets[j].getBulletImage().getY()
                        // and if bullet y is smaller than enemies max y
                        && enemies[i].enemyImage().getMaxY() > bullets[j].getBulletImage().getY()) {


                    enemies[i].getDamaged();
                    bullets[j].hide();
                    this.destroyBullet(j);

                    if (enemies[i].getHp() < 1) {

                        enemies[i].deleteImage();
                        gameController.increaseScore(enemies[i].getPointsAwarded());
                        enemies[i].getDyingSound().play(true);
                        enemies[i] = null;

                    }

                    break;

                }

            }

        }
    }

    //Lose life on impact
    public void loseLife(){

        this.lives--;

    }

    // Get life
    public int getLives(){

        return this.lives;

    }

    //Check collisions between bullet and master coders
    public void checkMasterCoderCollisionsAndDestroy(MasterCoders[] masterCoders,GameController gameController){

        for(int i = 0; i < masterCoders.length; i++){

            if(masterCoders[i] == null){continue;}

            for(int j = 0; j < bullets.length; j++){

                if(bullets[j] == null){continue;}

                //if bullet contact point x is bigger than enemies minimum x
                if(masterCoders[i].getMasterCoderImage().getX() < bullets[j].contactPoint()
                        //and if bullet contact point x is less than enemies maximum x
                        && masterCoders[i].getMasterCoderImage().getMaxX() > bullets[j].contactPoint()
                        //and if bullet y is bigger than enemies y
                        && masterCoders[i].getMasterCoderImage().getY() < bullets[j].getBulletImage().getY()
                        // and if bullet y is smaller than enemies max y
                        && masterCoders[i].getMasterCoderImage().getMaxY() > bullets[j].getBulletImage().getY()) {


                    masterCoders[i].getDamaged();
                    bullets[j].hide();
                    this.destroyBullet(j);

                    if (masterCoders[i].getHp() < 1) {

                        masterCoders[i].deleteImage();
                        masterCoders[i].getDyingSound().play(true);
                        masterCoders[i] = null;

                    }

                    break;

                }

            }

        }


    }

    }
