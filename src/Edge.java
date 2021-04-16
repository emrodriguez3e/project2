import java.math.*;

public class Edge {

    double distance = 0;
    double reciever = 0;
    double transmission = 0;
    double electric = Math.pow(100, -12);
    double amp = Math.pow(100, -9);
    double packetSize = 3200;


    //needs two nodes
    public Edge(Axis node1, Axis node2){

        boolean node1Status = node1.isStorageType();
        boolean node2Status = node2.isStorageType();

        if(node1Status == false && node2Status == false){
            //this is where they are both data nodes

        } else if (node1Status == true && node2Status == true){
            //this is where they are both storage nodes
        }else{
            //get their distance
            double distance = Math.sqrt(((node2.getxAxis()-node1.getxAxis()) + (node2.getyAxis() - node2.getyAxis())));
            distance = this.distance;
            calculateEdge();
        }
    }

    double setTranmission(){
        transmission = electric * packetSize + amp * packetSize * Math.pow(distance, 2);
        return transmission;
        //this is for the data nodes
    }

    double setReceiver(){
        reciever = electric * packetSize; //this is the value for the storage nodes
        return reciever;
    }

    double calculateEdge(){
        double edgeCost = setReceiver() + setTranmission();
        return edgeCost;
    }


}