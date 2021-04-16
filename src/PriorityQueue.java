import java.util.Map;
import java.util.Set;

class PriorityQueue
{
    //private Axis[] heap;
    private int heapSize;
    int capacity;
    private int[] heap;

    /** Constructor **/
    public PriorityQueue(int capacity){
        this.capacity = capacity + 1;
        heap = new int[this.capacity];
        heapSize = 0;
    }//end of constructor


    /** function to clear **/
    public void clear(){
        heap = new int[capacity];
        heapSize = 0;
    }

    /** function to check if empty **/
    public boolean isEmpty(){
        return heapSize == 0;
    }

    /** function to check if full **/
    public boolean isFull(Map<Integer, Set<Integer>> adjList){
        return heapSize == capacity - 1;
    }//end of full method

    /** function to get Size **/
    public int size(){
        return heapSize;

    }

    /** function to insert task **/

    public void insert(int ax1) {
        heap[++heapSize] = ax1;

        int pos = heapSize;

        // while (pos != 1 && ax1.priority > heap[pos/2].priority) {
        heap[pos] = heap[pos/2];
        pos /=2;
        // }
        heap[pos] = ax1;
    }

    /** function to remove task **/
    public int remove() {
        int parent, child, item, temp;
        // Axis item, temp;
        if (isEmpty() ) {
            System.out.println("Heap is empty");
            return 0;
        }
        item = heap[1];
        temp = heap[heapSize--];
        parent = 1;
        child = 2;

        while (child <= heapSize){

            // if (child < heapSize && heap[child].priority < heap[child + 1].priority)
            //     child++;
//
            // if (temp.priority >= heap[child].priority)

            //    break;
            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;
        return item;
    }
    public void clear(Map<Integer, Set<Integer>> adjList){
        heap = new int[adjList.size()];
        heapSize = 0;
    }
}