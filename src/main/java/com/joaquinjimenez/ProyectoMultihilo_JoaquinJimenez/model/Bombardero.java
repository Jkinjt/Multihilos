package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import javafx.beans.property.SimpleStringProperty;

public class Bombardero {
	private boolean mision=false;
	private SimpleStringProperty fabrica;
	private SimpleStringProperty bajas;
	
	
	
	/**
	 * @param mision
	 */
	public Bombardero() {
		super();
		this.fabrica=new SimpleStringProperty();
		this.bajas=new SimpleStringProperty();
	}

	

	/**
	 * @return the mision
	 */
	public boolean isMision() {
		return mision;
	}



	/**
	 * @param mision the mision to set
	 */
	public void setMision(boolean mision) {
		this.mision = mision;
	}



	/**
	 * @return the fabrica
	 */
	public SimpleStringProperty getFabrica() {
		return fabrica;
	}



	/**
	 * @param fabrica the fabrica to set
	 */
	public void setFabrica(SimpleStringProperty fabrica) {
		this.fabrica = fabrica;
	}



	/**
	 * @return the bajas
	 */
	public SimpleStringProperty getBajas() {
		return bajas;
	}



	/**
	 * @param bajas the bajas to set
	 */
	public void setBajas(SimpleStringProperty bajas) {
		this.bajas = bajas;
	}



	/**
	 * Método que se usa para simular el bombardeo
	 * @param frente
	 */
	public synchronized void bombardeo(Frente frente) {
			this.fabrica.set(frente.getNombre());
				double muertes=	Math.random()*(21-1)+1;
					int bajas=(int)muertes;
			this.bajas.set(""+bajas);
			try {
				Thread.sleep(100*bajas);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

}
