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

    //Variables initialization
    int index,index2 = 0;
    double x1,y1,x2,y2;
    private AtomicInteger count = new AtomicInteger(0);
    List<Coordinate> coordinates = new ArrayList<>();
    List<String> indexPaired = new ArrayList<>();
    List<Integer> usedIndex = new ArrayList<>();
    
    //Create an instance of Random class
    Random randomNumber = new Random();
    
    //Constructor receive the arraylist of coordinates
    public Edge(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        
    }
    
    
    // Synchronized Method 
    public synchronized void pair() {
        //Generate random index number
        index = randomNumber.nextInt(coordinates.size());
        index2 = randomNumber.nextInt(coordinates.size());
        
        while(usedIndex.contains(index)) {
            System.out.println("Same index has been created: "+index);
            index = randomNumber.nextInt(coordinates.size());
            System.out.println("Generating.."+index);
        }
        usedIndex.add(index);
        
        while(usedIndex.contains(index2)) {
            System.out.println("Same index has been created: "+index2);
            index2 = randomNumber.nextInt(coordinates.size());
             System.out.println("Generating index2.."+index2);
        }
        usedIndex.add(index2);
        

        x1 = coordinates.get(index).getX();
        y1 = coordinates.get(index).getY();
        
        x2 = coordinates.get(index2).getX();
        y2 = coordinates.get(index2).getY();
        
        //Add  as a string to the array list indexPaired
         indexPaired.add("("+x1+","+y1+" ----------------- "+x2+","+y2+")");
//         indexPaired.add("("+index+" , "+index2+")");
        
        //Increment the count
        count.incrementAndGet();
        
        System.out.println("Pairing coordinate 1 at: "+index+" at thread "+Thread.currentThread().getName());
        System.out.println("Pairing coordinate 2 at: "+index2+" at thread "+Thread.currentThread().getName());
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
