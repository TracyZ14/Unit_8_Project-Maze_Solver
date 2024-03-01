import java.util.ArrayList;
public class MazeSolver
{
    private String[][] maze;
    private ArrayList<ArrayList<String>> possiblePaths;
    private ArrayList<String> correctPath;
    private int endingPointRow;
    private int endingPointColumn;

    public MazeSolver(String[][] maze)
    {
        this.maze = maze;
        this.possiblePaths = new ArrayList<ArrayList<String>>();
        this.correctPath = new ArrayList<String>();
        this.endingPointRow = maze.length - 1;
        this.endingPointColumn = maze[0].length - 1;
    }

    public ArrayList<int[]> findNextCoordinate(int row, int column)
    {
        ArrayList<int[]> nextCoordinates = new ArrayList<int[]>();
        int[] coordinate = new int[2];
        if(row != 0)
        {
            if(column != 0)
            {
                if(maze[row - 1][column - 1].equals("."))
                {
                    coordinate[0] = row - 1;
                    coordinate[1] = column - 1;
                    nextCoordinates.add(coordinate);
                }
            }
            if(maze[row - 1][column].equals("."))
            {
                coordinate[0] = row - 1;
                coordinate[1] = column;
                nextCoordinates.add(coordinate);
            }
            if(column != endingPointColumn)
            {
                if(maze[row - 1][column + 1].equals("."))
                {
                    coordinate[0] = row - 1;
                    coordinate[1] = column + 1;
                    nextCoordinates.add(coordinate);
                }
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
            if(column != 0)
            {
                if(maze[row = 1][column - 1].equals("."))
                {
                    coordinate[0] = row + 1;
                    coordinate[1] = column - 1;
                    nextCoordinates.add(coordinate);
                }
            }
            if(maze[row + 1][column].equals("."))
            {
                coordinate[0] = row + 1;
                coordinate[1] = column;
                nextCoordinates.add(coordinate);
            }
            if(column != endingPointColumn)
            {
                if(maze[row + 1][column + 1].equals("."))
                {
                    coordinate[0] = row + 1;
                    coordinate[1] = column + 1;
                    nextCoordinates.add(coordinate);
                }
            }
        }
        return nextCoordinates;
    }

    public boolean isPreviousCoordinate(int previousRow, int previousColumn, int row, int column)
    {
        if((row == previousRow) && (column == previousColumn))
        {
            return true;
        }
        return false;
    }

    public boolean isDeadEnd(int previousRow, int previousColumn, int row, int column)
    {
        ArrayList<int[]> nextCoordinates = findNextCoordinate(row, column);
        int[] previousCoordinates = {previousRow, previousColumn};
        if((nextCoordinates.size() == 1) && (nextCoordinates.get(1).equals(previousCoordinates)))
        {
            return true;
        }
        return false;
    }

    public void addCoordinate(ArrayList<String> path, int row, int column)
    {
        path.add("(" + row + ", " + column + ")");
    }

    public String findCorrectPath()
    {
        int[] endingCoordinate = {endingPointRow, endingPointColumn};
        int[] nextCoordinate = new int[2];
        ArrayList<String> path = new ArrayList<String>();
        possiblePaths.add(path);
        for(int i = 0; !nextCoordinate.equals(endingCoordinate); i++)
        {
            
        }
        return printCorrectPath();
    }

    public String printCorrectPath()
    {
        String path = "";
        for(String coordinate : correctPath)
        {
            path += coordinate;
            if(coordinate != ("(" + endingPointRow + ", " + endingPointColumn + ")"))
            {
                path += " ---> ";
            }
        }
        return path;
    }
}