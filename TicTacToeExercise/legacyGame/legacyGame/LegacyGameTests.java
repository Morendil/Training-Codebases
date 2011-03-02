package legacyGame;

import org.junit.*;
import static org.junit.Assert.*;

// TODO: Declan 17-May-2009 Legacy game tests

public class LegacyGameTests {

	private LegacyGame game;

	@Before
	public void setup() {
		game = new LegacyGame();
	}
	
	@Test
	public void player_should_win_with_five_in_row() {
		
		setPlayerMark(0,0);
		setPlayerMark(0,1);
		setPlayerMark(0,2);
		setPlayerMark(0,3);
		setPlayerMark(0,4);
		
		assertEquals(LegacyGame.X_MARK_FOR_PLAYER, game.win());
	}
	
	@Test
	public void computer_should_not_win_with_five_in_row() {
		
		setComputerMark(0,0);
		setComputerMark(0,1);
		setComputerMark(0,2);
		setComputerMark(0,3);
		setComputerMark(0,4);
		
		assertEquals(LegacyGame.EMPTY, game.win());
	}
	
	@Test
	public void computer_should_win_with_six_in_row() {
		
		setComputerMark(0,0);
		setComputerMark(0,1);
		setComputerMark(0,2);
		setComputerMark(0,3);
		setComputerMark(0,4);
		setComputerMark(0,5);
		
		assertEquals(LegacyGame.ZERO_MARK_FOR_COMPUTER, game.win());
	}
	
	private void setPlayerMark(int row, int column) {
		int position = getPosition(row, column);
		game.gameBoard[0][position] = LegacyGame.X_MARK_FOR_PLAYER;
	}

	private void setComputerMark(int row, int column) {
		int position = getPosition(row, column);
		game.gameBoard[0][position] = LegacyGame.ZERO_MARK_FOR_COMPUTER;
	}

	private int getPosition(int row, int column) {
		return column * LegacyGame.SQUARES_PER_SIDE + row;
	}
}
