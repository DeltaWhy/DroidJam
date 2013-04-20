package net.miscjunk.droidjam;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {

	public enum Instrument {
		KEYS, GUITAR, BASS, DRUMS;
		
		public Instrument next() {
		    return values()[ordinal() + 1 % values().length];
		}
	}
	
	private String username;
	private Instrument instrument;
	private boolean ready;
	private String id;
	private String bandId;
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	
	public Player(String username) {
		this.username = username;
		instrument = null;
	}
	
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}
	
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public boolean getReady() {
		return ready;
	}
	
	public boolean toggleReady() {
		ready = !ready;
		return ready;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void toggleInstrument() {
		instrument = instrument.next();
	}
	
	public boolean equals(Object o) {
		return o instanceof Player && id.equals(((Player)o).id);
	}
	
	/*
	 * Persistence
	 */
	public boolean create() {
	    try {
        	    CommService comm = new CommService();
        	    JSONObject jRequest = new JSONObject();
        	    jRequest.put("name", username);
        	    JSONObject playerJson = comm.postJSON(CommService.API_BASE+"/players", jRequest);
        	    fromJSONObject(playerJson);
        	    return true;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean reload() {
	    try {
        	    CommService comm = new CommService();
        	    JSONObject playerJson = comm.getJSON(CommService.API_BASE+"/players/"+id);
        	    fromJSONObject(playerJson);
        	    return true;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Player findById(String id) {
	    CommService comm = new CommService();
	    JSONObject playerJson = comm.getJSON(CommService.API_BASE+"/players/"+id);
	    Player player = new Player("");
	    try {
        	    player.fromJSONObject(playerJson);
        	    return player;
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	void fromJSONObject(JSONObject json) throws JSONException {
	    id = json.getString("id");
	    username = json.getString("name");
	    if (json.has("instrument")) {
	        String inst = json.getString("instrument");
	        if (inst.equals("keys")) {
	            instrument = Instrument.KEYS;
	        } else if (inst.equals("drums")) {
	            instrument = Instrument.DRUMS;
	        } else if (inst.equals("null")) {
	            instrument = null;
	        } else {
	            throw new JSONException("Unknown instrument "+inst);
	        }
	    }
	    if (json.has("ready")) {
	        ready = json.getBoolean("ready");
	    }
	}
	
	JSONObject toJSONObject(boolean forBand) throws JSONException {
	    //TODO stub
	    return null;
	    
	}
	
	public boolean update() {
	    //TODO stub
	    return false;
	}
	
	public boolean joinBand(Band band) {
	    return joinBand(band.getId());
	}
	
	public boolean joinBand(String bandId) {
	    //TODO stub
	    return false;
	}
	
	public boolean leaveBand() {
	    //TODO stub
	    return false;
	}
}
