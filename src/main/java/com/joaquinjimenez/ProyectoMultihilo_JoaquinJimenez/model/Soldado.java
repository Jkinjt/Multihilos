package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model;

import javafx.beans.property.SimpleStringProperty;

public class Soldado extends Thread{
	private String nombre;
	private String estado;
	private SimpleStringProperty estadoProperty;	
	private boolean operado=false;
	private SimpleStringProperty operadoProperty;

	/**
	 * @param nombre
	 */
	public Soldado(String nombre) {
		super();
		this.nombre = nombre;
		this.estado="herido";
		this.estadoProperty=new SimpleStringProperty(estado);
		
		this.operadoProperty=new SimpleStringProperty(""+operado);
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
	 * Método que decide que determina si el soldado se ha curado o no
	 * @return
	 */
	public boolean curacion() {
		boolean result=false;
		
		
		return result;
	}
	
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the estadoProperty
	 */
	public SimpleStringProperty getEstadoProperty() {
		return estadoProperty;
	}

	/**
	 * @param estadoProperty the estadoProperty to set
	 */
	public void setEstadoProperty(SimpleStringProperty estadoProperty) {
		this.estadoProperty = estadoProperty;
	}
	
	

	/**
	 * @return the operado
	 */
	public boolean isOperado() {
		return operado;
	}

	/**
	 * @param operado the operado to set
	 */
	public void setOperado(boolean operado) {
		this.operado = operado;
	}

	/**
	 * @return the operadoProperty
	 */
	public SimpleStringProperty getOperadoProperty() {
		return operadoProperty;
	}

	/**
	 * @param operadoProperty the operadoProperty to set
	 */
	public void setOperadoProperty(SimpleStringProperty operadoProperty) {
		this.operadoProperty = operadoProperty;
	}

	@Override
	public void run() {	
		int aux=2;
		for(int i=0;i<3;i++) {
			double aux2=Math.random()*(4-1)+1;
			aux=(int)aux2;
			switch (aux) {
			case 1://se cura
				this.estado="curado";
				this.estadoProperty.set(this.estado);
				i=3;
				break;
			case 2://sigue enfermo
				if(i==2) {
					this.estado="muerto";
					this.estadoProperty.set(this.estado);
				}
				break;
			case 3://muere
				i=3;
				this.estado="muerto";
				this.estadoProperty.set(this.estado);
				break;
			default:
				break;
			}
			operado=true;
			this.operadoProperty.set(""+operado);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
