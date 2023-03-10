// Brady Manske
// 1/10/23
// CS&145
// Lab 1: Guessing Game

// This program will play a guessing-numbers game
// with the user a desired number of times,
// and report the user's game statistics upon termination.

import java.util.*; // for Scanner & Random

public class Guess {
    public static final int MAX = 100;
   
    public static void main(String[] args) {
        // initializing variables & objects
        int totalGuesses = 0;
        int totalRounds = 0;
        int currentScore = 0;
        int bestScore = 99999;
        boolean again = false;
        char response = 'x';
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
      
        intro();
        do {
            currentScore = round(scanner, random);
            if (currentScore < bestScore) {
                bestScore = currentScore; // updates high score
            } // end of if
            totalGuesses += currentScore; // updates guess count
            totalRounds++; // updates round count
            do {
                System.out.print("Do you want to play again? ");
                response = scanner.next().toLowerCase().charAt(0);
                switch (response) {
                    case 'y':
                        again = true;
                        break;
                    case 'n':
                        again = false;
                        break;
                    default:
                    System.out.println("Invalid command. Please type yes or no.");
                } // end of switch
                scanner.nextLine(); // carriage return
            } while (response != 'y' && response != 'n');
            System.out.println(); // received valid user input
        } while (again); // starts another round, if user requests
        stats(totalRounds, totalGuesses, bestScore);
    } // end of main method
   
    // Introduces the game's rules to the user
    public static void intro() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and");
        System.out.println(MAX + " and will allow you to guess until");
        System.out.println("you get it. For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower");
        System.out.println("than your guess.");
        System.out.println();
    } // end of intro method
   
    // Plays one round of a guessing-numbers game with the user,
    // and returns an integer representing the user's score for the round
    public static int round(Scanner scanner, Random random) {
        // initializing variables
        int number = random.nextInt(MAX) + 1;
        int guesses = 0;
        int input = 0;
      
        System.out.println("I'm thinking of a number between 1 and " +
            MAX + "...");
        do {
            System.out.print("Your guess? ");
            try {
                input = scanner.nextInt();
                if (MAX < input || input < 1) { // input valid but
                    // outside guessing range
                    System.out.println("Your guess is " +
                        "outside the range. Remember,");
                    System.out.println("the answer is somewhere between " +
                        "1 and " + MAX + ". ");
                    System.out.println();
                    System.out.println("Please try again.");
                } else if (input < number) { // guess is
                    // lower than answer but within range
                    System.out.println("It's higher.");
                    guesses++;
                } else if (input > number) { // guess is
                    // higher than answer but within range
                    System.out.println("It's lower.");
                    guesses++;
                } else { // correct guess
                    guesses++;
                    String plural = "";
                    if (guesses > 1) {
                        plural = "es";
                    } // end of if
                    System.out.println("You got it right in " +
                        guesses + " guess" + plural);
                } // end of if/elses
            } catch (InputMismatchException e) { // input is not an integer
                System.out.println("Invalid guess. Please type a number " +
                    "between 1 and " + MAX + ".");
            } // end of try/catch
            scanner.nextLine(); // carriage return
        } while (input != number); // keep guessing until correct
        return guesses;
    } // end of round method
   
    // Displays the user's total number of games played,
    // total and average number of guesses made,
    // and high score
    public static void stats(int totalRounds, int totalGuesses, int bestScore) {
        System.out.println("Overall results:");
        System.out.printf("\t %-14s= %d%n", "total games", totalRounds);
        System.out.printf("\t %-14s= %d%n", "total guesses", totalGuesses);
        System.out.printf("\t %-14s= %.1f%n", "guesses/game",
            (double)totalGuesses / totalRounds);
        System.out.printf("\t %-14s= %d%n", "best game", bestScore);
    } // end of stats method
} // end of Guess class