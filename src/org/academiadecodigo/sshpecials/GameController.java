package org.academiadecodigo.sshpecials;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.sshpecials.controls.Controls;
import org.academiadecodigo.sshpecials.controls.Sound;
import org.academiadecodigo.sshpecials.gameobjects.Player;
import org.academiadecodigo.sshpecials.gameobjects.enemies.*;
import org.academiadecodigo.sshpecials.mastercoders.MasterCoders;

public class GameController {

    /*
     * Constant Variable
     * */

    //Padding for images
    public static final int PADDING = 10;

    /*
     * Variable Declaration
     * */

    //Background Image
    private Picture backgroundGameImage;

    //Player
    private Player player;

    //Game controls
    private Controls controls;

    //Game over
    private boolean gameOver;

    //Enemies list
    private Enemy[] enemies;

    //EndGame Screen
    private Picture endGameScreen;

    //Enemy lower point allowed
    private Rectangle lowerBoundary;

    //Arrays of life images
    private Picture[] lives;

    //Score holder
    private int score;

    //Draw Score
    private Text showScore;

    //Draw life text
    private Text livesText;

    //Game Sound
    private Sound gameSound;

    //Menu Sound
    private Sound menuSound;

    //Menu Image
    private Picture mainMenu;

    //On Menu boolean
    private boolean onMenu;

    //Instructions Image
    private Picture instructions;

    //Difficulty setting
    private int diff;

    //Rounds Counter
    private int rounds;

    //Boss Rounds
    private int bossRounds;

    //Master coder array
    private MasterCoders[] masterCoders;






    //------------------------------------------------------------------------
    /*
     * Constructors
     * */

    //Game Controller Constructor
    public GameController(){

        //Initialise canvas
        Canvas.getInstance();

        //Assign a Background Image
        this.backgroundGameImage = new Picture (PADDING,PADDING, "terminal.jpeg");

        //Create the player
        this.player = new Player(backgroundGameImage.getWidth(),backgroundGameImage.getHeight());

        //Assign controls to the game
        this.controls = new Controls();

        //Initiate the controls
        this.controls.init();

        //Set the player to the controls
        this.controls.setPlayer(player);

        //Set Game Controller to the controls
        this.controls.setGameController(this);

        //Set game over to false
        this.gameOver = false;

        //Set enemies array length
        this.enemies = new Enemy[18];

        //initialise end game screen
        this.endGameScreen = new Picture(10,10,"gameover.jpg");

        // set lower boundary
        this.lowerBoundary = new Rectangle(PADDING,480,backgroundGameImage.getWidth() ,0);

        //Initialise score at 0
        this.score = 0;

        //Initialise Draw Score
        this.showScore = new Text(15,20,"Score: " + score);

        //Initialise Lives Text
        this.livesText = new Text(650 , 15, "Lives: ");

        //Initialize Diff
        this.diff = 0;

        //Initialize rounds
        this.rounds = 0;

        //Boss Rounds
        bossRounds = 10;

        //Initialize Master Coders Array
        masterCoders = new MasterCoders[3];


    }


    //------------------------------------------------------------------------
    /*
     * Getters and Setters
     * */

    //------------------------------------------------------------------------
    /*
     * Methods
     * */
    //Main menu run
    public void runMainMenu(){

        mainMenu = new Picture(PADDING,PADDING,"menu2.png");
        menuSound = new Sound("/menuSound.wav");
        instructions = new Picture(PADDING,PADDING,"instructions.png");

        this.onMenu = true;
        menuSound.play(true);
        mainMenu.draw();

        while(onMenu){

            System.out.println("");

        }


    }

    //End game methods
    public void endGameState(){

       this.endGameScreen.draw();
       this.gameSound.stop();
       this.onMenu = true;

       Sound gameOverSound = new Sound("/gameOverSound.wav");
       gameOverSound.play(true);

    }

