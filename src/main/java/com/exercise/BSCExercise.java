package com.exercise;

import com.exercise.exceptions.WrongInputException;
import com.exercise.resolvers.PackageResolver;
import com.exercise.tasks.PostalCodeWeightsTask;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BSCExercise {
    private static final String FORMAT_MESSAGE =
            "Input line format: <weight: positive number, >0, maximal 3 decimal places, " +
            ". (dot) as decimal separator><space><postal code: fixed 5 digits>";
    private static final String FILE_ERROR = "File not found or is not readable";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        PackageResolver packageResolver = new PackageResolver();
        if (args.length > 1) {
            System.out.println("Too many arguments");
            return;
        } else if (args.length == 1) {
            try {
                packageResolver.loadDataFromFile(args[0]);
            } catch (IOException e) {
                System.out.println(FILE_ERROR);
                return;
            } catch (WrongInputException e) {
                System.out.println("Wrong data format in file. " + FORMAT_MESSAGE);
                return;
            }
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new PostalCodeWeightsTask(packageResolver.getPostalCodePackagesMap()), 0, 60, TimeUnit.SECONDS);
        Scanner in = new Scanner(System.in);

        while(true) {
            String inputLine = in.nextLine();
            if (inputLine.equals("quit")) {
                executor.shutdown();
                break;
            }
            try {
                packageResolver.processInput(inputLine);
            } catch (WrongInputException e) {
                System.out.println("Wrong input. Try it again... " + FORMAT_MESSAGE);
            }
        }
    }
}
