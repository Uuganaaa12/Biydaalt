package com.mycompany.app.cli;
import java.util.Arrays;

public class CommandLineOptions {
    private boolean help = false;
    private String order = "random";
    private int repetitions = 1;
    private boolean invertCards = false;
    private String cardsFile = null;
    public CommandLineOptions(String[] args) {
        parseOptions(args);
    }

    private void parseOptions(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--help":
                    help = true;
                    break;
                case "--order":
                    if (i + 1 < args.length) {
                        String orderType = args[++i];
                        if (Arrays.asList("random", "worst-first", "recent-mistakes-first").contains(orderType)) {
                            order = orderType;
                        } else {
                            System.err.println("Invalid order type: " + orderType);
                            help = true;
                        }
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) {
                        try {
                            int repetitions = Integer.parseInt(args[++i]);
                            if (repetitions > 0) {
                                this.repetitions = repetitions;
                            } else {
                                System.err.println("Dawtalt ni eyreg too baih ystoi");
                                help = true;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("aldaatai dawtaltiin utga: " + args[i]);
                            help = true;
                        }
                    }
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    if (!args[i].startsWith("--")) {
                        cardsFile = args[i];
                    } else {
                        System.err.println("iim songolt baihgui baina: " + args[i]);
                        help = true;
                    }
            }
        }
    }

    public boolean isHelp() {
        return help;
    }

    public String getOrder() {
        return order;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public boolean isInvertCards() {
        return invertCards;
    }
    

    public String getCardsFile() {
        return cardsFile;
    }
}