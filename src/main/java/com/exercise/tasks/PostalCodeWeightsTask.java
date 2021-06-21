package com.exercise.tasks;

import java.util.List;
import java.util.Map;

public class PostalCodeWeightsTask implements Runnable {
    private final Map<String, List<Double>> postalCodePackagesMap;

    public PostalCodeWeightsTask(Map<String, List<Double>> postalCodePackagesMap) {
        this.postalCodePackagesMap = postalCodePackagesMap;
    }

    @Override
    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, List<Double>> entry : postalCodePackagesMap.entrySet()) {
            stringBuffer.append(entry.getKey())
                        .append(" ")
                        .append(String.format("%.3f", entry.getValue().stream().mapToDouble(Double::doubleValue).sum()))
                        .append("\n");
        }
        System.out.println(stringBuffer.toString());
    }
}
