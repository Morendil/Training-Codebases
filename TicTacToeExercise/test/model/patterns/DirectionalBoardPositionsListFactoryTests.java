package model.patterns;

import static org.junit.Assert.*;
import model.gamestate.Board;

import org.junit.Before;
import org.junit.Test;

public class DirectionalBoardPositionsListFactoryTests {

	private DirectionalBoardPositionsListFactory playerFactory;
	private DirectionalBoardPositionsListFactory computerFactory;
	private GroupOfDirectionalBoardPositionLists listGroup;
	
	@Before
	public void setUp() throws Exception {
		playerFactory = new DirectionalBoardPositionsListFactory(Board.HUMAN_PLAYER_MARK);
		computerFactory = new DirectionalBoardPositionsListFactory(Board.COMPUTER_PLAYER_MARK);
		listGroup = new GroupOfDirectionalBoardPositionLists();
	}

	@Test
	public void rows_should_be_number_of_board_rows() {
		playerFactory.addRowLists(listGroup);
		assertEquals(Board.MAX_ROWS, listGroup.size());
	}

	@Test
	public void rows_should_have_spaces_equal_to_number_of_columns() {
		playerFactory.addRowLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertEquals(Board.MAX_COLUMNS, listGroup.get(index).size());
		}
	}

	@Test
	public void rows_should_start_with_leftmost_space_in_each_row() {
		playerFactory.addRowLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertEquals(Board.getSingleCoordValueFor(index, 0), listGroup.get(index).get(0));
		}
	}

	@Test
	public void rows_should_have_contiguous_positions() {
		playerFactory.addRowLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertContiguousSpaces(index, DirectionalBoardPositionsListFactory.HORIZONTAL_AFTER_INCREMENT);
		}
	}

	@Test
	public void columns_should_be_number_of_board_columns() {
		playerFactory.addColumnLists(listGroup);
		assertEquals(Board.MAX_COLUMNS, listGroup.size());
	}

	@Test
	public void columns_should_have_spaces_equal_to_number_of_rows() {
		playerFactory.addColumnLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertEquals(Board.MAX_ROWS, listGroup.get(index).size());
		}
	}

	@Test
	public void columns_should_start_with_topmost_space_in_each_column() {
		playerFactory.addColumnLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertEquals(Board.getSingleCoordValueFor(0, index), listGroup.get(index).get(0));
		}
	}

	@Test
	public void columns_should_have_contiguous_positions() {
		playerFactory.addColumnLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertContiguousSpaces(index, DirectionalBoardPositionsListFactory.VERTICAL_AFTER_INCREMENT);
		}
	}

	@Test
	public void down_diagonals_for_five_in_row_should_be_11() {
		playerFactory.addDownDiagonalLists(listGroup);
		assertEquals(11, listGroup.size());
	}

	@Test
	public void down_diagonals_for_six_in_row_should_be_9() {
		computerFactory.addDownDiagonalLists(listGroup);
		assertEquals(9, listGroup.size());
	}

	@Test
	public void down_diagonals_should_start_on_top_row_or_left_column() {
		playerFactory.addDownDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			int firstSpacePosition = listGroup.get(index).get(0);
			boolean onTopRow = Board.getRowCoordFor(firstSpacePosition) == 0;
			boolean onLeftColumn = Board.getColumnCoordFor(firstSpacePosition) == 0;
			assertTrue(onTopRow | onLeftColumn);
		}
	}

	@Test
	public void down_diagonals_distance_travelled_from_top_left_corner_should_be_equal_to_number_of_columns() {
		playerFactory.addDownDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			int firstSpacePosition = listGroup.get(index).get(0);
			int distanceFromTopEdge = Board.getRowCoordFor(firstSpacePosition);
			int distanceFromLeftEdge = Board.getColumnCoordFor(firstSpacePosition);
			int distanceAlongDiagonal = listGroup.get(index).size();
			int totalDistance = distanceFromTopEdge + distanceFromLeftEdge + distanceAlongDiagonal;
			
			assertEquals(Board.MAX_ROWS, totalDistance);
		}
	}

	@Test
	public void down_diagonals_should_have_contiguous_positions() {
		playerFactory.addDownDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertContiguousSpaces(index, DirectionalBoardPositionsListFactory.DIAGONAL_DOWN_AFTER_INCREMENT);
		}
	}

	@Test
	public void up_diagonals_for_five_in_row_should_be_11() {
		playerFactory.addUpDiagonalLists(listGroup);
		assertEquals(11, listGroup.size());
	}

	@Test
	public void up_diagonals_for_six_in_row_should_be_9() {
		computerFactory.addUpDiagonalLists(listGroup);
		assertEquals(9, listGroup.size());
	}

	@Test
	public void up_diagonals_should_start_on_bottom_row_or_left_column() {
		playerFactory.addUpDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			int firstSpacePosition = listGroup.get(index).get(0);
			boolean onBottomRow = Board.getRowCoordFor(firstSpacePosition) == Board.MAX_ROW_INDEX;
			boolean onLeftColumn = Board.getColumnCoordFor(firstSpacePosition) == 0;
			assertTrue(onBottomRow | onLeftColumn);
		}
	}

	@Test
	public void up_diagonals_distance_travelled_from_bottom_left_corner_should_be_number_of_rows() {
		playerFactory.addUpDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			int firstSpacePosition = listGroup.get(index).get(0);
			int distanceAlongLeftEdge = Board.MAX_ROW_INDEX - Board.getRowCoordFor(firstSpacePosition);
			int distanceAlongBottomEdge = Board.getColumnCoordFor(firstSpacePosition);
			int distanceAlongDiagonal = listGroup.get(index).size();
			int totalDistance = distanceAlongLeftEdge + distanceAlongBottomEdge + distanceAlongDiagonal;
			
			assertEquals(Board.MAX_ROWS, totalDistance);
		}
	}

	@Test
	public void up_diagonals_should_have_contiguous_positions() {
		playerFactory.addUpDiagonalLists(listGroup);
	
		for (int index=0; index < listGroup.size(); index++) {
			assertContiguousSpaces(index, DirectionalBoardPositionsListFactory.DIAGONAL_UP_AFTER_INCREMENT);
		}
	}

	private void assertContiguousSpaces(int index, int offset) {
		int expectedPosition = listGroup.get(index).get(0);

		for (int space=1; space < listGroup.get(index).size(); space++) {
			expectedPosition += offset;
			assertEquals("Index[" + index + "," + space + "]", expectedPosition, listGroup.get(index).get(space));
		}
	}	
}
