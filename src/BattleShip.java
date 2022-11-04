import java.util.ArrayList;
import java.util.Scanner;

//Q1: why isn't the grid 10x10?
//why add 1 for randnum.
//如果将1在grid放的途中发现当前flag是1, 那么之前放的1 (on the grid) 怎么办?
//为什么最后totalPeg是17的时候才会赢，17是怎么算出来的

public class BattleShip {
    static int[][] MyGrid = new int[7][9];
    static int[][] RivalGrid = new int[7][9];

    //Simulate players placing the ships, and create ships in random places
    public static void PlaceShip(int[][] Grid, ArrayList<Ship> Ship)
    {
        for (int i = 0; i < Ship.size(); i++) {
            while (true) {
                int flag=0; //to tell if the ship has been places around some area, 1 is true
                int randnum = (int) (Math.random() * 100 + 1);
                int x = (int) (Math.random() * 7);//行数
                int y = (int) (Math.random() * 9);//列数
                if(x+Ship.get(i).holes>6||y+Ship.get(i).holes>8) {
                    continue;
                }
                if (randnum % 2 == 1)// 随机数是奇数则方向为下
                {
                    for (int j = x; j < x + Ship.get(i).holes; j++) {
                        if(Grid[j][y] == 1)
                        {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {
                        for (int j = x; j < x + Ship.get(i).holes; j++)
                            Grid[j][y] = 1;
                        break;
                    }

                } else// 随机数是偶数则方向为右
                {
                    for (int j = y; j < y + Ship.get(i).holes; j++) {
                        if(Grid[x][j] == 1)
                        {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {
                        for (int j = x; j < x + Ship.get(i).holes; j++)
                            Grid[x][j] = 1;
                        break;
                    }
                }
            }

        }
    }
    public static void PrintGrid(int[][] Grid)
    {
        String title="ABCDEFG";
        System.out.print("   ");
        for(int i=0;i<9;i++)
            System.out.print(i+1+" ");
        System.out.println();
        for(int i=0;i<7;i++)
        {
            System.out.print(title.charAt(i)+"  ");
            for(int j=0;j<9;j++)
            {
                System.out.print(Grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        ArrayList<Ship> myfleet = new ArrayList<Ship>();
        myfleet.add(new Ship("CARRIER", 5));
        myfleet.add(new Ship("BATTLESHIP", 4));
        myfleet.add(new Ship("DESTROYER", 3));
        myfleet.add(new Ship("SUBMARINE", 3));
        myfleet.add(new Ship("PATROL BOAT", 2));
        ArrayList<Ship> rivalfleet = new ArrayList<Ship>();
        rivalfleet.add(new Ship("CARRIER", 5));
        rivalfleet.add(new Ship("BATTLESHIP", 4));
        rivalfleet.add(new Ship("DESTROYER", 3));
        rivalfleet.add(new Ship("SUBMARINE", 3));
        rivalfleet.add(new Ship("PATROL BOAT", 2));

        PlaceShip(MyGrid, myfleet);
        PlaceShip(RivalGrid, rivalfleet);


        Scanner input = new Scanner(System.in);
        int times = 1; //decides whose turn this is.
        int Mypegs = 0;
        int Rivalpegs = 0;
        while (true) {
            if (times % 2 == 1) {
                PrintGrid(RivalGrid); //should be changed to MyGrid right? Since this is my turn.
                System.out.println("You and Steve are the players.It's your turn.");
                System.out.print("You call: ");
                String cmd = input.next();
                int x = cmd.charAt(0) - 'A';
                int y = cmd.charAt(2) - '1';
                System.out.print("Steve answers: ");
                if (RivalGrid[x][y] == 1) {
                    System.out.println("Hit.");
                    RivalGrid[x][y] = 2; //mark as hit.
                    Mypegs++;
                } else if (RivalGrid[x][y] == 0) {
                    System.out.println("Miss.");
                } else {

                }
            } else {
                PrintGrid(MyGrid);
                System.out.println("You and Steve are the players.It's Steve's turn.");
                System.out.print("Steve call: ");
                String cmd = input.next();
                int x = cmd.charAt(0) - 'A';
                int y = cmd.charAt(2) - '1';
                System.out.print("You answers: ");
                if (MyGrid[x][y] == 1) {
                    System.out.println("Hit.");
                    MyGrid[x][y] = 2;
                    Rivalpegs++;
                } else if (MyGrid[x][y] == 0) {
                    System.out.println("Miss.");
                } else {

                }
            }
            if (Rivalpegs == 17) { //total number of squares hit.
                System.out.println("Steve Win.");
                break;
            }
            if (Mypegs == 17) {
                System.out.println("You Win.");
                break;
            }
            times++;
        }

    }

}
