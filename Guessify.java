import java.util.Scanner;

public class Guessify {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int highScore = 0;
        System.out.println("Welcome to the Advanced Number Guessing Game!");
        boolean playAgain = true;
        while (playAgain) {
            int score = playNumberGuessingGame(scanner);
            if (score > highScore) {
                highScore = score;
                System.out.println("New High Score: " + highScore);
            }
            System.out.print("Do you want to play again? (yes/no): ");
            String again = scanner.next();
            playAgain = again.equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for playing! Your highest score was: " + highScore);
    }

    public static int playNumberGuessingGame(Scanner scanner) {
        System.out.println("Choose difficulty level: 1. Easy 2. Medium 3. Hard");
        int min = 1, max = 100, maxAttempts = 10, difficulty = 1;
        while (true) {
            System.out.print("Enter 1, 2, or 3: ");
            difficulty = scanner.nextInt();
            if (difficulty == 1) { max = 50; maxAttempts = 10; break; }
            else if (difficulty == 2) { max = 100; maxAttempts = 7; break; }
            else if (difficulty == 3) { max = 500; maxAttempts = 12; break; }
            else System.out.println("Invalid input. Try again.");
        }
        int numberToGuess = min + (int) (Math.random() * (max - min + 1));
        int[] guesses = new int[maxAttempts];
        int score = 0;
        System.out.println("Guess the number between " + min + " and " + max + " within " + maxAttempts + " trials.");
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            System.out.print("Attempt " + (attempt + 1) + ": Enter your guess: ");
            int userGuess = scanner.nextInt();
            guesses[attempt] = userGuess;
            if (userGuess == numberToGuess) {
                score = (maxAttempts - attempt) * difficulty * 10;
                System.out.println("Congratulations! You guessed the number in " + (attempt + 1) + " attempts.");
                System.out.println("Your score: " + score);
                break;
            } else {
                if (userGuess < numberToGuess) {
                    System.out.println("The number is greater than " + userGuess);
                } else {
                    System.out.println("The number is less than " + userGuess);
                }
                // Give a unique hint
                System.out.println("Hint: " + getHint(numberToGuess, userGuess));
                // Show previous guesses
                System.out.print("Your previous guesses: ");
                for (int i = 0; i <= attempt; i++) {
                    System.out.print(guesses[i] + (i < attempt ? ", " : "\n"));
                }
            }
            if (attempt == maxAttempts - 1) {
                System.out.println("You have exhausted all " + maxAttempts + " trials.");
                System.out.println("The correct number was " + numberToGuess);
            }
        }
        return score;
    }

    // Provide unique hints
    public static String getHint(int number, int lastGuess) {
        if (number % 2 == 0) return "The number is even.";
        if (number % 2 != 0) return "The number is odd.";
        if (isPrime(number)) return "The number is prime.";
        if (number % 5 == 0) return "The number is a multiple of 5.";
        if (Math.abs(number - lastGuess) < 10) return "You are very close!";
        return "Try a different range.";
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
