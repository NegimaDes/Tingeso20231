package tintin.tingeso2023.Services;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tintin.tingeso2023.Entities.proveedorEntity;
import tintin.tingeso2023.Repositories.proveedorRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class proveedorService {

    @Autowired
    proveedorRepository repo;

    //Guardado de un nuevo proveedor al sistema
    public proveedorEntity save(proveedorEntity new_prov){return repo.save(new_prov);}

    //elimina un proveedor (solo para deshacer test)
    @Generated
    public proveedorEntity delete(Integer codigo){
        proveedorEntity buscado = repo.findById(codigo).orElse(null);
        if(buscado != null){
            repo.deleteById(codigo);
        }
        return buscado;
    }

    //revisa si el codigo es posible
    public boolean codigo_correcto(Integer codigo){
        proveedorEntity buscado = repo.findById(codigo).orElse(null);
        return buscado == null;
    }

    //busca el siguiente codigo aceptable para un proveedor dentro de la misma region
    public Integer next_code(Integer codigo){
        Iterable<proveedorEntity> all = repo.findAll();
        List<proveedorEntity> aceptables = new LinkedList<>();
        for(proveedorEntity ac: all){
            if(ac.getCodigo() - (ac.getCodigo()%1000) == codigo - (codigo%1000)){
                aceptables.add(ac);
            }
        }
        Integer code = 0;
        for(proveedorEntity ac: aceptables){
            if(ac.getCodigo() > code){
                code = ac.getCodigo();
            }
        }
        return code+1;
    }

    @Generated
    public Iterable<proveedorEntity> listar_proveedores(){
        return repo.findAll();
    }

    @Generated
    public List<proveedorEntity> listar_proveedores_region(Integer region){
        Iterable<proveedorEntity> all = repo.findAll();
        List<proveedorEntity> aceptados = new LinkedList<>();
        for(proveedorEntity ac:all){
            if(ac.getCodigo()/1000 == region){
                aceptados.add(ac);
            }
        }
        return aceptados;
    }

    public proveedorEntity obtener_proveedor(Integer codigo){
        return repo.findById(codigo).orElse(null);
    }
}
