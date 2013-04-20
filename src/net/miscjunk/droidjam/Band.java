package net.miscjunk.droidjam;

import java.util.Observable;

public class Band extends Observable {

	public static final int NUM_PLAYERS = 4;
	
	private Player[] players;
	private Player host;
	private String id;
	private String name;
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	
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
		setChanged();
		notifyObservers();
	}
	
	public void removePlayer(Player player) {
		int i = 0;
		while (i < players.length && !players[i].equals(player)) {
			i++;
		}
		
		if (i == players.length)
			return;
		players[i] = player;
		setChanged();
		notifyObservers();
	}
	
	public Player getHost() {
	    return host;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}

	public String toString() {
	    return "a band by " + host.getUsername();
	}
}
