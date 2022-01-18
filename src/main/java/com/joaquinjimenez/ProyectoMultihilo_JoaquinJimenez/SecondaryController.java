package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Fabrica;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Quirofano;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Soldado;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController {

    @FXML
    private Slider nSoldier;
    @FXML
	private TableView<Soldado> soldadoTable;
	@FXML
	private TableColumn<Soldado, String> soldadoNumeroColum;
	@FXML
	private TableColumn<Soldado, String> soldadoEstadoColum;
	@FXML
	private TableColumn<Soldado, String> soldadoOperadoColum;
	
	
	private Quirofano quirofano;
	private boolean usado=false;//boleano que establece si los soldados han pasado por quirofano o no;
	
	/**
	 * Método que crea la lista de soldados en función del número que tenga en el slider
	 */
	public void quirofano() {
		int aux=(int) nSoldier.getValue();
		List<Soldado> soldados=new ArrayList<>();
		for(int i=0;i<aux;i++) {
			soldados.add(new Soldado("Soldado: "+(i+1)));
			}
		quirofano=new Quirofano(soldados);
		setSoldados();
		
	}
	
	public void setSoldados() {
		usado=false;
		soldadoTable.setItems(FXCollections.observableArrayList(quirofano.getSoldados()));
		soldadoNumeroColum.setCellValueFactory(eachSoldado->{
			SimpleStringProperty result=new SimpleStringProperty(eachSoldado.getValue().getNombre());
			return result;
		});
		soldadoEstadoColum.setCellValueFactory(eachSoldado->{
			return eachSoldado.getValue().getEstadoProperty();
		});
		soldadoOperadoColum.setCellValueFactory(eachSoldado->{
			return eachSoldado.getValue().getOperadoProperty();
		});
	}
	
	/**
	 * Método que cura a los soldados
	 */
	public void curar() {
		if(!usado) {
			
			quirofano.start();
			usado=true;
		}else {
			mostrarAlerta("Los soldados ya han pasado por quirofano, envia soldados nuevos");
		}
	}
	
	/*
	 * Muestra una alerta cuando se produce un error
	 */
	private void mostrarAlerta(String error) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.setContentText(error);
		alert.showAndWait();
	}
    
	
}