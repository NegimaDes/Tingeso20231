package tintin.tingeso2023.services;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tintin.tingeso2023.entities.AcopioAcumEntity;
import tintin.tingeso2023.entities.CalidadEntity;
import tintin.tingeso2023.repositories.CalidadRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

@Service
public class CalidadService {

    @Autowired
    CalidadRepository repo;

    @Autowired
    AcopioService servacop;

    public CalidadEntity save(CalidadEntity nuevo){
        return repo.save(nuevo);
    }

    public void delete(CalidadEntity calidad){
        repo.delete(calidad);
    }
    public CalidadEntity createAndSave(AcopioAcumEntity acopio, Integer grasa, Integer solidos){
        CalidadEntity nuevo = new CalidadEntity();
        nuevo.setAcopio(acopio);
        nuevo.setGrasa(grasa);
        nuevo.setSolido(solidos);
        return save(nuevo);
    }

    public CalidadEntity saveHandler(Integer codigo, Integer grasa, Integer solidos, Integer[] fecha){
        AcopioAcumEntity acopio = servacop.findAcopio(codigo, fecha[0], fecha[1], fecha[2]);
        if(acopio != null){
            return createAndSave(acopio, grasa, solidos);
        }
        return null;
    }

    @Generated
    public void readDoc(MultipartFile doc, Integer[] fecha){
        String line;
        BufferedReader br;
        try{
            InputStream is = doc.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            line = br.readLine();
            while ((line = br.readLine()) != null){
                readLine(line, fecha);
            }
            is.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Generated
    public void readLine(String line, Integer[] fecha){
        String[] arr = line.split(",");
        Integer codigo = Integer.parseInt(arr[0]);
        Integer grasa = Integer.parseInt(arr[1]);
        Integer solidos = Integer.parseInt(arr[2]);
        saveHandler(codigo, grasa, solidos, fecha);
    }


    public CalidadEntity getCalidad(Integer idacopio){
        Iterable<CalidadEntity> all = repo.findAll();
        for(CalidadEntity revisando:all){
            if(Objects.equals(revisando.getAcopio().getIdacopio(), idacopio)){
                return revisando;
            }
        }
        return null;
    }
}
