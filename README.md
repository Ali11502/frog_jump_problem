# Frog Puzzle Solver

This project is a Java implementation of a graph-based frog puzzle solver. It uses Breadth-First Search (BFS) and Depth-First Search (DFS) algorithms to find paths from a starting state to a goal state in the puzzle.

## Overview

The frog puzzle consists of frogs and empty spaces, where an empty space is denoted by 'x'. The goal is to move the frogs in such a way that a specified goal state is reached. The program accepts strings of length 5 or 7, where each string represents the state of the puzzle with 'A', 'B', 'C' for frogs and 'x' for empty spaces.

## Features

- Generates a graph of possible states from a given starting state.
- Displays the adjacency list of the graph.
- Finds paths using BFS and DFS.
- Tracebacks the path from the goal state to the source state for both BFS and DFS.

## Requirements

- Java Development Kit (JDK) 8 or higher.

## How to Run

1. **Compile the Java files:**

    ```sh
    javac Main.java Queue.java Vertex.java Graph.java
    ```

2. **Run the program:**

    ```sh
    java Main
    ```

3. **Input Instructions:**

    - You will be prompted to enter a starting state. The state should be a string of length 5 or 7 consisting of 'A', 'B', 'C', and 'x'. Example: `AxxBC`.
    - The program will generate and display the graph.
    - You will be prompted to enter a source state and a goal state to find paths using BFS and DFS.

## Example Usage

```
Enter state to generate graph: AxBxC
AxBxC   AxBxC ---> [AxCBx, BxAxC]
BxAxC ---> [AxBxC, BCxAx]
AxCBx ---> [AxBxC, ABxCx]
BCxAx ---> [BxAxC, xCBAx]
ABxCx ---> [AxCBx, xBACx]
xCBAx ---> [BCxAx]
xBACx ---> [ABxCx]

Enter source: AxBxC
Enter goal state: xCBAx

Path using BFS:
AxBxC ---> BxAxC ---> BCxAx ---> xCBAx
TOTAL STEPS FOR SOLUTION THROUGH BFS: 3

Path using DFS:
AxBxC ---> BxAxC ---> BCxAx ---> xCBAx
TOTAL STEPS FOR SOLUTION THROUGH DFS: 3
```

## Classes and Methods

### Main Class
- `main(String[] args)`: Entry point of the program. Handles user input and initiates graph generation and search processes.

### Queue Class
- `Enqueue(Vertex obj)`: Adds a vertex to the queue.
- `Dequeue()`: Removes and returns a vertex from the queue.
- `isEmpty()`: Checks if the queue is empty.
- `isFull()`: Checks if the queue is full.

### Vertex Class
- `Vertex(StringBuilder x)`: Constructor to initialize the vertex.
- `toString()`: Returns the string representation of the vertex.
- `equals(Object obj)`: Checks if two vertices are equal.

### Graph Class
- `insert(StringBuilder ch)`: Inserts a vertex into the graph.
- `createEdge(StringBuilder x, StringBuilder y)`: Creates an edge between two vertices.
- `DisplayAdjList()`: Displays the adjacency list of the graph.
- `DisplayGraph()`: Displays the graph with neighbors.
- `GenerateGraph(String startState)`: Generates the graph from the given starting state.
- `EnqueueChildrenOf(Vertex X, Queue Q)`: Enqueues the children of a given vertex.
- `CreateNeighbours(StringBuilder str)`: Creates neighboring states of a given state.
- `MakeMove(StringBuilder str, int SourceIndex, int DestinationIndex)`: Makes a move and returns the new state.
- `PossibleMove(StringBuilder str, int SourceIndex, int DestinationIndex)`: Checks if a move is possible.
- `dfs()`: Performs DFS on the graph.
- `BFS()`: Performs BFS on the graph.
- `TraceBack(String goal, String source)`: Traces back the path from goal to source using BFS results.
- `dfsTraceback(String goal, String source)`: Traces back the path from goal to source using DFS results.
- `allUnvisit()`: Marks all vertices as unvisited.

## Notes

- Ensure that the input strings are in the correct format and of the correct length (5 or 7).
- The program assumes that the input string will contain only 'A', 'B', 'C', and 'x'.

---

Feel free to customize the README further to fit your specific requirements.