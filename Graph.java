import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    ArrayList<Vertex> AdjList = new ArrayList();
    int[] queuePredecessors;
    int[] stackPredecessors;

    public void insert(StringBuilder ch) {
        Vertex X = new Vertex(ch);
        AdjList.add(X);
    }

    public void createEdge(StringBuilder x, StringBuilder y) {
        Vertex ref1 = null;
        Vertex ref2 = null;
        for (int i = 0; i < AdjList.size(); i++) {
            if (AdjList.get(i).ch.equals(x) || AdjList.get(i).ch.equals(y)) {
                if (ref1 == null) {
                    ref1 = AdjList.get(i);
                } else {
                    ref2 = AdjList.get(i);
                }
            }
        }
        if (ref1 != null && ref2 != null) {
            ref1.neighbours.add(ref2);
            ref2.neighbours.add(ref1);

        }
    }

    public void DisplayAdjList() {
        for (int i = 0; i < AdjList.size(); i++) {
            System.out.println(AdjList.get(i).ch);
        }
    }

    public void DisplayGraph() {
        for (int i = 0; i < AdjList.size(); i++) {
            Vertex x = AdjList.get(i);
            System.out.println(x + " ---> " + x.neighbours);
        }
    }

    public void GenerateGraph(String startState) {
        Queue Q = new <Vertex>Queue(10000000);
        AdjList.add(new Vertex(new StringBuilder(startState)));

        System.out.print(AdjList.get(0) + "\t");

        AdjList.get(0).visited = true;
        Q.Enqueue(AdjList.get(0));
        while (!Q.isEmpty()) {
            Vertex F = Q.Dequeue();
            EnqueueChildrenOf(F, Q);
        }
        allUnvisit();
    }

    public void EnqueueChildrenOf(Vertex X, Queue Q) {
        ArrayList<StringBuilder> list = CreateNeighbours(X.ch);
        for (int i = 0; i < list.size(); i++) {
            Vertex x = new Vertex(list.get(i));
            if (AdjList.contains(x)) {
                int indexOfAlreadyContainedState = AdjList.indexOf(x);
                X.neighbours.add(AdjList.get(indexOfAlreadyContainedState));
            } else {
                insert(list.get(i));
                X.neighbours.add(AdjList.get(AdjList.size() - 1));
            }
        }
        LinkedList<Vertex> L = new LinkedList();
        L = X.neighbours;
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i).visited == false) {
                L.get(i).visited = true;
                Q.Enqueue(L.get(i));
            }
        }
    }

    public ArrayList CreateNeighbours(StringBuilder str) {
        int IndexOfx = str.indexOf("x");
        ArrayList Neighbours = new ArrayList();
        for (int i = IndexOfx - 2; i < IndexOfx + 3; i++) {
            if (PossibleMove(str, i, IndexOfx)) {
                StringBuilder Temp = MakeMove(str, i, IndexOfx);
                Neighbours.add(Temp);
            }
        }
        return Neighbours;
    }

    public StringBuilder MakeMove(StringBuilder str, int SourceIndex, int DestinationIndex) {
        StringBuilder temp = new StringBuilder(str);
        temp.setCharAt(DestinationIndex, str.charAt(SourceIndex));
        temp.setCharAt(SourceIndex, 'x');
        return temp;
    }

    public boolean PossibleMove(StringBuilder str, int SourceIndex, int DestinationIndex) {
        if (SourceIndex < 0 || SourceIndex > str.length() - 1)
            return false;
        else {
            if (str.charAt(SourceIndex) == 'A' || str.charAt(SourceIndex) == 'B' || str.charAt(SourceIndex) == 'C') {
                return DestinationIndex > SourceIndex;
            } else {
                return DestinationIndex < SourceIndex;
            }
        }
    }

    public void dfs() {
        stackPredecessors = new int[AdjList.size()];
        Stack s = new <Vertex>Stack();
        AdjList.get(0).visited = true;
        s.push(AdjList.get(0));
        while (!s.isEmpty()) {
            Vertex F = (Vertex) s.pop();
            pushChildrenForDfs(F, s);
        }
        allUnvisit();
    }

    void pushChildrenForDfs(Vertex X, Stack Q) {
        LinkedList<Vertex> L = X.neighbours;
        int IndexOfPredecessor = AdjList.indexOf(X);
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i).visited == false) {
                L.get(i).visited = true;
                int IndexOfState = AdjList.indexOf(L.get(i));
                stackPredecessors[IndexOfState] = IndexOfPredecessor;
                Q.push(L.get(i));
            }
        }
    }

    public void BFS() {
        queuePredecessors = new int[AdjList.size()];
        Queue Q = new <Vertex>Queue(AdjList.size() + 2);
        AdjList.get(0).visited = true;
        Q.Enqueue(AdjList.get(0));
        while (!Q.isEmpty()) {
            Vertex F = (Vertex) Q.Dequeue();
            EnqueueChildrenForBFS(F, Q);
        }
        allUnvisit();
    }

    public void EnqueueChildrenForBFS(Vertex X, Queue Q) {
        LinkedList<Vertex> L = X.neighbours;
        int IndexOfPredecessor = AdjList.indexOf(X);
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i).visited == false) {
                L.get(i).visited = true;
                int IndexOfState = AdjList.indexOf(L.get(i));
                queuePredecessors[IndexOfState] = IndexOfPredecessor;
                Q.Enqueue(L.get(i));
            }
        }
    }

    public void allUnvisit() {
        for (int i = 0; i < AdjList.size(); i++) {
            if (AdjList.get(i) == null)
                continue;
            else
                AdjList.get(i).visited = false;
        }
    }

    public void TraceBack(String goal, String source) {
        int steps = 0;
        int AnsIndex = AdjList.indexOf(new Vertex(new StringBuilder(goal)));
        if (AnsIndex != -1) {
            int SourceIndex = AdjList.indexOf(new Vertex(new StringBuilder(source)));
            String str = "" + goal;
            int PredecessorIndex;
            do {

                PredecessorIndex = queuePredecessors[AnsIndex];

                str = AdjList.get(PredecessorIndex) + "--->" + str;
                AnsIndex = PredecessorIndex;
                steps++;
            } while (AnsIndex != SourceIndex);
            System.out.println();
            System.out.println(str);
            System.out.println("TOTAL STEPS FOR SOLUTION THROUGH BFS: " + steps);
        } else System.out.println("Goal state doesn't exist, make sure it does ");
    }

    public void dfsTraceback(String goal, String source) {
        int steps = 0;
        int AnsIndex = AdjList.indexOf(new Vertex(new StringBuilder(goal)));
        if (AnsIndex != -1) {


            int SourceIndex = AdjList.indexOf(new Vertex(new StringBuilder(source)));
            String str = "" + goal;
            int PredecessorIndex;
            do {
                PredecessorIndex = stackPredecessors[AnsIndex];
                str = AdjList.get(PredecessorIndex) + "--->" + str;
                AnsIndex = PredecessorIndex;
                steps++;
            } while (AnsIndex != SourceIndex);
            System.out.println();
            System.out.println(str);
            System.out.println("TOTAL STEPS FOR SOLUTION THROUGH DFS: " + steps);
        } else System.out.println("Goal state not exists");
    }
}



