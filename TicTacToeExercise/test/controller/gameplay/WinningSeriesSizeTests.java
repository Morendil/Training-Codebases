package controller.gameplay;

import junit.framework.TestCase;
import model.gamestate.Board;
import strategy.StrategyFake;

public class WinningSeriesSizeTests extends TestCase {
	private static final int SERIES_SIZE_OF_FOUR = 4;
	private static final int SERIES_SIZE_OF_FIVE = 5;
	private static final int SERIES_SIZE_OF_SIX = 6;
	
	private StrategyFake fakeStrategy;
	private TicTacToeGame game;

	protected void setUp() throws Exception {
		fakeStrategy = new StrategyFake();	
		game = new TicTacToeGame(fakeStrategy, new StubView());
	}

        // TODO: Declan 17-May-2009 - Updated to test for 5 row in a row rather than 4	
	public void testThatComputerDoesNotWinWithFiveInARow() throws Exception {
		fakeStrategy.playerSeriesSizes.put(Board.COMPUTER_PLAYER_MARK, SERIES_SIZE_OF_FIVE);	
		
		assertTrue("there was a winner when there shouldn't be", game.noWinnerYet());		
	}
	
	public void testThatHumanDoesNotWinWithFourInARow() {
		fakeStrategy.playerSeriesSizes.put(Board.HUMAN_PLAYER_MARK, SERIES_SIZE_OF_FOUR);	
		
		assertTrue("there was a winner when there shouldn't be", game.noWinnerYet());		
	}

        // TODO: Declan 17-May-2009 - Updated to test for 6 row in a row rather than 5
	public void testThatItTakesSixForTheComputerToWin() {
		fakeStrategy.playerSeriesSizes.put(Board.COMPUTER_PLAYER_MARK, SERIES_SIZE_OF_SIX);
		game.updateGameState();
		
		assertFalse(game.noWinnerYet());
		assertTrue(game.computerWon());
	}
	
	public void testThatItTakesFiveForTheHumanPlayerToWin() {
		fakeStrategy.playerSeriesSizes.put(Board.HUMAN_PLAYER_MARK, SERIES_SIZE_OF_FIVE);
		game.updateGameState();
		
		assertFalse(game.noWinnerYet());
		assertTrue(game.humanPlayerWon());
	}

}
