import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class KenKenSolver {

	/**
	 * Entry point of the application.
	 * Asks user for a name of puzzle and show the solution.
	 * @param args	Arguments are ignored
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Scanner inputScanner = new Scanner(System.in);
		
		System.out.println("Enter a name of puzzle file : ");
		String filename = inputScanner.nextLine();
		JFrame frame = new JFrame();
		KenKenComponent kc = new KenKenComponent(filename, frame);
		try {
			File file = new File(filename);
			if (file.exists() || file.isFile()) {
				// Read puzzle description for a file
				KenKenPuzzle puzzle = readFile(file);
				
				// Try to solve it
				boolean solvable = solve(puzzle);
				
				kc.setNumber(puzzle.grid);
				// If solvable then show solution
				if (solvable) {
					// If solved successfully then show solution
					for (int row = 0; row < puzzle.size; row++) {
						for (int col = 0; col < puzzle.size; col++) {
							System.out.print(puzzle.grid[row][col] + " ");
						}
						System.out.println();
					}
				}
				else {
					System.out.println("This Ken Ken puzzle hasn't a solution.");
				}
			}
			else {
				System.out.println("Such file doesn't exist.");
			}
		}
		catch (Exception e) {
			System.out.println("Bad input.");
		}
		
		
		inputScanner.close();
	}
	
	/**
	 * Solves the Ken Ken puzzle.
	 * Puzzle is required to be unfilled (all cells must not contain numbers).
	 * After solving (or trying to solve) the puzzle object is altered: in case
	 * of success it will contain the solution, otherwise it will contain
	 * garbage.
	 * @param puzzle	Puzzle to be solved
	 * @return		True if and only if the solution exists.
	 */
	static boolean solve(KenKenPuzzle puzzle) {
		return solve(puzzle, 0, 0);
	}
	
	/**
	 * Solves the Ken Ken puzzle using recursion and backtracing.
	 * Already filled cells are not changed.
	 * @param puzzle		Puzzle to be solved
	 * @param cageNumber	Order number of cage
	 * @param cellNumber	Order number of cell within a cage to be filled
	 * @return		True if and only if the solution exists within given filling
	 */
	static boolean solve(KenKenPuzzle puzzle, int cageNumber, int cellNumber) {
		if (cageNumber == puzzle.cages.size()) {
			// Recursion stop condition - all cages and cells are processed
			return true;
		}
		
		// Try to put different numbers in specified cell of the given cage
		Cage cage = puzzle.cages.get(cageNumber);
		int row = cage.row[cellNumber];
		int col = cage.col[cellNumber];
		int cageNext = cageNumber;
		int cellNext = cellNumber + 1;
		if (cellNext == cage.size) {
			cageNext++;
			cellNext = 0;
		}
		for (int number = 1; number <= puzzle.size; number++) {
			if (cage.operator == Operation.PRODUCT) {
				if (cage.target % number == 0) {
					// Target is divisible by this number, therefore we can try
					puzzle.grid[row][col] = number;
					// Check if this number doesn't repeat in row and column
					if (checkRow(puzzle, row, col) && checkColumn(puzzle, row, col)) {
						// If this is the last cell in cage then check the product
						if (cellNext == 0) {
							int product = 1;
							for (int i = 0; i <= cellNumber; i++) {
								product *= puzzle.getCell(cageNumber, i);
							}
							if (product == cage.target) {
								// Go deeper recursively
								if (solve(puzzle, cageNext, cellNext)) {
									// Success
									return true;
								}
							}
						}
						else {
							// Go deeper recursively
							if (solve(puzzle, cageNext, cellNext)) {
								// Success
								return true;
							}
						}
					}
					// Backtrack - restore (unset) number in cell
					puzzle.grid[row][col] = KenKenPuzzle.CELL_UNSET;
				}
			}
			else if (cage.operator == Operation.PLUS) {
				if (cage.target > number) {
					// Target is greater then tested number, therefore we can try
					puzzle.grid[row][col] = number;
					// Check if this number doesn't repeat in row and column
					if (checkRow(puzzle, row, col) && checkColumn(puzzle, row, col)) {
						// If this is the last cell in cage then check the sum
						if (cellNext == 0) {
							int sum = 0;
							for (int i = 0; i <= cellNumber; i++) {
								sum += puzzle.getCell(cageNumber, i);
							}
							if (sum == cage.target) {
								// Go deeper recursively
								if (solve(puzzle, cageNext, cellNext)) {
									// Success
									return true;
								}
							}
						}
						else {
							// Go deeper recursively
							if (solve(puzzle, cageNext, cellNext)) {
								// Success
								return true;
							}
						}
					}
					// Backtrack - restore (unset) number in cell
					puzzle.grid[row][col] = KenKenPuzzle.CELL_UNSET;
				}
			}
			else if (cage.operator == Operation.MINUS) {
				puzzle.grid[row][col] = number;
				// Check if this number doesn't repeat in row and column
				if (checkRow(puzzle, row, col) && checkColumn(puzzle, row, col)) {
					// If this is the last cell in cage then check the difference
					// Assume that cage with '-' operator always consists of 2 cells
					if (cellNext == 0) {
						int a = puzzle.getCell(cageNumber, 0);
						int b = puzzle.getCell(cageNumber, 1);
						if (Math.abs(a - b) == cage.target) {
							// Go deeper recursively
							if (solve(puzzle, cageNext, cellNext)) {
								// Success
								return true;
							}
						}
					}
					else {
						// Go deeper recursively
						if (solve(puzzle, cageNext, cellNext)) {
							// Success
							return true;
						}
					}
				}
				// Backtrack - restore (unset) number in cell
				puzzle.grid[row][col] = KenKenPuzzle.CELL_UNSET;
			}
			else if (cage.operator == Operation.DIVISION) {
				puzzle.grid[row][col] = number;
				// Check if this number doesn't repeat in row and column
				if (checkRow(puzzle, row, col) && checkColumn(puzzle, row, col)) {
					// If this is the last cell in cage then check the quotient
					// Assume that cage with '/' operator always consists of 2 cells
					if (cellNext == 0) {
						int a = puzzle.getCell(cageNumber, 0);
						int b = puzzle.getCell(cageNumber, 1);
						if (a / b == cage.target || b / a == cage.target) {
							// Go deeper recursively
							if (solve(puzzle, cageNext, cellNext)) {
								// Success
								return true;
							}
						}
					}
					else {
						// Go deeper recursively
						if (solve(puzzle, cageNext, cellNext)) {
							// Success
							return true;
						}
					}
				}
				// Backtrack - restore (unset) number in cell
				puzzle.grid[row][col] = KenKenPuzzle.CELL_UNSET;
			}
			else { // Single cell within a cage without any operator
				if (cage.target == number) {
					puzzle.grid[row][col] = number;
					// Check if this number doesn't repeat in row and column
					if (checkRow(puzzle, row, col) && checkColumn(puzzle, row, col)) {
						return solve(puzzle, cageNext, cellNext);
					}
				}
			}
		}
		
		// None of tested number fitted, go up in recursive calls
		return false;
	}
	
	/**
	 * Checks if a number doesn't repeat in a row.
	 * @param puzzle	Ken Ken puzzle
	 * @param row		Row to check
	 * @param col		Column in which number to be checked is located
	 * @return			True if and only if number doesn't repeat within a row
	 */
	static boolean checkRow(KenKenPuzzle puzzle, int row, int col) {
		// Check each column except of given one
		for (int i = 0; i < puzzle.size; i++) {
			if (i != col) {
				if (puzzle.grid[row][i] == puzzle.grid[row][col]) {
					// When repetition found - return fail
					return false;
				}
			}
		}
		// Repetition not found - return success
		return true;
	}
	
	/**
	 * Checks if a number doesn't repeat in a column.
	 * @param puzzle	Ken Ken puzzle
	 * @param row		Row in which number to be checked is located
	 * @param col		Column to check
	 * @return			True if and only if number doesn't repeat within a row
	 */
	static boolean checkColumn(KenKenPuzzle puzzle, int row, int col) {
		// Check each row except of given one
		for (int i = 0; i < puzzle.size; i++) {
			if (i != row) {
				if (puzzle.grid[i][col] == puzzle.grid[row][col]) {
					// When repetition found - return fail
					return false;
				}
			}
		}
		// Repetition not found - return success
		return true;
	}

	/**
	 * Reads description of Ken Ken puzzle fro a file.
	 * @param file	File containing description
	 * @return		Object describing the puzzle or null in cases of bad format
	 * 				of file or other exceptions
	 * @throws Exception	If any exception occurs while reading from file
	 * 						or file has bad format
	 */
	static KenKenPuzzle readFile(File file) throws Exception {
		BufferedReader fileReader = new BufferedReader(new FileReader(file));

		// Read size of puzzle
		int size = Integer.parseInt(fileReader.readLine().trim());

		// Read number of cages
		int countCages = Integer.parseInt(fileReader.readLine().trim());

		// Read in cages
		ArrayList<Cage> cages = new ArrayList<Cage>(countCages);
		for (int i = 0; i < countCages; i++) {
			// Read in target, operator and number of cells in one cage
			String[] tokens = fileReader.readLine().split(",");
			if (tokens.length != 3) { // There must be 3 tokens in format *,*,*
				throw new Exception(); // Bad format of input file
			}

			// First token is target of cage
			int target = Integer.parseInt(tokens[0].trim());

			// Second token is operator
			Operation operator = null;
			switch (tokens[1].charAt(0)) {
			case '+':
				operator = Operation.PLUS;
				break;
			case '-':
				operator = Operation.MINUS;
				break;
			case '*':
				operator = Operation.PRODUCT;
				break;
			case '/':
				operator = Operation.DIVISION;
				break;
			default:
				operator = Operation.NONE;
			}

			// Third token is number of cells in cage
			int countCells = Integer.parseInt(tokens[2].trim());

			Cage cage = new Cage(operator, target, countCells);

			// Read corresponding number of lines containing row and column
			for (int j = 0; j < countCells; j++) {
				tokens = fileReader.readLine().trim().split(",");
				if (tokens.length != 2) { // Line must contain two tokens
					throw new Exception(); // Bad format of file
				}
				// First token is row
				int row = Integer.parseInt(tokens[0]);
				// Second one is column
				int col = Integer.parseInt(tokens[1]);

				cage.setCell(j, row, col);
			}

			cages.add(cage);
		}

		fileReader.close(); // Close file

		return new KenKenPuzzle(size, cages);
	}

	/**
	 * Class representing the Ken Ken puzzle
	 */
	private static class KenKenPuzzle {
		public static final int CELL_UNSET = 0;

		final int size;
		final ArrayList<Cage> cages = new ArrayList<Cage>();
		final int[][] grid;

		/**
		 * Creates a new unfilled Ken Ken puzzle of specified size and with
		 * specified list of cages.
		 * 
		 * @param size
		 *            Size of puzzle
		 * @param cages
		 *            List of cages
		 */
		public KenKenPuzzle(int size, List<Cage> cages) {
			this.size = size;
			grid = new int[size][size];
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					grid[row][col] = CELL_UNSET;
				}
			}
			this.cages.addAll(cages);
		}
		
		/**
		 * Returns the value stored in a cell of cage
		 * @param cageNumber	Order number of a cage
		 * @param cellNumber	Order number of a cell within a cage
		 * @return	The value stored at specified cell
		 */
		public int getCell(int cageNumber, int cellNumber) {
			Cage cage = cages.get(cageNumber);
			return grid[cage.row[cellNumber]][cage.col[cellNumber]];
		}
	}

	/**
	 * Class representing a single cage of the Ken Ken puzzle.
	 */
	private static class Cage {
		final Operation operator;
		final int target;
		final int size;
		final int[] row;
		final int[] col;

		/**
		 * Creates a new cage with specified operator and of specified size.
		 * 
		 * @param operator
		 *            Operator of cage
		 * @param target
		 *            Target number of cage
		 * @param size
		 *            Size of cage
		 */
		Cage(Operation operator, int target, int size) {
			this.operator = operator;
			this.target = target;
			this.size = size;
			this.row = new int[size];
			this.col = new int[size];
		}

		/**
		 * Sets coordinates of ith cell of this cage.
		 * 
		 * @param i
		 *            Ordering number of cell (numbering starts from 0)
		 * @param row
		 *            Row in which cell is located
		 * @param col
		 *            Column in which cell is located
		 */
		void setCell(int i, int row, int col) {
			this.row[i] = row;
			this.col[i] = col;
		}
	}
}

/**
 * Enumeration for different types of operator of cages.
 */
enum Operation {
	NONE, PLUS, MINUS, PRODUCT, DIVISION,
}
