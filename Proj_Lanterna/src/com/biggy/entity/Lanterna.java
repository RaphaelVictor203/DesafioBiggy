package com.biggy.entity;

public class Lanterna {
	
	private Bateria bateria;
	private boolean status;
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean st) {
		this.status = st;
	}

	public Lanterna() {
		this.bateria = new Bateria();
		this.status = false;
	}
	
	public void liga_desliga() {
		this.status = (this.status == true) ? false : true;
	}
	
	public void trocarBateria() {
		this.bateria = new Bateria();
	}
	
	public int checarBateria() {
		return this.bateria.getCarga();
	}
	
	public void setCargaBateria(int i) {
		this.bateria.setCarga(i);
	}
	
}
