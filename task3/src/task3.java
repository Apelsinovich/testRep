
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static class Cash {
        ArrayList<Double> cash = new ArrayList<Double>();
        public int id = 0;

        public Cash  (String fileName, int id){
            this.id = id;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String interval = reader.readLine();
                while (interval != null) {
                    if (interval.equals("")){
                        break;
                    }
                    this.cash.add(Double.parseDouble(interval));
                    interval = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e){
                System.out.println("Файл по заданному пути не найден");
            } catch (NumberFormatException e){
                System.out.println("Строка либо пуста");
            } catch (IOException e){
                System.out.println("IOExceptions - иное исключения ввода-вывода");
            }
        }
    }
    public static void main(String[] args) {
	// write your code here
        String[] fileArray = new String[5];
        for (int i = 0; i < fileArray.length; i++) {
            fileArray[i] = args[i];
        }
        List<Cash> cashList = initCash(fileArray);
        BigDecimal sum = new BigDecimal(0.000).setScale(3, BigDecimal.ROUND_HALF_UP);
        int interval = 0;
            for (int i = 0; i < 16; i++) {
                double A = cashList.get(0).cash.get(i);
                double B = cashList.get(1).cash.get(i);
                double C = cashList.get(2).cash.get(i);
                double D = cashList.get(3).cash.get(i);
                double F = cashList.get(4).cash.get(i);
                BigDecimal newMax = maxValue(A,B,C,D,F);
                if (newMax.compareTo(sum) > 0){
                    sum = newMax;
                    interval = i + 1;
                }
            }
        System.out.println(interval);
    }
    public static List<Cash> initCash(String[] fileArray){
        List<Cash> cashList= new ArrayList<>();
        for (int i = 0; i < 5; i++){
            cashList.add(new Cash(fileArray[i], i));
        }
        return  cashList;
    }
    public static BigDecimal  maxValue(double d1, double d2, double d3, double d4, double d5){
        BigDecimal bigD1 = new BigDecimal(d1).setScale(3, RoundingMode.HALF_UP);
        BigDecimal bigD2 = new BigDecimal(d2).setScale(3, RoundingMode.HALF_UP);
        BigDecimal bigD3 = new BigDecimal(d3).setScale(3, RoundingMode.HALF_UP);
        BigDecimal bigD4 = new BigDecimal(d4).setScale(3, RoundingMode.HALF_UP);
        BigDecimal bigD5 = new BigDecimal(d5).setScale(3, RoundingMode.HALF_UP);

        BigDecimal sum = bigD1.add(bigD2).add(bigD3).add(bigD4).add(bigD5);
        return sum;
    }
}
