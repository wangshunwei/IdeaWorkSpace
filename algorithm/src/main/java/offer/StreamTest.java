package offer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        Teacher t1 = new Teacher("1","女");
        Teacher t2 = new Teacher("2","女");
        ArrayList<Teacher> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        Student s1 = new Student("2", "男");
        Student s2 = new Student("3", "男");
        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(s1);
        list2.add(s2);
        t1.setList(list2);
        t2.setList(list2);

        /**
         * 1 一对一映射
         */
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        // 映射的过程
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        /**
         * 2 合并
         */
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),// childList
                Arrays.asList(7, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        List<Integer> list =outputStream.collect(Collectors.toList());
        // System.out.println(list.toString());
        /**
         * 3 拿到类中的name属性封装到集合中。集合中的所有的类的属性name封装到一个map中。
         */
        //Map<String,String> names = list1.stream().map(Teacher::getName).collect(Collectors.toMap(a -> a,a -> a));
        //List<String> names = list1.stream().map(Teacher::getName).collect(Collectors.toCollection(ArrayList :: new));
        //List<String> names = list1.stream().map(Teacher::getName).collect(Collectors.toList());
        //System.out.println(names);

        /**
         * filter forEach 过滤指定条件的数据 留下name为2的老师并封装到list中
         */
        List<Teacher> names =  list1.stream().filter(p -> p.getName() == "2").collect(Collectors.toList());
        System.out.println(names);
        //list1.stream().filter(p -> p.getName() == "2").forEach(p -> System.out.println(p.getName()));



        /**
         * peek
         */
        /*List<String> stream = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());*/
        //System.out.println(stream);

        /**
         * limit/skip  limit查出多少，skip从前面舍弃几个
         */

        /**
         * distinct/min/max/
         */

        /**
         *
         * sorted排序
         *
         */

        /**
         * groupingBy/partitioningBy 分组
         */

        /*// 按照字母顺序进行排序，如果是个对象就进行获取到这个属性来进行比较 或者直接在数据库进行排序。
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia","anc");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        */






    }

}
