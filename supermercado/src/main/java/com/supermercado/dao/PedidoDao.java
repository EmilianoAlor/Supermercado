package com.supermercado.dao;


//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.supermercado.models.Pedido;
import com.supermercado.conexion.Conexion;

public class PedidoDao {
private String tabla = "pedidos"; 
	
	public Pedido buscarPedido(int codigo) {
		Conexion conex = new Conexion();
		Pedido busqueda = new Pedido();
		boolean existe = false;
		try {

			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM " + tabla + " where IDdelPedido = ? ");
			consulta.setInt(1, codigo);
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				existe = true;
				busqueda.setIDdelPedido(Integer.parseInt(res.getString("IDdelPedido")));
				busqueda.setIDusuario(Integer.parseInt(res.getString("iDusuario")));
				
				busqueda.setFechaYhoraDelPedido(ParseFecha(res.getString("fechaYhoraDelPedido")));;
								
				busqueda.setPrecioTotalDelPedido(Double.parseDouble(res.getString("precioTotalDelPedido")));
				
				if(Integer.parseInt(res.getString("cancelarElPedido"))==1)
					busqueda.setCancelarElPedido(true);	
				else
					busqueda.setCancelarElPedido(false);
			
			}
			res.close();
			conex.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}

		if (existe) {
			return busqueda;
		} else
			return null;
	}
	
	public List<Pedido> ListaPedidos() {
		Conexion conex = new Conexion();
		
		List<Pedido> Pedidos = new ArrayList<>();
		
		boolean existe = false;
		try {

			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM " + tabla);

			ResultSet res = consulta.executeQuery();
			
			while (res.next()) {
				Pedido busqueda = new Pedido();
				existe = true;
				busqueda.setIDdelPedido(Integer.parseInt(res.getString("IDdelPedido")));
				busqueda.setIDusuario(Integer.parseInt(res.getString("iDusuario")));
				
				busqueda.setFechaYhoraDelPedido(ParseFecha(res.getString("fechaYhoraDelPedido")));;
								
				busqueda.setPrecioTotalDelPedido(Double.parseDouble(res.getString("precioTotalDelPedido")));
				
				if(Integer.parseInt(res.getString("cancelarElPedido"))==1)
					busqueda.setCancelarElPedido(true);	
				else
					busqueda.setCancelarElPedido(false);
				
				Pedidos.add(busqueda);
			}
			res.close();
			conex.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}

		if (existe) {
			return Pedidos;
		} else
			return null;
	}
	
	public void eliminarPedido(int id) {
		
		if(buscarPedido(id)==null)
		{
			System.out.println("Error: No se encuentra el pedido a borrar.");
		}
		else
		{
			Conexion conex = new Conexion();
			try {
				Statement estatuto = conex.getConnection().createStatement();
				
				estatuto.executeUpdate("DELETE FROM " + tabla + " WHERE IDdelPedido='" + id + "'");
				
				estatuto.close();
				conex.desconectar();
	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "ERROR: No se Elimino");
			}
		}
	}
	
	public void registrarPedido(Pedido agregar) {
		Conexion conex = new Conexion();

		try {
			Statement estatuto = conex.getConnection().createStatement();
			
			estatuto.executeUpdate("INSERT INTO `" + tabla + "`(`IDusuario`, `PrecioTotalDelPedido`, `CancelarElPedido`) VALUES ('" + agregar.getIDusuario() + "','" + agregar.getPrecioTotalDelPedido() + "'," + agregar.isCancelarElPedido() +")");
			
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR: No se Registro");
		}
	}
	
	public void modificarPedido(int idpedido, Pedido modificar) {

		Conexion conex = new Conexion();

		try {

			
			String consulta = "UPDATE " + tabla + " SET IDusuario= '" + modificar.getIDusuario() + "', PrecioTotalDelPedido ='" + modificar.getPrecioTotalDelPedido() + "', CancelarElPedido =" + modificar.isCancelarElPedido() + " WHERE IDdelPedido = '" + idpedido + "' ";
			
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

			estatuto.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Error: no se pudo modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	/**
     * Permite convertir un String en fecha (Date).
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
	
}
