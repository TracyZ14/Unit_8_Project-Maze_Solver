import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        String[][] maze1 = getMaze("data/maze1");
        MazeSolver solver1 = new MazeSolver(maze1);
        System.out.println("Maze 1: " + solver1.findCorrectPath());

        String[][] maze2 = getMaze("data/maze2");
        MazeSolver solver2 = new MazeSolver(maze2);
        System.out.println("Maze 1: " + solver2.findCorrectPath());

        String[][] maze3 = getMaze("data/maze3");
        MazeSolver solver3 = new MazeSolver(maze3);
        System.out.println("Maze 1: " + solver3.findCorrectPath());

        String[][] maze4 = getMaze("data/maze4");
        MazeSolver solver4 = new MazeSolver(maze4);
        System.out.println("Maze 1: " + solver4.findCorrectPath());

        String[][] maze5 = getMaze("data/maze5");
        MazeSolver solver5 = new MazeSolver(maze5);
        System.out.println("Maze 1: " + solver5.findCorrectPath());
    }

    public static String[][] getMaze(String fileName)
    {
        File f = new File(fileName);
        Scanner s = null;
        try
        {
            s = new Scanner(f);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
        {
            fileData.add(s.nextLine());
        }
        int rows = fileData.size();
        int cols = fileData.get(0).length();
        String[][] maze = new String[rows][cols];
        for (int i = 0; i < fileData.size(); i++)
        {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++)
            {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }
}