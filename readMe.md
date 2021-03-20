

### Energy Model

All the sensor nodes are powered by battery, 
which has limited amount of energy reservoir. 
Sensor nodes communicate with each other using wireless communication, 
which costs the battery power of sensor nodes.
For two sensor nodes i an j who are within each other’s 
transmission range, they can communicate by sending and 
receiving data packets. Assume their distance is l meters. 
When node i sends k-bit data packet to its neighbor node j 
over their l-meter distance, both i and j spend their battery 
power as follows

# Programming Objective 

When executed, your program should prompt to ask

1. the width x and length y of the sensor network (e.x., 50meter x 50meter)

2. number of sensor nodes: N

3. transmission range in meters: Tr

4. number of DNs: p, and number of data packets each DN has: 
   q. Here we assume each DN has the same number of data packets.

5. Storage capacity of each storage node: m. 
   Here we assume each storage node has the same storage capacity.

First, your program still needs to check if the sensor 
network graph is connected (i.e.. connectivity). 
If not, it prints out a message “the network is not connected”, 
and asks the user to input again.

Second, your program should check if the user inputs 
satisfy: p x q <= (N-p) x m (i.e., feasibility). 
That is, there should have enough storage spaces in the entire 
network to store all the data packets generated. 
If not, it prints out a message “there is not enough 
storage in the network’, and asks the user to input again.

After both connectivity and feasibility are satisfied, next, your program should list the IDs of DNs and the IDs of storage nodes. It then asks user to input a DN node and a storage node as follows:

6. Please input the IDs of a DN and a SN:

7. Please choose algorithm to execute: 
   0 for Dijkstra’s shortest path algorithm, 
   1 for Bellman-Ford dynamic programming algorithm, 
   2 for finding a shortest path between them with k edges. 
   If input is 2, then ask to input k.