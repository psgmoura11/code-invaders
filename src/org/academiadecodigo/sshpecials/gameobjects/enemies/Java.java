package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Java extends Enemy{

    /*
     * Constructors
     * */

    //Constructor
    public Java (int x, int y){

        super();
        super.setEnemyImage(new Picture(x,y,"java.png"));
        super.drawEnemyImage();
        super.init(2);
        super.setHp(3);
        super.setPointsAwarded(300);
        super.setShootingProb(2);

    }

}
