import java.util.*;
public class DesertQueen {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        char[][] grid = new char[n][n];
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < n; i++) {
            String line = s.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                if (grid[i][j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] water = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(water[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        water[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int currentWater = water[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (grid[nx][ny] == 'M') continue;
                    int newWater = (grid[nx][ny] == 'T') ? currentWater : currentWater + 1;
                    if (newWater < water[nx][ny]) {
                        water[nx][ny] = newWater;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        System.out.println(water[end[0]][end[1]]);
    }
}