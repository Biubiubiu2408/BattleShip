import java.util.ArrayList;
import java.util.Scanner;

public class BattleShip {
    static int[][] MyGrid = new int[10][10];
    static int[][] RivalGrid = new int[10][10];
    static char[][] MyResult = new char[10][10];
    static char[][] RivalResult = new char[10][10];
    public static void PlaceShip(int[][] Grid, ArrayList<Ship> Ship)
    {
        for (int i = 0; i < Ship.size(); i++) {
            while (true) {
                int flag = 0;
                int randnum = (int) (Math.random() * 100 + 1);
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);
                if (x + Ship.get(i).holes > 9 || y + Ship.get(i).holes > 9)
                    continue;

                if (randnum % 2 == 1)
                {
                    for (int j = x; j < x + Ship.get(i).holes; j++) {
                        if (Grid[j][y] == 1) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        for (int j = x; j < x + Ship.get(i).holes; j++)
                            Grid[j][y] = 1;
                        break;
                    }

                } else
                {
                    for (int j = y; j < y + Ship.get(i).holes; j++) {
                        if (Grid[x][j] == 1) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        for (int j = x; j < x + Ship.get(i).holes; j++)
                            Grid[x][j] = 1;
                        break;
                    }
                }
            }

        }
    }

    public static void PrintGrid(int[][] Grid,char[][] Result) {
        String title = "ABCDEFGHIJ";
        System.out.print("   ");
        for (int i = 0; i < 10; i++)
            System.out.print(i + 1 + " ");
        System.out.print("       ");
        for (int i = 0; i < 10; i++)
            System.out.print(i + 1 + " ");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(title.charAt(i) + "  ");
            for (int j = 0; j < 10; j++) {
                if (Grid[i][j] == 1)
                    System.out.print(Grid[i][j] + " ");
                else
                    System.out.print("  ");

            }
            System.out.print("    ");
            System.out.print(title.charAt(i) + "   ");
            for (int j = 0; j < 10; j++) {
                if (Result[i][j] == 'H'||Result[i][j] == 'M')
                    System.out.print(Result[i][j] + " ");
                else
                    System.out.print("  ");

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<Ship> myfleet = new ArrayList<Ship>();
        myfleet.add(new Ship("CARRIER", 5));
        myfleet.add(new Ship("BATTLESHIP", 4));
        myfleet.add(new Ship("DESTROYER", 3));
        myfleet.add(new Ship("PATROL BOAT", 2));
        ArrayList<Ship> rivalfleet = new ArrayList<Ship>();
        rivalfleet.add(new Ship("CARRIER", 5));
        rivalfleet.add(new Ship("BATTLESHIP", 4));
        rivalfleet.add(new Ship("DESTROYER", 3));
        rivalfleet.add(new Ship("PATROL BOAT", 2));

        PlaceShip(MyGrid, myfleet);
        PlaceShip(RivalGrid, rivalfleet);

        Scanner input = new Scanner(System.in);
        int times = 1;
        int Mypegs = 0;
        int Rivalpegs = 0;
        while (true) {
            if (times % 2 == 1) {
                PrintGrid(RivalGrid,RivalResult);
                System.out.println("You and computer are the players.It's your turn.");
                System.out.print("You call: ");
                String cmd = input.next();
                int x = cmd.charAt(0) - 'A';
                int y = cmd.charAt(2) - '1';
                System.out.print("computer answers: ");
                if (RivalGrid[x][y] == 1) {
                    System.out.println("Hit.");
                    RivalGrid[x][y] = 2;
                    RivalResult[x][y]='H';
                    Mypegs++;
                } else if (RivalGrid[x][y] == 0) {
                    System.out.println("Miss.");
                    RivalResult[x][y]='M';
                } else {

                }
            } else {
                PrintGrid(MyGrid,MyResult);
                System.out.println("You and computer are the players.It's computer's turn.");
                System.out.print("computer call: ");
                int x,y;
                while(true)
                {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                    if (MyResult[x][y] != 'H'||MyResult[x][y] != 'M')
                        break;
                }
                char ch=(char) (x+'A');
                System.out.println(ch+"-"+y);
                System.out.print("You answers: ");
                if (MyGrid[x][y] == 1) {
                    System.out.println("Hit.");
                    MyGrid[x][y] = 2;
                    MyResult[x][y]='H';
                    Rivalpegs++;
                } else if (MyGrid[x][y] == 0) {
                    System.out.println("Miss.");
                    MyResult[x][y]='M';
                } else {

                }
            }
            if (Rivalpegs == 14) {
                System.out.println("computer Win.");
                break;
            }
            if (Mypegs == 14) {
                System.out.println("You Win.");
                break;
            }
            times++;
        }

    }

}
