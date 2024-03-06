import org.example.FileReader;
import org.example.ListOfInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfIntegerFirstOptionTest {
    List<String> list = new FileReader("10m_test1.txt").readList();
    ListOfInteger listOfNumber = new ListOfInteger(list);


    public ListOfIntegerFirstOptionTest() throws FileNotFoundException, URISyntaxException {

    }

    @Test
    void findMax_ShouldReturnMax() {
        var expectResult = 49819300;
        var result = listOfNumber.findMax();
        System.out.println("Test findMax:");
        Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findMin_ShouldReturnMin() {
        var expectResult = -49776700;
        var result = listOfNumber.findMin();
        System.out.println("Test findMin:");
        Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findMedian_ShouldReturnMedian() {
         var expectResult = -30596791;
         var result = listOfNumber.findMedian();
         System.out.println("Test findMedian:");
         Assertions.assertEquals(expectResult, result);
    }

    @Test
    void findLargestIncreasingSequence_ShouldReturnLargestIncreasingSequence() {

        List<Integer> expectResult = new ArrayList<>(Arrays.asList(-3292051, 19125595, 21483576, 46188838));
         var result = listOfNumber.findLargestIncreasingSequence();
         System.out.println("Test findLargestIncreasingSequence:");
         Assertions.assertEquals(expectResult, result);
    }
    @Test
    void findLargestDecreasingSequence_ShouldReturnLargestDecreasingSequence() {

        List<Integer> expectResult = new ArrayList<>(Arrays.asList(26833668, -7623681, -43275275, -48454940));
        var result = listOfNumber.findLargestDecreasingSequence();
        System.out.println("Test findLargestDecreasingSequence:");
        Assertions.assertEquals(expectResult, result);
    }


}
