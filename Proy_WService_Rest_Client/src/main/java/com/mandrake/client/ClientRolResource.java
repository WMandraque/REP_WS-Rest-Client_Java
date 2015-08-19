package com.mandrake.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import com.mandrake.model.RolDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import util.MessageSystem;

/**
 * 
 * Esta clase permite consumir los recursos de un servicio WEB REST.
 *
 */

public class ClientRolResource 
{
	//Configuración para un cliente WS (acepta JSON)
	private ClientConfig wsConfig;
	
	//Es el cliente que va a consumir los WS.
	private Client wsClient;
	
	//Permite invocar (HTTP) a una operación
	private WebResource wsCall;
	
	/* A través de esta URL accederemos al recurso "Rol"*/
	private final String urlBase = "http://localhost:8080/Proy_WService_Rest_Serv/rest/Rol/";
	
	
	//Constructor para inicualizar La configuracion
	public ClientRolResource()
	{
		//Creo la configuración para aceptar JSON
		wsConfig=new DefaultClientConfig();
		wsConfig.getClasses().add(JacksonJsonProvider.class);
		
		//Creo el cliente utilizando la configuración
		wsClient = Client.create(wsConfig);
				
		//Creo el componente que hace las llamadas
		wsCall = wsClient.resource(urlBase);
	}
	
	public List<RolDTO> selectAll()
	{
		/**
		 * path: la uri a cual queremos acceder
		 * accept: Que tipo de dato vamosa  recibir
		 * get:    Cuando es lista, generamos un GenericType
		 */
		
		return wsCall.path("/").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<RolDTO>>(){});
	}
	
	public RolDTO select(Integer _idRol)
	{
		/**
		 * path: la uri a cual queremos acceder
		 * accept: El tipo de datos que queremos acceptar
		 * get: Que tipo de clase es
		 */
		return wsCall.path(""+_idRol).accept(MediaType.APPLICATION_JSON).get(RolDTO.class);
	}
	
	public Integer insert(RolDTO _rolDTO)
	{
		Integer r=0;
		try 
		{
			/**
			 * path: La uri a cual queremosa acceder
			 * type: El tipo de dato que deseamos enviar
			 * pot; clase a enviar, objeto a enviar
			 */
			wsCall.path("/Insert").type(MediaType.APPLICATION_JSON).post(RolDTO.class, _rolDTO);
			r=1;
		} 
		catch (Exception e) 
		{
			MessageSystem.messageError(new Object() {}.getClass(), e, "");
		} 
		
		return r;
	}
	
	public Integer delete(Integer _idRol)
	{
		Integer r=wsCall.path("/Delete/"+_idRol).accept(MediaType.APPLICATION_JSON).get(Integer.class);
		return r;
	}

}
