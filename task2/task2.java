package task2;

import javafx.scene.chart.ScatterChart;
import javafx.scene.transform.Scale;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


class Main {
    public static class Point {
        public BigDecimal x;
        public BigDecimal y;

        Point(String x, String y) {
            this.x = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
            this.y = new BigDecimal(y).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile1 = args[0];
        String inputFile2 = args[1];

        List<Point> fiugura = fiuguraInit(inputFile1);
        List<Point> points = pointsInit(inputFile2);
        for (Point point : points) {
            contains(point, fiugura);
        }
    }
    // Лучше на реализацию не смотреть, сдатут нервы.
    public static void contains(Point test, List<Point> points){

        //int i;
        //int j;
        boolean result = false;

        Point A = points.get(0);
        Point B = points.get(1);
        Point C = points.get(2);
        Point D = points.get(3);

        // Я предупреждал
        try {
            if ((test.x.compareTo(A.x) == 0 && test.y.compareTo(A.y) == 0) ||
                    (test.x.compareTo(B.x) == 0 && test.y.compareTo(B.y) == 0) ||
                    (test.x.compareTo(C.x) == 0 && test.y.compareTo(C.y) == 0) ||
                    (test.x.compareTo(D.x) == 0 && test.y.compareTo(D.y) == 0)) {
                System.out.println("0");
            } else if (((((A.x.subtract(B.x).divide((A.y.subtract(B.y)))).compareTo(A.x.subtract(test.x).divide((A.y.subtract(test.y)), 2, RoundingMode.HALF_UP)) == 0) &&
                    (test.x.compareTo(A.x) > 0 && test.x.compareTo(B.x) < 0 || test.y.compareTo(A.y) > 0 && test.y.compareTo(B.y) < 0)) ||

                    (((B.x.subtract(C.x).divide((B.y.subtract(C.y)))).compareTo(A.x.subtract(test.x).divide((B.y.subtract(test.y)), 2, RoundingMode.HALF_UP)) == 0) &&
                            (test.x.compareTo(B.x) > 0 && test.x.compareTo(C.x) < 0 || test.y.compareTo(B.y) > 0 && test.y.compareTo(C.y) < 0)) ||

                    (((C.x.subtract(D.x).divide((C.y.subtract(D.y)))).compareTo(A.x.subtract(test.x).divide((C.y.subtract(test.y)), 2, RoundingMode.HALF_UP)) == 0) &&
                            (test.x.compareTo(C.x) > 0 && test.x.compareTo(D.x) < 0 || test.y.compareTo(C.y) > 0 && test.y.compareTo(D.y) < 0)) ||

                    (((D.x.subtract(A.x).divide((D.y.subtract(A.y)))).compareTo(A.x.subtract(test.x).divide((D.y.subtract(test.y)), 2, RoundingMode.HALF_UP)) == 0)) &&
                            (test.x.compareTo(D.x) > 0 && test.x.compareTo(A.x) < 0 || test.y.compareTo(D.y) > 0 && test.y.compareTo(A.y) < 0)))
             {
                System.out.println("1");
            }
        } catch (ArithmeticException e){

                boolean first = A.y.compareTo(test.y) > 0;
                boolean second = B.y.compareTo(test.y) > 0;
                BigDecimal third = B.x.subtract(A.x);
                BigDecimal fourth = test.y.subtract(A.y);
                BigDecimal fifth = B.y.subtract(A.y);
                BigDecimal six = A.x;

                boolean first1 = B.y.compareTo(test.y) > 0;
                boolean second1 = C.y.compareTo(test.y) > 0;
                BigDecimal third1 = C.x.subtract(B.x);
                BigDecimal fourth1 = test.y.subtract(B.y);
                BigDecimal fifth1 = C.y.subtract(B.y);
                BigDecimal six1 = B.x;

                boolean first2 = C.y.compareTo(test.y) > 0;
                boolean second2 = D.y.compareTo(test.y) > 0;
                BigDecimal third2 = D.x.subtract(C.x);
                BigDecimal fourth2 = test.y.subtract(C.y);
                BigDecimal fifth2 = D.y.subtract(C.y);
                BigDecimal six2 = C.x;

                boolean first3 = D.y.compareTo(test.y) > 0;
                boolean second3 = A.y.compareTo(test.y) > 0;
                BigDecimal third3 = A.x.subtract(D.x);
                BigDecimal fourth3 = test.y.subtract(D.y);
                BigDecimal fifth3 = A.y.subtract(D.y);
                BigDecimal six3 = D.x;

                if (((first != second) &&
                            (test.x.compareTo(((third).multiply(fourth).divide(fifth, 2, RoundingMode.HALF_UP)).add(six)) < 0)) ||
                    ((first1 != second1) &&
                            (test.x.compareTo(((third1).multiply(fourth1).divide(fifth1, 2, RoundingMode.HALF_UP)).add(six1)) < 0)) ||
                    ((first2 != second2) &&
                            (test.x.compareTo(((third2).multiply(fourth2).divide(fifth2, 2, RoundingMode.HALF_UP)).add(six2)) < 0)) ||
                    ((first3 != second3 &&
                            (test.x.compareTo(((third3).multiply(fourth3).divide(fifth3, 2, RoundingMode.HALF_UP)).add(six3)) < 0)))) {
                    System.out.println("2");
                } else {
                    System.out.println("3");
                }
            }
    }

    public static List<Point> fiuguraInit(String inputFile1) throws Exception {
        ArrayList<Point> fiugura = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile1));

        String strValue = reader.readLine();

        while (strValue != null) {
            String[] strArray = strValue.split(" ");
            String x = strArray[0];
            String y = strArray[1];
            fiugura.add(new Point(x, y));
            strValue = reader.readLine();
        }
        reader.close();
        return fiugura;
    }

    public static List<Point> pointsInit(String inputFile2) throws Exception {
        ArrayList<Point> pointsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile2));

        String strValue = reader.readLine();

        while (strValue != null) {
            String[] strArray = strValue.split(" ");
            String x = strArray[0];
            String y = strArray[1];
            pointsList.add(new Point(x, y));
            strValue = reader.readLine();
        }
        reader.close();
        return pointsList;
    }
}

