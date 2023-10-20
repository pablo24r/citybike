package repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioMemoria<T extends Identificable> implements RepositorioString<T> {

	private HashMap<String, T> entidades = new HashMap<>();
	
	@Override
	public String add(T entity) {
		

		this.entidades.put(entity.getId(), entity);		
		
		return entity.getId();
	}

	@Override
	public void update(T entity) throws EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.put(entity.getId(), entity);
	}

	@Override
	public void delete(T entity) throws EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.remove(entity.getId());
	}

	@Override
	public T getById(String id) throws EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(id))
			throw new EntidadNoEncontrada(id + " no existe en el repositorio");
		
		return this.entidades.get(id);
	}

	@Override
	public List<T> getAll() {
		
		return new ArrayList<>(this.entidades.values());
	}

	@Override
	public List<String> getIds() {
		
		return new ArrayList<>(this.entidades.keySet());
	}

}
