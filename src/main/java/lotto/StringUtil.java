package lotto;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<Integer> toIntegerList(String str) throws IllegalArgumentException {
        List<Integer> list = new ArrayList<>();
        String[] numbers = str.split(",");
        for(String number : numbers)
            list.add(Integer.parseInt(number));
        return list;
    }
}
