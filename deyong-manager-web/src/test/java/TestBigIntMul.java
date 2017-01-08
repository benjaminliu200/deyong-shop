import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2017/1/8.
 */
public class TestBigIntMul {
    public String bigIntMul(BigInteger b1, BigInteger b2) {
        // 将b2 转化为字符数组
        String num = b2.toString();
        List<Integer> result = new ArrayList<>();
        char[] arrs = b2.toString().toCharArray();
        if (b1.intValue() == 1) {
            return b2.toString();
        }
        char[] numChars = num.toCharArray();            // 和转化为 字符数组
        StringBuffer tempSB = new StringBuffer();       // 临时存储每一次的和
        StringBuffer sb = new StringBuffer();           // 结果
        for (int i = 1; i < b1.intValue(); i++) {
            result = new ArrayList<>();                 // 清除每一次加法的集合
            int flag = 0; // 进位
            for (int m = arrs.length -1; m > -1; m--) {
                int i1 = flag + Integer.parseInt(String.valueOf(numChars[m])) + Integer.parseInt(String.valueOf(arrs[m]));
                flag = i1>9? 1:0;
                result.add(i1%10);
            }

            for (int m = numChars.length - 1; m > arrs.length - 1; m++) {
                int i2 = flag + numChars[m];
                flag = i2>9? 1:0;
                result.add(i2 % 10);
            }
            for (int j = result.size() - 1; j > -1; j--) {
                tempSB.append(result.get(j));
            }
            numChars = tempSB.toString().toCharArray();
            tempSB = new StringBuffer();
        }

        for (int i = result.size() - 1; i > -1; i--) {
            sb.append(result.get(i));
        }
        return sb.toString();
    }

    public String shiTran16(int val) {
        char[] data = new char[]{'0', '1','2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        List<Character> list = new ArrayList<>();
        while (val / 16 != 0) {
            list.add(data[val % 16]);
            val = val / 16;
        }
        list.add(data[val]);
        StringBuffer sb = new StringBuffer("Ox");

        for (int i = list.size() -1 ;i > -1; i--) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(new TestBigIntMul().shiTran16(17));
    }

    @Test
    public void testResurt() {
        String s = new TestBigIntMul().bigIntMul(new BigInteger("3"), new BigInteger("241414141413678597806"));
        System.out.println(s);
        BigInteger b2 = new BigInteger("241414141413678597806");
        BigInteger b3 = b2.multiply(new BigInteger("3"));
        System.out.println(b3);
    }

}
