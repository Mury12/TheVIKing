/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.MoveableObjects;

/**
 *
 * @author Andre
 */
class MobType {
    
    private TYPE type;

    public MobType(TYPE type) {
        switch(type){
            case WATER:
                
                break;
            case EARTH:
                
                break;
            case FIRE:
            
                break;
            case ICE:
                
                break;
            case PHYSICAL:
                
                break;
                
        }
    }
    
    public enum TYPE{
        WATER,
        FIRE,
        ICE,
        EARTH,
        PHYSICAL,
        NONE
    }
    
    
}
