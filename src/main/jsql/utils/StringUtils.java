package jsql.utils;

public class StringUtils {
    public static String getRepeatedValue(String value, int repeatTimes){
        StringBuilder buffer = new StringBuilder(value.length() * repeatTimes);
        for(int loopCount=0; loopCount<repeatTimes; loopCount++)
            buffer.append(value);
        return buffer.toString();
    }
}
