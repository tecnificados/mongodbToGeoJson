package org.tecnificados;

import java.util.ArrayList;

public class Municipio {

	private String nombre;
	private String natCode;
	private String geometry;
	private Integer idMunicipio;
	private Integer idAutonomia;
	private Integer idProvincia;
	private Integer idPais;
	private double lat;
	private double lon;
	private String nombreNormalizado;
	private ArrayList<Integer> limit;
	private Integer poblacion;
	
	
	
	
	public int getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNatCode() {
		return natCode;
	}
	public void setNatCode(String natCode) {
		this.natCode = natCode;
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public Integer getIdAutonomia() {
		return idAutonomia;
	}
	public void setIdAutonomia(Integer idAutonomia) {
		this.idAutonomia = idAutonomia;
	}
	public Integer getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getNombreNormalizado() {
		return nombreNormalizado;
	}
	public void setNombreNormalizado(String nombreNormalizado) {
		this.nombreNormalizado = nombreNormalizado;
	}
	public ArrayList<Integer> getLimit() {
		return limit;
	}
	public void setLimit(ArrayList<Integer> string) {
		this.limit = string;
	}
	
	
	
}
