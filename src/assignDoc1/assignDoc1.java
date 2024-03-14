package assignDoc1;

import java.io.*;
import java.util.StringTokenizer;

public class assignDoc1 {
    static int count = 0;

    public static void Algo(int n){
        if(n == 1){
            count++;
            return;
        }
        else if(n % 2 == 1){
            count++;
            Algo(3*n+1);
        }
        else{
            count++;
            Algo(n/2);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("3nplus1.inp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("3nplus1.out"));

        String line;
        while((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken()) + 1;
            int b = Integer.parseInt(st.nextToken());

            int[] arr = new int[b>a ? (b-a-1) : (a-b-1)];

            int max = 0;

            for(int i=0; i<arr.length; i++){
                arr[i] = i + (a<b ? a : b);
                Algo(arr[i]);
                if(count >= max)
                    max = count;
                count = 0;
            }

            bw.write((a-1) + " " + b + " " + max);
            bw.write("\n");
        }

        br.close();
        bw.close();

    }
}
