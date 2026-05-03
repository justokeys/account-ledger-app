package com.pluralsight;
import java.util.Random;
public class TerminalArt {
    public static final String ANSI_RESET          = "\u001B[0m";
    public static final String ANSI_GREEN_BG_BRIGHT= "\u001B[102m";
    public static final String ANSI_WHITE_BRIGHT   = "\u001B[97m";
   // method displays Matrix movie ASCii rain drop effect
    public static void matrixDropFx() throws InterruptedException {
        // sets the width and height of the effect
        int width = 50;
        int height = 15;
        //sets the width to array the size of the width, will track the y position set in each index
        int[] rain = new int[width];
        Random rDrop = new Random(); // each rdrop will be random charter to give ASCII effect when looped through

        for(int i = 0; i < width; i++){
            int rNN = rDrop.nextInt(height);// set a random start row between 0 and 14
            rain [i] = rNN; //stores the column in the tracker from i (width)
        }
            // the charaters that will be randomly assigned to give matrix raindrop pixel effect
        String Ascii = "ABILiklISHDLHKLMOPQRSTUVWXYZ0123456789!@#$%^&*+{}|";


        for(int iframe = 0; iframe < 60; iframe++){  //loops runs 60 times thread 75 mill so 60 frames per sec

            System.out.print("\u001B[7;1H");//clears old frame from moving cursor to column 1 row 7
            StringBuilder frame = new StringBuilder();//temporary "canvas" to build the entire screen text

            for (int y = 0; y < height; y++){ // Move from Top Row to bottom row

                //Move from Left Column to Right Column
                for(int x = 0; x < width; x++){
                    // checks if y is the head of row , if so it prints white random ascii
                    if (rain[x] == y){
                        char randomChar = Ascii.charAt(rDrop.nextInt(Ascii.length()));
                        frame.append(ANSI_WHITE_BRIGHT).append(randomChar).append(ANSI_RESET);
                        // checks if y is greater than 6 if  true prints all 6 as a green tail
                    } else if (y < rain[x] && y >= rain[x] - 6) {
                        char randomChar = Ascii.charAt(rDrop.nextInt(Ascii.length()));
                        frame.append(ANSI_GREEN_BG_BRIGHT).append(randomChar).append(ANSI_RESET);
                        // both false prints blank , greats space to make rain drop effect
                    }else
                        frame.append(" ");



                }
                frame.append("\n");//Move to the next row on our canvas.




            }


            System.out.print(frame.toString());
            for (int i = 0; i < width; i++){//prepare for the NEXT frame.
                rain[i]++;// Move every drop down by 1 row (Gravity).
                if (rain[i] >= height) {// Move every drop down by 1 row (Gravity).
                    rain[i] = rDrop.nextInt(height / 2) * -1;
                }
            }
            Thread.sleep(75);
            }// Wait 75ms so the human eye can actually see the movement.

            // I have to credit and Cite Gemini Ai for help with math and nested for loop
        }












    }



