import java.util.*;

class BFSAdjMatrix {
    private int v;
    private int[][] adj;

    public BFSAdjMatrix(int v) {
        this.v = v;
        adj = new int[v][v];
    }

    public void BFS(int start) {
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vis = queue.poll();
            System.out.print(vis + " ");

            for (int i = 0; i < v; i++) {
                if (adj[vis][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int v = scanner.nextInt();

        BFSAdjMatrix graph = new BFSAdjMatrix(v);

        System.out.println("Enter the adjacency matrix (" + v + "x" + v + "): ");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                graph.adj[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the starting vertex for BFS: ");
        int start = scanner.nextInt();

        System.out.println("BFS traversal starting from vertex " + start + ":");
        graph.BFS(start);

        scanner.close();
    }
}
