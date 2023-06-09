package tintin.tingeso2023.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tintin.tingeso2023.models.DatosPagos;
import tintin.tingeso2023.services.PagoService;

import java.util.List;

@Controller
@RequestMapping
public class PagosController {

    @Autowired
    PagoService serv;

    @GetMapping("/pagos")
    public String listarPagos(Model model){
        List<DatosPagos> data = serv.getAllData();
        model.addAttribute("data", data);
        return "listar-pagos";
    }

}
