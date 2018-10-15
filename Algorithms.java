import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Map;

public class Algorithms {

    static int[][] hexArray = new int[233][2]; // The Rectangular Hexagonal Grid
    static int[] values = new int[233];
    static int[] keys = new int[233];
    static int[][] position = new int[15][16];
    static Map<Integer, Integer> map = new HashMap<>();
    static int[] leftEdge = new int[16];
    static int[] rightEdge = new int[16];
    static double[][] adj_matrix = new double[234][234];
    static double[] hexFinal = new double[233];
    static double[] path = new double[233];
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(new BufferedReader(new FileReader("C:\\Users\\britt\\IdeaProjects\\ForwardMethod\\src\\input")));
        while (scan.hasNextLine()) {
            for (int i = 0; i < hexArray.length; i++) {
                String[] string = scan.nextLine().trim().split(" ");
                for (int j = 0; j < string.length; j++) {
                    hexArray[i][j] = Integer.parseInt(string[j]);
                }
            }
        }

        for (int j = 0; j < hexArray.length; j++) {
            values[j] = hexArray[j][1];
            keys[j] = hexArray[j][0];
            map.put(keys[j], values[j]);
        }

        int eKey = 1;
        int reset;

        int oKey = 1;

        for (int i = 0; i <= 14; i += 2) {
            reset = eKey;
            for (int j = 0; j <= 15; j++) {
                if (i == 0) {
                    leftEdge[j] = keys[eKey - 1];
                }
                if (i == 14) {
                    rightEdge[j] = keys[eKey - 1];
                }
                position[i][j] = keys[eKey - 1];
                eKey += 15;
            }
            eKey = reset + 1;
            oKey += 1;
        }

        for (int i = 1; i <= 13; i += 2) {
            reset = oKey;
            for (int j = 0; j <= 14; j++) {
                position[i][j] = keys[oKey - 1];
                oKey += 15;
            }
            oKey = reset + 1;
        }

        for(int i = 0; i < 233; i++) {
            for (int j = 0; j < 233; j++) {
                adj_matrix[i][j] = 0;
            }
        }

        for (int i = 1; i <= 233; i ++) {
            if(map.get(i) != -1)
                Program(i);
        }

        Algorithms t = new Algorithms();
        t.dijkstra(adj_matrix, 226);

        //System.out.println(adj_matrix[226][219]);

     /*   for(int i = 0; i < 233; i++) {
            for(int j = 0; j < 233; j++) {
                System.out.print(adj_matrix[i][j] + "   ");
            }
            System.out.print("\n");
        } */

    }

    public static void Program(int currKey) {

            int caseNum = 3;

            for (int i = 0; i < leftEdge.length; i++) {
                if (currKey == leftEdge[i])
                    caseNum = 1;
            }
            for (int i = 0; i < rightEdge.length; i++) {
                if (currKey == rightEdge[i])
                    caseNum = 2;
            }

            switch (caseNum) {
                case 1:
                    leftEdgeCase(currKey);
                    break;
                case 2:
                    rightEdgeCase(currKey);
                    break;
                case 3:
                    centerCase(currKey);
                    break;
            }

    }


    public static void leftEdgeCase(int key) {
        int top = key - 15;
        int topRight = key - 7;
        int bottomRight = key + 8;
        int bottom = key + 15;


        if(key == 1) {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
        }

        else if(key == 226) {
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
        }

        else {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
        }
    }


    public static void rightEdgeCase(int key) {
        int top = key - 15;
        int topLeft = key - 8;
        int bottomLeft = key + 7;
        int bottom = key + 15;


        if(key == 8) {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottomLeft))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottomLeft))/2);
            }
        }

        else if(key == 233) {
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
        }

        else {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottomLeft))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottomLeft))/2);
            }
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
        }
    }


    public static void centerCase(int key) {
        int top = key - 15;
        int topLeft = key - 8;
        int topRight = key - 7;
        int bottomRight = key + 8;
        int bottomLeft = key + 7;
        int bottom = key + 15;

        if(key > 0 && key < 9) {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
        }
        else if(key > 8 && key < 16) {
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottomLeft))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottomLeft))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
        }
        else if(key > 218 && key < 226) {
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottomLeft))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottomLeft))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
        }
        else if(key > 225 && key < 234) {
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
        }
        else {
            if(map.get(top) != -1) {
                adj_matrix[key][top] = ((double)(map.get(key) + map.get(top))/2);
                adj_matrix[top][key] = ((double)(map.get(key) + map.get(top))/2);
            }
            if(map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = ((double)(map.get(key) + map.get(topLeft))/2);
                adj_matrix[topLeft][key] = ((double)(map.get(key) + map.get(topLeft))/2);
            }
            if(map.get(topRight) != -1) {
                adj_matrix[key][topRight] = ((double)(map.get(key) + map.get(topRight))/2);
                adj_matrix[topRight][key] = ((double)(map.get(key) + map.get(topRight))/2);
            }
            if(map.get(bottom) != -1) {
                adj_matrix[key][bottom] = ((double)(map.get(key) + map.get(bottom))/2);
                adj_matrix[bottom][key] = ((double)(map.get(key) + map.get(bottom))/2);
            }
            if(map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = ((double)(map.get(key) + map.get(bottomLeft))/2);
                adj_matrix[bottomLeft][key] = ((double)(map.get(key) + map.get(bottomLeft))/2);
            }
            if(map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = ((double)(map.get(key) + map.get(bottomRight))/2);
                adj_matrix[bottomRight][key] = ((double)(map.get(key) + map.get(bottomRight))/2);
            }
        }
    }


    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V=233;
    double minDistance(double dist[], Boolean sptSet[])
    {
        // Initialize min value
        double min = Integer.MAX_VALUE;
        int min_index=-1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(double dist[], int n)
    {
        int target = 8;
        int min = target;
        double t;
        int num = 1;
        path[0] = target;
        while(target != 226) {
            for(int i = 1; i <= 233; i++) {
                t = adj_matrix[target][i];
                if(t != 0 && dist[min] > dist[i])
                    min = i;
            }
            path[num] = min;
            num++;
            target = min;
        }

        for (int i = 232; i >= 0; i--) {
            if(path[i] != 0)
                System.out.println((int)path[i]);
        }
        System.out.println("MINIMAL-COST PATH COSTS: " + (int)dist[8]);
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(double graph[][], int src)
    {
        double dist[] = new double[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        int num = 0;
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = (int)minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = (dist[u] + graph[u][v]);
                    hexFinal[num] = u;
                    num++;
                }
            }
        }

        int add = ((map.get(position[0][15]) + map.get(position[14][0]))/2);
        dist[8] += add;
        printSolution(dist, V);
    }
}


