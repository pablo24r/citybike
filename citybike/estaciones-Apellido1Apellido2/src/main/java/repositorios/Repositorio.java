package repositorios;

import java.util.List;

public interface Repositorio<T, K> {

	K add(T entity);

	void update(T entity);

	void delete(T entity);

	T getById(K id);

	List<T> getAll();

	List<K> getIds();
}
