import java.util.*;
import java.text.*;
public class FoldedArea {
    public static double[] reflect(double px, double py, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dx1 = px - x1;
        double dy1 = py - y1;
        double dotProduct = dx1 * dx + dy1 * dy;
        double lenSq = dx * dx + dy * dy;
        double projection = dotProduct / lenSq;
        double rx = 2 * (x1 + projection * dx) - px;
        double ry = 2 * (y1 + projection * dy) - py;
        return new double[]{rx, ry};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int area = sc.nextInt();
        sc.nextLine();
        double s = Math.sqrt(area);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        double[][] corners = {
            {0, 0},
            {0, s},
            {s, s},
            {s, 0}
        };
        List<double[]> newCorners = new ArrayList<>();
        for (double[] corner : corners) {
            newCorners.add(reflect(corner[0], corner[1], x1, y1, x2, y2));
        }
        Collections.sort(newCorners, (p1, p2) -> {
            if (Double.compare(p1[0], p2[0]) == 0) {
                return Double.compare(p1[1], p2[1]);
            }
            return Double.compare(p1[0], p2[0]);
        });
        DecimalFormat df = new DecimalFormat("0.00");
        for (double[] point : newCorners) {
            System.out.println(df.format(point[0]) + " " + df.format(point[1]));
        }
    }
}