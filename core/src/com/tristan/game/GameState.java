package com.tristan.game;

public class GameState {

    // Singleton instance, handles the gamestate of the dungeon

    // Red Slime
    // Slime inventory

    // Cooking pot / "Inventory"
    // Cooking pot status

    private static GameState GAMESTATE;

    int slimeCount;
    boolean slimeState;
    boolean potState;

    public GameState(){
        slimeCount = 0;
        slimeState = true;
        potState = false;
    }

    public void slimeKilled(){
        slimeState = false;
        slimeCount++;
    }

    public boolean tapPot(){
        if(slimeCount > 0 && potState == false){
            slimeCount--;
            return true;
        }
        else if(potState == true){
            potState = false;
            return false;
        } else{
            return false;
        }
    }


    public static GameState getInstance() {
        if (GAMESTATE == null) {
            GAMESTATE = new GameState();
        }
        return (GAMESTATE);
    }

    public static void resetInstance(){
        if (GAMESTATE != null){
            GAMESTATE = null;
        }
    }




}
