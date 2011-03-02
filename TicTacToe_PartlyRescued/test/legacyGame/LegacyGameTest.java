package legacyGame;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LegacyGameTest {
	
	private LegacyGame game;
	
	@Before
	public void setUp() {
		game = new LegacyGame();
	}

	@Test
	public void computerCanWinWithFiveInARowVertical() {
		
		game.gameBoard = new int[][]{
		 {
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 },
		 {}, {}};

		int result = game.check();
		assertEquals(2, result);
	}

	@Test
	public void playerCanWinWithFiveInARowHorizontal() {
		game.gameBoard = new int[][]{
		 {
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 1, 1, 1, 1, 1, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 },
		 {}, {}};

		int result = game.check();
		assertEquals(1, result);
	}

}
