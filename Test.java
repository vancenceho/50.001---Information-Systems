package ProblemSets;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.println(convertStartDate("2024-04-16T14:45:39.869"));

        Pair<Integer, BigDecimal> pair = new Pair(1, new BigDecimal("1.23"));
        Pair a  = pair.swap();
        System.out.println(a.getFirst());
    }

    public static String convertStartDate(String startDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

            Date date = sdf.parse(startDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM HH:mm");

            String output = outputFormat.format(date);

            return output;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

class Pair<F,S>{
    private F first;
    private S second;

    public Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    public Pair<S, F> swap(){
        return new Pair<S, F>(this.second, this.first);
    }
}
