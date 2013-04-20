package net.miscjunk.droidjam;

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
		return o instanceof Player && username.equals(((Player)o).username);
	}

}
