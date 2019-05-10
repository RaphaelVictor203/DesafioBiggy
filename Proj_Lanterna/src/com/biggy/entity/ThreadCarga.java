package com.biggy.entity;

public class ThreadCarga extends Thread{

	private Lanterna lanterna;
	
	public ThreadCarga(Lanterna lanterna) {
		this.lanterna = lanterna;
	}
	
	@Override
    public void run() {
		diminuiCarga();
	}
	
	private void diminuiCarga() {
		while(this.lanterna.getStatus()) {
			try {
				if(this.lanterna.checarBateria() > 0) {
					Thread.sleep(1000);
					this.lanterna.setCargaBateria(this.lanterna.checarBateria()-1);
				}else {
					this.lanterna.setStatus(false);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
