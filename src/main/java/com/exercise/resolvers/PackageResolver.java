package com.exercise.resolvers;

import com.exercise.exceptions.WrongInputException;
import com.exercise.utils.BSCExerciseUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageResolver {
    private static final Map<String, List<Double>> postalCodePackagesMap = new HashMap<>();

    /**
     * Load, parse and save data with packages
     *
     * @param filePath filePath
     */
    public void loadDataFromFile(String filePath) throws IOException, WrongInputException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            processInput(line);
        }
    }

    public void processInput(String line) throws WrongInputException {
        String [] inputs = line.trim().split(" ");
        if (BSCExerciseUtil.validateInput(inputs)) {
            postalCodePackagesMap.computeIfAbsent(inputs[1], k -> new ArrayList<>()).add(Double.parseDouble(inputs[0]));
        } else {
            throw new WrongInputException();
        }
    }

    public Map<String, List<Double>> getPostalCodePackagesMap() {
        return postalCodePackagesMap;
    }
}
