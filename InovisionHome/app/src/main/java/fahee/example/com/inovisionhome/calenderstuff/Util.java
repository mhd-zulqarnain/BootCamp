package fahee.example.com.inovisionhome.calenderstuff;

/**
 * Created by iagomendesfucolo on 24/03/17.
 */

public class Util {


    public static int getImageMood(int i){

        return 1;
    }


    public static boolean resolveDate(int monthDate, int actualMonth){

        if (monthDate != actualMonth)
            return false;

        return true;
    }

    public static boolean setImageVisiblity(int[] date, int day, int month, int year){
        return date[0] == day && date[1] == month - 1 && date[2] == year;
    }

    public static String getMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "";
    }
}
