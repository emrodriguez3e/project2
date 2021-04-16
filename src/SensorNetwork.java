import java.awt.Dimension;
import java.util.*;

public class SensorNetwork {
    //TODO create a working priority queue
    //TODO What is dijkstra's algorithm

    private Map<Integer, Axis> nodes = new LinkedHashMap<Integer, Axis>();
    Map<Integer, Boolean> discovered = new HashMap<Integer,Boolean>();
    Map<Integer, Boolean> explored = new HashMap<Integer, Boolean>();
    Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
    static Axis ax = new Axis();

    static int cost;
    PriorityQueue pq;
    private Set<Integer> storage = new HashSet<Integer>();
    public List dataNodesList = new ArrayList<>();
    public List storageNodesList = new ArrayList<>();
    public static int[] dist;

    double electric = Math.pow(100, -12);
    double amp = Math.pow(100, -9);
    double k = 3200;

    Stack<Integer> s = new Stack<Integer>();


    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the width:");
        double width = scan.nextDouble();

        System.out.println("Enter the height:");
        double height = scan.nextDouble();


        System.out.println("Enter the number of nodes:");
        int numberOfNodes = scan.nextInt();

        System.out.println("Enter the Transmission range in meters:");
        int transmissionRange = scan.nextInt();

        System.out.println("How many data nodes do you want to set?");
        int dataNodes = scan.nextInt();
//        System.out.println(dataNodes +" nodes have been set to data nodes.");

        System.out.println("How much data should each data node have?");
        int dataPacket = scan.nextInt();
//        System.out.println("Tuning data output to" +dataPacket+" bits");

        System.out.println("How much storage should each storage node have?");
        int storage = scan.nextInt();
//        System.out.println("Storage node capacities have been set to store "+storage+" bits.\n");

        double allData = dataNodes * dataPacket;
        double allStorage = (numberOfNodes-dataNodes)*storage;

        if(allData > allStorage){
            System.out.println("There is not enough storage available to store all the data.\nPlease try again.");
            // System.exit(1);
        }//feasability check

        SensorNetwork sensornetwork = new SensorNetwork();
        sensornetwork.populateNodes(numberOfNodes,width, height, dataNodes); //this will give the location of the nodes

        for(int key : sensornetwork.nodes.keySet()){
            ax = sensornetwork.nodes.get(key);
            System.out.println("Node: "+key+", xAxis:"+ ax.getxAxis() + ", yAxis:"+ax.getyAxis());
        }

        Map<Integer, Set<Integer>> adjList = new LinkedHashMap();

        sensornetwork.populateAdjacencyList(numberOfNodes, transmissionRange, adjList);

        System.out.println();

        //sensornetwork.executeStackDFS(width, height, adjList);

