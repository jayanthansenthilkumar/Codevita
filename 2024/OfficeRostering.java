import java.util.*;

public class OfficeRostering {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        List<List<Integer>> f = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            f.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            f.get(a).add(b);
            f.get(b).add(a);
        }

        int k = s.nextInt();
        s.close();

        boolean[] o = new boolean[n];
        Arrays.fill(o, true);
        int r = n;
        int d = 1;

        while (r < k) {
            boolean[] t = new boolean[n];
            int c = 0;

            for (int i = 0; i < n; i++) {
                int wf = 0;
                for (int j : f.get(i)) {
                    if (o[j]) {
                        wf++;
                    }
                }
                if (o[i]) {
                    t[i] = (wf == 3);
                } else {
                    t[i] = (wf < 3);
                }

                if (t[i]) {
                    c++;
                }
            }

            r += c;
            o = t;
            d++;
        }

        System.out.print(d);
    }
}
