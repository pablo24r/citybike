package pruebas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import estaciones.IServicioEstaciones;
import estaciones.ISitiosTuristicos;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;
import servicio.FactoriaServicios;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {
		
		IServicioEstaciones servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
		ISitiosTuristicos servicioSitioTuristico = FactoriaServicios.getServicio(ISitiosTuristicos.class);

		String id = servicioEstaciones.darAlta("Catedral de Murcia", 30, "30001", "37", "0",
				"La Santa Iglesia Catedral de Santa María,​ conocida como catedral de Murcia, es el templo principal y sede de la Diócesis de Cartagena. Se encuentra en pleno casco antiguo de la ciudad de Murcia, en la Plaza del Cardenal Belluga.");

		System.out.println("ID de la estación creada: " + id + "\n");
		
		List<SitioTuristico> sitiosCercanos = servicioEstaciones.getSitiosTuristicosCercanos(id);
		
		System.out.println("Sitios turísticos cercanos a la Catedral de Murcia:\n" + sitiosCercanos);
		
		LinkedList<SitioTuristico> lista = new LinkedList<>();
		Collections.addAll(lista, sitiosCercanos.get(4), sitiosCercanos.get(2));
		
		servicioEstaciones.setSitiosTuristicos(id, lista);
		
		System.out.println("\nLista de sitios turisticos asociada a la Catedral de Murcia: \n" + servicioEstaciones.getEstacion(id).getListaSitios());
		
		System.out.println("\nInformación completa de la Estación de Benahadux-Pechina:\n" + servicioSitioTuristico.getInfoSitioDeInteres(sitiosCercanos.get(4).getId()));

	}

}
