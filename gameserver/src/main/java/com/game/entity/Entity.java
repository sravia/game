package com.game.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    private int index;

    public Entity() {

    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    //public Location getLocation() {
    //    return location;
    //}

}
