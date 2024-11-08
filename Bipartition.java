import java.util.ArrayList;
import java.util.Arrays;

public class Bipartition {

    public static void gloutonPartition(int[] elements) {
        Arrays.sort(elements);
        ArrayList<Integer> E1 = new ArrayList<>();
        ArrayList<Integer> E2 = new ArrayList<>();

        int sum1 = 0, sum2 = 0;
        for (int i = elements.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                E1.add(elements[i]);
                sum1 += elements[i];
            } else {
                E2.add(elements[i]);
                sum2 += elements[i];
            }
        }

        System.out.println("Ensemble E1 : " + E1 + " (Somme = " + sum1 + ")");
        System.out.println("Ensemble E2 : " + E2 + " (Somme = " + sum2 + ")");
    }

    public static void main(String[] args) {
        int[] ensemble1 = {2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
        System.out.println("Partition pour ensemble1 :");
        gloutonPartition(ensemble1);

        int[] ensemble2 = {771, 121, 281, 854, 885, 734, 486, 1003, 83, 62};
        System.out.println("Partition pour ensemble2 :");
        gloutonPartition(ensemble2);
    }
}
