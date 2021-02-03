package lesson1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int qty;
    private static long n;

    public static void main(String[] args) {
        File dataFolder = new File("input//lesson1//0.String");

        List<File> inputFiles = Arrays.stream(dataFolder.listFiles((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".in"))).sorted().collect(Collectors.toList());
        List<File> checksumFiles = Arrays.stream(dataFolder.listFiles((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".out"))).sorted().collect(Collectors.toList());

        for (int i = 0; i < inputFiles.size(); i++) {
            try {
                System.out.println("Test string #" + i + ": " + testString(inputFiles.get(i), checksumFiles.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataFolder = new File("input//lesson1//1.Tickets");

        inputFiles = Arrays.stream(dataFolder.listFiles((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".in"))).sorted().collect(Collectors.toList());
        checksumFiles = Arrays.stream(dataFolder.listFiles((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".out"))).sorted().collect(Collectors.toList());

        for (int i = 0; i < inputFiles.size(); i++) {
            try {
                System.out.println("Test lucky numbers #" + i + ": " + testLuckyNumbers(inputFiles.get(i), checksumFiles.get(i)));
                qty = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    private static boolean testString(File inputFile, File checksumFile) throws IOException {

        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        BufferedReader checksumReader = new BufferedReader(new FileReader(checksumFile));
        String input = inputReader.readLine();
//        String input = new String(Files.readAllBytes(Paths.get(inputFile.toURI())));
        int checksum = Integer.parseInt(checksumReader.readLine());
        int counter = countStringLength(input);
        return counter == checksum;
    }

    private static int countStringLength (String inputString) {
        int counter = 0;
//        System.out.println(inputString);
        for (int i = 0; i < inputString.length(); i++) {
            counter++;
        }
        return counter;
    }

    private static boolean testLuckyNumbers(File inputFile, File checksumFile) throws IOException {
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        BufferedReader checksumReader = new BufferedReader(new FileReader(checksumFile));
        n = Long.parseLong(inputReader.readLine());
//        String input = new String(Files.readAllBytes(Paths.get(inputFile.toURI())));
        long checksum = Long.parseLong(checksumReader.readLine());
        countLuckyNumbers(0,0,0);
        return qty == checksum;
    }

    private static int countLuckyNumbers(int nr, int sum1, int sum2) {

            if (nr == 2 * n) {
                if (sum1 == sum2)
                    qty++;
                    return 0;


            }
            for (int a = 0; a <= 9; a ++)
                if (nr < n)
                    countLuckyNumbers(nr + 1, sum1 + a, sum2);
                else
                    countLuckyNumbers(nr + 1, sum1, sum2 + a);


        return 0;
    }
}
