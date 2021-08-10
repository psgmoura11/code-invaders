package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class JavaScript extends Enemy{

    /*
     * Constructors
     * */

    //Constructor
    public JavaScript (int x, int y){

        super();
        super.setEnemyImage(new Picture(x,y,"javascript.png"));
        super.drawEnemyImage();
        super.init(1);
        super.setHp(2);
        super.setPointsAwarded(200);
        super.setShootingProb(2);

    }

}
