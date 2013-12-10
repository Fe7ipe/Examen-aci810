package com.example.aci570_db.model;

import java.io.Serializable;

public class Mensaje implements Serializable {

	public static final long serialVersionUID = 7526472295622774543L;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreMensaje() {
		return nombreMensaje;
	}
	public void setNombreMensaje(String nombreMensaje) {
		this.nombreMensaje = nombreMensaje;
	}
	private long id;
	private String nombreMensaje;
}
