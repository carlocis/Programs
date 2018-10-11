package mowitnow;

import java.util.List;

public class Mower {

	// this is the top right corner which is the same for all mowers
	private static Coordinates topRightCorner;

	// this is the initialPoint of a specific mower
	private Coordinates initialPoint;

	private char[] commands;

	// Constructor of the mower
	public Mower(Coordinates initialPoint, char[] commands) {
		this.initialPoint = initialPoint;
		this.commands = commands;
	}


	// It is static because it is a method valid for all mowers
	public static Coordinates getTopRightCorner() {
		return topRightCorner;
	}

	public Coordinates getInitialPoint() {
		return initialPoint;
	}
	public static void setTopRightCorner(Coordinates topRightCorner) {
		Mower.topRightCorner = topRightCorner;
	}
	public void setInitialPoint(Coordinates initialPoint) {
		this.initialPoint = initialPoint;
	}
	public char[] getCommands() {
		return commands;
	}
	public void setCommands(char[] commands) {
		this.commands = commands;
	}



	public void turnLeft() {
		switch (initialPoint.getOrientation()) {
		case 'N':
			this.initialPoint.setOrientation('W');
			break;
		case 'E':
			this.initialPoint.setOrientation('N');
			break;
		case 'S':
			this.initialPoint.setOrientation('E');
			break;
		case 'W':
			this.initialPoint.setOrientation('S');
			break;
		default:
			System.out.println("Orientation not recognized. Admissible values: [N, E, W, S]");
			break;
		}
	}


	public void turnRight() {
		switch (initialPoint.getOrientation()) {
		case 'N':
			this.initialPoint.setOrientation('E');
			break;
		case 'E':
			this.initialPoint.setOrientation('S');
			break;
		case 'S':
			this.initialPoint.setOrientation('W');
			break;
		case 'W':
			this.initialPoint.setOrientation('N');
			break;
		default:
			System.out.println("Orientation not recognized. Admissible values: [N, E, W, S]");
			break;
		}
	}


	public void moveForward() {

		switch (initialPoint.getOrientation()) {
		case 'N':
			if(initialPoint.getY_position() < topRightCorner.getY_position()) {
				initialPoint.setY_position(initialPoint.getY_position() + 1);
			}
			break;
		case 'E':
			if(initialPoint.getX_position() < topRightCorner.getX_position()) {
				initialPoint.setX_position(initialPoint.getX_position() + 1);
			}
			break;
		case 'S':
			if(initialPoint.getY_position() > 0) {
				initialPoint.setY_position(initialPoint.getY_position() - 1);
			}
			break;
		case 'W':
			if(initialPoint.getX_position() > 0) {
				initialPoint.setX_position(initialPoint.getX_position() - 1);
			}
			break;
		default:
			System.out.println("Orientation not recognized. Admissible values: [N, E, W, S]");
			break;
		}
	}

	public void printPosition() {
		System.out.println("The current position is ("+ initialPoint.getX_position() + ", "+initialPoint.getY_position()+", "+initialPoint.getOrientation()+")");
	}

	public static void printTopRightCorner() {
		System.out.println("Top right corner: ("+Mower.getTopRightCorner().getX_position()+", "
				+ ""+Mower.getTopRightCorner().getY_position()+")");
	}


	public void readCommands() {
		
		// If the initial orientation of the mower is different from NEWS,
		// the mower won't move as no valid orientation was given at the beginning.
		
		if(this.initialPoint.getOrientation() == 'N' ||
				this.initialPoint.getOrientation() == 'E' ||
				this.initialPoint.getOrientation() == 'W' ||
				this.initialPoint.getOrientation() == 'S') {

			for(char letter : commands) {
				switch (letter) {
				case 'D':
					turnRight();
					printPosition();
					break;
				case 'G':
					turnLeft();
					printPosition();
					break;
				case 'A':
					moveForward();
					printPosition();
					break;
				default:
					// If the sequence of commands contains a letter which is not recognized
					// the mower will process the next command/letter.
					System.out.println("Command not recognized. Admissible values: [D, G, A].");
					System.out.println("Processing next command.");
					break;
				}
			}
		}

		else {
			System.out.println("Orientation not admissible. Stopping program.");
			return;
		}

	}


	public static List<Mower> initializeMowers(List<Mower> listMowers, List<Coordinates> listCoordinates, List<char[]> commands) {

		for(int i=0; i<listCoordinates.size(); i++) {
			listMowers.add(new Mower(listCoordinates.get(i),commands.get(i)));
		}

		return listMowers;
	}


	// I print the initial configuration to check that the import of the file
	// was done correctly
	public static void printSetup(List<Mower> listMowers, List<Coordinates> listCoordinates, List<char[]> commands2) {
		
		System.out.println("");
		System.out.println("-----------");
		System.out.println("We have "+listCoordinates.size()+" mower(s):");
		System.out.println("-----------");
		for (int i=0; i< listCoordinates.size(); i++) {
			System.out.println("Mower "+i+": \nInitial position: "+listMowers.get(i).getInitialPoint().getX_position()+", "+listMowers.get(i).getInitialPoint().getY_position()+", "
					+ ""+listMowers.get(i).getInitialPoint().getOrientation());
			System.out.print("Commands: ");

			for(int j=0; j<listMowers.get(i).getCommands().length; j++) {
				System.out.print(listMowers.get(i).getCommands()[j]);
			}
			System.out.print("\n-----------\n");

		}

	}


	// This method keeps track of the points where the mower will go to
	public static void followMowers(List<Mower> listMowers, List<Coordinates> listCoordinates) {
		for(int i=0; i<listCoordinates.size(); i++) {
			System.out.println("Mower "+i);
			listMowers.get(i).readCommands();
		}

	}

}
