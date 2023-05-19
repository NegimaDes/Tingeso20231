package tintin.tingeso2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tintin.tingeso2023.Entities.ProveedorEntity;
import tintin.tingeso2023.Services.ProveedorService;

import java.util.Objects;

@Controller
@RequestMapping
public class ProveedorController {

    @Autowired
    ProveedorService serv;

    @GetMapping("/nuevo-proveedor")
    public String proveedor(){return "nuevo-proveedor";}
    @PostMapping("/save")
    public String saveNew(@RequestParam("codigo") String codigo,
                           @RequestParam("nombre") String nombre,
                           @RequestParam("categoria") String categoria,
                           RedirectAttributes redirect){
        String mensaje = "";
        if(codigo.matches("[0-9.]+")) {
            Integer icodigo = Integer.parseInt(codigo);
            mensaje = serv.manejarDatos(new ProveedorEntity(icodigo, nombre, categoria, false));
        }else {
            mensaje = "codigo no numerico";
        }
        if(!Objects.equals(mensaje, "proveedor guardado")){
            redirect.addFlashAttribute("clase", "danger");
        }else{
            redirect.addFlashAttribute("clase", "success");
        }
        redirect.addFlashAttribute("mensaje", mensaje);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores")
    public String listarProveedores(Model model){
        Iterable<ProveedorEntity> proveedores = serv.listarProveedores();
        model.addAttribute("proveedores", proveedores);
        return "listar-prov";
    }


}
