package com.supermercado.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.supermercado.conexion.Conexion;
import com.supermercado.dao.PedidoDao;
import com.supermercado.models.Pedido;

@RestController //suficiente para q se publique en el Spring

@RequestMapping("api/v1/pedido")
public class PedidoController {

	// metodo de prueba
		@GetMapping
		public List<Pedido> lista() {

			PedidoDao PD = new PedidoDao();
			
			return PD.ListaPedidos();
			
		}
		
@PostMapping("/agregar")
@ResponseStatus(HttpStatus.OK)
public void create(@RequestBody Pedido nuevo) {
	PedidoDao PD = new PedidoDao();
	
	PD.registrarPedido(nuevo);
	
}

// EJEMPLOS DE JSON PARA AGREGAR 
//{
//"iddelPedido": 0,
//"idusuario": 6,
//"fechaYhoraDelPedido": null,
//"cancelarElPedido": true,
//"precioTotalDelPedido": 2000.55
//}
//
//{
//    "idusuario": 9,
//    "cancelarElPedido": true,
//    "precioTotalDelPedido": 9999.66
//}

@PostMapping("/modificar/{id}")
@ResponseStatus(HttpStatus.OK)
public void modificar(@PathVariable("id") int id,@RequestBody Pedido modificar) {
	PedidoDao PD = new PedidoDao();
		
	PD.modificarPedido(id,modificar);
	
}

//url : localhost:8080/api/v1/pedido/2
@GetMapping("/{id}")
public Pedido get(@PathVariable("id") int id) {
	// si exite se envia al navegador y si no existe retornamos Null
	PedidoDao PD = new PedidoDao();
	
	return PD.buscarPedido(id);
}

//	localhost:8080/api/v1/pedido/borrar/2
@GetMapping("/borrar/{id}")
public void delete(@PathVariable int id) {
	
	PedidoDao PD = new PedidoDao();
	
	PD.eliminarPedido(id);
}

}
