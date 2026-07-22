public class Algorithm_1{
    static int[] findProtein(int W, int[] P, int[] C){
        int[] results = new int[2]; //used to return 2 values (in Python: tuples)
        int n = P.length; //number of portions
        int[][] X = new int[n+1][W+1]; //matrix decleration for optimal subproblem solutions (tabulation)
        
        for(int i = 0; i<=W; i++){
            X[0][i] = 0;//for 0 portions we cannot have any protein
        }
        for(int k = 1; k<=n; k++){
            X[k][0] = 0;//for 0 calories we cannot choose any portions
            for(int i = 0; i<=W; i++){
                if(C[k-1]<=i){//we can include the k-th portion
                    X[k][i] = Math.max(X[k-1][i], X[k-1][i-C[k-1]] + P[k-1]);
                }else{//if it cannot be included
                    X[k][i] = X[k-1][i];
                }
            }
        }
        results[0] = X[n][W];
        int k = n;
        int i = W;
        int calories =0;
        while(k != 0 && i > 0){    //backtracking
            if(X[k][i] != X[k-1][i]){ //Including the k-th item
                calories += C[k-1];
                i = i - C[k-1];
            }
            k = k - 1;
        }
        X = null;
        results[1] = calories;
        return results;
    }
}