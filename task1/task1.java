package task1;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

class Main {

    public static final String pattern = "#0.00";

    public static void main(String[] args) {
        String fileNameIn = args[0];
        callFunctions(createSortedArrayListFromFile(fileNameIn));
    }

    public static void callFunctions(ArrayList<Integer> valueList){
        find90Percental(valueList);
        findMedianasValue(valueList);
        findMaxValue(valueList);
        findMinValue(valueList);
        findAverageValue(valueList);
    }

    //функция поиска перценитиль 90
    public static void find90Percental(ArrayList<Integer> valueList) {

        int valueCount = valueList.size();

        /* x = ((P/100) * (N - 1)) + 1
            где P = процент
                N - порядковый номер (valueCount - 1) */

        double percentile90 = ((double) 90 / 100) * (valueCount - 2) + 1;
        System.out.println(formatDouble(percentile90));

    }

    //функция поиска медианного значения
    public static void findMedianasValue(ArrayList<Integer> valueList) {

        double mediana = 0.0;

        if (valueList.size() % 2 == 0) {
            int firstValue = valueList.get((valueList.size() / 2) - 1);
            int secondValue = valueList.get((valueList.size() / 2));
            mediana = ((double) firstValue + secondValue) / 2;
        } else {
            mediana = (double) valueList.get((valueList.size() - 1) / 2);
        }
        System.out.println(formatDouble(mediana));

    }

    //функция поиска максимального значения
    public static void findMaxValue(ArrayList<Integer> valueList) {

        double doubleMaxValue = valueList.get(valueList.size() - 1);
        System.out.println(formatDouble(doubleMaxValue));

    }

    //функция поиска минимального значения
    public static void findMinValue(ArrayList<Integer> valueList) {

        double doubleMinValue = valueList.get(0);
        System.out.println(formatDouble(doubleMinValue));
    }

    //функция поиска среднего значения
    public static void findAverageValue(ArrayList<Integer> valueList) {

        int totalValue = 0;
        int listSize = valueList.size();
        for (Integer integer : valueList) {
            totalValue += integer;
        }

        try {

            double doubleAverageValue = ((double) totalValue / listSize);
            System.out.println(formatDouble(doubleAverageValue));
        } catch (Exception e) {
            System.out.println("Деление на ноль");
        }

    }

    // формат double значений с точностью до двух знаков с раздлителем "."
    public static String formatDouble(double value) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat format = new DecimalFormat(pattern, symbols);

        return format.format(value);
    }

    //чтение файла с инициализацией списка
    public static ArrayList<Integer> createSortedArrayListFromFile(String fileNameIn) {
        ArrayList<Integer> valueList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileNameIn));
            String strValue = reader.readLine();
            while (strValue != null) {
                valueList.add(Integer.parseInt(strValue));
                strValue = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл по заданному пути не найден");
        } catch (IOException e) {
            System.out.println("Строки файла пусты");
        }

        Collections.sort(valueList);

        return  valueList;
    }

}
