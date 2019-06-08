package es.upm.eacs.pruebas.tictactoe;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import es.upm.eacs.pruebas.tictactoe.TicTacToeGame.EventType;
import es.upm.eacs.pruebas.tictactoe.TicTacToeGame.WinnerValue;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.*;


public class TicTacToeGameTest {

	private ArgumentCaptor<List<Player>> argumentCaptor;
	private ArgumentCaptor<WinnerValue> winnerArgumentCaptor;
	
	private List<Player> list;
	
	private TicTacToeGame game;
	private Player player0;
	private Player player1;
	
	private Connection connection1 = mock(Connection.class);
	private Connection connection2 = mock(Connection.class);
	
	
	@Before
	public void setUp() {
        System.out.println("Setting it up!");
        argumentCaptor = ArgumentCaptor.forClass(List.class);
    	winnerArgumentCaptor = ArgumentCaptor.forClass(WinnerValue.class);
    	
    	list =  new CopyOnWriteArrayList<>();
    	
    	game = new TicTacToeGame();
    	player0 = new Player(0, "X", "Pikachu");
    	player1 = new Player(1, "O", "Charmander");
    }
	
	@After
    public void tearDown() throws Exception {
		System.gc();
		reset(connection1);
		reset(connection2);
        System.out.println("Tear down.");
    }
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Win()
	{
		list.add(player0);
		
		game.addConnection(connection1);
		game.addConnection(connection2);

		reset(connection1);
		reset(connection2);

		game.addPlayer(player0);
		
		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		
		List<Player> values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		reset(connection2);
		
		game.addPlayer(player1);

		list.add(player1);

		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());

		values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		
		game.mark(0);		
		game.mark(3);
		game.mark(1);
		game.mark(5);
		game.mark(2);
		
		verify(connection1, times(4)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		verify(connection2, times(5)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		
		WinnerValue capturedWinner = winnerArgumentCaptor.getValue();
		
		WinnerValue winner = new WinnerValue();
		int[] pos = {0,1,2};
		winner.player = player0;
		winner.pos = pos;
		
		assertEquals(capturedWinner.player.getLabel(), winner.player.getLabel());
		assertArrayEquals(capturedWinner.pos, winner.pos);		
	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Lose()
	{
		list.add(player0);

		game.addConnection(connection1);
		game.addConnection(connection2);

		reset(connection1);
		reset(connection2);

		game.addPlayer(player0);
		
		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		
		List<Player> values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		reset(connection2);
		
		game.addPlayer(player1);

		list.add(player1);

		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());

		values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		
		game.mark(0);		
		game.mark(3);
		game.mark(1);
		game.mark(4);
		game.mark(6);
		game.mark(5);
		
		verify(connection1, times(5)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		verify(connection2, times(6)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		
		WinnerValue capturedWinner = winnerArgumentCaptor.getValue();
		
		WinnerValue winner = new WinnerValue();
		int[] pos = {3,4,5};
		winner.player = player1;
		winner.pos = pos;
		
		assertEquals(capturedWinner.player.getLabel(), winner.player.getLabel());
		assertArrayEquals(capturedWinner.pos, winner.pos);		
	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Draw()
	{
		list.add(player0);

		game.addConnection(connection1);
		game.addConnection(connection2);

		reset(connection1);
		reset(connection2);

		game.addPlayer(player0);
		
		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		
		List<Player> values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		reset(connection2);
		
		game.addPlayer(player1);

		list.add(player1);

		verify(connection1, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.JOIN_GAME), argumentCaptor.capture());

		values = argumentCaptor.getValue();
		assertEquals(values, list);
		
		reset(connection1);
		
		game.mark(0);		
		game.mark(1);
		game.mark(2);
		game.mark(4);
		game.mark(3);
		game.mark(5);
		game.mark(7);
		game.mark(6);
		game.mark(8);
		
		verify(connection1, times(8)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		verify(connection2, times(9)).sendEvent(eq(EventType.SET_TURN), argumentCaptor.capture());
		
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		verify(connection2, times(1)).sendEvent(eq(EventType.GAME_OVER), winnerArgumentCaptor.capture());
		
		WinnerValue capturedWinner = winnerArgumentCaptor.getValue();
		WinnerValue winner = null;
	
		assertEquals(winner, capturedWinner);
	}
		
}
