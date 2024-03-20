package assignDoc2;

import java.io.*;

public class trip {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("trip.inp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("trip.out"));


        while(true){
            int n = Integer.parseInt(br.readLine());

            if(n == 0)
                break;

            double[] person = new double[n];
            double sum = 0;
            double avg;
            double result;
            double a = 0, b = 0;

            for(int i=0; i<n; i++){
                person[i] = Double.parseDouble(br.readLine());
                sum += person[i];
            }

            avg = Math.round(sum / n*100) / 100.0;

            for(int i=0; i<n; i++){
                if(person[i] - avg >= 0)
                    a += (person[i] - avg);
                else
                    b += (avg - person[i]);
            }

            bw.write(String.format("$%.2f\n", Math.min(a,b)));

        }
        br.close();
        bw.close();
    }
}
