package robot.app.gameoflife;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import robot.Lcd;

/**
 * Game of Life
 * 
 * @author Vincent
 *
 */
public class GOL {

	// Extra; add simulated games (with hardcoded entities)
	// all the games clear screen, run, end with enter

	// board size
	public final static int WIDTH = 17;
	public final static int HEIGHT = 7;
	public static Lcd screen = new Lcd();

	// the board
	public int[][] board;

	public GOL() {
		super();
	}

	public void testDisplay() {

		int generation = 0;

		// create board
		startGame();
		// fill board with random life or dead cells
		fillRandom();
		// display board
		displayBoard(generation);
		Button.ENTER.waitForPress();
		// create next board
		nextBoard();
		// display board
		displayBoard(generation);
		Button.ENTER.waitForPress();

	}

	/**
	 * test basic functionality
	 */
	public void testUpdate() {

		// create board
		startGame();

		// fill board with random life or dead cells
		fillRandom();
		// print board works
		printBoard();

		System.out.println("");
		// prepare the board for the next generation
		nextBoard();
		// print board again , het werkt :D
		printBoard();
	}
	
	/**
	 * play the simulation for 180 generations
	 * press enter to restart or escape to escape
	 * @throws InterruptedException
	 */
	public void playGame() throws InterruptedException {

		GOL gameOfLife = new GOL();

		boolean run = true;

		while (Button.ESCAPE.isUp() && run) {

			// play a random simulation
			gameOfLife.runRandomGame();
			// do not do it again as default
			run = false;
			// wait for enter to run again
			// else you can press escape to exit

			boolean cntry = true;

			// end of program decission
			while (cntry) {
				// escape clause to escape program
				if (Button.ESCAPE.isDown()) {
					cntry = !Button.ESCAPE.isDown();
				}

				// restart met enter
				if (Button.ENTER.isDown()) {
					// run clause
					run = Button.ENTER.isDown();
					// escapte this loop with the same button
					cntry = !Button.ENTER.isDown();
				}

			}
		}
	}

	public void runRandomGame(int lastGen) throws InterruptedException {
		// simulate the game for this amount of generations
		int finalGeneration = lastGen;
		final int FRAMERATE = 45;

		// create board
		startGame();
		// fill board with random life or dead cells
		fillRandom();

		for (int i = 0; i < finalGeneration + 1; i++) {
			// display the board
			displayBoard(i);
			// prepare the board for the next generation
			nextBoard();
			// delay till next
			Thread.sleep(FRAMERATE);
		}

	}

	public void runRandomGame() throws InterruptedException {
		runRandomGame(180);
	}

	public void runRandomGameToConsole() throws InterruptedException {
		// simulate the game for this amount of generations
		final int FINALGEN = 200;

		runRandomGameToConsole(FINALGEN);

	}

	public void runRandomGameToConsole(int lastGen) throws InterruptedException {
		// simulate the game for this amount of generations
		int finalGeneration = lastGen;

		// create board
		startGame();
		// fill board with random life or dead cells
		fillRandom();
		// print board works
		printBoard();

		for (int i = 0; i < finalGeneration + 1; i++) {

			// prepare the board for the next generation
			nextBoard();
			// print board again , het werkt :D
			printBoard(i);
			// delay till next
			Thread.sleep(42);

		}

	}

	public void testCellCounter() {
		// TestBoard
		int[][] aBoard = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

		board = aBoard.clone();

		// test board edgecases
		System.out.printf("Lefttop count = %d", cellCounter(0, 0));
		System.out.printf("Midmid count = %d", cellCounter(1, 1));
		System.out.printf("Rightbottom count = %d", cellCounter(2, 2));
	}

	/**
	 * print the board to console
	 */
	public void printBoard() {
		System.out.println("\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.printf("%d ", board[i][j]);
				if (j == board[i].length - 1) {
					System.out.printf("\n");
				}
			}
		}
		// seperator
		System.out.println("\n");
	}

	/**
	 * @param generation
	 *            to be displayed
	 */
	public void displayBoard(int generation) {
		String lastRow = "Generation: ";

		BrickFinder.getDefault().getTextLCD().clear();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				screen.writeToScreen(cellChar(board[i][j]), j, i);
			}
		}

		screen.writeToScreen(String.format("%s%d\n", lastRow, generation), 0, 7);

	}

	/**
	 * display the board
	 */
	public void displayBoard() {
		BrickFinder.getDefault().getTextLCD().clear();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				screen.writeToScreen(cellChar(board[i][j]), j, i);
			}
		}
	}

	/**
	 * @param status
	 *            life or dead
	 * @return character for cel
	 */
	public String cellChar(int status) {
		String life = "O";
		String dead = " ";
		// empty block U+25A1
		// full block U+25A0

		if (status == 1) {
			return life;
		} else if (status == 0) {
			return dead;
		}
		return "x";
	}

	/**
	 * print the board to console with the generation
	 * 
	 * @param generation
	 *            that is printed
	 */
	public void printBoard(int generation) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.printf("%d ", board[i][j]);
				if (j == board[i].length - 1) {
					System.out.printf("\n");
				}
			}
		}

		System.out.printf("Generation: %d\n", generation);
	}

	/**
	 * Fill the board randomly with life or dead cells
	 */
	public void fillRandom() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ((int) (Math.random() * 2));
			}

		}
	}

	/**
	 * @return an empty board
	 */
	public int[][] newBoard() {

		return new int[HEIGHT][WIDTH];

	}

	/**
	 * start a game
	 */
	public void startGame() {
		board = newBoard();
	}

	/**
	 * create the next board
	 */
	public void nextBoard() {
		// create an empty board
		int[][] nextBoard = newBoard();

		// fill the board with values depending on the rules of Conway
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				nextBoard[i][j] = liveOrDie(i, j);
			}
		}

		// replace the previous board with the board of the new generation
		board = nextBoard;

	}

	/**
	 * Determines the value of the cell for the next generation
	 * 
	 * @param i
	 *            row on board
	 * @param j
	 *            column on board
	 * @return value for the cell by rules of Conway's Game of Life
	 */
	public int liveOrDie(int i, int j) {
		int life = 1;
		int death = 0;

		int count = cellCounter(i, j);
		if (board[i][j] == 1) {
			if (count < 2) {
				return death;
			} else if (count == 2 | count == 3) {
				return life;
			} else {
				return death;
			}

		}

		if (board[i][j] == 0) {
			if (count == 3) {
				return life;
			}
		}

		return death;

	}

	/**
	 * Determines the amount of life neighbours
	 * 
	 * @param i
	 *            row on board
	 * @param j
	 *            column on board
	 * @return count of life neighbours
	 */
	public int cellCounter(int i, int j) {
		int count = 0;

		// deduct the value of itself to prevent double
		count -= board[i][j];

		for (int a = -1; a <= 1; a++) {
			// skip if x is out of bound
			if ((i + a) >= 0 && (i + a) < (board.length)) {
				// System.out.printf("[a = %d, i = %d]", a,i);
				for (int b = -1; b <= 1; b++) {
					// skip if y is out of bound
					if ((j + b) >= 0 && (j + b) < (board[i].length)) {
						// System.out.printf("[b = %d, j = %d]", a,i);
						count += board[i + a][j + b];
					}
				}

			}

		}

		return count;

	}

	public void stop() {
		System.exit(0);
	}

}
