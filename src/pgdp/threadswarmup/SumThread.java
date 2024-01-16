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
        sum = Optional.of(node.sum());
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
        int count = 0;
        Node currentNode = node;
        while (currentNode.getLeft().isPresent()) {
            currentNode = currentNode.getLeft().get();
            count++;
        }
        return count;
    }

}
