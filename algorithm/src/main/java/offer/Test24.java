package offer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test24 {

    /**
     * // 12小时制
     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
      // 24小时制
     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
     * @param args
     */

    public static void main(String[] args)  {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       /* Date date = new Date();
        String today = sf.format(new Date());
        System.out.println(date.getDate());

        // 当前时间获得毫米值
        long time = date.getTime();
        System.out.println(time);

        //获得当前时间的月的指定某一天
        Calendar calendar1 = Calendar.getInstance();
        //当前月10号   2019-05-19 22:00:00
        calendar1.set(Calendar.DAY_OF_MONTH, 23);
        // 当前月 amount = 0为当前月， 下个月加1
        calendar1.add(Calendar.MONTH, 1);
        // 获得date类型数据,然后可以计算毫秒值
        Date da = calendar1.getTime();
        System.out.println("==========================");
        String to = sf.format(da);
        System.out.println(to);*/
        // 比较大小可以用before()  或者after() 或者直接用毫秒值。



        System.out.println("++++++++++++++++++++++++++++++++++++");
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.MONTH, 3);
        // 时 HOUR_OF_DAY  24小时制
        // calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 分
	 	//calendar.set(Calendar.MINUTE, 0);
	 	// 秒
	 	//calendar.set(Calendar.SECOND, 0);
	 	// 毫秒

        double a = 4.0000;
        int b = 3;
        double c= a/b;

        double d = (double) (Math.round(c*10000)/10000.0);
        //double c = a + b;
        System.out.println(d);


        /*String substring = t.substring(0, 7);
        System.out.println(substring);
        System.out.println(calendar.get(Calendar.MONTH) + 1);*/

        /*String str = "2019-05-19 22:00:00";
        int count = 0;
        for (int i = 0;i<str.length();i++) {
            if ("-".equals(String.valueOf(str.charAt(i)))) {
                count++;
                if (count == 2) {
                     str = str.substring(0, i);
                    break;
                }
            }
        }
        System.out.println(str);*/

//          //获取当前月最后一天
//          System.out.println("//////////////////////////////////////////////");
//          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          Calendar ca = Calendar.getInstance();
//          // 最后一天当前时间
//          ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//          String last = format.format(ca.getTime());
//          System.out.println("===============last:"+last);

        /*String str = "12";
        Integer integer = Integer.valueOf(12);
        int i = Integer.parseInt(str);*/

    }

    // 判断序列是否是二叉搜索树的后序遍历
    public static boolean verifySequnenceOfBST(int[] sequence) {

        if (sequence == null || sequence.length <= 0) {
            return false;
        }
        return verify(sequence,0,sequence.length -1);
    }

    public static boolean verify(int[] sequence, int start, int end) {
        int tempIndex = start;
        while (tempIndex < end && sequence[tempIndex] < sequence[end]) {
            // 左子树
            tempIndex++;
        }
        // 左子树遍历完啦找到啦最后一个左子树元素的索引
        int left = tempIndex;
        while (tempIndex < end && sequence[tempIndex] > sequence[end]) {
            tempIndex++;
        }
        if (tempIndex != end - 1) {
            return false;
        }
        // 左边 右边子树分别进行递归判断
        return verify(sequence,start,left -1) && verify(sequence,left,tempIndex - 1);
    }

}
