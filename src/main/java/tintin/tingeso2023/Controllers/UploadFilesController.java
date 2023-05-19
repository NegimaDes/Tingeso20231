package tintin.tingeso2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tintin.tingeso2023.Services.AcopioService;
import tintin.tingeso2023.Services.CalidadService;

@Controller
@RequestMapping
public class UploadFilesController {

    @Autowired
    AcopioService acopserv;

    @Autowired
    CalidadService caliserv;

    @GetMapping("/subir_data")
    public String interfaz(){
        return "upload_files";
    }

    @PostMapping("/subir_data")
    public String subirArchivos(@RequestParam("doc1")MultipartFile doc1, @RequestParam("doc2")MultipartFile doc2){
        Integer[] fecha;
        fecha = acopserv.readDoc(doc1);
        caliserv.readDoc(doc2, fecha);
        return "main";
    }
}
