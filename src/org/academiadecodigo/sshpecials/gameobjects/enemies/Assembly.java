package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Assembly extends Enemy{

    /*
     * Constructors
     * */

    //Constructor
    public Assembly  (int x, int y){


        super();
        super.setEnemyImage(new Picture(x,y,"assembly.png"));
        super.drawEnemyImage();
        super.init(5);
        super.setHp(10);
        super.setShootingProb(10);
        super.setPointsAwarded(1000);

    }
}
