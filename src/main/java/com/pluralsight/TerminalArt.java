package com.pluralsight;
import java.util.Random;
public class TerminalArt {
    public static final String ANSI_RESET          = "\u001B[0m";
    public static final String ANSI_GREEN_BG_BRIGHT= "\u001B[102m";
    public static final String ANSI_WHITE_BRIGHT   = "\u001B[97m";

    public static void matrixDropFx() throws InterruptedException {
        int width = 50;
        int height = 15;
        int[] rain = new int[width];
        Random rDrop = new Random();

        for(int i = 0; i < width; i++){
            int rNN = rDrop.nextInt(height);
            rain [i] = rNN;
        }

        String Ascii = "ABILiklISHDLHKLMOPQRSTUVWXYZ0123456789!@#$%^&*+{}|";

        for(int iframe = 0; iframe < 60; iframe++){

            System.out.print("\u001B[7;1H");
            StringBuilder frame = new StringBuilder();

            for (int y = 0; y < height; y++){


                for(int x = 0; x < width; x++){

                    if (rain[x] == y){
                        char randomChar = Ascii.charAt(rDrop.nextInt(Ascii.length()));
                        frame.append(ANSI_WHITE_BRIGHT).append(randomChar).append(ANSI_RESET);
                    } else if (y < rain[x] && y >= rain[x] - 6) {
                        char randomChar = Ascii.charAt(rDrop.nextInt(Ascii.length()));
                        frame.append(ANSI_GREEN_BG_BRIGHT).append(randomChar).append(ANSI_RESET);
                    }else
                        frame.append(" ");



                }
                frame.append("\n");




            }

            System.out.print(frame.toString());

            for (int i = 0; i < width; i++){
                rain[i]++;
                if (rain[i] >= height) {
                    rain[i] = rDrop.nextInt(height / 2) * -1;
                }
            }
            Thread.sleep(75);
            }


        }












    }