    //Run game methods
    //Game Initializer
    public void gameInit() throws InterruptedException {

        this.menuSound.stop();
        this.instructions.delete();
        this.mainMenu.delete();

        //Draw background
        this.backgroundGameImage.draw();

        //Draw player
        player.drawPlayer();

        //Draw Boundary
        this.lowerBoundary.setColor(Color.RED);
        this.lowerBoundary.draw();

        //Draw Score
        this.showScore.setColor(Color.WHITE);
        this.showScore.draw();

        //Draw Lives Text
        this.livesText.setColor(Color.WHITE);
        this.livesText.draw();

        //Initialise LiVes
        lives = new Picture[]{

                new Picture(710,15,"life1.png"),
                new Picture(740,15,"life2.png"),
                new Picture(770,15,"life3.png")

        };

        //Initialize game sound
        this.gameSound = new Sound("/gameSound.wav");

        //Play game sound
        this.gameSound.play(true);
        this.gameSound.setLoop(-1);

        //Run game
        this.runGame();

    }

    //Run Game
    public void runGame() throws InterruptedException{

        while(!gameOver){

            boolean levelComplete = false;
            //Creating enemies and placing them on the map
            generateEnemies();

            //Delete Master Coders
            deleteMasterCoders();


            //Creating Master Coders
            generateMasterCoders();

            while(!levelComplete){

                Thread.sleep(50);
                //Draw UI
                drawUI();

                //Move enemies
                moveEnemies();

                //Shoot Bullets
                shootBullets();

                //Moves all bullets
                moveBullets();

                //Check collisions
                checkCollisions();

                //Check if enemies are dead
                if(areEnemiesLeft()){

                    levelComplete = true;
                    diff++;
                    rounds++;

                }

                //Set game over on player dead
                setGameOverOnPlayerDead();

                //set level complete if player is dead

                if(this.gameOver){

                    levelComplete = true;
                }

            }

        }

        endGameState();

    }

    //Master Methods
    public void drawUI(){

        reDrawScore();
        redrawLives();

    }

    //Shoot Bullets
    public void shootBullets(){

        enemyBulletShot();

    }

    //Move Bullets
    public void moveBullets(){

        movePlayerBullets();
        enemiesBulletsMove();


    }

    //Check all bullets collisions
    public void checkCollisions(){

        checkBulletCollisionWithEnemyAndDestroy();
        checkPlayerBulletCollisionMasterCoderAndDestroy();
        checkEnemyBulletCollisionMastrerCoderAndDestroy();
        enemyCollisionWithMasterCoder();
        enemiesReachLowerBoundary();
        playerBulletReachesTop();
        checkBulletBottomCollision();
        checkEnemyBulletPlayerCollision();

    }

    //Move enemies
    public void moveEnemies(){

        moveEnemiesSideways();
        bounceEnemy();

    }

    //Generate enemies
    public void generateEnemies(){

        int posX = 0;
        int posY = 50;

        for(int i = 0; i < enemies.length; i++){

            if (posX > 8){

                posX = 0;
                posY += 70;

            }

            int picX = 55 + posX * backgroundGameImage.getWidth()/10;

            if(this.rounds == bossRounds){

                enemies[i] = new Assembly(backgroundGameImage.getX() + backgroundGameImage.getWidth()/2 + PADDING, posY);
                bossRounds += 10;
                break;
            }

            //C++ Enemy
            if((int) Math.round(Math.random() * diff) > 9) {

                enemies[i] = new Cplusplus(picX, posY);
                posX++;

                continue;
            }

            //C# Enemy
            if((int) Math.round(Math.random() * diff) > 7) {

                enemies[i] = new Csharp(picX, posY);
                posX++;

                continue;
            }

            //Java Enemy
            if((int) Math.round(Math.random() * diff) > 4) {

                enemies[i] = new Java(picX, posY);
                posX++;

                continue;
            }

            //Javascript Enemy
            if((int) Math.round(Math.random() * diff) > 2) {

                enemies[i] = new JavaScript(picX, posY);
                posX++;

                continue;
            }

            //Python Enemy
            enemies[i] = new Python(picX, posY);
            posX++;

            continue;

        }

    }

