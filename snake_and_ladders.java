import java.util.Scanner;
import static java.lang.Math.*;
public class snake_and_ladders {
    /* OUTLINE
    array set for players; max 4.
     */
    String i_only_take_input;
    String global_choice;
    int max_player;
    int dice_val;
    String players[] = new String[4];
    int player_location[] = {0, 0, 0, 0};
    int player_lock[] = {0, 0, 0, 0};
    int snake_in[] = {32, 36, 48, 62, 88, 95, 97};
    int snake_out[] = {10, 6, 26, 18, 24, 56, 78};
    int ladder_in[] = {1, 4, 8, 21, 28, 50, 71, 88};
    int ladder_out[] = {38, 14, 30, 42, 76, 67, 92, 99};
    Scanner sc = new Scanner(System.in);
    snake_and_ladders() {
        System.out.println("Welcome to Snake and Ladders Game\nCreated by Ayushmaan Karmokar.");
        System.out.print("Enter Maximum(4) Number of Players Playing: ");
        max_player = 2;
        System.out.print("\nCool! Enter names for each players!");
        for (int i = 0; i < max_player; i++) {
            System.out.print("\nPlayer "+(i+1)+"?: ");
            players[i] = sc.nextLine();
        }
        System.out.print("\nDo you want CPU to play?\nCPU can ONLY be ONE(will be the first one). (Y/N) ");
        global_choice = sc.nextLine();
        if(global_choice == "Y") {
            players[0] = "CPU";
            System.out.println("PLAYER 1 is CPU.");
        }
        game_start();
    }

    void game_start() {
        System.out.println("Game Start! You need at least 6 to get out!");
        while(true) {
            for (int i = 0; i < max_player; i++) {
                if (players[i] == "CPU" && i == 0) {
                    /* block for CPU AUTO */
                    if (player_lock[i] == 0) {
                        dice();
                        System.out.printf("%s, dice: %d", players[i], dice_val);
                        if (dice_val == 6) {
                            player_lock[i] = 1;
                            System.out.println("CPU got out! Rolling the Dice again to move!");
                            player_location[i] += dice();
                            System.out.printf("%s, dice: %d\nLocation: %d",
                                    players[i], dice_val, player_location[i]);
                            game_check();
                        }
                        else {
                            System.out.println("\nNot 6!");
                        }
                    }
                    else {
                        System.out.println("%s 's turn.\nRolling the Dice!");
                        player_location[i] += dice();
                        System.out.printf("%s, dice: %d\nLocation: %d",
                                players[i], dice_val, player_location[i]);
                        game_check();
                    }
                    System.out.println("NEXT PLAYER!");
                }
                else {
                    /* block for Player HUMAN */
                    if (player_lock[i] == 0) {
                        dice();
                        System.out.printf("Player %s, dice: %d", players[i], dice_val);
                        if (dice_val == 6) {
                            player_lock[i] = 1;
                            System.out.println("Congratulations! You got out! Roll the Dice again to move!");
                            i_only_take_input = sc.nextLine();
                            player_location[i] += dice();
                            System.out.printf("Player %s, dice: %d\nLocation: %d",
                                    players[i], dice_val, player_location[i]);
                            game_check();
                        }
                        else {
                            System.out.println("\nNot 6!");
                        }
                    }
                    else {
                        System.out.println("Player %s 's turn.\nRoll the Dice!");
                        i_only_take_input = sc.nextLine();
                        player_location[i] += dice();
                        System.out.printf("Player %s, dice: %d\nLocation: %d",
                                players[i], dice_val, player_location[i]);
                        game_check();
                    }
                    System.out.println("NEXT PLAYER!");
                }
            }
        }
    }

    void game_check() {
        for (int i = 0; i < max_player; i++) {
            for (int j = 0; j < 7; j++) {
                if (player_location[i] == snake_in[j]) {
                    player_location[i] = snake_out[j];
                    System.out.printf("\nOh no! Player %s got eaten by snake! New Position: %d",
                            players[i], player_location[i]);
                }
            }
            for (int k = 0; k < 8; k++) {
                if (player_location[i] == ladder_in[k]) {
                    player_location[i] = ladder_out[k];
                    System.out.printf("\nYeah! Player %s found a ladder! Climbing, Climbing! New Position: %d",
                            players[i], player_location[i]);
                }
            }
        }
    }

    int dice() {
        /* will return dice value between 1 and 6 */
        double dicer = random() * 7;
        dice_val = (int)dicer;
        if (dice_val == 0) {
            dice();
        }
        return dice_val;
    }

    public static void main(String[] args) {
        snake_and_ladders ob = new snake_and_ladders();
    }
}
