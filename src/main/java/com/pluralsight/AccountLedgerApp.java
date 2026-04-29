package com.pluralsight;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AccountLedgerApp {
    static LocalDateTime today = LocalDateTime.now();
    static Scanner thescanner = new Scanner(System.in);
    static ArrayList<Transactions> ledger = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_Purple = "\u001B[35m";

    public static void main(String[] args) throws InterruptedException {
        getTransactions();
        homeScreen();

    }

    // menu for Home screen , prompt user to make a selection the calls methods based on their selection to perform certain task
    public static void homeScreen() throws InterruptedException {
        System.out.println( ANSI_GREEN +
        """
╔════════════════════════════════════════════════╗
║  █▀▄▀█ ▄▀█ ▀█▀ █▀█ █ ▀▄▀    █▄▄ ▄▀█ █▄░█ █▄▀░  ║
║  █░▀░█ █▀█ ░█░ █▀▄ █ █░█    █▄█ █▀█ █░▀█ █░▀▄  ║
╚════════════════════════════════════════════════╝
""" + ANSI_RESET
        );

        boolean appRunning = true;


        while (appRunning) {
            System.out.print(ANSI_GREEN +
                    """
                    ==================================================
                       🏦             PRODUCT MENU               🏦
                    ==================================================
                    """ + ANSI_RESET);
            System.out.println(
                    """
                     D - Add Deposit
                     P - Make Payment (Debit)
                     L - Leger Display Ledger
                     x - Exit """ );
            System.out.print( ANSI_GREEN + """ 
                ==================================================
                Enter command:\s""" + ANSI_RESET);
            String userInput = thescanner.nextLine();
            switch (userInput.toUpperCase().trim()) {

                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    appRunning = false;
                    break;
                default:
                    System.out.println("Wrong input");


            }

        }

    }


    // This method reads the transactions csv file and saves it to an array list
    public static void getTransactions() {


        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] tactions = line.split("\\|");
                String date = tactions[0];
                String time = tactions[1];
                String description = tactions[2];
                String vendor = tactions[3];
                double amount = Double.parseDouble(tactions[4]);

                Transactions allTransactions = new Transactions(date, time, description, vendor, amount);
                ledger.add(allTransactions);


            }
            bufferedReader.close();


        } catch (Exception e) {
            System.out.println("Something went wrong");
            throw new RuntimeException(e);
        }

    }


    public static void displayLedger() throws InterruptedException {
        loading1();


        boolean appRunning = true;

        while (appRunning) {
            System.out.println("Ledger");
            System.out.println("A - Display all entries");
            System.out.println("D) Deposits - Display only the entries that are deposits into the account");
            System.out.println("P) Payments - Display only the negative entries (or payments)");
            System.out.println("R) Reports");
            System.out.println("H) Home - go back to the home page");
            String userInput = thescanner.nextLine();
            switch (userInput.toUpperCase().trim()) {

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
                    break;
                case "H":
                    appRunning = false;
                    break;

                default:
                    System.out.println("Wrong input");

            }
        }
    }

    // This methods prompts user for input, in the case a deposit, adds the record info to the csv ledger file.
    public static void addDeposit() {

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            DateTimeFormatter fmt1 =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateAndTime = LocalDate.now().format(fmt1);
            DateTimeFormatter fmt2 =
                    DateTimeFormatter.ofPattern("HH:mm:ss");
            String minuteAndSeconds = LocalTime.now().format(fmt2);


            System.out.println("""
                    ========================================= 
                                  RECORD A DEPOSIT
                    ========================================= """);
            System.out.print("Enter Description (Ex - ergonomic keyboard / Invoice 1001 paid - : ");
            String description = thescanner.nextLine();
            System.out.print("Enter Payer/Client Name: ");
            String vendor = thescanner.nextLine();
            System.out.print("Enter Deposit Amount: ");
            double amount = thescanner.nextDouble();
            thescanner.nextLine();
            String userinput = thescanner.nextLine();
            System.out.println("Enter (x) to Exit -");

            Transactions deposit = new Transactions(dateAndTime, minuteAndSeconds, description, vendor, amount);
            ledger.add(deposit);

            String formatBar = String.format("\n%s|%s|%s|%s|%.2f", dateAndTime, minuteAndSeconds, description, vendor, amount);
            bufferedWriter.write(formatBar);
            bufferedWriter.close();
            System.out.println("Deposit successfully recorded");


        } catch (Exception e) {
            System.out.println("invalid input");
            throw new RuntimeException(e);
        }


    }

    public static void makePayment() {

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateAndTime = LocalDate.now().format(fmt1);
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String minuteAndSeconds = LocalTime.now().format(fmt2);
            System.out.println("""
                    ========================================= 
                                  RECORD A DEBIT
                    ========================================= """);
            System.out.print("Enter Description (Ex - ergonomic keyboard / Invoice 1001 paid - : ");
            String description = thescanner.nextLine();
            System.out.print("Enter Payer/Client Name: ");
            String vendor = thescanner.nextLine();
            System.out.print("Enter Debit Amount: ");
            double amount = thescanner.nextDouble();
            thescanner.nextLine();
            System.out.println("Enter (X) to Exit -");
            String userinput = thescanner.nextLine();


            Transactions deposit = new Transactions(dateAndTime, minuteAndSeconds, description, vendor, amount);
            ledger.add(deposit);

            String formatBar = String.format("\n%s|%s|%s|%s|%.2f", dateAndTime, minuteAndSeconds, description, vendor, -amount);
            bufferedWriter.write(formatBar);
            bufferedWriter.close();
            System.out.println("Deposit successfully recorded");


        } catch (Exception e) {
            System.out.println("invalid input");
            throw new RuntimeException(e);
        }


    }

    public static void displayAll() throws InterruptedException {
        loading1();

        ledger.sort(Comparator.comparing(Transactions::getDate).reversed());
        System.out.print(ANSI_CYAN +
                """
                        ======================================================================
                           *                      Account Ledger                       *
                        ======================================================================
                         Date | Time |              Description        | Vendor     | Price
                        ----------------------------------------------------------------------
                        """ + ANSI_RESET
        );

        for (Transactions product : ledger) {
            System.out.printf("%s|%s|%s|%s|%.2f\n", product.getDate(), product.getTime(), product.getDescription(), product.getVendor(), product.getAmount());

        }
        System.out.println(ANSI_CYAN +
                """
                        ----------------------------------------------------------------------                   
                        ======================================================================
                        """ + ANSI_RESET
        );


    }

    public static void displayDeposits() {
        ledger.sort(Comparator.comparing(Transactions::getDate).reversed());
        System.out.print(ANSI_CYAN +
                """
                        ======================================================================
                           *                      Account Deposits                      *
                        ======================================================================
                         Date | Time |              Description        | Vendor     | Price
                        ----------------------------------------------------------------------
                        """ + ANSI_RESET
        );

        double deposits = 0.00;

        for (Transactions product : ledger) {


            if ( product.getAmount() > 0)

                System.out.printf( "%s|%s|%s|%s|" + ANSI_GREEN + "%.2f\n" + ANSI_RESET , product.getDate(), product.getTime(), product.getDescription(), product.getVendor(), product.getAmount());

        }
        System.out.println(ANSI_CYAN +
                """
                        ----------------------------------------------------------------------                   
                        ======================================================================
                        """ + ANSI_RESET
        );


    }

    public static void displayPayments() {
        ledger.sort(Comparator.comparing(Transactions::getDate).reversed());
        System.out.print(ANSI_CYAN +
                """
                        ======================================================================
                           *                      Account Debits                       *
                        ======================================================================
                         Date | Time |              Description        | Vendor     | Price
                        ----------------------------------------------------------------------
                        """ + ANSI_RESET
        );

        double debits = 0.00;

        for (Transactions product : ledger) {

            if (product.getAmount() < debits)
                System.out.printf("%s|%s|%s|%s|" + ANSI_RED + "%.2f\n" + ANSI_RESET, product.getDate(), product.getTime(), product.getDescription(), product.getVendor(), product.getAmount());

        }
        System.out.println(ANSI_CYAN +
                """
                        ----------------------------------------------------------------------                   
                        ======================================================================
                        """ + ANSI_RESET
        );


    }

    public static void reports() throws InterruptedException {
        loading1();
        boolean appRunning = true;


        while (appRunning) {

            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Return home");
            int UserInput = thescanner.nextInt();
            thescanner.nextLine();

            switch (UserInput) {
                case 1:
                    searchMtM();
                    break;
                case 2:
                    searchPm();
                    break;
                case 3:
                    searchYTD();
                    break;
                case 4:
                    searchPY();
                    break;
                case 5:
                   searchByV();
                    break;
                case 6:
                //    customSearch();
                    break;
                case 0 :
                    appRunning = false;
                    break;

            }

        }

    }

    public static void searchMtM() {
        System.out.print("Enter the year: ");
        int year = thescanner.nextInt();

        System.out.print("Enter the month: ");
        int month = thescanner.nextInt();

        System.out.print("Enter the Day of Month: ");
        int dOM = thescanner.nextInt();
        thescanner.nextLine();

        LocalDate userDate = LocalDate.of(year, month, dOM);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate userTime = LocalDate.parse(userDate.format(fmt1));
        int userYtime = userTime.getYear();
        int userMTime = userTime.getMonthValue();
        for (Transactions date : ledger) {
            LocalDate timeyTime = LocalDate.parse(date.getDate());

            int yearTime = timeyTime.getYear();
            int monthTime = timeyTime.getMonthValue();


            if (userMTime ==  monthTime && yearTime == userYtime)

                System.out.printf("%s|%s|%s|%s|%.2f\n", date.getDate(), date.getTime(), date.getDescription(), date.getVendor(), date.getAmount());

        }
    }

    public static void searchPm(){
        LocalDate nowTime = LocalDate.now().minusMonths(1);
        int newMonth = nowTime.getMonthValue();
        int newYear = nowTime.getYear();

        for (Transactions date : ledger) {
            LocalDate timeyTime = LocalDate.parse(date.getDate());

            int yearTime = timeyTime.getYear();
            int monthTime = timeyTime.getMonthValue();


            if (monthTime == newMonth &&  newYear == yearTime)

                System.out.printf("%s|%s|%s|%s|%.2f\n", date.getDate(), date.getTime(), date.getDescription(), date.getVendor(), date.getAmount());



        }

    }

    public static void searchYTD(){

        for (Transactions date : ledger) {
            int thisYear = today.getYear();
            LocalDate timeyTime = LocalDate.parse(date.getDate());

            int yearTime = timeyTime.getYear();

            if (yearTime == thisYear)

                System.out.printf("%s|%s|%s|%s|%.2f\n", date.getDate(), date.getTime(), date.getDescription(), date.getVendor(), date.getAmount());



        }

    }

    public static void searchPY(){

        for (Transactions date : ledger) {
            int thisYear = today.getYear();
            LocalDate timeyTime = LocalDate.parse(date.getDate());

            int yearTime = timeyTime.getYear();

            if (yearTime == thisYear - 1)

                System.out.printf("%s|%s|%s|%s|%.2f\n", date.getDate(), date.getTime(), date.getDescription(), date.getVendor(), date.getAmount());

        }
    }

    public static void searchByV(){
        System.out.println("Enter the vendor: ");

        String theVendor = thescanner.nextLine();

        for (Transactions vendor : ledger){

            if(theVendor.equals(vendor.getVendor())){

                System.out.printf("%s|%s|%s|" + ANSI_Purple + "%s|" + ANSI_RESET + "%.2f\n", vendor.getDate(), vendor.getTime(), vendor.getDescription(), vendor.getVendor(), vendor.getAmount());

            }
        }
    }

    public static void loading1() throws InterruptedException {

        for(int i = 1; i < 3; i++){
            System.out.print("\rFethching Data [>     ]");
            Thread.sleep(300);
            System.out.print("\rFethching Data [>>    ]");
            Thread.sleep(300);
            System.out.print("\rFethching Data [>>>   ]");
            Thread.sleep(300);
            System.out.print("\rFethching Data [>>>>  ]");
            Thread.sleep(300);
            System.out.print("\rFethching Data [>>>>> ]");
            Thread.sleep(300);
            System.out.print("\rFethching Data [>>>>>>]");
            Thread.sleep(300);

        }
        System.out.println();

    }

}

