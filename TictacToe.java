import java.util.Random;
import java.util.Scanner;
public class player 
{
    public String Marker;
    public void setMarker(String marker)
    {
        Marker = marker;
    }
    public String getMarker()
    {
        return Marker;
    }
}
public class set 
{
    private int N = 3;
    public String[][] board = new String [N][N];
    public boolean hasWon (String [] [] board)
    {
         for(int i = 0; i<3; i++)
         {
            if(board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
            {
                return true;
            }
        }        
        for(int i = 0; i<3; i++)
        {
            if(board [0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
            {
                return true;
            }
        }
     
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) || board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]))
            return true;
        return false;
    }
    int x = 1;
    public void createBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                board[i][j] = "" + (x);
                x++;
            }
        }
    }

    public void printBoard(){

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                System.out.print("[" + board[i][j] + "]" + " ");

            }
            System.out.println();
        }
    }

    public String[][] getBoard()
    {
        return board;
    }
}
class Computer extends player 
{

public Computer(){}
int boardsize = 3;
public void takeTurn(String[][] board, Human human) 
{
    int vertical = 0;
    int horizontal = 0;
    int diagonal = 0;
    boolean mademove = false;
    for(int i = 0; i<3; i++)
{
        if(board[0][i].equals(board[1][i]) && board[0][i].equals(Marker))
        {

            if(board[2][i] != human.getMarker() && board[2][i] != Marker)
            {
                board[2][i] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[2][i].equals(board[1][i]) && board[2][i].equals(Marker))
        {
            if(board[0][i] != human.getMarker() && board[0][i] != Marker)
            {
                board[0][i] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[i][0].equals(board[i][1]) && board[i][0].equals(Marker))
        {
            if(board[i][2] != human.getMarker() && board[i][2] != Marker)
            {
                board[i][2] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[i][2].equals(board[i][1]) && board[i][2].equals(Marker))
        {
            if(board[i][0] != human.getMarker() && board[i][0] != Marker)
            {
                board[i][0] = Marker;
                mademove = true;
                return;
            }
        }
    }
    if(board[0][0].equals(board[1][1]) && board[0][0].equals(Marker))
    {
        if(board[2][2] != human.getMarker() && board[2][2] != Marker)
        {
            board[2][2] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[2][2].equals(board[1][1]) && board[2][2].equals(Marker))
    {
        if(board[0][0] != human.getMarker() && board[0][0] != Marker)
        {
            board[0][0] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[0][0].equals(board[1][1]) && board[0][0].equals(Marker))
    {
        if(board[2][2] != human.getMarker() && board[2][2] != Marker)
        {
            board[2][2] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[0][2].equals(board[1][1]) && board[0][2].equals(Marker))
    {
        if(board[2][0] != human.getMarker() && board[2][0] != Marker)
        {
            board[2][0] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[2][0].equals(board[1][1]) && board[2][0].equals(Marker))
    {
        if(board[0][2] != human.getMarker() && board[0][2] != Marker)
        {
            board[0][2] = Marker;
            mademove = true;
            return;
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[0][i].equals(board[1][i]) && board[0][i].equals(human.getMarker()))
        {
            if(board[2][i] != Marker && board[2][i] != human.getMarker())
            {
                board[2][i] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[2][i].equals(board[1][i]) && board[0][i].equals(human.getMarker()))
        {
            if(board[0][i] != Marker && board[0][i] != human.getMarker())
            {
                board[0][i] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[i][0].equals(board[i][1]) && board[i][0].equals(human.getMarker()))
        {
            if(board[i][2] != Marker && board[i][2] != human.getMarker()){
                board[i][2] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[i][2].equals(board[i][1]) && board[i][2].equals(human.getMarker()))
        {
            if(board[i][0] != Marker && board[i][0] != human.getMarker())
            {
                board[i][0] = Marker;
                mademove = true;
                return;
            }
        }
    }
    for(int i = 0; i<3; i++)
    {
        if(board[2][i].equals(board[1][i]) && board[2][i].equals(human.getMarker()))
        {
            if(board[0][i] != Marker && board[0][i] != human.getMarker())
            {
                board[0][i] = Marker;
                mademove = true;
                return;
            }
        }
    }
    if(board[0][0].equals(board[1][1]) && board[0][0].equals(human.getMarker()))
    {
        if(board[2][2] != Marker && board[2][2] != human.getMarker()){
            board[2][2] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[2][2].equals(board[1][1]) && board[2][2].equals(human.getMarker()))
    {
        if(board[0][0] != Marker && board[0][0] != human.getMarker()){
            board[0][0] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[0][0].equals(board[1][1]) && board[0][0].equals(human.getMarker())){
        if(board[2][2] != Marker && board[2][2] != human.getMarker()){
            board[2][2] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[0][2].equals(board[1][1]) && board[0][2].equals(human.getMarker())){

        if(board[2][0] != Marker && board[2][0] != human.getMarker()){
            board[2][0] = Marker;
            mademove = true;
            return;
        }
    }
    if(board[2][0].equals(board[1][1]) && board[2][0].equals(human.getMarker())){

        if(board[0][2] != Marker && board[0][2] != human.getMarker()){
            board[0][2] = Marker;
            mademove = true;
            return;
        }
    }
    int rand1 = 0;
    int rand2 = 0;
    while(!mademove){
       rand1 = (int) (Math.random() * 3);
        rand2 = (int) (Math.random() * 3);
        if(board[rand1][rand2] != "x" && board[rand1][rand2] != "o"){
            board[rand1][rand2] = Marker;
            mademove = true;        
        }
    }
}   
}
class Human extends player {
public Human(){}
public void takeTurn(String[][] board) 
{
    Scanner s = new Scanner(System.in);
    boolean turn = true;
    while(turn){
        System.out.println("please enter row");
        int row = s.nextInt();
        System.out.println("please enter col");
        int col = s.nextInt();
        System.out.print("you entered "+row+" "+col);
        System.out.println();
        if(board[row - 1][col - 1] != "x" && board[row - 1][col - 1] != "o"){
            board[row - 1][col - 1] = Marker;
            turn = false;
        } 
        else {
            System.out.println("Already Marker here! please guess again!");
        }
    } 
} 
} 
public class Play {
    public static void main(String[] args) {
        System.out.println("Welcome to Tickle Tackle Toe!!! :D");
        System.out.println();
        String marker1 = "x";
        String marker2 = "o";
        boolean playAgain = true;
        Scanner s = new Scanner(System.in);
        Human human = new Human();
        Computer computer = new Computer();
        while(playAgain){
            set Setup = new set();
            Setup.createBoard();
            Setup.printBoard();
            System.out.println("please choose your marker");
            System.out.println("type 1 for 'x' or 2 for 'o'");
            if(s.nextInt() == 1){
                human.setMarker("x");
                computer.setMarker("o");
            } 
            else
            {
                human.setMarker("o");
                computer.setMarker("x");
            }
            int first = (int) (Math.random() * 2);
            boolean won = false;
            int turns = 0;
            if(first == 0){
                System.out.println("You gots the winz!");
                System.out.println();
                while(!won){
                    human.takeTurn(Setup.getBoard());
                    turns++;
                    Setup.printBoard();
                    if(Setup.hasWon(Setup.getBoard())){
                        won = true;
                        System.out.println("Congrats you won!");
                    }
                    if(turns == 9){
                        won = true;
                        System.out.println("Its a bore draw!");
                        break;
                    }
                    if(!won){
                        computer.takeTurn(Setup.getBoard(), human);
                        turns++;
                        System.out.println();
                        Setup.printBoard();
                        System.out.println();
                        if(Setup.hasWon(Setup.getBoard())){
                            won = true;
                            System.out.println("You just got pwned by an A.I with an incomplete rule set. FACEPALM.");
                        }
                        if(turns == 9){
                            won = true;
                            System.out.println("Its a bore draw!");
                            break;
                        }
                    }
                }  
            }
            else {
                System.out.println("Computer goes first!");
                System.out.println();
                while(!won){
                    computer.takeTurn(Setup.getBoard(), human);
                    turns++;
                    Setup.printBoard();
                    if(Setup.hasWon(Setup.getBoard())){
                        won = true;
                        System.out.println("You just got pwned by an A.I with an incomplete rule set. FACEPALM.");
                    }
                    if(turns == 9){
                        won = true;
                        System.out.println("Its a draw!");
                        break;
                    }
                    if(!won){
                        human.takeTurn(Setup.getBoard());
                        turns++;
                        System.out.println();
                        Setup.printBoard();
                        System.out.println();
                        if(Setup.hasWon(Setup.getBoard())){
                            won = true;
                            System.out.println("You gots the winz!");
                        }
                        if(turns == 9){
                            won = true;
                            System.out.println("Its a draw!");
                            break;
                        }
                    }
                }  
            }
            System.out.println("Would you like to play again? Type 1 for yes or 2 to quit");
            System.out.println();
            if(s.nextInt() == 2){
                playAgain = false;
            }
        }
    }
}
