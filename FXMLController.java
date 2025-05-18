package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLController {

	/*
	 * Deklarationen
	*/
		@FXML private Stage meineBuehne;
		@FXML private Label guthaben;
		
		//die 3 Würfel deklarieren
		@FXML private Label wuerfel1;
		@FXML private Label wuerfel2;
		@FXML private Label wuerfel3;
		
		//Startguthaben initialiseren
		int startGuthaben = 100;
	
		//Array für die Würfelaugen
		String[] wuerfelArray = new String[] {"bilder/eins.png", "bilder/zwei.png", "bilder/drei.png","bilder/vier.png","bilder/fuenf.png","bilder/sechs.png"};
		
	/*
	 * Die Methoden	
	*/
		
		//die Methode setzt die Bühne auf den übergebenen Wert
		public void setMeineStage(Stage meineStage) {
			this.meineBuehne = meineStage;
			guthaben.setText(String.valueOf(startGuthaben) + " €");
		}
		
		//Methode zum Beenden
		@FXML protected void beendenKlick(ActionEvent event) {
			Platform.exit();
		}
		
		//Methode für die Software-Info
		@FXML protected void infoKlick(ActionEvent event) {
			Alert info = new Alert(AlertType.INFORMATION, "Von Martin Tastler");
			info.setHeaderText("Glücksspielautomat Version 1.0");
			info.show();
		}
		
		//Die Methode für das Wetten
		@FXML private void wetten() {
			
			/*
			 * Ermitteln einer Zufallszahl für jedes einzelne Würfellabel
			 * Math.random ergibt eine Zufallszahl vom Wert Double zwischen 0.0 und 1.0
			 * diese wird mit der Arraylänge multipliziert und auf einen Integerwert "gekürzt"
			*/
			 
			int zufall1 = (int)(Math.random() * (wuerfelArray.length));
			int zufall2 = (int)(Math.random() * (wuerfelArray.length));
			int zufall3 = (int)(Math.random() * (wuerfelArray.length));
			
			//Hinzufügen des zufälligen Bildes aus dem Würfelarray anhand 
			//der jeweiligen Zufallszahl für jedes Label
			wuerfel1.setGraphic(new ImageView(wuerfelArray[zufall1]));
			wuerfel2.setGraphic(new ImageView(wuerfelArray[zufall2]));
			wuerfel3.setGraphic(new ImageView(wuerfelArray[zufall3]));
			
			//Bedingung dass Guthaben ausreicht
			if (startGuthaben >= 0) {
				//zunächst 10 € Gebühr abziehen
				startGuthaben = startGuthaben -10;
				
				//wenn alle 3 Zufallszahlen in den 3 Labels gleich sind, 
				//also demnach auch alle Würfelaugenpaare gleich sind -> Guthaben um 100 erhöhen
				if (zufall1 == zufall2 && zufall1 == zufall3) {
					Alert info = new Alert(AlertType.INFORMATION, "100 € gewonnen!");
					info.show();
					startGuthaben = startGuthaben + 100;
				}
				//wenn 2 Zufallszahlen in den 3 Labels gleich sind, 
				//also demnach auch 2 Würfelaugenpaare gleich sind -> Guthaben um 20 erhöhen
				else if (zufall1 == zufall2 || zufall2 == zufall3 || zufall1 == zufall3) {
					startGuthaben = startGuthaben + 20;
				}

				//Umwandlung des Guthabens in einen String und Ausgabe in das Label "guthaben"
				guthaben.setText(String.valueOf(startGuthaben) + " €");
				
			} else {
				//Falls das Guthaben 0 erreicht, Meldung dass das Spiel zuende ist.
				Alert info = new Alert(AlertType.INFORMATION, "Ihr Guthaben ist aufgebraucht!\nDas Spiel ist beendet.");
				info.show();
			}
		}
}
