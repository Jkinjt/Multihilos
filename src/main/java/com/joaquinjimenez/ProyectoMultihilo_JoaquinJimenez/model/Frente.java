package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import javafx.beans.property.SimpleStringProperty;

public class Frente extends Thread {
	private String nombre;
	private int balas;
	private Almacen almacen;
	private Bombardero bombardero;
	private boolean batalla = true;

	private SimpleStringProperty balasProperty;
	private SimpleStringProperty gastadasProperty;

	/**
	 * @param balas
	 */
	public Frente(int balas, Almacen almacen, String nombre, Bombardero bombardero) {
		super();
		this.nombre = nombre;
		this.balas = balas;
		this.almacen = almacen;
		this.bombardero = bombardero;
		balasProperty = new SimpleStringProperty("" + balas);
		gastadasProperty = new SimpleStringProperty("" + 0);
	}

	/**
	 * @return the balas
	 */
	public int getBalas() {
		return balas;
	}

	/**
	 * @param balas the balas to set
	 */
	public void setBalas(int balas) {
		this.balas = balas;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the almacen
	 */
	public Almacen getAlmacen() {
		return almacen;
	}

	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	/**
	 * @return the batalla
	 */
	public boolean isBatalla() {
		return batalla;
	}

	/**
	 * @param batalla the batalla to set
	 */
	public void setBatalla(boolean batalla) {
		this.batalla = batalla;
	}

	/**
	 * @return the bombardero
	 */
	public Bombardero getBombardero() {
		return bombardero;
	}

	/**
	 * @param bombardero the bombardero to set
	 */
	public void setBombardero(Bombardero bombardero) {
		this.bombardero = bombardero;
	}

	/**
	 * @return the balasProperty
	 */
	public SimpleStringProperty getBalasProperty() {
		return balasProperty;
	}

	/**
	 * @param balasProperty the balasProperty to set
	 */
	public void setBalasProperty(SimpleStringProperty balasProperty) {
		this.balasProperty = balasProperty;
	}

	/**
	 * @return the gastadasProperty
	 */
	public SimpleStringProperty getGastadasProperty() {
		return gastadasProperty;
	}

	/**
	 * @param gastadasProperty the gastadasProperty to set
	 */
	public void setGastadasProperty(SimpleStringProperty gastadasProperty) {
		this.gastadasProperty = gastadasProperty;
	}

	@Override
	public void run() {
		operacion();

	}
	/**
	 * Método que simula una batalla en el frente
	 */
	public void operacion() {
		while (batalla) {
			if (balas > 0) {
				double balasDisparadas = Math.random() * (11 - 1) + 1;
				balas -= (int) balasDisparadas;
				this.gastadasProperty.set("" + (int) balasDisparadas);
			} else {
			}
			balas += almacen.sacaBalas((int) (Math.random() * (11 - 1) + 1));
			bombardero.bombardeo(this);
			this.balasProperty.set("" + balas);

		}

	}

	/*
	 * Método que para la battalla en este frente
	 */

	public void armisticio() {
		batalla = false;
	}

	/*
	 * Método que reinicia la batalla en este frente
	 */
	public void ataque() {
		batalla = true;
	}

}
