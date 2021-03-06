package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Almacen;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Bombardero;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Fabrica;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Frente;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

	@FXML
	private Text balasAlmacen;
	@FXML
	private Button fabricasButton;
	@FXML
	private Slider nFabricas;
	@FXML
	private Slider nFrentes;
	@FXML
	private TableView<Fabrica> fabricaTable;
	@FXML
	private TableColumn<Fabrica, String> fabricaNumeroColum;
	@FXML
	private TableColumn<Fabrica, String> fabricaBalasAProducirColum;
	@FXML
	private TableColumn<Fabrica, String> fabricaBalasProducidasColum;
	@FXML
	private TableView<Frente> frenteTable;
	@FXML
	private TableColumn<Frente, String> frenteNumeroColum;
	@FXML
	private TableColumn<Frente, String> frenteBalasColum;
	@FXML
	private TableColumn<Frente, String> frenteGastadasColum;	
	@FXML
	private TableView<Bombardero> bombarderoTable;
	@FXML
	private TableColumn<Bombardero, String> bombarderoFrenteColum;
	@FXML
	private TableColumn<Bombardero, String> bajasColum;
	
	private Almacen almacen;
	private List<Fabrica> fabricas;
	private List<Frente> frentes;
	private Bombardero bombardero;
	private SimpleStringProperty nbalas;

	@FXML
	private void initialize() {
		almacen = new Almacen();
		bombardero=new Bombardero();
		balasAlmacen.setText(almacen.getNbalas());
		almacen.nBalasProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				balasAlmacen.setText(almacen.getNbalas());

			}

		});
		nFabricas.setShowTickMarks(true);
		nFabricas.setShowTickLabels(true);

	}
	/*
	 * M??todo que inicia la producci??n de los hilos de las f??bricas en funcion
	 * de el numero introducido en el slider nF??bricas
	 * 
	 * */
	public void iniciarFabricas() {
		int aux = (int) nFabricas.getValue();
		fabricas=new ArrayList<>();
		try {
			int produccion = almacen.balasNecesarias() / aux;
			for (int i = 0; i < aux; i++) {
				fabricas.add(new Fabrica(almacen, produccion, i + 1));
				fabricas.get(i).start();
			}

		} catch (Exception e) {
			mostrarAlerta("Fallo al iniciar las fabricas");
		}
		setFabricaTable();
	}
	/**
	 * M??todo que crea un n??mero determinado de hilos de la clase f??brica 
	 * en funci??n de de lo especificado por el usuario
	 */
	public void iniciarFrentes() {
		int aux=(int) nFrentes.getValue();
		
		frentes=new ArrayList<>();
		
		for (int i=0; i<aux;i++) {
			frentes.add(new Frente(10*i, almacen,""+( i+1),bombardero));
			frentes.get(i).start();
		}
		setFrenteTable();
		setBombarderoTable();
	}
	
	public void irQuirofano() {
		newModal("secondary.fxml");
	}
	
	/**
	 * M??todo que muestra la informaci??n de las fabricas en una tabla
	 */
	public void setFabricaTable() {
		fabricaTable.setItems(FXCollections.observableArrayList(fabricas));
		fabricaNumeroColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=new SimpleStringProperty();
			result.setValue("F??brica "+eachFabrica.getValue().getnFabrica());
			return result;
		});
		fabricaBalasAProducirColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=new SimpleStringProperty();
			result.setValue(eachFabrica.getValue().getProduccion()+"");
			return result;
		});
		fabricaBalasProducidasColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=eachFabrica.getValue().nProducidasProperty();
			return result;
		});
		
	}
	
	/**
	 * M??todo que muestra la informaci??n de los frentes
	 */
	public void setFrenteTable() {
		frenteTable.setItems(FXCollections.observableArrayList(frentes));
		frenteNumeroColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=new SimpleStringProperty();
			result.setValue("F??brica "+eachFabrica.getValue().getNombre());
			return result;
		});
		frenteBalasColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=eachFabrica.getValue().getBalasProperty();
			
			return result;
		});
		frenteGastadasColum.setCellValueFactory(eachFabrica->{
			SimpleStringProperty result=eachFabrica.getValue().getGastadasProperty();
			return result;
		});
		
	}
	
	/**
	 * M??todo que muestra a quien esta apoyando el bombardero
	 */
	public void setBombarderoTable() {
		bombarderoTable.setItems(FXCollections.observableArrayList(bombardero));
		bombarderoFrenteColum.setCellValueFactory(eachBombardero->{
			return eachBombardero.getValue().getFabrica();
		});
		bajasColum.setCellValueFactory(eachBombardero->{
			return eachBombardero.getValue().getBajas();
		});
	}

	/**
	 * M??todo que sirve para abrir una nueva pantalla
	 * @param root
	 */
	public void newModal(String root) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(root));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			//este metodo se ejecuta una vez se vuelve a la pantalla
			modalStage.show();			
			modalStage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
