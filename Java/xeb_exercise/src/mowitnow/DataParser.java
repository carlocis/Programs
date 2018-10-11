package mowitnow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

	
	// Parsing the file into single lines to be processed later
	public static List<String> parseFile(File f) throws IOException {
        List<String> lines = new ArrayList<String>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.replaceAll("\\s+",""));   // I remove all the spaces from each line
            }
        }
        return lines;
    }
	
	// I convert the string to a list of char
	public static char[] parseString(String line) {
		return line.toCharArray();
	}
	
	
	// I take the first line of the input file and I save it as the top right corner
	public static Coordinates rightCornerCoordinates(List<String> lines) {
		char[] boundaryChar = DataParser.parseString(lines.get(0));
		int[] boundaryInt = new int[boundaryChar.length];
		
		for (int i=0; i<boundaryChar.length; i++) {
			boundaryInt[i] = Integer.parseInt(String.valueOf(boundaryChar[i]));
		}
		
		return new Coordinates(boundaryInt[0], boundaryInt[1]);
		
	}
	
	// This will store the list of initial coordinates of the mowers
	public static List<Coordinates> coordinatesList(List<char[]> position) {
		List<Coordinates> list = new ArrayList<Coordinates>();
		
		
		for(int i=0; i<position.size(); i++) {
			list.add(new Coordinates(Integer.parseInt(String.valueOf(position.get(i)[0])),
					+ Integer.parseInt(String.valueOf(position.get(i)[1])), position.get(i)[2]));
		}
		return list;
	}
	
	
	// I parse the other lines of the input file (starting from the second one, 
	// as I have already used/saved the first one), and I will group separately
	// the initial position and the commands of each mower
	public static List<List<char[]>> getPositionAndCommands(List<String> lines) {
		
		// At each index of commands corresponds the commands that the mower(s) will do
		List<char[]> position = new ArrayList<char[]>();
		List<char[]> commands = new ArrayList<char[]>();
		
		// I start the cycle from 1 because I want to skip the first line
		// which is my top right corner
		for(int i=1; i < lines.size(); i++) {
			// odd line => coordinates
			if (i%2 != 0) {
				position.add(DataParser.parseString(lines.get(i)));
			}

			// even line => commands
			if (i%2 == 0) {
				commands.add(DataParser.parseString(lines.get(i)));
			}

		}
		
		List<List<char[]>> result = new ArrayList<List<char[]>>();
		result.add(position);
		result.add(commands);
		
		return result;
	}

	
}
