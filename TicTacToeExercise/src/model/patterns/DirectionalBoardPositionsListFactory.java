package model.patterns;

import model.gamestate.Board;

public class DirectionalBoardPositionsListFactory {
	private int winningSeriesSize;

	public static final int DIAGONAL_UP_AFTER_INCREMENT = -(Board.MAX_COLUMNS - 1);
	public static final int DIAGONAL_UP_BEFORE_INCREMENT = (Board.MAX_COLUMNS - 1);
	public static final int DIAGONAL_DOWN_AFTER_INCREMENT = (Board.MAX_COLUMNS + 1);
	public static final int DIAGONAL_DOWN_BEFORE_INCREMENT = -(Board.MAX_COLUMNS + 1);
	public static final int VERTICAL_AFTER_INCREMENT = Board.MAX_COLUMNS;
	public static final int VERTICAL_BEFORE_INCREMENT = -Board.MAX_COLUMNS;
	public static final int HORIZONTAL_AFTER_INCREMENT = 1;
	public static final int HORIZONTAL_BEFORE_INCREMENT = -1;

	public DirectionalBoardPositionsListFactory(int playerMark) {
		if (playerMark == Board.HUMAN_PLAYER_MARK)
			this.winningSeriesSize = 5;
		else if (playerMark == Board.COMPUTER_PLAYER_MARK)
			this.winningSeriesSize = 6;
		else
			throw new RuntimeException("Invalid playerMark: " + playerMark);
	}

	public GroupOfDirectionalBoardPositionLists getAllIndexLists() {
		GroupOfDirectionalBoardPositionLists listGroup = new GroupOfDirectionalBoardPositionLists();

		addRowLists(listGroup);
		addColumnLists(listGroup);
		addDownDiagonalLists(listGroup);
		addUpDiagonalLists(listGroup);

		return listGroup;
	}

	public void addRowLists(GroupOfDirectionalBoardPositionLists listGroup) {
		for (int row = Board.MIN_ROW_INDEX; row < Board.MAX_ROWS; row++) {
			listGroup.add(createRowList(row));
		}
	}

	private DirectionalBoardPositionsList createRowList(int row) {
		int startPosition = Board.getSingleCoordValueFor(row, Board.MIN_COLUMN_INDEX);
		int count = Board.MAX_COLUMNS;
		
		return createList(startPosition, HORIZONTAL_AFTER_INCREMENT, count);
	}

	public void addColumnLists(GroupOfDirectionalBoardPositionLists listGroup) {
		for (int column = Board.MIN_COLUMN_INDEX; column < Board.MAX_COLUMNS; column++) {
			listGroup.add(createColumnList(column));
		}
	}

	private DirectionalBoardPositionsList createColumnList(int column) {
		int startPosition = Board.getSingleCoordValueFor(Board.MIN_ROW_INDEX, column);
		int count = Board.MAX_ROWS;
		return createList(startPosition, VERTICAL_AFTER_INCREMENT, count);
	}

	public void addDownDiagonalLists(GroupOfDirectionalBoardPositionLists listGroup) {
		addDownDiagonalsStartingFromTopLeftCorner(listGroup);
		addDownDiagonalsStartingFromTopEdge(listGroup);
		addDownDiagonalsStartingFromLeftEdge(listGroup);
	}

	private void addDownDiagonalsStartingFromTopLeftCorner(
			GroupOfDirectionalBoardPositionLists listGroup) {
		addDownDiagonal(listGroup, Board.MIN_ROW_INDEX, Board.MIN_COLUMN_INDEX);
	}

	private void addDownDiagonalsStartingFromTopEdge(GroupOfDirectionalBoardPositionLists listGroup) {
		int row = Board.MIN_ROW_INDEX;
		for (int column = Board.MIN_COLUMN_INDEX+1; column < getDiagonalCountStartingFromTopOrBottomEdge() ; column++) {
			addDownDiagonal(listGroup, row, column);
		}
	}

	private void addDownDiagonalsStartingFromLeftEdge(GroupOfDirectionalBoardPositionLists listGroup) {
		int column = Board.MIN_COLUMN_INDEX;
		for (int row = Board.MIN_ROW_INDEX+1; row < getDiagonalCountStartingFromLeftEdge() ; row++) {
			addDownDiagonal(listGroup, row, column);
		}
	}

	private void addDownDiagonal(GroupOfDirectionalBoardPositionLists listGroup, int row, int column) {
		int startPosition = Board.getSingleCoordValueFor(row, column);
		int diagonalLength = Board.MAX_COLUMNS - row - column;
		
		DirectionalBoardPositionsList list = createList(startPosition, DIAGONAL_DOWN_AFTER_INCREMENT, diagonalLength);
		listGroup.add(list);
	}

	public void addUpDiagonalLists(GroupOfDirectionalBoardPositionLists listGroup) {
		addUpDiagonalsStartingFromBottomLeftCorner(listGroup);
		addUpDiagonalsStartingFromBottomEdge(listGroup);
		addUpDiagonalsStartingFromLeftEdge(listGroup);
	}

	private void addUpDiagonalsStartingFromBottomLeftCorner(
			GroupOfDirectionalBoardPositionLists listGroup) {
		addUpDiagonal(listGroup, Board.MAX_ROW_INDEX, Board.MIN_COLUMN_INDEX);
	}

	private void addUpDiagonalsStartingFromBottomEdge(GroupOfDirectionalBoardPositionLists listGroup) {
		int row = Board.MAX_ROW_INDEX;
		for (int column = Board.MIN_COLUMN_INDEX+1; column < getDiagonalCountStartingFromTopOrBottomEdge() ; column++) {
			addUpDiagonal(listGroup, row, column);
		}
	}

	private void addUpDiagonalsStartingFromLeftEdge(GroupOfDirectionalBoardPositionLists listGroup) {
		int column = Board.MIN_COLUMN_INDEX;
		for (int rowOffset = Board.MIN_ROW_INDEX+1; rowOffset < getDiagonalCountStartingFromLeftEdge() ; rowOffset++) {
			int row = Board.MAX_ROWS - rowOffset;
			addUpDiagonal(listGroup, row, column);
		}
	}

	private void addUpDiagonal(GroupOfDirectionalBoardPositionLists listGroup, int row, int column) {
		int startPosition = Board.getSingleCoordValueFor(row, column);
		int rowOffsetFromBottomLeftCorner = Board.MAX_ROW_INDEX - row;
		int diagonalLength = Board.MAX_ROWS - rowOffsetFromBottomLeftCorner - column;
		
		DirectionalBoardPositionsList list = createList(startPosition, DIAGONAL_UP_AFTER_INCREMENT, diagonalLength);
		listGroup.add(list);
	}

	private DirectionalBoardPositionsList createList(int startPosition, int offset, int count) {
		DirectionalBoardPositionsList list = new DirectionalBoardPositionsList();
		int position = startPosition;
		
		for (int i=0; i < count; i++) {
			list.add(position);
			position += offset;
		}
		
		return list;
	}

	private int getDiagonalCountStartingFromTopOrBottomEdge() {
		return Board.MAX_COLUMNS - this.winningSeriesSize + 1;
	}

	private int getDiagonalCountStartingFromLeftEdge() {
		return Board.MAX_ROWS - this.winningSeriesSize + 1;
	}
}