package org.academiadecodigo.sshpecials.gameobjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Csharp extends Enemy{

    /*
     * Constructors
     * */

    //Constructor
    public Csharp  (int x, int y){


        super();
        super.setEnemyImage(new Picture(x,y,"c#.png"));
        super.drawEnemyImage();
        super.init(3);
        super.setHp(1);
        super.setPointsAwarded(400);
        super.setShootingProb(5);

    }
}
