package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ListOfInteger {
    List<Integer> listNumber;

    public ListOfInteger(List<String> stringList) {
        this.listNumber = stringList.stream()
                .flatMap(line -> Arrays.stream(line.split(System.lineSeparator())))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
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

        for (Integer number : numbers) {
            if (currentSequence.isEmpty() || comparator.compare(number, currentSequence.getLast()) > 0) {
                currentSequence.add(number);
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(number);
            }
        }

        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }

        return longestSequence;
    }
}
