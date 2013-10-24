package utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 13:41
 */
public class Util {

    public static int generateRandomNumber(int start, int end) {
        Random random = new Random();
        return start + random.nextInt(end);
    }

    public static double getMean(ArrayList<Double> data) {
        double sum = 0.0;
        for (double a : data)
            sum += a;
        return sum / data.size();
    }

    public static double getVariance(ArrayList<Double> data) {
        double mean = getMean(data);
        double temp = 0;
        for (double a : data)
            temp += (mean - a) * (mean - a);
        return temp / data.size();
    }

    public static double getStdDev(ArrayList<Double> data) {
        return Math.sqrt(getVariance(data));
    }
}
