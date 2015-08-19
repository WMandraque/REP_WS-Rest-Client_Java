package com.mandrake.lanzador;


import java.util.List;

import com.mandrake.client.ClientRolResource;
import com.mandrake.model.RolDTO;

public class Lanzador 
{
	ClientRolResource servRol=new ClientRolResource();

	
	public static void main(String arg[])
	{
	
		RolDTO newRolDTO=new RolDTO();
		newRolDTO.setIdRol(96);
		newRolDTO.setRol("Designer");
		
		new Lanzador().testInsert(newRolDTO);
	}
	
	
	
	public void testSelectAll()
	{
		System.out.println("----------------- Listado de Roles -------------------");
		
		List<RolDTO> Roles=servRol.selectAll();
		
		System.out.println("Tamaño de Lista: "+Roles.size()+"\n");
		
		for (int i = 0; i < Roles.size(); i++) 
		{
			System.out.println("  "+Roles.get(i).getIdRol()+" - "+Roles.get(i).getRol());
		}
		
		System.out.println("------------------------------------------------------"+"\n");
	}
	
	public void testSelect(Integer _idRol)
	{

		System.out.println("------------------- Buscando Rol ----------------------");
		System.out.println("Buscando por id "+_idRol+": ");
		RolDTO rolDTO=servRol.select(_idRol);
		System.out.println("  "+rolDTO.getIdRol()+" - "+rolDTO.getRol());
		System.out.println("------------------------------------------------------"+"\n");
	}
	
	public void testDelete(Integer _idRol)
	{
		System.out.println("----------------- Eliminando Rol ----------------------");
		System.out.println("Eliminando por id "+_idRol+": ");
		Integer r=servRol.delete(_idRol);
		System.out.println(r);
		System.out.println("-------------------------------------------------------"+"\n");
	}
	
	public void testInsert(RolDTO _newRolDTO)
	{	
		servRol.insert(_newRolDTO);
	}

}
