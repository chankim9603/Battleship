//I worked on the homework assignment alone, using only course materials.
import java.util.Scanner;
public class Battleship {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Battleship!\n");
        char[][] player1 = new char[5][5];
        char[][] player2 = new char[5][5];
        char[][] player1Status = new char[5][5];
        char[][] player2Status = new char[5][5];


        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                player1[row][col] = '-';
                player2[row][col] = '-';
                player1Status[row][col] = '-';
                player2Status[row][col] = '-';
            }
        }
        //for player1 input
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        userInput(player1, sc);
        printBattleShip(player1);


        for (int i = 0; i <= 99; i++) {
            System.out.print("\n");
        }

  //for player2
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        userInput(player2, sc);
        printBattleShip(player2);

        for (int i = 0; i <= 98; i++) {
            System.out.print("\n");
        }
        boolean checker = true;
        int counter = 0;
        int counter1 = 0;
        int counter2 = 0;


        while (checker) {
        //player1
            counter1 += playGame(player2, player1Status, 1, 2, counter, sc);
            if (counter1 == 5) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
                checker = false;
                break;
            }
        //player2
            counter2 += playGame(player1, player2Status, 2, 1, counter, sc);
            if (counter2 == 5) {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
                checker = false;
                break;
            }
        }
        System.out.println("Final boards: ");
        printBattleShip(player1);
        System.out.println();
        printBattleShip(player2);
        System.out.println();
    }



    private static void userInput(char[][] player, Scanner sc) {

        int counter = 0;
        do {
            System.out.println("Enter ship " + (counter + 1) + " location: ");
            int coordinate1 = sc.nextInt();
            int coordinate2 = sc.nextInt();
            if (coordinate1 >= 5 || coordinate1 < 0) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                continue;
            }
            if (coordinate2 >= 5 || coordinate2 < 0) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                continue;
            }
            if (player[coordinate1][coordinate2] == '-') {
                player[coordinate1][coordinate2] = '@';
            } else {
                System.out.println("You already have a ship there. Choose different coordinates.");
                continue;
            }
            ++counter;
        } while (counter < 5);
    }
    private static int playGame(char[][] player, char[][] playerStat, int playerNum, int oppo, int count, Scanner sc) {
        int coordinate1;
        int coordinate2;
        boolean playerChecker = true;
        while (playerChecker) {
            playerChecker = true;
            System.out.printf("\nPlayer %d, enter hit row/column: \n", playerNum);
            coordinate1 = sc.nextInt();
            coordinate2 = sc.nextInt();
            if (coordinate1 >= 5 || coordinate1 < 0) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                continue;
            }
            if (coordinate2 >= 5 || coordinate2 < 0) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                continue;
            }
            if (player[coordinate1][coordinate2] == '-') {
                player[coordinate1][coordinate2] = 'O';
                playerStat[coordinate1][coordinate2] = 'O';
                System.out.printf("PLAYER %d MISSED!\n", playerNum);
                printBattleShip(playerStat);
                System.out.println();
                playerChecker = false;
            } else if (playerStat[coordinate1][coordinate2] == 'X' || playerStat[coordinate1][coordinate2] == 'O') {
                System.out.print("You already fired on this spot. Choose different coordinates.");
                continue;
            } else {
                player[coordinate1][coordinate2] = 'X';
                playerStat[coordinate1][coordinate2] = 'X';
                System.out.printf("PLAYER %d HIT PLAYER %d's SHIP!\n", playerNum, oppo);
                printBattleShip(playerStat);
                ++count;
                playerChecker = false;
            }
        }
        return count;
    }
 // Use this method to print game boards to the console.
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }
}
