package tintin.tingeso2023.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tintin.tingeso2023.Entities.AcopioAcumEntity;
import tintin.tingeso2023.Entities.CalidadEntity;
import tintin.tingeso2023.Repositories.AcopioAcumRepository;
import tintin.tingeso2023.Repositories.CalidadRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CalidadService {

    @Autowired
    CalidadRepository repo;

    @Autowired
    AcopioService servacop;

    public CalidadEntity save(CalidadEntity nuevo){
        return repo.save(nuevo);
    }

    public void createAndSave(AcopioAcumEntity acopio, Integer grasa, Integer solidos){
        CalidadEntity nuevo = new CalidadEntity();
        nuevo.setAcopio(acopio);
        nuevo.setGrasa(grasa);
        nuevo.setSolido(solidos);
        save(nuevo);
    }

    public void saveHandler(Integer codigo, Integer grasa, Integer solidos, Integer[] fecha){
        AcopioAcumEntity acopio = servacop.findAcopio(codigo, fecha[2], fecha[1], fecha[0]);
        if(acopio != null){
            createAndSave(acopio, grasa, solidos);
        }
    }

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
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void readLine(String line, Integer[] fecha){
        String[] arr = line.split(",");
        Integer codigo = Integer.parseInt(arr[0]);
        Integer grasa = Integer.parseInt(arr[1]);
        Integer solidos = Integer.parseInt(arr[2]);
        saveHandler(codigo, grasa, solidos, fecha);
    }
}
