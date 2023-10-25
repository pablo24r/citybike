package repositorios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;


public abstract class RepositorioJSON<T extends Identificable> implements RepositorioString<T> {

    public final static String DIRECTORIO = "repositorio-json/";

    static {

        File directorio = new File(DIRECTORIO);

        if (!directorio.exists()) {
            directorio.mkdir();
        }
    }

    // Método abstracto que se delega a los repositorios específicos
    public abstract Class<T> getClase();

    /*** Métodos de apoyo ***/

    protected String getDocumento(String id) {
        return DIRECTORIO + getClase().getSimpleName() + "-" + id + ".json";
    }

    protected boolean checkDocumento(String id) {
        final String documento = getDocumento(id);
        File fichero = new File(documento);
        return fichero.exists();
    }

    protected void save(T entity) throws RepositorioException {
        final String documento = getDocumento(entity.getId());

        try {
            JsonbConfig config = new JsonbConfig()
                    .withNullValues(true)
                    .withFormatting(true)
                    .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
                    .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);

            Jsonb jsonb = JsonbBuilder.create(config);
            String jsonString = jsonb.toJson(entity);
            try (FileOutputStream fos = new FileOutputStream(documento)) {
                fos.write(jsonString.getBytes());
            }
        } catch (Exception e) {
            throw new RepositorioException("Error al guardar la entidad con id: " + entity.getId(), e);
        }
    }

    protected T load(String id) throws RepositorioException, EntidadNoEncontrada {
        if (!checkDocumento(id)) {
            throw new EntidadNoEncontrada("La entidad no existe, id: " + id);
        }
        final String documento = getDocumento(id);

        try {
            JsonbConfig config = new JsonbConfig()
                    .withNullValues(true)
                    .withFormatting(true)
                    .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
                    .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);

            Jsonb jsonb = JsonbBuilder.create(config);
            Reader reader = new FileReader(documento);
            return jsonb.fromJson(reader, getClase());
        } catch (Exception e) {
            throw new RepositorioException("Error al cargar la entidad con id: " + id, e);
        }
    }

    /*** Fin métodos de apoyo ***/

    @Override
    public String add(T entidad) throws RepositorioException {
        String id = UUID.randomUUID().toString();
        entidad.setId(id);
        save(entidad);
        return id;
    }

    @Override
    public void update(T entidad) throws RepositorioException, EntidadNoEncontrada {
        if (!checkDocumento(entidad.getId())) {
            throw new EntidadNoEncontrada("La entidad no existe, id: " + entidad.getId());
        }
        save(entidad);
    }

    @Override
    public void delete(T entidad) throws EntidadNoEncontrada {
        if (!checkDocumento(entidad.getId())) {
            throw new EntidadNoEncontrada("La entidad no existe, id: " + entidad.getId());
        }
        final String documento = getDocumento(entidad.getId());
        File fichero = new File(documento);
        fichero.delete();
    }

    @Override
    public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
        return load(id);
    }

    @Override
    public List<String> getIds() {
        LinkedList<String> resultado = new LinkedList<>();
        File directorio = new File(DIRECTORIO);
        File[] entidades = directorio.listFiles(f -> f.isFile() && f.getName().endsWith(".json"));
        final String prefijo = getClase().getSimpleName() + "-";
        for (File file : entidades) {
            String id = file.getName().substring(prefijo.length(), file.getName().length() - 5); 
            resultado.add(id);
        }
        return resultado;
    }

    @Override
    public List<T> getAll() throws RepositorioException {
        LinkedList<T> resultado = new LinkedList<T>();
        for (String id : getIds()) {
            try {
                resultado.add(load(id));
            } catch (EntidadNoEncontrada e) {
                // no debería suceder
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
