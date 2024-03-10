import org.example.FileReader;
import org.example.ListOfInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfIntegerThirdOptionTest {
    List<String> list = new FileReader("10m_test_10.txt").readList();
    ListOfInteger listOfNumber = new ListOfInteger(list);


    public ListOfIntegerThirdOptionTest() throws FileNotFoundException, URISyntaxException, ExecutionException, InterruptedException {

    }

    @Test
    void findMax_ShouldReturnMax() {
        var expectResult = 5;
        var result = listOfNumber.findMax();
        System.out.println("Test findMax:");
        Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findMin_ShouldReturnMin() {
        var expectResult = -9;
        var result = listOfNumber.findMin();
        System.out.println("Test findMin:");
        Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findMedian_ShouldReturnMedian() {
         var expectResult = 1.5;
         var result = listOfNumber.findMedian();
         System.out.println("Test findMedian:");
         Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findArithmetic_ShouldReturnArithmetic() {

         var expectResult = -1.5;
         var result = listOfNumber.findArithmetic();
         System.out.println("Test findArithmetic:");
         Assertions.assertEquals(expectResult, result);
    }
    @Test
    void findLargestIncreasingSequence_ShouldReturnLargestIncreasingSequence() {

        List<Integer> expectResult = new ArrayList<>(Arrays.asList(1, 2, 3));
        var result = listOfNumber.findLargestIncreasingSequence();
        System.out.println("Test findLargestIncreasingSequence:");
        Assertions.assertEquals(expectResult, result);
    }
    @Test
    void findLargestDecreasingSequence_ShouldReturnLargestDecreasingSequence() {

        List<Integer> expectResult = new ArrayList<>(Arrays.asList(3, -5, -7, -9));
        var result = listOfNumber.findLargestDecreasingSequence();
        System.out.println("Test findLargestDecreasingSequence:");
        Assertions.assertEquals(expectResult, result);
    }

}
