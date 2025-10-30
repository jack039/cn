import java.util.*;

public class CRCDetection {
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++)
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return result.toString();
    }

    static String mod2div(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);
        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1')
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            else
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            pick++;
        }
        if (tmp.charAt(0) == '1')
            tmp = xor(divisor, tmp);
        else
            tmp = xor("0".repeat(pick), tmp);
        return tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data bits: ");
        String data = sc.next();
        System.out.print("Enter divisor: ");
        String divisor = sc.next();
        int n = divisor.length();
        String appendedData = data + "0".repeat(n - 1);
        String remainder = mod2div(appendedData, divisor);
        String codeword = data + remainder;
        System.out.println("Transmitted Codeword: " + codeword);
        System.out.print("Enter received codeword: ");
        String recv = sc.next();
        String remainderRecv = mod2div(recv, divisor);
        if (Integer.parseInt(remainderRecv) == 0)
            System.out.println("No Error Detected");
        else
            System.out.println("Error Detected");
        sc.close();
    }
}
