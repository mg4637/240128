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
        int leftThreadCount = leftThreadCount();
        int rightThreadCount = remainingThreads - 1 - leftThreadCount;
        if (node.getLeft().isPresent() && leftThreadCount > 0) {
            leftThread = new SumThread(node.getLeft().get(), leftThreadCount);
            leftThread.start();
        }
        if (node.getRight().isPresent() && rightThreadCount > 0) {
            rightThread = new SumThread(node.getRight().get(), rightThreadCount);
            rightThread.start();
        }
    }

    protected int leftThreadCount() {
        int remainingThreadsForChildren = remainingThreads - 1;
        if (node.getLeft().isEmpty()) {
            return 0;
        } else if (node.getRight().isEmpty()) {
            return remainingThreadsForChildren;
        } else {
            return remainingThreadsForChildren / 2;
        }
    }

}
