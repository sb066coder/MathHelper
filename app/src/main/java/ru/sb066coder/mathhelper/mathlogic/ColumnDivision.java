package ru.sb066coder.mathhelper.mathlogic;

import androidx.annotation.NonNull;

public class ColumnDivision {

    public static String result(String divided, String divisor) {
        if (divided.equals("") || divisor.equals("") || divisor.equals("0")) {
            return "";
        } else {
            return(Long.toString(Long.parseLong(divided) / Long.parseLong(divisor)));
        }
    }
    public static String remain(String divided, String divisor) {
        if (divided.equals("") || divisor.equals("") || divisor.equals("0")) {
            return "";
        } else {
            return(Long.toString(Long.parseLong(divided) % Long.parseLong(divisor)));
        }
    }

    @NonNull
    public static String columnText(String s_divided, String s_divisor) {
        if (s_divided.equals("") || s_divisor.equals("")) {
            return "";
        }
        if (s_divisor.equals("0")) {
            return "div0";
        }
        long divided = Long.parseLong(s_divided);
        long divisor = Long.parseLong(s_divisor);
        long currentDivisor, remain, result;
        int currentRank, stringLength;
        remain = divided;
        result = divided / divisor;
        currentRank = numberOfDigits(result);
        stringLength = numberOfDigits(divided);
        StringBuilder resStr = new StringBuilder();
        resStr.append(divided).append(" ").append(divisor).append("\n"); //printing 1-st line
        for (int i = 1; i <= numberOfDigits(result); i++) {
            currentRank -= 1;
            if (digitAtPosition(result, i) == 0) {
                continue;
            }
            currentDivisor = divisor * digitAtPosition(result, i);
            remain = remain - (long)(currentDivisor * Math.pow(10,currentRank));
            writeSpace(resStr, stringLength -
                    numberOfDigits((long)(currentDivisor*Math.pow(10, currentRank))));
            resStr.append(currentDivisor);
            if (i == 1){
                writeSpace(resStr, currentRank + 1);
                resStr.append(result);
            }
            resStr.append("\n");
            writeSpace(resStr, stringLength - numberOfDigits(remain));
            resStr.append(cutRemain(remain,divisor)).append("\n");
        }
        return(resStr.toString());
    }

    public static int numberOfDigits(long n){
        return(Long.toString(n).length());
    }

    private static int digitAtPosition(long num,int pos){
        return(Integer.parseInt(Long.toString(num).substring(pos-1, pos)));
    }

    private static void writeSpace(StringBuilder resStr, int num){
        for(int i = 0; i < num; i++){
            resStr.append(" ");
        }
    }

    private static long cutRemain(long remain, long divisor){
        while (remain / divisor > 9) {
            remain = remain / 10;
        }
        return (remain);
    }
}
