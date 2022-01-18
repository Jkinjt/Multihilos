package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Almacen {
	private final int LIMITEBALAS=1000;
	private int balas=0;
	private boolean descargando=true;
	private SimpleStringProperty nbalas;
	
	/**
	 * 
	 */
	public Almacen() {
		nbalas=new SimpleStringProperty(""+balas);
		
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
	 * @return the nbalas
	 */
	public String getNbalas() {
		return nbalas.get();
	}
	
	public SimpleStringProperty nBalasProperty() {
		return nbalas;
	}
	



	/**
	 * @param nbalas the nbalas to set
	 */
	public void setNbalas(String nbalas) {
		this.nbalas.set(nbalas);
	}
	
	
	



	/**
	 * Método queñade balas al almacen
	 * @param balas
	 */
	public synchronized void annadeBalas(int balas) {
		while(!descargando) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.balas+=balas;
		nbalas.set(this.balas+"");
		System.out.printf("\nEn el almacen hay %d",getBalas());
		descargando=false;
		notifyAll();
		
		
	}
	
	/**
	 * Método que extrae las balas del almacen
	 * @param balas
	 * @return balas
	 */
	public  synchronized int sacaBalas(int balas) {
		while(descargando) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(balas>0) {
			if(this.balas>=balas) {			
				this.balas-=balas;
				nbalas.set(this.balas+"");
			}else if(this.balas>0) {
				int sobrante=this.balas-balas;
				balas-=sobrante;
				this.balas=0;
				nbalas.set(this.balas+"");
			}else if(this.balas==0){
				balas=0;
				this.balas=0;
				nbalas.set(this.balas+"");
			}
			try {
				int espera=(int)Math.random()*(10-1)+1;
				Thread.sleep(1000*espera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("balas sacadas "+ balas);
			System.out.println("En el almacen quedan "+getBalas()+" balas");
			
			
		}
		
		descargando=true;
		notifyAll();
		return balas;
	}
	
	/**
	 * Método para saber cuantas balas son necesarias para completar el almacén
	 * @return
	 */
	public int balasNecesarias() {
		return LIMITEBALAS-balas;
		
	}
	
	



	

}
