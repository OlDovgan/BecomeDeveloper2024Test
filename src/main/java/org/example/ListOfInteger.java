package org.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.example.Main.logger;

public class ListOfInteger {
    List<Integer> listNumber;

    public ListOfInteger(List<String> stringList) {
        this.listNumber = stringList.stream()
                .flatMap(line -> Arrays.stream(line.split(System.lineSeparator())))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    public void getAll() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> futures = new ArrayList<>();
            futures.add(executorService.submit(() -> executeAndTime(this::findMax, "Max")));
            futures.add(executorService.submit(() -> executeAndTime(this::findMin, "Min")));
            futures.add(executorService.submit(() -> executeAndTime(this::findMedian, "Median")));
            futures.add(executorService.submit(() -> executeAndTime(this::findArithmetic, "Arithmetic")));
            futures.add(executorService.submit(() -> executeAndTime(this::findLargestIncreasingSequence, "Largest Increasing Sequence")));
            futures.add(executorService.submit(() -> executeAndTime(this::findLargestDecreasingSequence, "Largest Decreasing Sequence")));

            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error executing tasks", e);
        }
    }


    private <T> void executeAndTime(Callable<T> task, String taskResult) {
        long startTime = System.nanoTime();
        try {
            T result = task.call();
            long endTime = System.nanoTime();
            System.out.println(taskResult + " = " + result);
            System.out.println("Method " + taskResult + " execution time: " + (endTime - startTime) / 1_000_000 + " milliseconds");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error executing task", e);
        }
    }

    public int findMax() {
        if (listNumber.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return Collections.max(listNumber);
    }

    public int findMin() {
        if (listNumber.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return Collections.min(listNumber);
    }

    public double findMedian() {
        int size = listNumber.size();
        if (size % 2 == 0) {
            int mid1 = listNumber.get(size / 2 - 1);
            int mid2 = listNumber.get(size / 2);
            return (double) (mid1 + mid2) / 2;
        } else {
            return listNumber.get(size / 2);
        }
    }

    public double findArithmetic() {
        int sum = 0;
        for (Integer num : listNumber) {
            sum += num;
        }
        return (double) sum / listNumber.size();
    }

    public List<Integer> findLargestIncreasingSequence() {
        return getLongestSequence(listNumber, Comparator.naturalOrder());
    }

    public List<Integer> findLargestDecreasingSequence() {
        return getLongestSequence(listNumber, Comparator.reverseOrder());
    }

    private List<Integer> getLongestSequence(List<Integer> numbers, Comparator<Integer> comparator) {
        List<Integer> longestSequence = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();
        Integer previous = null;

        for (Integer number : numbers) {
            if (currentSequence.isEmpty() || comparator.compare(number, previous) > 0) {
                currentSequence.add(number);
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(number);
            }
            previous = number;
        }

        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }

        return longestSequence;
    }

}
