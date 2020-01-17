package offer;

/**
 * 用StringBuilder
 * 也可以用replaceAll(" ","%20")
 *
 */
public class Test04 {

    public String replaceSpace(String str) {

        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

}
