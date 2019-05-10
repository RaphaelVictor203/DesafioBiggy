package com.biggy.boundary;

import com.biggy.control.ControlLanterna;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BoundaryLanterna extends Application implements EventHandler<MouseEvent>{

	private Button btnLigaDesliga;
	private ControlLanterna clanterna;
	private Button btnTrocarBateria;
	private Label lblStatus;
	private Label lblBateria;
	private Thread threadAtualizacao;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		clanterna = new ControlLanterna();
		
		lblStatus = new Label("status: ");
		lblBateria = new Label("bateria: ");
		
		String status = (clanterna.getStatus()) ? "ligada" : "desligada";
		
		lblStatus.setText(lblStatus.getText() + " " + status);
		lblBateria.setText(lblBateria.getText() + " " + clanterna.checkBateria());
		
		btnLigaDesliga = new Button("Ligar");
		btnLigaDesliga.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnLigaDesliga.setMinWidth(250);
		btnTrocarBateria = new Button("Trocar Bateria");
		btnTrocarBateria.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnTrocarBateria.setMinWidth(250);
		
		GridPane gp = new GridPane();
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(50);
		gp.getColumnConstraints().add(cc);
		gp.add(lblStatus, 0, 0);
		gp.add(btnLigaDesliga, 1, 0);
		gp.add(lblBateria, 0, 1);
		gp.add(btnTrocarBateria, 1, 1);
		
		Scene scene = new Scene(gp, 500, 500);
		
		stage.setScene(scene);
		stage.setTitle("Lanterna");
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

                clanterna.suspendThreadCarga();

                stage.close();
                Platform.exit();
                System.exit(0);
            }
        });
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void handle(MouseEvent event) {
		if(event.getSource() == btnLigaDesliga) {
			this.clanterna.ligar_desligar_lanterna();
			if(this.btnLigaDesliga.getText().equals("Ligar")) {
				this.btnLigaDesliga.setText("Desligar");
				this.clanterna.atualizarCarga();
				atualizaBateria();
				btnTrocarBateria.setDisable(true);
			}else {
				this.btnLigaDesliga.setText("Ligar");
				this.threadAtualizacao.suspend();
				btnTrocarBateria.setDisable(false);
			}
			lblStatus.setText("status: " + ((clanterna.getStatus()) ? "ligada" : "desligada"));
			lblBateria.setText("bateria: " + clanterna.checkBateria());
		}else
		if(event.getSource() == btnTrocarBateria) {
			this.clanterna.trocar_bateria();
			lblBateria.setText("bateria: " + clanterna.checkBateria());
		}
	}
	
	public void atualizaBateria() {
		this.threadAtualizacao = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        lblBateria.setText("bateria: " + clanterna.checkBateria());
                        lblStatus.setText("status: " + ((clanterna.getStatus()) ? "ligada" : "desligada"));
                        if(clanterna.checkBateria() == 0) {
                        	btnLigaDesliga.setText("Ligar");
                        	btnTrocarBateria.setDisable(false);
                        }
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    }

                    Platform.runLater(updater);
                }
            }

        });
        
		this.threadAtualizacao.setDaemon(true);
		this.threadAtualizacao.start();
	}
	
	
}
