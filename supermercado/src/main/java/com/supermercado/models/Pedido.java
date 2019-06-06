package com.supermercado.models;

import java.util.Date;


public class Pedido {

	private int IDdelPedido;
	private int IDusuario;
	private Date FechaYhoraDelPedido;
	private double PrecioTotalDelPedido;
	private boolean CancelarElPedido;
	
	
	public int getIDdelPedido() {
		return IDdelPedido;
	}
	public void setIDdelPedido(int iDdelPedido) {
		this.IDdelPedido = iDdelPedido;
		
	//	System.out.println("¿Entra en IDdelPedido?");
		
	}
	public int getIDusuario() {
		return IDusuario;
	}
	public void setIDusuario(int iDusuario) {
		IDusuario = iDusuario;
		
	//	System.out.println("¿Entra en IDusuario?");
		
	}
	public Date getFechaYhoraDelPedido() {
		return FechaYhoraDelPedido;
	}
	public void setFechaYhoraDelPedido(Date fechaYhoraDelPedido) {
		FechaYhoraDelPedido = fechaYhoraDelPedido;
	}
	public double getPrecioTotalDelPedido() {
		return PrecioTotalDelPedido;
	}
	public void setPrecioTotalDelPedido(double precioTotalDelPedido) {
		PrecioTotalDelPedido = precioTotalDelPedido;
	}
	public boolean isCancelarElPedido() {
		return CancelarElPedido;
	}
	public void setCancelarElPedido(boolean cancelarElPedido) {
		CancelarElPedido = cancelarElPedido;
	}
	@Override
	public String toString() {
		return "Pedido [IDdelPedido=" + IDdelPedido + ", IDusuario=" + IDusuario + ", FechaYhoraDelPedido="
				+ FechaYhoraDelPedido + ", PrecioTotalDelPedido=" + PrecioTotalDelPedido + ", CancelarElPedido="
				+ CancelarElPedido + "]";
	}

}
