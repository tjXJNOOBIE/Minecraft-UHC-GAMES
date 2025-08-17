package dev.tjxjnoobie.uhcgames.other;

public class Average {


    // "MEAN"
    public static void getAverage(String[] args, double sumof, double total) {
        double count = sumof;       // number input values
        double sum = total;    // sum of input values

        // read data and compute statistics
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            sum += value;
            count++;
        }

        // compute the average
        double average = sum / count;

        // print results
        StdOut.println(average);
    }
}


