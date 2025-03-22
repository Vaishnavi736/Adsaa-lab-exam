import java.util.*;

public class GraphColoring {
static int graph[][];
static int colors[];
static int V;

public GraphColoring(int graph[][], int V) {  
    this.graph = graph;  
    this.V = V;  
    colors = new int[V];  
}  

public void graphColoring(int m) {  
    Arrays.fill(colors, 0);  
    if (!graphColoringUtil(m, 0)) {  
        System.out.println("Solution doesn't exist with given colors!!");  
        return;  
    }  
    printSolution();  
}  

public static boolean graphColoringUtil(int m, int v) {  
    if (v == V)  
        return true;  

    for (int c = 1; c <= m; c++) {  
        if (isSafe(v, c)) {  
            colors[v] = c;  

            if (graphColoringUtil(m, v + 1)) {  
                return true;  
            }  
            // Backtrack  
            colors[v] = 0;  
        }  
    }  
    return false; // No solution   
}  
public static boolean isSafe(int v, int c) {  
    for (int i = 0; i < V; i++) {  
        if (graph[v][i] == 1 && colors[i] == c) {  
            return false; // If adjacent vertex has the same color  
        }  
    }  
    return true;  
}  

public void printSolution() {  
    System.out.println("Solution is:");  
    for (int i = 0; i < V; i++) {  
        System.out.println("Vertex " + i + " ---> Color " + colors[i]);  
    }  
}  

public static void main(String[] args) {  
    Scanner sc = new Scanner(System.in);  
    System.out.println("Enter the number of vertices:");  
    int n = sc.nextInt();  

    int[][] graph = new int[n][n];  
    System.out.println("Enter the graph matrix (row by row):");  
    for (int i = 0; i < n; i++) {  
        for (int j = 0; j < n; j++) {  
            graph[i][j] = sc.nextInt();  
        }  
    }  

    System.out.print("Enter the number of colors: ");  
    int m = sc.nextInt();  

    GraphColoring mcoloring = new GraphColoring(graph, n);  
    mcoloring.graphColoring(m);  

    sc.close();  
}

}

