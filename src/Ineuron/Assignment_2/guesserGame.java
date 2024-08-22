package Ineuron.Assignment_2;

import java.util.Random;
import java.util.Scanner;

class Guesser {
    int numToGuess;
    Random rand = new Random();

    int guesserNumber(int start, int end) {
        numToGuess = rand.nextInt(end - start + 1) + start;
        System.out.println("Guesser guessed the number: " + numToGuess); // For checking
        return numToGuess;
    }
}



class Player {
    int numGuessByPlayer;
    Random rand = new Random();

    int guessNumByPlayer(int start, int end, int playerNumber) {
        numGuessByPlayer = rand.nextInt(end - start + 1) + start;
        System.out.println("Player " + playerNumber + " guessed the number: " + numGuessByPlayer); // For checking
        return numGuessByPlayer;
    }
}



class Umpire {
    int guesserNum;
    int numGuessByPlayer1;
    int numGuessByPlayer2;
    int numGuessByPlayer3;
    int numGuessByPlayer4;
    int countWonPlayer1 = 0;
    int countWonPlayer2 = 0;
    int countWonPlayer3 = 0;
    int countWonPlayer4 = 0;

    Scanner sc = new Scanner(System.in);
    int st, en;
    boolean isCheck = false;

    void takeGuesserNum() {
        System.out.print("Please, provide Guesser start point of range: ");
        st = sc.nextInt();
        System.out.print("Please, provide Guesser end point of range: ");
        en = sc.nextInt();

        Guesser g = new Guesser();
        guesserNum = g.guesserNumber(st, en);
        isCheck = true;
    }

    void takePlayersNum() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        numGuessByPlayer1 = p1.guessNumByPlayer(st, en, 1);
        numGuessByPlayer2 = p2.guessNumByPlayer(st, en, 2);
        numGuessByPlayer3 = p3.guessNumByPlayer(st, en, 3);
        numGuessByPlayer4 = p4.guessNumByPlayer(st, en, 4);
    }

    void usualGame() {
        if (guesserNum == numGuessByPlayer1) {
            countWonPlayer1 += 1;
        }
        if (guesserNum == numGuessByPlayer2) {
            countWonPlayer2 += 1;
        }
        if (guesserNum == numGuessByPlayer3) {
            countWonPlayer3 += 1;
        }
        if (guesserNum == numGuessByPlayer4) {
            countWonPlayer4 += 1;
        }
    }

    void takeSemiFinalPlayers(int[] nums1) {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        int p = 0, idx = 0;

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != 0) {
                p = nums1[i];
                idx = i;
                break;
            }
        }
        numGuessByPlayer1 = p1.guessNumByPlayer(st, en, p);

        for (int i = idx + 1; i < nums1.length; i++) {
            if (nums1[i] != 0) {
                p = nums1[i];
                idx = i;
                break;
            }
        }
        numGuessByPlayer2 = p2.guessNumByPlayer(st, en, p);

        for (int i = idx + 1; i < nums1.length; i++) {
            if (nums1[i] != 0) {
                p = nums1[i];
                break;
            }
        }
        numGuessByPlayer3 = p3.guessNumByPlayer(st, en, p);
    }

    void semiFinalGame() 
    {
        int[] arr = {countWonPlayer1, countWonPlayer2, countWonPlayer3, countWonPlayer4};
        int[] nums = new int[4];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min != arr[i]) {
                nums[j] = i + 1;
            } else {
                nums[j] = 0;
            }
            j += 1;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                count += 1;
            }
        }
        if (count == 0) {
            for (int i = 0; i < 5; i++) {
                takePlayersNum();
                usualGame();
            }
        } else if (count == 2) {
            System.out.println("These players play final game: " + nums[0] + ", " + nums[1]);
            takeGuesserNum();
            finalGame(nums[0], nums[1]);
        } else if (count == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    System.out.println("Player " + nums[i] + " won the final game.");
                }
            }
        } else {
            System.out.print("These players play semi final game out of 4 players: ");
            for (int p : nums) {
                if (p != 0) {
                    System.out.print(p + " ");
                }
            }

            System.out.println();
            takeGuesserNum();

            countWonPlayer1 = 0;
            countWonPlayer2 = 0;
            countWonPlayer3 = 0;

            for (int i = 0; i < 3; i++) {
                takeSemiFinalPlayers(nums);

                if (guesserNum == numGuessByPlayer1) {
                    countWonPlayer1 += 1;
                }
                if (guesserNum == numGuessByPlayer2) {
                    countWonPlayer2 += 1;
                }
                if (guesserNum == numGuessByPlayer3) {
                    countWonPlayer3 += 1;
                }
            }

            int[] arr1 = {countWonPlayer1, countWonPlayer2, countWonPlayer3};
            int[] fp = new int[2];
            int min1 = Integer.MAX_VALUE;
            int pos = 0;
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] < min1) {
                    min1 = arr1[i];
                    if (nums[i] == 0)
                        pos = i + 1;
                    else
                        pos = i;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0 && nums[pos] == nums[i]) {
                    nums[pos] = 0;
                }
            }

            int j1 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    fp[j1] = nums[i];
                    j1 += 1;
                }
            }

            int pp1 = fp[0], pp2 = fp[1];
            System.out.println("These players play final game: " + pp1 + ", " + pp2);
            takeGuesserNum();
            finalGame(pp1, pp2);
        }
    }

    void finalGame(int pn1, int pn2) {
        Player p1 = new Player();
        Player p2 = new Player();

        numGuessByPlayer1 = p1.guessNumByPlayer(st, en, pn1);
        numGuessByPlayer2 = p2.guessNumByPlayer(st, en, pn2);

        if (guesserNum == numGuessByPlayer1) {
            if (guesserNum == numGuessByPlayer2) {
                System.out.println("Player " + pn1 + " and " + pn2 + " won the final game.");
            } else {
                System.out.println("Player " + pn1 + " won the final game.");
            }
        } else if (guesserNum == numGuessByPlayer2) {
            System.out.println("Player " + pn2 + " won the final game.");
        } else {
            System.out.println("Game Tied! No one guessed the correct number.");
            finalGame(pn1, pn2);
        }
    }
}

public class guesserGame {

    public static void main(String[] args) {
        Umpire umpire = new Umpire();
        umpire.takeGuesserNum();
        if (umpire.isCheck) {
            for (int i = 0; i < 5; i++) {
                umpire.takePlayersNum();
                umpire.usualGame();
            }
            umpire.semiFinalGame();
        }
    }
}

