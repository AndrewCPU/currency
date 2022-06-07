package net.andrewcpu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void printTable() {
        List<String> options = IntStream.range(1,Currency.values().length + 1).mapToObj( i -> (i + ") " + Currency.values()[i - 1].getSymbol() + " " + Currency.values()[i - 1].toString())).collect(Collectors.toList());
        options.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        boolean runAgain;

        do {
            printTable();
            String input = null;
            Currency currency1 = null, currency2 = null;

            System.out.println("Select converting from:");
            while(input == null){
                System.out.print("> ");
                input = scanner.nextLine();
                int option;
                try{
                    option = Integer.parseInt(input);
                    if(option - 1 > Currency.values().length - 1 || option -1 < 0){
                        throw new Exception();
                    }
                }catch (Exception e){
                    System.out.println("Invalid input. Try again.");
                    input = null;
                    continue;
                }

                System.out.println("Selected " + Currency.values()[option - 1].toString());
                if(currency1 == null){
                    currency1 = Currency.values()[option - 1];
                    printTable();
                    System.out.println("Select converting to:");
                    input = null;
                }
                else {
                    currency2 = Currency.values()[option - 1];
                }
            }
            System.out.println("Converting from " + currency1 + " > " + currency2.toString());

            double fromAmount;
            while(true){
                System.out.print(currency1.getSymbol() + " ");
                try{
                    fromAmount = Double.parseDouble(scanner.nextLine());
                    break;
                }catch (Exception e){
                    System.out.println("Invalid amount. Try again.");
                }
            }

            double inUSD = fromAmount / currency1.getConversionToUSD();
            double inTarget = inUSD * currency2.getConversionToUSD();

            DecimalFormat format = new DecimalFormat("#.##");

            System.out.println("= " + currency2.getSymbol() + " " + format.format(inTarget) + "\n");
            System.out.print("Run another conversion? (Y/n)   ");
            runAgain = scanner.nextLine().equalsIgnoreCase("Y");
        }while(runAgain);


    }
}
