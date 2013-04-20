package net.miscjunk.droidjam;

import org.json.JSONObject;

public class Player {

	public enum Instrument {
		KEYS, GUITAR, BASS, DRUMS;
		
		public Instrument next() {
		    return values()[ordinal() + 1 % values().length];
		}
	}
	
	private final String username;
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
	    //TODO stub
	    return false;
	}
	
	public boolean reload() {
	    //TODO stub
	    return false;
	}
	
	public static Player findById(String id) {
	    //TODO stub
	    return null;
	}
	
	void fromJSONObject(JSONObject json) {
	    //TODO stub
	}
	
	JSONObject toJSONObject(boolean forBand) {
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
