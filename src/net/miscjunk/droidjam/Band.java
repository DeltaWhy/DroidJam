package net.miscjunk.droidjam;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	
	public Band(String name) {
	    this.name = name;
	    players = new Player[NUM_PLAYERS];
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
	    return getName();
	}
	
	/*
	 * Persistence
	 */
	public boolean create() {
	    try {
        	    CommService comm = new CommService();
        	    JSONObject jRequest = new JSONObject();
        	    jRequest.put("name", name);
        	    JSONObject bandJson = comm.postJSON(CommService.API_BASE+"/bands", jRequest);
        	    fromJSONObject(bandJson);
        	    return true;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean reload() {
	    try {
        	    CommService comm = new CommService();
        	    JSONObject bandJson = comm.getJSON(CommService.API_BASE+"/bands/"+id);
        	    fromJSONObject(bandJson);
        	    return true;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Band findById(String id) {
	    CommService comm = new CommService();
	    JSONObject bandJson = comm.getJSON(CommService.API_BASE+"/bands/"+id);
	    Band band = new Band("");
	    try {
        	    band.fromJSONObject(bandJson);
        	    return band;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static List<Band> all() {
	    try {
	        CommService comm = new CommService();
	        JSONArray jsonBands = comm.getJSONArray(CommService.API_BASE+"/bands");
	        List<Band> bands = new ArrayList<Band>(jsonBands.length());
	        for (int i=0; i < jsonBands.length(); i++) {
	            JSONObject jsonBand = jsonBands.getJSONObject(i);
	            Band band = new Band("");
	            band.fromJSONObject(jsonBand);
	            bands.add(band);
	        }
	        return bands;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return new ArrayList<Band>();
	    }
	}
	
	void fromJSONObject(JSONObject json) throws JSONException {
	    this.id = json.getString("id");
	    this.name = json.getString("name");
	    //TODO players
	}
}