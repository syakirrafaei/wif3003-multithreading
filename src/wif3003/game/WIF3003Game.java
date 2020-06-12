package wif3003.game;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Syakir
 */
public class WIF3003Game {

    public static void main(String[] args) {
        
        //Variables Initialization
        int n,m,t = 0;
        double x,y = 0;
        
        //Create an instance of Scanner class
        Scanner scan = new Scanner(System.in);
        
        //Create an array list containing the coordinate objects
        List<Coordinate> coordinates = new ArrayList<>();
        
        //Create an instance of Random class
        Random randomNumber = new Random();
        
        //Getting inputs from user
        System.out.println("Please enter the number of coordinate, n: ");
        n = scan.nextInt();
        
        System.out.println("How long will the program run in seconds, m: ");
        m = scan.nextInt();
        
        System.out.println("How many threads should be used, t ");
        t = scan.nextInt();
        
       for(int i=0; i<n; i++) {
           //Generate random number from 0 to 1000
           x = randomNumber.nextDouble()*1000; 
           y = randomNumber.nextDouble()*1000; 
           
           //We have to make sure that there are no duplicate random number
           if(coordinates.size() > 2){
               for(int j = 0; j< coordinates.size();j++){
                   double x1 = coordinates.get(j).getX();
                   double y1 = coordinates.get(j).getY();
                   for(int k = 0; k< coordinates.size(); k++){
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
       }
        
        coordinates.forEach(coordinate -> {
        System.out.println("The coordinate in x:" + coordinate.getX() + 
                " and y:" + coordinate.getY());
        });
        
        
       
    }
    
}
