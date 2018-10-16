package mx.ine.sif.soapws.adminusuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HelperAUS {
	public static Map<String,Set<String>> ListaAtributosToMap(List<Atributo> atributos){
		Map<String, Set<String>> map=new TreeMap<String, Set<String>>(String.CASE_INSENSITIVE_ORDER);
		if (atributos!=null)
		for(Atributo attr: atributos){
			String nombre=attr.getNombre();
			if(nombre!=null){
				if(map.containsKey(nombre)){
					map.get(attr.getNombre()).addAll(attr.getValores());
				}else{
		
					Set<String> set= new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
					set.addAll(attr.getValores());
					map.put(attr.getNombre(),set );
				}
			}
		}
		return map;
	}
	
	public static List<Atributo>  MapToListaAtributos( Map<String,Set<String>> map){
		List<Atributo> lista=new ArrayList<Atributo>();
		if(map!=null){
			for(String key:map.keySet()){
				Atributo attr=new Atributo();
				attr.setNombre(key);
				attr.getValores().addAll(map.get(key));
				lista.add(attr);
			}
		}
		
		return lista;
	}
}
