package pgdp.threadswarmup;

import java.util.Optional;

public class SumThread extends Thread {
    private Optional<Integer> sum;

    private Node node;

    private int remainingThreads;

    SumThread leftThread;
    SumThread rightThread;

    int leftThreadCount;
    int rightThreadCount;

    public SumThread(Node node, int remainingThreads) {
        this.node = node;
        this.remainingThreads = remainingThreads;
        sum = Optional.empty();
    }

    public Optional<Integer> getSum() {
        return sum;
    }

    public void run() {
        int sum = 0;
        startChildThreads();

        if (node.getLeft().isPresent() && leftThreadCount > 0) {
            sum += leftThread.sum.orElse(0);
            try {
                leftThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (node.getLeft().isPresent()) {
            sum += node.getLeft().get().sum();
        }

        if (node.getRight().isPresent() && rightThreadCount > 0) {
            sum += rightThread.sum.orElse(0);
            try {
                rightThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (node.getRight().isPresent()) {
            sum += node.getRight().get().sum();
        }

        this.sum = Optional.of(sum);
    }

    protected void startChildThreads() {
        leftThreadCount = leftThreadCount();
        rightThreadCount = remainingThreads - 1 - leftThreadCount;

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
