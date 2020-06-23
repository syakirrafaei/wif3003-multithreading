/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author Syakir
 */
public class Edge implements Runnable {

    int index,index2 = 0;
    private AtomicInteger count = new AtomicInteger(0);
    List<Coordinate> coordinates = new ArrayList<>();
    List<String> indexPaired = new ArrayList<>();
    
    //Create an instance of Random class
    Random randomNumber = new Random();
    
    
    public Edge(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        
    }
    
    
    // Synchronized Method 
    public synchronized void pair() {
        //Generate random index number
        index = randomNumber.nextInt(coordinates.size());
        index2 = randomNumber.nextInt(coordinates.size());
        
        
        //Unset the index
        coordinates.remove(index);
        coordinates.remove(index2);
        
        //Add  as a string to the array list indexPaired
        indexPaired.add("("+index+" , "+index2+")");
        
        //Increment the count
        count.incrementAndGet();
        
        System.out.println("Removing index 1: "+index+" at thread "+Thread.currentThread().getName());
        System.out.println("Removing index 2: "+index2+" at thread "+Thread.currentThread().getName());
        System.out.println("Finished... ");
        
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
    
    public List<String> getIndexPaired() {
        return indexPaired;
    }

    public int getCount() {
        return count.get();
    }
    
    @Override
    public void run() {
        System.out.println("Run method");
    }
}
