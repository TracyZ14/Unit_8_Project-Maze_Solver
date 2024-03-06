import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean checkUp(int[] previousCoordinate, int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        if(row != 0)
        {
            if(maze[row - 1][column].equals("."))
            {
                int[] upCoordinate = {row - 1, column};
                if(!isSameCoordinate(previousCoordinate, upCoordinate))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLeft(int[] previousCoordinate, int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        if(column != 0)
        {
            if(maze[row][column - 1].equals("."))
            {
                int[] leftCoordinate = {row, column - 1};
                if(!isSameCoordinate(previousCoordinate, leftCoordinate))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRight(int[] previousCoordinate, int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        if(column != endingPointColumn)
        {
            if(maze[row][column + 1].equals("."))
            {
                int[] rightCoordinate = {row, column + 1};
                if(!isSameCoordinate(previousCoordinate, rightCoordinate))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDown(int[] previousCoordinate, int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        if(row != endingPointRow)
        {
            if(maze[row + 1][column].equals("."))
            {
                int[] downCoordinate = {row + 1, column};
                if(!isSameCoordinate(previousCoordinate, downCoordinate))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<int[]> findNextCoordinate(int[] previousCoordinate, int[] currentCoordinate)
    {
        int row = currentCoordinate[0];
        int column = currentCoordinate[1];
        ArrayList<int[]> nextCoordinates = new ArrayList<int[]>();
        if(checkUp(previousCoordinate, currentCoordinate))
        {
            int[] coordinate = {row - 1, column};
            nextCoordinates.add(coordinate);
        }
        if(checkLeft(previousCoordinate, currentCoordinate))
        {
            int[] coordinate = {row, column - 1};
            nextCoordinates.add(coordinate);
        }
        if(checkRight(previousCoordinate, currentCoordinate))
        {
            int[] coordinate = {row, column + 1};
            nextCoordinates.add(coordinate);
        }
        if(checkDown(previousCoordinate, currentCoordinate))
        {
            int[] coordinate = {row + 1, column};
            nextCoordinates.add(coordinate);
        }
        return nextCoordinates;
    }

    public boolean isSameCoordinate(int[] coordinate1, int[] coordinate2)
    {
        if(Arrays.equals(coordinate1, coordinate2))
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
        while(!Arrays.equals(coordinate, endingCoordinate))
        {
            ArrayList<int[]> thisPath = possiblePaths.get(0);
            int[] currentCoordinate = thisPath.get(thisPath.size() - 1);
            int[] previousCoordinate = new int[2];
            if(thisPath.size() < 2)
            {
                previousCoordinate = currentCoordinate;
            }
            else
            {
                previousCoordinate = thisPath.get(thisPath.size() - 2);
            }
            ArrayList<int[]> nextCoordinates = findNextCoordinate(previousCoordinate, currentCoordinate);
            if(nextCoordinates.size() == 1)
            {
                thisPath.add(nextCoordinates.get(0));
            }
            else
            {
                for(int[] coordinates : nextCoordinates)
                {
                    ArrayList<int[]> pathTaken = new ArrayList<int[]>();
                    for(int[] coordinatesPassed : thisPath)
                    {
                        pathTaken.add(coordinatesPassed);
                    }
                    ArrayList<int[]> newPath = pathTaken;
                    newPath.add(coordinates);
                    possiblePaths.add(newPath);
                }
                possiblePaths.remove(0);
                thisPath = possiblePaths.get(0);
            }
            coordinate = thisPath.get(thisPath.size() - 1);
        }
        correctPath = possiblePaths.get(0);
        return printCorrectPath();
    }

    public String printCorrectPath()
    {
        String path = "";
        int[] endingCoordinate = {endingPointRow, endingPointColumn};
        for(int[] coordinate : correctPath)
        {
            path += ("(" + coordinate[0] + ", " + coordinate[1] + ")");
            if(!isSameCoordinate(coordinate, endingCoordinate))
            {
                path += " ---> ";
            }
        }
        return path;
    }
}