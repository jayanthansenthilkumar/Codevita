import java.util.*;
public class Plague2050 {    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    public static boolean isValid(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    public static int[][] simulateInfection(int[][] grid, int N) {
        int[][] nextGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    int infectedNeighbors = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int ni = i + dx[dir], nj = j + dy[dir];
                        if (isValid(ni, nj, N) && grid[ni][nj] == 1) {
                            infectedNeighbors++;
                        }
                    }
                    if (infectedNeighbors < 2 || infectedNeighbors > 3) {
                        nextGrid[i][j] = 0;
                    } else {
                        nextGrid[i][j] = 1;
                    }
                } else if (grid[i][j] == 0) {
                    int infectedNeighbors = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int ni = i + dx[dir], nj = j + dy[dir];
                        if (isValid(ni, nj, N) && grid[ni][nj] == 1) {
                            infectedNeighbors++;
                        }
                    }
                    if (infectedNeighbors == 3) {
                        nextGrid[i][j] = 1;
                    }
                }
            }
        }
        return nextGrid;
    }
    public static int bfs(int[][] grid, int N, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            if (x == destination[0] && y == destination[1]) {
                return dist[x][y];
            }
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
                if (isValid(nx, ny, N) && grid[nx][ny] == 0 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        int[][] grid = new int[N][N];
        int[] start = new int[2];
        int[] destination = new int[2];
        for (int i = 0; i < N; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < N; j++) {
                if (row.charAt(j) == 's') {
                    start[0] = i;
                    start[1] = j;
                    grid[i][j] = 0;
                } else if (row.charAt(j) == 'd') {
                    destination[0] = i;
                    destination[1] = j;
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = (row.charAt(j) == '1' ? 1 : 0);
                }
            }
        }
        int days = 0;
        while (true) {
            int result = bfs(grid, N, start, destination);
            if (result != -1) {
                System.out.println(days + result);
                return;
            }
            grid = simulateInfection(grid, N);
            days++;
        }
    }
}