        sensornetwork.execute(width, height,ax, adjList);
        sensornetwork.drawGraph(width, height, adjList);
        sensornetwork.bellmanFord(adjList);
        // sensornetwork.shortestPast(3);
    }//end of main method

    void executeStackDFS(double width, double height, Map<Integer, Set<Integer>> adjList){
        System.out.println("\nExecuting DFS Stack Algorithm");
        List<Set<Integer>> connectedNodes = new ArrayList<Set<Integer>>();

        for(int node: adjList.keySet()){
            Set<Integer> connectedNode = new LinkedHashSet<Integer>();
            stackDFS(node, connectedNode, adjList);


            if(!connectedNode.isEmpty()){
                connectedNodes.add(connectedNode);
            }
        }//end of for loop

        if(connectedNodes.size() == 1) {
            System.out.println("Graph is fully connected with one connected component.");
        } else {
            System.out.println("Graph is not fully connected.\nPlease try again.");

        }

        System.out.println("There are " + connectedNodes.size() + " connected components");
        for(Set<Integer> list: connectedNodes) {
            System.out.println(list);
        }
    }

    void stackDFS(int u, Set<Integer> connectedNode, Map<Integer, Set<Integer>> adjList){
        s.push(u);

        while(!s.isEmpty())
        {
            int v = s.pop();
            if(!discovered.containsKey(v))
            {
                connectedNode.add(v); //add here
                discovered.put(v, true);
            }

            List<Integer> list = new ArrayList<Integer>(adjList.get(v));
            for(int i : list)
            {
                if(!discovered.containsKey(i))
                {
                    s.push(i);
                }
            }
        }
    }

    void execute(double width, double height, Axis axis1, Map<Integer, Set<Integer>> adjList){
        System.out.println("\nExecuting Dijkstras Algorithm");

        List<Set<Integer>> connectedNodes = new ArrayList<Set<Integer>>();

        for(int node: adjList.keySet()){
            Set<Integer> connectedNode = new LinkedHashSet<Integer>();


            DijkstraAlgorithm(node, axis1, adjList);


            if(!connectedNode.isEmpty()){
                connectedNodes.add(connectedNode);
            }
        }
    }

    void DijkstraAlgorithm(int u , Axis axis1,Map<Integer, Set<Integer>> adjList)//need to get a new cost, the edge one in this case
    {
        //make a custom priority queue and implement it for the shortest path here
        int cost = 0;
        int selected = 0;
        double temp = 999999999;
        int capacity = adjList.size();
        Axis raxis = axis1;
        discovered.put(u, false);
        explored.put(u,false);
        //List<Set<Integer>> pathCost = new ArrayList<Set<Integer>>();
        List<Set<Integer>> connectedNodes = new ArrayList<Set<Integer>>();
        PriorityQueue pq = new PriorityQueue(capacity);

        //create a node for every adjList

        for ( int i : adjList.keySet())
        {
            pq.insert(u);
            //idk what this would leave us with with the shortest path i think
        }
        while(pq!=null)
        {
            System.out.println(pq+":"+"x axis:"+ raxis.getxAxis()+","+"y axis:"+raxis.getyAxis());


            break;
        }

    }

    void calcEdges(Map<Integer, Set<Integer>> adjList)
    {
        nodes.get(1).setDistance(0);
        for (int node : adjList.keySet()) //change to loop through sn values
        {
            Set<Integer> connectedNode = new LinkedHashSet<Integer>();

            for(int j: adjList.get(node))//change to loop thought the dn values
            {//get the costs for the individual dn values that go with the corresponding sn
                //store it in a priority queue
                if(node != j && (nodes.get(j).getDistance() > nodes.get(node).getDistance() + (Math.sqrt((Math.pow((nodes.get(j).getxAxis() - nodes.get(node).getxAxis()), 2) + Math.pow((nodes.get(j).getyAxis() - nodes.get(node).getyAxis()), 2))))))
                {
                    nodes.get(j).setDistance((Math.sqrt((Math.pow((nodes.get(j).getxAxis() - nodes.get(node).getxAxis()), 2) + Math.pow((nodes.get(j).getyAxis() - nodes.get(node).getyAxis()), 2)))));
                    nodes.get(j).setNodeInd(node);
                }
            }
            //store the smallest value in another priority queue
        }
        Edge[] e = new Edge[nodes.size()];
        for(int i = 0; i < nodes.size(); i++)
        {
            e[i] = new Edge(nodes.get(i), nodes.get(i+1));


        }

        System.out.println("the cost per node is");
        for(int i = 1; i <= nodes.size(); i++)
        {
            System.out.println(i + "'s distance is " +  nodes.get(i).getDistance() + " from  " + nodes.get(i).getNodeInd());
        }
        System.out.println("the edges are");
        for(int i = 1; i < e.length; i++)
        {
            System.out.println(e[i].calculateEdge()+" for the node " + (i+1));
        }

    }
    void bellmanFord( Map<Integer, Set<Integer>> adjList)
    {
        System.out.println("\nExecuting BellmanFord dynamic programming algorithm");
        nodes.get(1).setDistance(0);
        for(int i = 1; i < nodes.size();i++)
        {
            for (int node : adjList.keySet()) //change to loop through sn values
            {
                Set<Integer> connectedNode = new LinkedHashSet<Integer>();

                for(int j: adjList.get(node))//change to loop thought the dn values
                {//get the costs for the individual dn values that go with the corresponding sn
                    //store it in a priority queue
                    if(node != j && (nodes.get(j).getDistance() > nodes.get(node).getDistance() + (Math.sqrt((Math.pow((nodes.get(j).getxAxis() - nodes.get(node).getxAxis()), 2) + Math.pow((nodes.get(j).getyAxis() - nodes.get(node).getyAxis()), 2))))))
                    {
                        nodes.get(j).setDistance((Math.sqrt((Math.pow((nodes.get(j).getxAxis() - nodes.get(node).getxAxis()), 2) + Math.pow((nodes.get(j).getyAxis() - nodes.get(node).getyAxis()), 2)))));
                        nodes.get(j).setNodeInd(node);
                    }
                }
                //store the smallest value in another priority queue
            }
        }
        Edge[] e = new Edge[nodes.size()];
        for(int i = 1; i < nodes.size(); i++)
        {
            e[i] = new Edge(nodes.get(i), nodes.get(i+1));


        }
        System.out.println("the cost per node is");
        for(int i = 1; i <= nodes.size(); i++)
        {
            System.out.println(i + "'s distance is " +  nodes.get(i).getDistance() + " from  " + nodes.get(i).getNodeInd());
        }
        System.out.println("the edges are");
        for(int i = 1; i < e.length; i++)
        {
            System.out.println(e[i].calculateEdge()+" for the node " + (i+1));
        }
    }
    void shortestPast(int k)
    {
        System.out.println("\nExecuting shortest path between them using with k edges");
        int counter = 0;
        LinkedList<List> nodeThing = new LinkedList();
        int cheapestPath = 999999999, cheapestCost = 999999999;
        int temp = 0;
        for(int i = 1; i <= nodes.size(); i++)
        {
            counter = 0;
            LinkedList inners = new LinkedList();
            while(inners.size() < i)
            {
                inners.add(i + counter);
                counter++;
            }
            nodeThing.add(inners);
        }
        for(int i = 0; i < nodeThing.size(); i++)
        {
            if(nodeThing.get(i).size() == k)
            {
                for(int j = 0; j < nodeThing.get(i).size(); j++)
                {
                    temp += nodes.get(nodeThing.get(i).get(j)).getDistance();
                }
                if(temp < cheapestCost)
                {
                    cheapestCost = temp;
                    cheapestPath = i;
                }

            }
        }
        System.out.print("The Shortest Path is: " );
        for(int i = 0; i < k; i++)
        {
            System.out.print("(" +cheapestPath + "," + nodes.get(nodeThing.get(cheapestPath).get(i))+ ")");
        }


    }
    void drawGraph(double width, double height, Map<Integer, Set<Integer>> adjList){
        SensorNetworkGraph graph = new SensorNetworkGraph();
        graph.setGraphWidth(width);
        graph.setGraphHeight(height);
        graph.setNodes(nodes);
        graph.setAdjList(adjList);
        graph.setPreferredSize(new Dimension(960, 800));
        Thread graphThread = new Thread(graph);
        graphThread.start();
    }
    void populateNodes(int nodeCount, double width, double height, int dataNode) {
        Random random = new Random();

        Random nodeType = new Random();
        int dataNodeCount = 0;

        for(int i = 1; i <= nodeCount; i++) {
            Axis axis = new Axis();
            int scale = (int) Math.pow(10, 1);
            double xAxis =(0 + random.nextDouble() * (width - 0));
            double yAxis =(0 + random.nextDouble() * (height - 0));

            xAxis = (double)Math.floor(xAxis * scale) / scale;
            yAxis = (double)Math.floor(yAxis * scale) / scale;

            axis.setxAxis(xAxis);
            axis.setyAxis(yAxis);

            //set a certain amount of nodes to be data
            if((random.nextDouble()) > 0.75 && dataNodeCount < dataNode){
                axis.setPackets(3200);
                dataNodeCount++;
                dataNodesList.add(i);
            }else{
                axis.setCapacity(400);
                storageNodesList.add(i);
            }
            nodes.put(i, axis);
        }
    }//end of populate nodes

    void populateAdjacencyList(int nodeCount, int tr, Map<Integer, Set<Integer>> adjList) {
        for(int i=1; i<= nodeCount; i++) {
            adjList.put(i, new HashSet<Integer>());
        }

        for(int node1: nodes.keySet()) {
            Axis axis1 = nodes.get(node1);

            for(int node2: nodes.keySet()) {
                Axis axis2 = nodes.get(node2);

                if(node1 == node2) {
                    continue;
                }
                double xAxis1 = axis1.getxAxis();
                double yAxis1 = axis1.getyAxis();

                double xAxis2 = axis2.getxAxis();
                double yAxis2 = axis2.getyAxis();

                double distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));


                //if a node is within range and has a data type

                double edge = electric * k + amp * k * Math.pow(distance,2) + electric *k;

                //place all nodes into the adj list
                if(distance <= tr) {
                    Set<Integer> tempList = adjList.get(node1);
                    tempList.add(node2);
                    adjList.put(node1, tempList);

                    tempList = adjList.get(node2);
                    tempList.add(node1);
                    adjList.put(node2, tempList);

                }//end of distance
            }
        }
    }//end of adjList
    void showDataLists(){
        System.out.println("Please enter the id for the data node ");
        System.out.println(dataNodesList);

    }
    void showStorageLists(){
        System.out.println("Please enter the id for the storage node");
        System.out.println(storageNodesList);
    }
}//end of file