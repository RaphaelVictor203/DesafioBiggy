package com.biggy.control;

import com.biggy.entity.Lanterna;
import com.biggy.entity.ThreadCarga;

public class ControlLanterna {
	
	private Lanterna lanterna;
	private ThreadCarga tc;
	
	public ControlLanterna() {
		this.lanterna = new Lanterna();
	}
	
	public void ligar_desligar_lanterna() {
		this.lanterna.liga_desliga();
	}
	
	public void trocar_bateria() {
		this.lanterna.trocarBateria();
	}
	
	public int checkBateria() {
		return this.lanterna.checarBateria();
	}
	
	public void atualizarCarga() {
		tc = new ThreadCarga(this.lanterna);
		tc.start();
	}
	
	public void suspendThreadCarga() {
		tc.suspend();
	}
	
	public boolean getStatus() {
		return this.lanterna.getStatus();
	}
	
}
