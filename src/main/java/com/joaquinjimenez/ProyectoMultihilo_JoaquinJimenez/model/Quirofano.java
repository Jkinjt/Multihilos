package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import java.util.List;

public class Quirofano extends Thread{
	List<Soldado> soldados;
	
	
	
	/**
	 * @param soldados
	 */
	public Quirofano(List<Soldado> soldados) {
		super();
		this.soldados = soldados;
	}



	/**
	 * @return the soldados
	 */
	public List<Soldado> getSoldados() {
		return soldados;
	}



	/**
	 * @param soldados the soldados to set
	 */
	public void setSoldados(List<Soldado> soldados) {
		this.soldados = soldados;
	}


	@Override
	public void run() {
		cirujia();
	}

	public void cirujia() {
		for (Soldado soldado : soldados) {
			soldado.start();
			try {
				
				soldado.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
