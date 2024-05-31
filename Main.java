import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new
                Scanner(System.in);

        String starting, source, goal;
        System.out.print("Enter state to generate graph: ");
        starting = sc.next();
        Graph X = new Graph();
        X.GenerateGraph(starting);
        X.DisplayGraph();
        System.out.print("Enter source: ");
        source = sc.next();
        System.out.print("Enter goal state: ");
        goal = sc.next();
        X.BFS();
        X.TraceBack(goal, source);
        X.dfs();
        X.dfsTraceback(goal, source);

    }
}
