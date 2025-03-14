package com.mycompany.app.cli;

public class HelpFormatter {

    public static void displayHelp() {
        System.out.println("--------< FlashCardFile >--------");
        System.out.println("Songoltuud:");
        System.out.println("  --help                   Ashiglaj boloh songoltuudiig haruulna");
        System.out.println("  --order <type>           Asuultuudiig zohion baiguulna [random, worst-first, recent-mistakes-first]");
        System.out.println("                           Default: random");
        System.out.println("  --repetitions <number>   Card burt dor hayj heden udaa zuw hariulah too");
        System.out.println("                           Default: 1");
        System.out.println("  --invertCards            Asuult bolon hariultuudiin bairiig solino");
        System.out.println("                           Default: false");
    }
}