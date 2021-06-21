package com.exercise;

import com.exercise.exceptions.WrongInputException;
import com.exercise.resolvers.PackageResolver;
import com.exercise.utils.BSCExerciseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BSCExerciseTest {

    @Test
    void validateInputTest() {
        String [] emptyInput = {};
        String [] incorrectInput1 = {"1", "1111"};
        String [] incorrectInput2 = {"1", "111111"};
        String [] incorrectInput3 = {"-1", "11111"};
        String [] incorrectInput4 = {"1.1111", "1111"};
        String [] incorrectInput5 = {"x", "11111"};
        String [] incorrectInput6 = {"1", "1111x"};
        String [] incorrectInput7 = {"1", "11111 111"};
        String [] incorrectInput8 = {"1"};

        String [] correctInput1 = {"1", "11111"};
        String [] correctInput2 = {"1.111", "11111"};

        Assertions.assertFalse(BSCExerciseUtil.validateInput(emptyInput));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput1));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput2));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput3));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput4));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput5));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput6));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput7));
        Assertions.assertFalse(BSCExerciseUtil.validateInput(incorrectInput8));

        Assertions.assertTrue(BSCExerciseUtil.validateInput(correctInput1));
        Assertions.assertTrue(BSCExerciseUtil.validateInput(correctInput2));



    }

    @Test
    void loadDataFromFileCorrectTest() throws IOException, WrongInputException {
        PackageResolver packageResolver = new PackageResolver();
        packageResolver.loadDataFromFile("src/test/testdata/correct_data.txt");
        Map<String, List<Double>> postalCodePackagesMap = packageResolver.getPostalCodePackagesMap();

        Assertions.assertEquals(3, postalCodePackagesMap.size());
        Assertions.assertEquals(4.1d,
                postalCodePackagesMap.get("11111").stream().mapToDouble(Double::doubleValue).sum());
        Assertions.assertEquals(2.22d,
                postalCodePackagesMap.get("22222").stream().mapToDouble(Double::doubleValue).sum());
        Assertions.assertEquals(3.333d,
                postalCodePackagesMap.get("33333").stream().mapToDouble(Double::doubleValue).sum());

    }

    @Test
    void loadDataFromFileIncorrectTest() {
        PackageResolver packageResolver = new PackageResolver();
        Assertions.assertThrows(WrongInputException.class,
                () -> packageResolver.loadDataFromFile("src/test/testdata/incorrect_data.txt"));


    }
}
