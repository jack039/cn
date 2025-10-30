import java.util.*;

public class HammingCode {
    static int[] generateHammingCode(int[] data) {
        int m = data.length, r = 0;
        while (Math.pow(2, r) < (m + r + 1)) r++;
        int[] hamming = new int[m + r];
        int j = 0, k = m - 1;
        for (int i = 0; i < hamming.length; i++) {
            if ((i + 1) == Math.pow(2, j)) {
                hamming[i] = 0;
                j++;
            } else {
                hamming[i] = data[k--];
            }
        }
        for (int i = 0; i < r; i++) {
            int parityPos = (int) Math.pow(2, i) - 1;
            int parity = 0;
            for (int x = parityPos; x < hamming.length; x += 2 * (parityPos + 1)) {
                for (int y = x; y < x + parityPos + 1 && y < hamming.length; y++)
                    parity ^= hamming[y];
            }
            hamming[parityPos] = parity;
        }
        return hamming;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of data bits: ");
        int n = sc.nextInt();
        int[] data = new int[n];
        System.out.println("Enter data bits (MSB first):");
        for (int i = 0; i < n; i++) data[i] = sc.nextInt();
        int[] hamming = generateHammingCode(data);
        System.out.print("Generated Hamming Code: ");
        for (int bit : hamming) System.out.print(bit);
        System.out.println();
        System.out.print("Enter received code: ");
        int[] recv = new int[hamming.length];
        for (int i = 0; i < recv.length; i++) recv[i] = sc.nextInt();
        int errorPos = 0;
        for (int i = 0; i < Math.log(hamming.length) / Math.log(2); i++) {
            int parityPos = (int) Math.pow(2, i) - 1;
            int parity = 0;
            for (int x = parityPos; x < recv.length; x += 2 * (parityPos + 1)) {
                for (int y = x; y < x + parityPos + 1 && y < recv.length; y++)
                    parity ^= recv[y];
            }
            if (parity != 0)
                errorPos += parityPos + 1;
        }
        if (errorPos == 0)
            System.out.println("No Error Detected");
        else {
            System.out.println("Error at position: " + errorPos);
            recv[errorPos - 1] ^= 1;
            System.out.print("Corrected Code: ");
            for (int bit : recv) System.out.print(bit);
            System.out.println();
        }
        sc.close();
    }
}
