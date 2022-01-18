package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import javafx.beans.property.SimpleStringProperty;

public class Fabrica extends Thread{
	private Almacen almacen;
	private int produccion;
	private int nFabrica;
	private SimpleStringProperty nProducidas;

	/**
	 * @param almacen
	 */
	public Fabrica(Almacen almacen, int produccion, int nFabrica) {
		this.produccion=produccion;
		this.almacen = almacen;
		this.nFabrica=nFabrica;
		this.nProducidas=new SimpleStringProperty(""+0);
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
	 * @return the produccion
	 */
	public int getProduccion() {
		return produccion;
	}



	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(int produccion) {
		this.produccion = produccion;
	}



	/**
	 * @return the nFabrica
	 */
	public int getnFabrica() {
		return nFabrica;
	}



	/**
	 * @param nFabrica the nFabrica to set
	 */
	public void setnFabrica(int nFabrica) {
		this.nFabrica = nFabrica;
	}



	/**
	 * @return the nProducidas
	 */
	public String getnProducidas() {
		return nProducidas.get();
	}



	/**
	 * @param nProducidas the nProducidas to set
	 */
	public void setnProducidas(String nProducidas) {
		this.nProducidas.set(nProducidas);
	}
	
	/**
	 * @return nProducidas SimpleStringProperty
	 */
	public SimpleStringProperty nProducidasProperty() {
		return nProducidas;
	}



	@Override
	public void run() {
		int balasProducidas=0;
		while (balasProducidas<this.produccion) {	
			balasProducidas++;
			if(balasProducidas%10==0) {
				
				almacen.annadeBalas(10);
				this.nProducidas.set(""+balasProducidas);
			}
			
		}
		try {
			int espera=(int)Math.random()*(10-1)+1;
			Thread.sleep(1000*espera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nProducidas.set(""+balasProducidas);
		
	}
	
	

}
