public class Algorithm_2{
    static int[] findProtein(int W, int[] P, int[] C){
        int[] results = new int[2];
        int n = P.length;
        int p_total = 0;
        for(int i=0; i<n ; i++){//Computing the total protein sum of the portions
            p_total = p_total + P[i];
        }

        int[][] X = new int[n+1][p_total+1];
        X[0][0] = 0;
        for (int p = 1 ; p<= p_total ; p++){
            X[0][p] = 1000000; //setting the upper bound to 1 million 
        }
        for (int k = 1; k<=n ; k++){
            X[k][0]= 0;
            for (int p = 1 ; p<= p_total ; p++){
                if(P[k-1] < p){
                    X[k][p] = Math.min(X[k-1][p] , X[k-1][p-P[k-1]] + C[k-1]); //we check the (k-1)-th index (for arrays P and C) because of zero-based array indexing
                }                                                              
                else{
                    X[k][p] = Math.min(X[k-1][p] , X[k-1][0] + C[k-1]);
                }
            }

        }
        int p = p_total;
        int protein = p;
        int calories =0;
        
        while (p >= 0) { 
            calories = X[n][p];
            if (calories <= W) {
                protein = p; 
                break;       // when (calories <= W) is true: break
            }
            p--; 
        }

        results[0] = protein;
        results[1] = calories;
        X = null;
        return results;
    }
    
}