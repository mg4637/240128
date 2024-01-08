package pgdp.threadswarmup;

import java.util.Optional;

public class SumThread extends Thread {
    private Optional<Integer> sum;

    private Node node;

    private int remainingThreads;

    SumThread leftThread;
    SumThread rightThread;


    public SumThread(Node node, int remainingThreads) {
        // TODO Exercise 2
    }

    public Optional<Integer> getSum() {
        return sum;
    }

    public void run() {
        // TODO Exercise 5
    }

    protected void startChildThreads() {
        // TODO Exercise 4
    }

    protected int leftThreadCount() {
        // TODO Exercise 3
        return -1;
    }

}
