package pgdp.threadswarmup;

import java.util.Optional;

public class SumThread extends Thread {
    private Optional<Integer> sum;

    private Node node;

    private int remainingThreads;

    SumThread leftThread;
    SumThread rightThread;


    public SumThread(Node node, int remainingThreads) {
        this.node = node;
        this.remainingThreads = remainingThreads;
        sum = Optional.empty();
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
        if (node.getLeft().isEmpty()) {
            return 0;
        } else if (node.getRight().isEmpty()) {
            return remainingThreads;
        } else {
            return remainingThreads / 2;
        }
    }

}
