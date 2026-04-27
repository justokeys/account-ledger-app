package com.pluralsight;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountLedgerApp {
    static LocalDateTime today = LocalDateTime.now();
    static Scanner thescanner = new Scanner(System.in);
    static ArrayList<Transactions> ledger = new ArrayList<>();
    public static void main(String[] args) {
        getTransactions();
        homeScreen();

    }
    // menu for Home screen , prompt user to make a selection the calls methods based on their selection to perform certain task
    public static void homeScreen(){
        boolean appRunning = true;
        System.out.println("D -Add Deposit");
        System.out.println("P - Make Payment (Debit)");
        System.out.println("L - Leger Display Ledger");
        System.out.println("x - Exit");

        while (appRunning){
            String userInput = thescanner.nextLine();
            switch (userInput.toUpperCase()){

                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                case "X":
                    appRunning = false;
                default:
                    System.out.println("Wrong input");


            }

        }

    }


// This method reads the transactions csv file and saves it to an array list
    public static void getTransactions(){






            try {
                FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                bufferedReader.readLine();
                String line;
                while ((line = bufferedReader.readLine()) != null){

                    String[] tactions = line.split("\\|");
                    String date = tactions[0];
                    String time = tactions[1];
                    String description = tactions[2];
                    String vendor = tactions[3];
                    double amount = Double.parseDouble(tactions[4]);

                    Transactions allTransactions = new Transactions(date,time,description,vendor,amount);
                    ledger.add(allTransactions);



                }
                bufferedReader.close();





            } catch (Exception e) {
                System.out.println("Something went wrong");
                throw new RuntimeException(e);
            }

        }


    public static void displayLedger(){
        System.out.println("Ledger");
        System.out.println("A - Display all entries");
        System.out.println("D) Deposits - Display only the entries that are deposits into the account");
        System.out.println("P) Payments - Display only the negative entries (or payments)");
        System.out.println("R) Reports");
        System.out.println("H) Home - go back to the home page");

        while (appRunning){
            String userInput = thescanner.nextLine();
            switch (userInput.toUpperCase()){

                case "A":
                    displayAll();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reports();
                case "H":
                    appRunning = false;
                default:
                    System.out.println("Wrong input");


            }

        }








    }

// This methods prompts user for input, in the case a deposit, adds the recored info to the csv ledger file.
    public static void addDeposit(){
         boolean apprunning = true;

         while (apprunning)
             try {
                 FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 DateTimeFormatter fmt1 =
                         DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 String dateAndTime = LocalDate.now().format(fmt1);
                 DateTimeFormatter fmt2 =
                         DateTimeFormatter.ofPattern("HH:mm:ss");
                 String minuteAndSeconds = LocalDate.now().format(fmt2);
                 System.out.println("""
                                       ========================================= 
                                                     RECORD A DEPOSIT
                                       ========================================= """  );
                 System.out.print("Enter Description (Ex - ergonomic keyboard / Invoice 1001 paid - : ");
                 String description = thescanner.nextLine();
                 System.out.print("Enter Payer/Client Name: ");
                 String vendor = thescanner.nextLine();
                 System.out.print("Enter Deposit Amount: ");
                 double amount = thescanner.nextDouble();
                 thescanner.nextLine();
                 String userinput = thescanner.nextLine();
                 System.out.println("Enter (x) to Exit -");

                 if (userinput.equalsIgnoreCase("X")){
                     apprunning = false;

                 }

                 Transactions deposit = new Transactions(dateAndTime,minuteAndSeconds,description,vendor,amount);
                 ledger.add(deposit);

                 String formatBar = String.format("%s|%s|%s|%s|%.2f",dateAndTime,minuteAndSeconds,description,vendor,amount);
                 bufferedWriter.write(formatBar);
                 bufferedWriter.newLine();
                 bufferedWriter.close();
                 System.out.println("Deposit successfully recorded");


             } catch (Exception e) {
                 System.out.println("invalid input");
                 throw new RuntimeException(e);
             }











    }





    }


