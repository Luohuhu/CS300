//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Treasure Hunt
// Course:   CS 300 Fall 2020
//
// Author:   Xianfu Luo
// Email:    xluo96@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A class to describe actions.
 * @author Xianfu Luo
 * @version 1.0
 */

public class Action {
    private String message; // message printed by this action (or null to do nothing)
    private InteractiveObject object = null;
    public Action(InteractiveObject object){
        this.object=object;
    }
    public Action(String message, InteractiveObject object){
        this.object=object;
        this.message = message;
    }
    public Action(String message) {
        this.message = message;
    } // create and initialize this new action
    public void act(ArrayList<InteractiveObject> gameObjects) {  // when message is not null,
        // message is printed to System.out
        if (object!=null){
            object.activate();
            gameObjects.add(object);
            object = null;
        }
        if (message!=null){
            System.out.println(message);
        }
    }
}
