package other;

import java.util.ArrayList;
import java.util.List;

public class UnionList {

    public static List<Integer> unionList(List<Integer> list1, List<Integer> list2) {
        ArrayList<Integer> list = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;
        int number1 = list1.get(count1);
        int number2 = list2.get(count2);
        while(count1 <= list1.size() - 1 && count2 <= list2.size() - 1) {
            if (number1 <= number2) {
                list.add(number1);
                count1 ++;
                if (count1 > list1.size() - 1 ) {
                    break;
                }
                number1 = list.get(count1);
            }

            if (number1 > number2) {
                list.add(number2);
                count2 ++;
                if (count2 > list2.size() - 1 ) {
                    break;
                }
                number1 = list.get(count2);
            }

        }
        // 处理剩余的元素
        while(count1 <= list1.size() - 1) {
            list.add(list1.get(count1));
            count1++;
        }
        while(count2 <= list2.size() - 1) {
            list.add(list2.get(count2));
            count2++;
        }
        return list;
    }

}
