import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Main {

    public static class Visitor {
        public int timeIn;
        public int timeOut;

        public Visitor(int timeIn, int timeOut) {
            this.timeIn = timeIn;
            this.timeOut = timeOut;
        }
    }

    public static void main(String[] args) {

        List<Visitor> visitorList = visitorList(args[0]);
        printIntervals(visitorList);
    }

    public static List<Visitor> visitorList(String timeFile) {
        ArrayList<Visitor> visitorList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(timeFile));
            String strValue = reader.readLine();

            while (strValue != null) {
                String[] strArray = strValue.split(" ");
                String t1 = strArray[0];
                String t2 = strArray[1];

                visitorList.add(new Visitor(convertToMinutes(t1), convertToMinutes(t2)));
                strValue = reader.readLine();
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Пустая строка / некорректный символ");
        }
        return visitorList;

    }

    public static int convertToMinutes(String t) {

        Pattern pt = Pattern.compile("(\\d+):(\\d+)");
        Matcher m = pt.matcher(t);
        int minute = 0;
        if (m.find()) {
            minute = Integer.parseInt(m.group(1)) * 60 + Integer.parseInt(m.group(2));
        }
        return minute;
    }

    public static int[] initTimeArray(List<Visitor> visitorList) {

        int[] timeArray = new int[1440];

        for (Visitor visitor : visitorList) {
            for (int k = visitor.timeIn; k < visitor.timeOut; k++) {
                timeArray[k]++;
            }
        }
        return timeArray;
    }

    public static void convertMinutetsToString(int valueIn, int valueOut) {
        int hourIn;
        int minuteIn;
        String time1;
        String time2;

        if (valueIn % 60 == 0) {
            hourIn = valueIn / 60;
            time1 = hourIn + ":" + "00";
        } else {
            hourIn = valueIn / 60;
            minuteIn = valueIn - hourIn * 60;
            if (minuteIn > 0 && minuteIn < 10) {
                time1 = hourIn + ":" + "0" + minuteIn;
            } else {
                time1 = hourIn + ":" + minuteIn;
            }
        }

        int hourOut;
        int minuteOut;

        if (valueOut % 60 == 0) {
            hourOut = valueOut / 60;
            time2 = hourOut + ":" + "00";
        } else {
            hourOut = valueOut / 60;
            minuteOut = valueOut - hourOut * 60;
            if (minuteOut > 0 && minuteOut < 10) {
                time2 = hourOut + ":" + "0" + minuteOut;
            } else {
                time2 = hourOut + ":" + minuteOut;
            }
        }

        System.out.println(time1 + " " + time2);

    }

    public static void printIntervals(List<Visitor> visitorList){
        int[] timeArray = initTimeArray(visitorList);
        int max = 0;
        ArrayList<Integer> startPoints = new ArrayList<>();
        ArrayList<Integer> finalPoints = new ArrayList<>();
        //480 - я минута это 8:00
        int dayStart = 8 * 60;
        //1200 - я минута это 20:00
        int dayEnd = 20 * 60;

        int startPointInterval;
        int finalPointInterval;

        for (int i = dayStart; i < dayEnd; i++) {
            if (timeArray[i] > max) {
                max = timeArray[i];
            }
        }

        for(int i = dayStart; i < dayEnd; i++ ) {
            if (timeArray[i] == max) {
                startPointInterval = i;
                dayStart = startPointInterval;
                startPoints.add(startPointInterval);
                for (int j = dayStart; j < dayEnd; j++ ) {
                    if (timeArray[j] < max) {
                        finalPointInterval = j;
                        i = finalPointInterval;
                        finalPoints.add(finalPointInterval);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < startPoints.size(); i++) {
            convertMinutetsToString(startPoints.get(i), finalPoints.get(i));
        }
    }
}

