package by.itransition.training.task03;

import java.security.SecureRandom;
import java.util.Scanner;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        boolean repeat = checkRepeat(args);
        if (repeat) {
            System.out.println("Error. Input parameters must not be repeated.");
        } else if (args.length % 2 != 0 && args.length > 1) {

            boolean menuSelected = false;
            Table table = new Table(args);
            SecureRandom random;
            Hmac hmacMachine;
            int machineMove;
            String menu;

            random = new SecureRandom();
            machineMove = 1 + random.nextInt(args.length);
            hmacMachine = new Hmac(args[machineMove - 1]);
            hmacMachine.printHmac();

            menu = "Available moves:\n";
            for (int i = 0; i < args.length; i++) {
                menu += String.format("%s - %s\n", i + 1, args[i]);
            }
            menu += "0 - exit\n";
            menu += "? - help\n";
            System.out.println(menu);

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while (!menuSelected) {
                System.out.println("Enter you move: ");

                if (sc.hasNextInt()) {
                    int userMove = sc.nextInt();

                    if (userMove > 0 && userMove <= args.length) {
                        menuSelected = true;
                        Referee referee = new Referee();
                        System.out.println("You move: " + args[userMove - 1]);
                        System.out.println("Machine move: " + args[machineMove - 1]);
                        int result = referee.getUserResult(args.length, userMove, machineMove);

                        if (result == 1) {
                            System.out.println("You win!");
                        } else if (result == -1) {
                            System.out.println("You lose!");
                        } else {
                            System.out.println("Draw!");
                        }
                        hmacMachine.printKey();
                    } else if (userMove == 0) {
                        return;
                    } else {
                        System.out.println("Error. Enter a menu number from the list.");
                    }
                } else if (sc.nextLine().equals("?")) {
                    table.viewTable();
                } else {
                    System.out.println("Error. Enter a menu number from the list.");
                }
            }
        } else {
            System.out.println("Error. Enter an odd number of arguments (>1).");
        }
    }

    public static boolean checkRepeat(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                for (int j = i + 1; j < args.length; j++) {
                    if (args[i].equals(args[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}