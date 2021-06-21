package com.exercise.utils;

public class BSCExerciseUtil {

    /**
     * Validate if postalCode has correct format
     * <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator>
     *
     * @param postalCode postalCode
     */
    public static boolean validPostalCode(String postalCode) {
        try {
            if (postalCode.length() != 5) {
                return false;
            }
            Double.parseDouble(postalCode);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validate if package weight has correct format
     * <postal code: fixed 5 digits>
     *
     * @param weight package weight
     */
    public static boolean validatePackageWeight(String weight) {
        try {
            String [] splitWeights = weight.split("\\.");
            if (splitWeights.length > 2) {
                return false;
            } else if (splitWeights.length == 2 && splitWeights[1].length() > 3) {
                return false;
            }
            double parsedWeight = Double.parseDouble(weight);
            return !(parsedWeight < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validate if input from file or user have correct format
     *
     * @param inputList package weight and postcode as input from file or user
     */
    public static boolean validateInput(String [] inputList) {
        return inputList.length == 2
                && BSCExerciseUtil.validPostalCode(inputList[1])
                && BSCExerciseUtil.validatePackageWeight(inputList[0]);
    }
}
