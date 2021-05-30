package com.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Denomination implements Comparable<Denomination> {

	@JsonProperty("noteValue")
	private int noteValue;
	
	@JsonProperty("noteQuantity")
	private int noteQuantity;
	
	public Denomination(int noteValue, int noteQuantity) {
		super();
		this.noteValue = noteValue;
		this.noteQuantity = noteQuantity;
	}
	
	public Denomination() {
		
	}
	
	public int getNoteValue() {
		return noteValue;
	}
	public void setNoteValue(int noteValue) {
		this.noteValue = noteValue;
	}
	
	public int getNoteQuantity() {
		return noteQuantity;
	}
	public void setNoteQuantity(int noteQuantity) {
		this.noteQuantity = noteQuantity;
	}

	public int compareTo(Denomination o) {
		
		return o.getNoteValue() - this.getNoteValue(); 
	}

	@Override
	public String toString() {
		return "Denomination [noteValue=" + noteValue + ", noteQuantity=" + noteQuantity + "]";
	}

}
