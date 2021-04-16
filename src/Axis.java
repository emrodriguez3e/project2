public class Axis {

    String job;
    int priority;

    private double xAxis;
    private double yAxis;
    private double capacity;
    private double packets;
    private double distance = 999999999;
    private int nodeInd;
    private Boolean sn = true;

    public Axis(){

    }

    public Axis(String job, int priority){
        this.job = job;
        this.priority = priority;
    }// end of constructor
    //TODO figure out how relationship between Axis and SensorNetwork
    public double getxAxis() {
        return xAxis;
    }
    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }
    public double getDistance()
    {
        return this.distance;
    }
    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    public double getNodeInd()
    {
        return this.nodeInd;
    }
    public void setNodeInd(int ind)
    {
        this.nodeInd = ind;
    }
    public double getyAxis() {
        return yAxis;
    }
    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }


    public double getCapacity(){
        return capacity;
    }

    public void setCapacity(double storage){
        this.capacity = storage;
//        this.setStorageType(true);
    }//end of setCapacity

    public double getPackets(){
        return packets;
    }

    public void setPackets(double packets){
        this.packets = packets;
//        this.setStorageType(false);
    }

    public boolean isStorageType(){
        return sn;
    }

    public void setStorageType(double packet, double storage){
        if(packet == 0){
            this.sn = false;
        }
        else if(storage == 0){
            this.sn = true;
        }
    }

    public String toString(){
        return "Job Name : "+ job +"\nPriority : "+ priority;
    }
}//end of file