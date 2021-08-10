package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Cplusplus extends Enemy{

    /*
     * Constructors
     * */

    //Constructor
    public Cplusplus  (int x, int y){


        super();
        super.setEnemyImage(new Picture(x,y,"c++.png"));
        super.drawEnemyImage();
        super.init(3);
        super.setHp(2);
        super.setPointsAwarded(500);
        super.setShootingProb(3);

    }

}
