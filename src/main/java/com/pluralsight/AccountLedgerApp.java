package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountLedgerApp {
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


    public static void addDeposit(){








    }





    }