    //Complementary Methods
    //Bullet related methods
    //Move player bullets
    public void movePlayerBullets(){

        for(int i = 0; i < player.getBullets().length; i++) {

            //if there is no bullet instance skip to next iteration
            if (player.getBullets()[i] == null) {
                continue;
            }
            // Bullet move
            player.getBullets()[i].playerBulletMove();

        }
    }

    //Enemy bullets shoot -------------------------------------------------------------------
    public void enemyBulletShot(){

        for (int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            if((int) Math.round(Math.random()* 1000) < enemies[i].getShootingProb()){

                enemies[i].enemyShoot();

            }
        }

    }

    //Enemy Bullets move
    public void enemiesBulletsMove(){

        for (int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            for(int j = 0; j < enemies[i].getBullets().length; j++){

                if(enemies[i].getBullets()[j] == null){continue;}

                enemies[i].getBullets()[j].enemyBulletMove();

            }

        }

    }

    //Collisions methods
    //Player bullet reaches top
    public void playerBulletReachesTop(){

        for(int i = 0; i < player.getBullets().length; i++) {

            if (player.getBullets()[i] == null) {continue;}

            if (player.getBullets()[i].checkPlayerBulletTopCollision()) {

                player.destroyBullet(i);

            }
        }

    }

    //Check if enemy reaches lower boundary
    public void enemiesReachLowerBoundary(){

        for (int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            if(enemies[i].enemyReachesLowerBoundary(lowerBoundary)){

                for(int j = 0; j < enemies.length; j++){

                    if(enemies[j] == null){continue;}

                    enemies[j].deleteImage();
                    enemies[j] = null;
                    this.gameOver = true;

                }


            }


        }

    }

    //Check collision between bullets and enemies and destroy them if true
    public void checkBulletCollisionWithEnemyAndDestroy(){

        for (int i = 0; i < player.getBullets().length;i++) {

            //Check collisions and destroy bullet and enemy
            if (player.getBullets()[i] == null) {continue;}

            player.checkCollisionsAndDestroy(enemies,this);

        }
    }

