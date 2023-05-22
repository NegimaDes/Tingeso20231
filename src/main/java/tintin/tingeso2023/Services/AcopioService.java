package tintin.tingeso2023.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tintin.tingeso2023.Entities.AcopioAcumEntity;
import tintin.tingeso2023.Entities.ProveedorEntity;
import tintin.tingeso2023.Repositories.AcopioAcumRepository;
import tintin.tingeso2023.Repositories.ProveedorRepository;

import java.io.*;
import java.util.*;

import static org.thymeleaf.util.StringUtils.length;

@Service
public class AcopioService {

    @Autowired
    AcopioAcumRepository repo;

    @Autowired
    ProveedorRepository repoprov;

    @Autowired
    ProveedorService servprov;

    public AcopioAcumEntity save(AcopioAcumEntity acopio){
        return repo.save(acopio);
    }

    public void createAndSave(String codigo, String kls, String turno, Integer anno, Integer mes, Integer quincena){
        AcopioAcumEntity nuevo = new AcopioAcumEntity();
        ProveedorEntity proveedor = repoprov.findById(Integer.parseInt(codigo)).orElse(null);
        if(proveedor != null){
            nuevo.setCodigo(proveedor);
            nuevo.setTotal_kls(Integer.parseInt(kls));
            nuevo.setAnno(anno);
            nuevo.setMes(mes);
            nuevo.setQuincena(quincena);
            nuevo = startTurnos(nuevo);
            nuevo = addTurno(nuevo, turno);
            save(nuevo);
        }
    }

    public void saveHandler(AcopioAcumEntity acopio, Integer anno, Integer mes, Integer quincena, String turno, String kls, String proveedor){
        if(acopio != null){
            acopio.setTotal_kls(acopio.getTotal_kls() + Integer.parseInt(kls));
            acopio = addTurno(acopio, turno);
            save(acopio);
        }else{
            createAndSave(proveedor, kls, turno, anno, mes, quincena);
        }
    }

    public AcopioAcumEntity startTurnos(AcopioAcumEntity acopio){
        acopio.setManana(0);
        acopio.setTarde(0);
        return acopio;
    }

    public AcopioAcumEntity addTurno(AcopioAcumEntity editar, String turno){
        if(Objects.equals(turno, "M")){
            editar.setManana(editar.getManana() + 1);
        }else{
            editar.setTarde(editar.getTarde() + 1);
        }
        return editar;
    }

    public AcopioAcumEntity findAcopio(int codigo, Integer anno, Integer mes, Integer quincena){
        Iterable<AcopioAcumEntity> posibles = filterByCode(codigo);
        for(AcopioAcumEntity acopio:posibles){
            if(confirmar(acopio, anno, mes, quincena)){
                return acopio;
            }
        }
        return null;
    }

    public List<AcopioAcumEntity> filterByCode(Integer codigo){
        Iterable<AcopioAcumEntity> all = repo.findAll();
        List<AcopioAcumEntity> coincidencias = new LinkedList<>();
        for(AcopioAcumEntity temp : all){
            if(Objects.equals(temp.getCodigo().getCodigo(), codigo)){
                coincidencias.add(temp);
            }
        }
        return coincidencias;
    }

    public boolean confirmar(AcopioAcumEntity acopio, Integer anno, Integer mes, Integer quincena){
        return Objects.equals(acopio.getAnno(), anno) && Objects.equals(acopio.getMes(), mes) && Objects.equals(acopio.getQuincena(), quincena);
    }

    public Integer[] readDoc(MultipartFile doc){
        String line;
        BufferedReader br;
        Integer[] fecha = null;
        try{
            InputStream is = doc.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine();
            while ((line = br.readLine()) != null){
                fecha = readLine(line);
            }
            is.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        return fecha;
    }

    public Integer[] readLine(String line){
        String[] arr = line.split(",");
        Integer[] fecha = getListDate(arr[0]);
        int quincena = 1;
        if(fecha[1] > 15){
            quincena = 2;
        }
        Integer[] fecharespuesta = new Integer[]{fecha[2], fecha[0], quincena};
        AcopioAcumEntity acopio = findAcopio(Integer.parseInt(arr[2]), fecha[2], fecha[0], quincena);
        saveHandler(acopio, fecha[2], fecha[0], quincena, arr[1], arr[3], arr[2]);
        return fecharespuesta;
    }

    public Integer[] getListDate(String fecha){
        String[] arr = fecha.split("/");
        int anno = Integer.parseInt(arr[2]);
        int mes = Integer.parseInt(arr[0]);
        int dia = Integer.parseInt(arr[1]);
        return new Integer[]{mes, dia, anno};
    }

    public List<AcopioAcumEntity> sinPagos(Integer[] fecha){
        Iterable<AcopioAcumEntity> all = repo.findAll();
        List<AcopioAcumEntity> porpagar = new ArrayList<>();
        for(AcopioAcumEntity actual: all){
            if(confirmar(actual, fecha[0], fecha[1], fecha[2])){
                porpagar.add(actual);
            }
        }
        return porpagar;
    }

    public AcopioAcumEntity getPrevio(AcopioAcumEntity actual){
        Iterable<AcopioAcumEntity> all = repo.findAll();
        for(AcopioAcumEntity previo:all){
            if(Objects.equals(actual.getCodigo().getCodigo(), previo.getCodigo().getCodigo())){
                if(esPrevio(actual, previo)){
                    return previo;
                }
            }
        }
        return null;
    }

    public boolean esPrevio(AcopioAcumEntity actual, AcopioAcumEntity previo){
        if(actual.getQuincena() == 1){
            if(actual.getMes() == 1){
                return confirmar(previo, actual.getAnno()-1, 12, 2);
            }
            return confirmar(previo, actual.getAnno(), actual.getMes()-1, 2);
        }
        return confirmar(previo, actual.getAnno(), actual.getMes(), 1);
    }
}
