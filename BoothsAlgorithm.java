import java.util.*;
public class BoothsAlgorithm {
    static String binaryToDecimal(String binary) {

        int num = Integer.parseInt(binary, 2);
        if (binary.charAt(0) == '1') {
            num -= (1 << binary.length());
        }
        return Integer.toString(num);
    }

    static String decimalToBinary(int num, int bits) {
        if (num < 0) {
            num = (1 << bits) + num;
        }
        String binary = Integer.toBinaryString(num);
        return String.format("%" + bits + "s", binary).replace(' ', '0');
    }

    static String addBinary(String a, String b, int bits) {
        int sum = Integer.parseInt(a, 2) + Integer.parseInt(b, 2);
        return decimalToBinary(sum, bits).substring(decimalToBinary(sum, bits).length() - bits);
    }

    static String[] arithmeticRightShift(String acc, String mq, String qn) {
        String combined = acc + mq + qn;
        String shifted = combined.charAt(0) + combined.substring(0, combined.length() - 1);
        return new String[]{shifted.substring(0, acc.length()), shifted.substring(acc.length(), acc.length() + mq.length()), shifted.substring(acc.length() + mq.length())};
    }

    static int boothMultiplication(int multiplicand, int multiplier, int bits) {
        String m = decimalToBinary(multiplicand, bits);
        String q = decimalToBinary(multiplier, bits);
        String qn = "0";
        String acc = "0".repeat(bits);
        String mNeg = decimalToBinary(-multiplicand, bits);

        System.out.println("Initial values: ACC=" + acc + ", Q=" + q + ", Q-1=" + qn + ", M=" + m);

        for (int i = 0; i < bits; i++) {
            if ((q.charAt(q.length() - 1) + qn).equals("10")) {
                acc = addBinary(acc, mNeg, bits);
            } else if ((q.charAt(q.length() - 1) + qn).equals("01")) {
                acc = addBinary(acc, m, bits);
            }

            String[] shifted = arithmeticRightShift(acc, q, qn);
            acc = shifted[0];
            q = shifted[1];
            qn = shifted[2];
            System.out.println("After step: ACC=" + acc + ", Q=" + q + ", Q-1=" + qn);
        }

        String result = acc + q;
        return Integer.parseInt(binaryToDecimal(result));
    }

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Multiplicand : ");
        int a=sc.nextInt();
        System.out.print("Enter multiplier :");
        int b=sc.nextInt();
        // int multiplicand = 5;
        // int multiplier = -3;
        int bits = 8;
        int result = boothMultiplication(a, b, bits);
        System.out.println("Result: " + result);
    }
}