    //Check enemy bullet collision bottom
    public void checkBulletBottomCollision(){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            enemies[i].bulletDestroyBottom();

        }


    }

    //Check enemy bullet player collisions
    public void checkEnemyBulletPlayerCollision(){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            enemies[i].enemyBulletCheckPlayerCollision(player);

        }

    }

    //Redraw lives images
    public void redrawLives(){

        for (int i = 0; i < 3; i++){

            if(lives[i] != null) {

                lives[i].delete();

            }

        }

        for (int j = 0; j < lives.length ; j++){


            if(lives[j] == null){continue;}


            if(player.getLives() > j) {

                lives[j].draw();
                continue;

            }

            lives[j] = null;

        }

    }

    //Refresh score
    public void reDrawScore(){

        this.showScore.delete();
        this.showScore = new Text(15,20,"Score: " + score);
        this.showScore.setColor(Color.WHITE);
        this.showScore.draw();

    }

    //Increase Score
    public void increaseScore(int points){

        this.score += points;

    }

    //Checks if there are enemies left
    public boolean areEnemiesLeft(){

        int enemiesLeft = 0;

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){

                continue;

            }

            enemiesLeft++;

        }

        if(enemiesLeft < 1){

            return true;

        }

        return false;

    }

    //Enemies movement methods
    //Move enemies
    public void moveEnemiesSideways(){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            enemies[i].moveEnemy();

        }
    }

    //Enemy bounce on wall and change direction
    public void bounceEnemy(){

        for(int j = 0; j <enemies.length;j++ ) {

            if(enemies[j] == null){continue;}

            if (enemies[j].enemyImage().getX() < 20) {

                for (int i = 0; i < enemies.length; i++) {

                    if(enemies[i] == null){continue;}

                    enemies[i].enemyImage().translate(0, 5);
                    enemies[i].changeDirection(false);

                }
            }

            if (enemies[j].enemyImage().getMaxX() > backgroundGameImage.getWidth() - 10) {

                for (int i = 0; i < enemies.length; i++) {

                    if(enemies[i] == null){continue;}

                    enemies[i].enemyImage().translate(0, 5);
                    enemies[i].changeDirection(true);

                }

            }
        }
    }

    //Game over if player is dead
    public void setGameOverOnPlayerDead(){

        if(player.getLives() < 1){

            this.gameOver = true;

        }

        return;

    }

    //Set on menu false
    public void setOnMenu(){

        this.onMenu = false;

    }

    //Get on menu
    public boolean isOnMenu() {
        return onMenu;
    }

    //Draw instructions
    public void drawInstructions(){

        instructions.draw();

    }

    //Delete Instructions
    public void deleteInstructions(){

        instructions.delete();

    }

    //Generate Master Coders
    public void generateMasterCoders(){

        int picX = 60;

        for(int i = 0; i < masterCoders.length; i++){

            //Master Coders
            masterCoders[i] = new MasterCoders(picX);
            picX += 300;

            continue;

        }



    }

    //Master coders Collisions
    //Check collision between player bullets and Master Coders and destroy them if true
    public void checkPlayerBulletCollisionMasterCoderAndDestroy(){

        for (int i = 0; i < player.getBullets().length;i++) {

            //Check collisions and destroy bullet and enemy
            if (player.getBullets()[i] == null) {continue;}

            player.checkMasterCoderCollisionsAndDestroy(masterCoders,this);

        }
    }

    //Check collision between enemy bullets and Master Coders and destroy them if true
    public void checkEnemyBulletCollisionMastrerCoderAndDestroy(){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            enemies[i].enemyBulletCheckMasterCodersCollision(masterCoders);

        }

    }

    //Check enemy collision with master coders
    public void enemyCollisionWithMasterCoder(){

        for(int i = 0; i < enemies.length; i++){

            if(enemies[i] == null){continue;}

            for(int j = 0; j < masterCoders.length; j++){

                if(masterCoders[j] == null){continue;}
                if(enemies[i] == null){continue;}

                if(enemies[i].enemyImage().getMaxX() > masterCoders[j].getMasterCoderImage().getX()
                        && enemies[i].enemyImage().getMaxX() < masterCoders[j].getMasterCoderImage().getMaxX()
                        && enemies[i].enemyImage().getMaxY() > masterCoders[j].getMasterCoderImage().getY()
                        && enemies[i].enemyImage().getMaxY() < masterCoders[j].getMasterCoderImage().getMaxY()){

                    masterCoders[j].deleteImage();
                    masterCoders[j].getDyingSound().play(true);
                    masterCoders[j] = null;
                    enemies[i].enemyImage().delete();
                    enemies[i].getDyingSound().play(true);
                    enemies[i] = null;
                    break;

                }

                if(enemies[i].enemyImage().getX() > masterCoders[j].getMasterCoderImage().getX()
                        && enemies[i].enemyImage().getX() < masterCoders[j].getMasterCoderImage().getMaxX()
                        && enemies[i].enemyImage().getMaxY() > masterCoders[j].getMasterCoderImage().getY()
                        && enemies[i].enemyImage().getMaxY() < masterCoders[j].getMasterCoderImage().getMaxY()){

                    masterCoders[j].deleteImage();
                    masterCoders[j].getDyingSound().play(true);
                    masterCoders[j] = null;
                    enemies[i].enemyImage().delete();
                    enemies[i].getDyingSound().play(true);
                    enemies[i] = null;
                    break;

                }


            }

        }

    }

    //Delete Master Coders
    public void deleteMasterCoders(){

        for (int i = 0; i < masterCoders.length; i++){

            if(masterCoders[i] == null) {continue;}

            masterCoders[i].deleteImage();
            masterCoders[i] = null;

        }


    }


}
