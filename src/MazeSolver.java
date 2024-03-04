import java.util.ArrayList;
public class MazeSolver
{
    private String[][] maze;
    private ArrayList<ArrayList<int[]>> possiblePaths;
    private ArrayList<int[]> correctPath;
    private int endingPointRow;
    private int endingPointColumn;

    public MazeSolver(String[][] maze)
    {
        this.maze = maze;
        this.possiblePaths = new ArrayList<ArrayList<int[]>>();
        this.correctPath = new ArrayList<int[]>();
        this.endingPointRow = maze.length - 1;
        this.endingPointColumn = maze[0].length - 1;
    }

    public ArrayList<int[]> findNextCoordinate(int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        System.out.println(row + " + " + column);
        ArrayList<int[]> nextCoordinates = new ArrayList<int[]>();
        int[] coordinate = new int[2];
        if(row != 0)
        {
            if(maze[row - 1][column].equals("."))
            {
                coordinate[0] = row - 1;
                coordinate[1] = column;
                nextCoordinates.add(coordinate);
            }
        }
        if(column != 0)
        {
            if(maze[row][column - 1].equals("."))
            {
                coordinate[0] = row;
                coordinate[1] = column - 1;
                nextCoordinates.add(coordinate);
            }
        }
        if(column != endingPointColumn)
        {
            if(maze[row][column + 1].equals("."))
            {
                coordinate[0] = row;
                coordinate[1] = column + 1;
                nextCoordinates.add(coordinate);
            }
        }
        if(row != endingPointRow)
        {
            if(maze[row + 1][column].equals("."))
            {
                coordinate[0] = row + 1;
                coordinate[1] = column;
                nextCoordinates.add(coordinate);
            }
        }
        return nextCoordinates;
    }

    public boolean isPreviousCoordinate(int[] previousCoordinate, int[] coordinate)
    {
        if(coordinate.equals(previousCoordinate))
        {
            return true;
        }
        return false;
    }

    public String findCorrectPath()
    {
        int[] endingCoordinate = {endingPointRow, endingPointColumn};
        int[] coordinate = {0, 0};
        ArrayList<int[]> path = new ArrayList<int[]>();
        possiblePaths.add(path);
        path.add(coordinate);
        while(!coordinate.equals(endingCoordinate))
        {
            ArrayList<int[]> thisPath = possiblePaths.get(0);
            int[] previousCoordinate = thisPath.get(thisPath.size() - 1);
            ArrayList<int[]> nextCoordinates = findNextCoordinate(previousCoordinate);
            for(int[] coordinates : nextCoordinates)
            {
                System.out.println(coordinates[0] + ", " + coordinates[1]);
            }
            System.out.println();
            for(int i = nextCoordinates.size() - 1; i >= 0; i--)
            {
                if(isPreviousCoordinate(previousCoordinate, nextCoordinates.get(i)))
                {
                    nextCoordinates.remove(i);
                }
            }
            if(nextCoordinates.size() == 1)
            {
                thisPath.add(nextCoordinates.get(0));
            }
            else
            {
                for(int[] coordinates : nextCoordinates)
                {
                    ArrayList<int[]> newPath = thisPath;
                    thisPath.add(coordinates);
                    possiblePaths.add(newPath);
                }
                possiblePaths.remove(0);
                thisPath = possiblePaths.get(0);
            }
            coordinate = thisPath.get(thisPath.size() - 1);
        }
        return printCorrectPath();
    }

    public String printCorrectPath()
    {
        String path = "";
        int[] endingCoordinate = {endingPointRow, endingPointColumn};
        for(int[] coordinate : correctPath)
        {
            path += ("(" + coordinate[0] + ", " + coordinate[1] + ")");
            if(!coordinate.equals(endingCoordinate))
            {
                path += " ---> ";
            }
        }
        return path;
    }
}