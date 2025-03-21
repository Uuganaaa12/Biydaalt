package com.mycompany.app.cli;

public class HelpFormatter {

    public static void displayHelp() {
        System.out.println("--------< FlashCard >--------");
        System.out.println("  - Kart burt heden udaa zuw hariulah shaardlagatai ve");
        System.out.println("  - Kartiin daraalliig songono uu:  (random, worst-first, recent-mistakes-first)");
        System.out.println("  - Kartuudiin asuult hariultiin bairiig solih uu?");
        System.out.println("\n Program ajilj baih uyiin kommanduud:");
        System.out.println("  Type 'skip' to skip the current question");
        System.out.println("  Type 'exit' to quit the application");
        System.out.println("\nBuh kartiig hariulj duussani daraa hiih solgoltuud:");
        System.out.println("  - Saynii kartuudaa dahin ajillah");
        System.out.println("  - Songoltuudiig dahin tohiruulah (repetitions, order, inversion)");
    }
}