package com.sainal.redmartchallenge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SkiInSin {
	
	private int sizeX, sizeY = -1;
	private int [][] grid;

	public static void main(String[] args) {

        SkiInSin obj = new SkiInSin();
        obj.getInput(args[0]);
        System.out.println(obj.searchPath().display());
	}
	 
	public void getInput(String fileName) {
		try {
            // Open the file filename is the first argument
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            if ((strLine = br.readLine()) != null) {
                String[] size = strLine.split(" ");
                sizeX = Integer.parseInt(size[0]);
                sizeY = Integer.parseInt(size[1]);
                grid = new int[sizeX][sizeY];

                System.out.println ("Rows: " + sizeX);
                System.out.println ("Columns: " + sizeY);
            }

            //Read File Line By Line
            int x = 0;
            while ((strLine = br.readLine()) != null)   {
//                 Print the content on the console
//                 System.out.println (strLine);

                String[] line = strLine.split(" ");
                int y = 0;
                for (String num : line) {
                    grid[x][y] = Integer.parseInt(num);
                    y++;
                }
                x++;
            }
            //Close the input stream
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	public PathProperty searchPath() {
		
		// initialize to null, later can be assigned with a valid PathProperty
		PathProperty res = null;
		for ( int i = 0; i<sizeX;i++) {
			for (int j = 0;j<sizeY;j++) {
				PathProperty curProp = recursiveDFS(i, j, new PathProperty(grid[i][j]));
				if(res == null || isPropGreater(curProp, res)) {
					res = curProp;
				}
			}
		}
		return res;
	}
	
	private PathProperty recursiveDFS(int x, int y, PathProperty prop) {
		//get all valid neighbors

		// initialize to null, later can be assigned with a valid PathProperty
		PathProperty res = prop;
		List<Position> neighbours = getNeighbours(x, y);
		
		for (Position pos : neighbours) {
			
			//determine whether neighbor elevation is lesser
			if(grid[pos.getX()][pos.getY()] < grid[x][y]) {
				PathProperty clonedProp = cloneProp(prop);
				clonedProp.incrementLength();
				clonedProp.setLowestElevation(grid[pos.getX()][pos.getY()]);
				PathProperty curProp = recursiveDFS(pos.getX(), pos.getY(), clonedProp);
				if(isPropGreater(curProp, res)) {
					res = curProp;
				}
			}
		}
		return res;
	}
	
	private List<Position> getNeighbours(int x, int y){
		List<Position> neighbours = new ArrayList<Position>();
		if(x > 0) {
			neighbours.add(new Position(x-1, y));
		}
		if (y > 0) {
			neighbours.add(new Position(x, y-1));
		}
		if (x < sizeX -1) {
			neighbours.add(new Position(x+1, y));
		}
		if (y < sizeY -1) {
			neighbours.add(new Position(x, y+1));
		}
		return neighbours;
	}
	
	private boolean isPropGreater(PathProperty first, PathProperty second) {
		if(first == null) {
			return false;
		}
		if(second == null) {
			return true;
		}
		return first.getLength() > second.getLength() || (first.getLength() == second.getLength() && first.getDrop() > second.getDrop());
	}
	
	private PathProperty cloneProp(PathProperty prop) {
		return new PathProperty(prop.getLength(), prop.getStartElevation(),prop.getLowestElevation());
	}

}
