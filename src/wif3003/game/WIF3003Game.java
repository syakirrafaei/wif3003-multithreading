package wif3003.game;



import java.awt.Point;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Syakir
 */
public class WIF3003Game {

    
    public static void main(String[] args) throws InterruptedException {
        
        //Variables Initialization
        int n = 0;
        int m,t = 0;
        double x,y = 0;
        Integer result = 0;
        
        
        //Create an instance of Scanner class
        Scanner scan = new Scanner(System.in);
        
        //Create an array list containing the coordinate objects
        List<Coordinate> coordinates = new ArrayList<>();
        
        
        //Create an instance of Random class
        Random randomNumber = new Random();
        
        //Error handling
        try {
            //Getting inputs from user
            System.out.println("Please enter the number of coordinate, n: ");
            n = scan.nextInt();

            while(n<1){
                System.out.println("Please enter the number of coordinate, n BIGGER THAN 0: ");
                n = scan.nextInt(); 
            }
        }
        catch (InputMismatchException ex) // If exception, try again.
        {
            System.out.println("Invalid input! You have to enter a number...restarting the program\n");
            main(args);
            
        }
        
        try {
             System.out.println("How long will the program run in seconds, m: ");
             m = scan.nextInt();

            while(m<1){
                System.out.println("How long will the program run in seconds, m BIGGER THAN 0: ");
                m = scan.nextInt(); 
            }
        }
        catch (InputMismatchException ex) // If exception, try again.
        {
            System.out.println("Invalid input! You have to enter a number...restarting the program\n");
            main(args);
            
        }
        
        try {
            System.out.println("How many threads should be used, t ");
            t = scan.nextInt();

            //Make sure that t is always less than n and t greater than 0.
            while(t>=n && t>0) {
                System.out.println("Please input value of t less than "+n);
                System.out.println("How many threads should be used, t ");
                t = scan.nextInt();
            }
        }
        catch (InputMismatchException ex) // If exception, try again.
        {
            System.out.println("Invalid input! You have to enter a number...restarting the program\n");
            main(args);
            
        }
       
        
        
        
        
        
        
        //Create an instance of ExecutorService with thread pool of size, t
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        
        //Looping based on the number of coordinates, n 
        for(int i=0; i<n; i++) {
           //Generate random n-umber from 0 to 1000
           x = randomNumber.nextDouble()*1000; 
           x = Math.round(x * 100.0) / 100.0;

           y = randomNumber.nextDouble()*1000; 
           y = Math.round(y * 100.0) / 100.0;
           
           //We have to make sure that there are no duplicate random number
           if(coordinates.size() > 2){
               for(int j = 0; j< coordinates.size();j++){
                   double x1 = coordinates.get(j).getX();
                   double y1 = coordinates.get(j).getY();
                   for(int k = 0; k< coordinates.size()-1; k++){
                        double x2 = coordinates.get(k+1).getX();
                        double y2 = coordinates.get(k+1).getY();
                        if(x1==x2){
                            x = randomNumber.nextDouble()*1000; 
                        }
                        if(y1==y2){
                            y = randomNumber.nextDouble()*1000;
                        }
                   }
               }
           }
          
           //Create new object of Coordinate class
           Coordinate myCoordinate = new Coordinate(x, y);
           
           //Add the object into the ArrayList coordinates
           coordinates.add(myCoordinate);
           
        } //End of loop
        
        
        
        /* 
        Section 2
        
        
        
        
        This part is for the creating edges.
        
        
        
        
        
        */
        System.out.println("");
        
        //Original Coordinates.
        int[] iarr = {0};
        coordinates.forEach(coordinate -> {
        System.out.println("The coordinate in index "+iarr[0]+" x:" + coordinate.getX() + 
                " and y:" + coordinate.getY());
        iarr[0]++;
        });
        
        System.out.println("");
        System.out.println("*********************************");
        System.out.println("Generating edges........");
        
        //Create object for class Edge.
        Edge edges = new Edge(coordinates);
        
        //The maximum no of task.
        for(int i=0; i<n/2; i++) {
            
           executorService.submit(() -> edges.pair());      
        }
        
        
         
        //Shutdown the executor service.
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(t , TimeUnit.SECONDS)) {
                System.out.println("SHUTDOWN IMMEDIATELY BECAUSE OF TIMEOUT!");
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        
        //Create a new array list to store the updated coordinates.
        List<Coordinate> newCoordinates = edges.getCoordinates();
        
        //Create a new array list to store the index paired.
        List<String> indexPaired = edges.getIndexPaired();
        
        //Print
        System.out.println("");
        
        indexPaired.forEach(paired -> {
            System.out.println(paired);
        });
        
        System.out.println("");
        
        
      
        
        //Print the count
        System.out.println("Count: "+edges.getCount());
         

       
    }
    
}
