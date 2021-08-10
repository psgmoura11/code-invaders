package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Python extends Enemy{
    /*
     * Constructors
     * */

    //Constructor
    public Python (int x, int y){

        super();
        super.setEnemyImage(new Picture(x,y,"python.png"));
        super.drawEnemyImage();
        super.init(1);
        super.setHp(1);
        super.setPointsAwarded(100);
        super.setShootingProb(1);

    }

}
