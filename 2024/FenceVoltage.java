import java.util.*;
public class FenceVoltage {
    public static int orientation(int px, int py, int qx, int qy, int rx, int ry) {
        return (qy - py) * (rx - qx) - (qx - px) * (ry - qy);
    }
    public static boolean doIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int o1 = orientation(x1, y1, x2, y2, x3, y3);
        int o2 = orientation(x1, y1, x2, y2, x4, y4);
        int o3 = orientation(x3, y3, x4, y4, x1, y1);
        int o4 = orientation(x3, y3, x4, y4, x2, y2);
        return o1 * o2 < 0 && o3 * o4 < 0;
    }
    public static int calculateVoltage(List<int[]> wires, Set<int[]> intersectionPoints) {
        int totalVoltage = 0;
        for (int[] intersection : intersectionPoints) {
            int count = 0;
            int minDistance = Integer.MAX_VALUE;

            for (int[] wire : wires) {
                int x1 = wire[0], y1 = wire[1], x2 = wire[2], y2 = wire[3];
                if (Math.min(x1, x2) <= intersection[0] && intersection[0] <= Math.max(x1, x2) &&
                    Math.min(y1, y2) <= intersection[1] && intersection[1] <= Math.max(y1, y2)) {
                    count++;
                    int dist1 = Math.abs(intersection[0] - x1) + Math.abs(intersection[1] - y1);
                    int dist2 = Math.abs(intersection[0] - x2) + Math.abs(intersection[1] - y2);
                    minDistance = Math.min(minDistance, Math.min(dist1, dist2));
                }
            }
            totalVoltage += count * minDistance;
        }
        return totalVoltage;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        List<int[]> wires = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            wires.add(new int[]{x1, y1, x2, y2});
            sc.nextLine();
        }
        Map<String, Integer> animalThresholds = new HashMap<>();
        String[] animals = sc.nextLine().split(" ");
        for (String animal : animals) {
            String[] animalData = animal.split(":");
            animalThresholds.put(animalData[0], Integer.parseInt(animalData[1]));
        }
        String touchedAnimal = sc.nextLine();
        Set<int[]> intersectionPoints = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int[] wire1 = wires.get(i);
                int[] wire2 = wires.get(j);
                if (doIntersect(wire1[0], wire1[1], wire1[2], wire1[3], wire2[0], wire2[1], wire2[2], wire2[3])) {
                    int[] intersection = new int[]{
                        Math.max(Math.min(wire1[0], wire1[2]), Math.min(wire2[0], wire2[2])),
                        Math.max(Math.min(wire1[1], wire1[3]), Math.min(wire2[1], wire2[3]))
                    };
                    intersectionPoints.add(intersection);
                }
            }
        }
        int totalVoltage = calculateVoltage(wires, intersectionPoints);
        int animalThreshold = animalThresholds.get(touchedAnimal);
        if (totalVoltage >= animalThreshold) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        int animalsDying = 0;
        for (int threshold : animalThresholds.values()) {
            if (threshold < totalVoltage) {
                animalsDying++;
            }
        }
        double probability = (double) animalsDying / animalThresholds.size();
        System.out.print("%.2f\n", probability);

        sc.close();
    }
}