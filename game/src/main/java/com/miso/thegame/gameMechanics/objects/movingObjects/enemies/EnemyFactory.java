package com.miso.thegame.gameMechanics.objects.movingObjects.enemies;

import android.content.res.Resources;
import android.graphics.Point;

import com.miso.thegame.gameMechanics.objects.movingObjects.enemies.groundEnemies.EnemyNest;
import com.miso.thegame.gameMechanics.objects.movingObjects.enemies.spaceEnemies.Enemy_alienShip;
import com.miso.thegame.gameMechanics.objects.movingObjects.enemies.spaceEnemies.Enemy_asteroidBase;
import com.miso.thegame.gameMechanics.objects.movingObjects.enemies.spaceEnemies.Enemy_carrier;

import java.util.List;

/**
 * Created by michal.hornak on 02.03.2016.
 */
public class EnemyFactory {

    private static EnemyFactory instance = new EnemyFactory();

    private EnemyFactory(){}

    public static EnemyFactory getInstance(){
        return instance;
    }

    public void initializeEnemies(List<Enemy> enemyList, List<SingleEnemyInitialData> enemyDatas, Resources res){
        for (SingleEnemyInitialData enemyInitialData : enemyDatas) {
            enemyList.add(this.makeEnemyShip(enemyInitialData, res));
        }
    }

    public Enemy makeEnemyShip(SingleEnemyInitialData enemyInitialData, Resources res){

        switch (enemyInitialData.enemyType){
            case carrier:
                return (new Enemy_carrier(res, new Point(enemyInitialData.x, enemyInitialData.y), enemyInitialData.waypointList));

            case asteroidBase:
                return (new Enemy_asteroidBase(res, new Point(enemyInitialData.x, enemyInitialData.y)));

            case alienShip:
                return (new Enemy_alienShip(res, new Point(enemyInitialData.x, enemyInitialData.y)));

            case nest:
                return (new EnemyNest(res, new Point(enemyInitialData.x, enemyInitialData.y)));

        }
        return null;
    }
}
