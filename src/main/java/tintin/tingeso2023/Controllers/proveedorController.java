package tintin.tingeso2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tintin.tingeso2023.Entities.proveedorEntity;
import tintin.tingeso2023.Services.proveedorService;

@RestController
@RequestMapping(value = "/proveedor")
public class proveedorController {

    @Autowired
    proveedorService serv;

    @GetMapping("nuevo_proveedor")
    public String main(){return "nuevo_proveedor";}

    @PostMapping("/save")
    public String save_new(@RequestParam("codigo") String codigo,
                           @RequestParam("nombre") String nombre,
                           @RequestParam("categoria") String categoria,
                           RedirectAttributes redirect){
        if(codigo.matches("[0-9.]+")) {
            Integer icodigo = Integer.parseInt(codigo);
            proveedorEntity proveedor = new proveedorEntity(icodigo, nombre, categoria, false);
            if(serv.codigo_correcto(icodigo)){
                serv.save(proveedor);
                redirect.addFlashAttribute("mensaje", "proveedor guardado");
                return "redirect:/nuevo_proveedor";
            }

            Integer codigo_correcto = serv.next_code(icodigo);
            String mensaje = "Siguiente codigo posible para la region es " +codigo_correcto;
            redirect.addFlashAttribute("mensaje", mensaje);
            return "redirect:/nuevo_proveedor";
        }
        redirect.addFlashAttribute("mensaje", "codigo no es numerico");
        return "redirect:/nuevo_proveedor";
    }


}
