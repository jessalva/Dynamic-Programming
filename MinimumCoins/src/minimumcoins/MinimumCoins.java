/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumcoins;
import java.util.Scanner;


/**
 *
 * @author jessal
 */
public class MinimumCoins {

    int total;
    int coin[];

    public MinimumCoins(int total, int[] coin) {
        this.total = total;
        this.coin = coin;
    }
    void findMinimum()
    {
        int n=coin.length;
        //Make two vector R and T
        int[] T= new int[total+1];
        //T is count the number of coins needed to get to a specified total
        //Thus the vector is of length total+1
        int[] R= new int[total+1];
        //R is used to find the last coin used to reach the total
        //initially all the value of T is infinity expect for T[0] which set as zero
        //All values of R are set to -1
        T[0]=0;
        R[0]=-1;
        for(int i=1;i<=total;i++)
        {
            T[i]=Integer.MAX_VALUE-1;
            R[i]=-1;
        }
        for(int j=0;j<n;j++)
        {
            for(int k=1;k<=total;k++)
            {
                //only if a specified coin value is greater than or equal to  total we need to reach, then only can the coin be used for consideration
                if(k>=coin[j])
                {
                    //if the coin can reached via a path which is less than the previous path
                    //Then modified the number of coin required as the new path
                    if(T[k]>(1+T[k-coin[j]]))
                    {
                        T[k]=1+T[k-coin[j]];
                        R[k]=j;
                        //R vector is set to the last coin used to reach the total
                    }
                }
            }
        }
        //if T vector's total location is still unchanged then there is no possible combination of coins to reach the required total
        if(T[total]<Integer.MAX_VALUE-1)
        {
            System.out.println("Number of Coins Required:"+T[total]);
        }
        printsequence(R);
    }
    void printsequence(int R[])
    {
        //to print the Coin combination if possible
        //if R[R.length-1] or R[total] is the value -1 then there is no possible coin combination to reach the total value
        if(R[R.length-1]==-1)
        {
            System.out.println("No Possible Coin Combination");
        }
        else
        {
            System.out.println("The Coins to be used are");
            int j;
            int start=R.length-1;
            //initially initialize start as total
            while(start!=0)
            {
                //R vector is setup to find the last coin used to reach from the previous sum to required total 
                //set variable j to extract the coin value used
                j=R[start];
                //print the current coin used
                System.out.print(coin[j]+"  ");
                start=start-coin[j];
                //Thus start is decremented by a value equal to the current coin used
            }
            System.out.println("");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a,b;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter total");
        a=sc.nextInt();
        System.out.println("Enter number of coins");
        b=sc.nextInt();
        int[] c=new int[b];
        
        System.out.println("Enter values of the coins");
        for(int i=0;i<b;i++)
        {
            c[i]=sc.nextInt();
        }
        MinimumCoins mc=new MinimumCoins(a,c);
        mc.findMinimum();
        
        // TODO code application logic here
    }
    
}
