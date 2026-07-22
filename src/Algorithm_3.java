import java.util.ArrayList;
import java.util.List;
public class Algorithm_3{
    static class Portion {
        int p;  // protein
        int c;  // calories

        public Portion(int p, int c) {//used to preserve the mapping between protein and calories after sorting
            this.p = p;
            this.c = c;
        }

        // helper method for the fraction p_i / c_i
        public double getRatio() {
            return (double) this.p / this.c; 
        }
    }
    
    static int[] findProtein(int W, int[] P, int[] C){
        
        int n = P.length;
        int[] results = new int[2];
        List<Portion> portions = new ArrayList<>();//list with all the portions
        for (int i = 0; i < n; i++) {
            portions.add(new Portion(P[i], C[i]));
        }

       portions.sort((o1, o2) -> Double.compare(o2.getRatio(), o1.getRatio()));//sort the portions based on the fraction p/c
        
       int protein = 0;
       int calories = 0;
       int i=0;
       while(i<n){
            if(calories + portions.get(i).c > W){
                break;//if the total calories goal is surpassed
            }
            else{
                calories = calories + portions.get(i).c;
                protein = protein + portions.get(i).p;
            }
            i++;
        }
       
       int maxProtein = 0;
       int maxCalories = 0;
       for(int k = 0; k<n ; k++){//find the portion with max-protein 
        if(P[k]>maxProtein){
            maxProtein = P[k];
            maxCalories = C[k];
        }                      
       }
       if(protein>maxProtein){//we check if the algorithm's solution has more protein than the portion with the max-protein quantity
        results[0] = protein;
        results[1] = calories;
       }
       else{
        results[0] = maxProtein;
        results[1] = maxCalories;
       }
       
       return results;
    }
}