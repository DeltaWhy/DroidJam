package net.miscjunk.droidjam;

public class Band {

	public static final int NUM_PLAYERS = 4;
	
	private Player[] players;
	private Player host;
	
	public Band(Player host) {
		this.host = host;
		players = new Player[NUM_PLAYERS];
		players[0] = host;
	}
	
	public void addPlayer(Player player) {
		int i = 0;
		while (i < players.length && players[i] != null) {
			i++;
		}
		
		if (i == players.length)
			return;
		players[i] = player;
	}
	
	public Player[] getPlayers() {
		return players;
	}

}
