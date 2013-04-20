package net.miscjunk.droidjam;

public class Player {

	public enum Instrument {
		KEYS, GUITAR, BASS, DRUMS;
	}
	
	private final String username;
	private Instrument instrument;
	
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
	
	public String getUsername() {
		return username;
	}

}
