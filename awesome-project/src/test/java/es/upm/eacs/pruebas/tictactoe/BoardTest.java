package es.upm.eacs.pruebas.tictactoe;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Win()
	{
		Board board = new Board();
		board.enableAll();
		
		board.getCell(0).value = "X";
		board.getCell(3).value = "O";
		board.getCell(1).value = "X";
		board.getCell(6).value = "O";
		board.getCell(2).value = "X";
		board.getCell(7).value = "O";
		
		int[] XPosition = board.getCellsIfWinner("X");
		int[] OPosition = board.getCellsIfWinner("O");
		int[] winPosEquals = {0,1,2};

		assertArrayEquals(winPosEquals, XPosition);
		assertNull("Lose O", OPosition);

	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Lose()
	{
		Board board = new Board();
		board.enableAll();
		
		board.getCell(0).value = "X";
		board.getCell(6).value = "O";
		board.getCell(2).value = "X";
		board.getCell(7).value = "O";
		board.getCell(3).value = "X";
		board.getCell(8).value = "O";
		
		int[] XPosition = board.getCellsIfWinner("X");
		int[] OPosition = board.getCellsIfWinner("O");
		int[] winPosEquals = {6,7,8};
		
		assertNull("Lose X", XPosition);
 		assertArrayEquals(winPosEquals, OPosition);
	}

	@Test
	public void checkDraw_isDraw()
	{
		Board board = new Board();
		board.enableAll();
				
		for(int i = 0; i < 9; i++)
		{
			 if(i%2 == 0)
			 {
				 board.getCell(i).value = "X";
			 }
			 else
			 {
				 board.getCell(i).value = "O";
			 }
		}
		boolean isDraw = board.checkDraw();
		assertTrue(isDraw);
	}
}
