package com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Almacen;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Bombardero;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Fabrica;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Frente;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Quirofano;
import com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez.model.Soldado;

public class Ejecutable {

	public static void main(String[] args) {
		
		testQuirofano();
		
		
		

	}
	static void testFabrica() {
		Almacen a=new Almacen();
		Fabrica[] fabricas;
		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		System.out.println(a.getBalas());
		do {
			try {
				System.out.println("¿Cuantas fabricas quieres?");
				int aux=sc.nextInt();
				fabricas=new Fabrica[aux];
				int produccion=a.balasNecesarias()/aux;
				for (int i=0; i<aux;i++) {
					fabricas[i]=new Fabrica(a, produccion, i+1);
					fabricas[i].start();
				}
				flag=false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}while(flag);
		
		System.out.println(""+a.getBalas());
	}
	
	static void testQuirofano() {
		List<Soldado> soldados=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		do {
			try {
				System.out.println("¿Cuantos soldados estan heridos?");
				int aux=sc.nextInt();
				
				for (int i=0; i<aux;i++) {
					soldados.add(new Soldado(""+(i+1)));
				}
				Quirofano q=new Quirofano(soldados);
				q.cirujia();
				flag=false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}while(flag);
	}
	static void testFrente() {
		Bombardero b=new Bombardero();
		Almacen a=new Almacen();
		Fabrica[] fabricas;
		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		System.out.println(a.getBalas());
		do {
			try {
				System.out.println("¿Cuantas fabricas quieres?");
				int aux=sc.nextInt();
				fabricas=new Fabrica[aux];
				
				int produccion=a.balasNecesarias()/aux;
				for (int i=0; i<aux;i++) {
					fabricas[i]=new Fabrica(a, produccion, i+1);
					fabricas[i].start();
				}
				flag=false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}while(flag);
		Frente[] frente;		
		System.out.println(a.getBalas());
		do {
			try {
				System.out.println("¿Cuantas frentes quieres?");
				int aux=sc.nextInt();
				frente=new Frente[aux];
				int produccion=a.balasNecesarias()/aux;
				for (int i=0; i<aux;i++) {
					frente[i]=new Frente(10*i, a,""+( i+1),b);
					frente[i].start();
				}
				flag=false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}while(flag);
		
		
		
	}

}
