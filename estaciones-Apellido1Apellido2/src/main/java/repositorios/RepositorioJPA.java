package repositorios;

import java.util.List;

public abstract class RepositorioJPA <T extends Identificable> implements RepositorioString<T>{

	// Método abstracto que se delega a los repositorios específicos
    public abstract Class<T> getClase();
	
	@Override
	public String add(T entity) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
