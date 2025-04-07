import java.util.*;

public class StringPuzzle {

    public static Map<String, Integer> a(Map<String, List<String>> b, String c, Set<String> d) {
        Map<String, Integer> e = new HashMap<>();
        Queue<String> f = new LinkedList<>();
        f.add(c);
        e.put(c, 1);
        while (!f.isEmpty()) {
            String g = f.poll();
            int h = e.get(g);
            for (String i : b.getOrDefault(g, new ArrayList<>())) {
                if (!e.containsKey(i)) {
                    e.put(i, h + 1);
                    f.add(i);
                }
            }
        }
        for (String j : d) {
            e.putIfAbsent(j, -1);
        }
        return e;
    }

    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        
        int l = k.nextInt();
        k.nextLine();

        Map<String, List<String>> m = new HashMap<>();
        Set<String> n = new HashSet<>();
        
        for (int o = 0; o < l; o++) {
            String[] p = k.nextLine().split(" ");
            String q = p[0];
            String r = p[1];
            m.putIfAbsent(q, new ArrayList<>());
            m.get(q).add(r);
            n.add(q);
            n.add(r);
        }

        String[] s = k.nextLine().split(" ");
        
        Set<String> t = new HashSet<>(n);
        for (String u : m.keySet()) {
            for (String v : m.get(u)) {
                t.remove(v);
            }
        }

        int w = 0;
        Map<String, Integer> x = new HashMap<>();
        
        for (String y : t) {
            Map<String, Integer> z = a(m, y, n);
            x.putAll(z);
        }

        for (String aa : s) {
            w += x.getOrDefault(aa, -1);
        }

        System.out.print(w);
    }
}
