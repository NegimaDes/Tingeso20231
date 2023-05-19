package tintin.tingeso2023.Services;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tintin.tingeso2023.Entities.ProveedorEntity;
import tintin.tingeso2023.Repositories.ProveedorRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository repo;

    //Guardado de un nuevo proveedor al sistema
    public ProveedorEntity save(ProveedorEntity new_prov){return repo.save(new_prov);}

    public String manejarDatos(ProveedorEntity new_prov){
        String mensaje = "";
        if(codigoCorrecto(new_prov.getCodigo())){
            save(new_prov);
            mensaje = "proveedor guardado";
        }else{
            mensaje = "Siguiente codigo posible para la region es " + nextCode(new_prov.getCodigo());
        }
        return mensaje;
    }

    //elimina un proveedor (solo para deshacer test)
    @Generated
    public ProveedorEntity delete(Integer codigo){
        ProveedorEntity buscado = repo.findById(codigo).orElse(null);
        if(buscado != null){
            repo.deleteById(codigo);
        }
        return buscado;
    }

    //revisa si el codigo es posible
    public boolean codigoCorrecto(Integer codigo){
        ProveedorEntity buscado = repo.findById(codigo).orElse(null);
        return buscado == null;
    }

    //busca el siguiente codigo aceptable para un proveedor dentro de la misma region
    public Integer nextCode(Integer codigo){
        Iterable<ProveedorEntity> all = repo.findAll();
        Integer code = 0;
        for(ProveedorEntity ac: all){
            if(ac.getCodigo() - (ac.getCodigo()%1000) == codigo - (codigo%1000)){
                code = max(ac.getCodigo(), code);
            }
        }
        if(code != 0)
            return code + 1;
        return codigo - (codigo%1000) +1;
    }

    Integer max(Integer num1, Integer num2){
        if(num1 > num2)
            return num1;
        return num2;
    }

    @Generated
    public Iterable<ProveedorEntity> listarProveedores(){
        return repo.findAll();
    }
    @Generated
    public List<ProveedorEntity> listarProveedoresRegion(Integer region){
        Iterable<ProveedorEntity> all = repo.findAll();
        List<ProveedorEntity> aceptados = new LinkedList<>();
        for(ProveedorEntity ac:all){
            if(ac.getCodigo()/1000 == region){
                aceptados.add(ac);
            }
        }
        return aceptados;
    }

    public ProveedorEntity obtenerProveedor(Integer codigo){
        return repo.findById(codigo).orElse(null);
    }
}
