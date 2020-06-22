/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author Syakir
 */
public class CreateEdge implements Callable<Integer> {

    List<Coordinate> coordinates = new ArrayList<>();
    
    CreateEdge(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    
    @Override 
    public Integer call() throws Exception {
        
        //Pair coordinates index
        
        //If successful, remove the index from the array.
        
        //Return the array
        return null;  
    }
}
