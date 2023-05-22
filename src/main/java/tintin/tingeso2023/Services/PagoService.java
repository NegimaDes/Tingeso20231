package tintin.tingeso2023.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tintin.tingeso2023.Entities.AcopioAcumEntity;
import tintin.tingeso2023.Entities.CalidadEntity;
import tintin.tingeso2023.Entities.PagoEntity;
import tintin.tingeso2023.Entities.ProveedorEntity;
import tintin.tingeso2023.Models.DatosPagos;
import tintin.tingeso2023.Repositories.PagoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class PagoService {
    static final int PAGO_A = 700;
    static final int PAGO_B = 550;
    static final int PAGO_C = 400;
    static final int PAGO_D = 250;
    static final int GRASA_BAJA = 30;
    static final int GRASA_MEDIA = 80;
    static final int GRASA_ALTA = 120;
    static final int SOL_BAJO = -130;
    static final int SOL_MEDIO_BAJO = -90;
    static final int SOL_MEDIO_ALTO = 95;
    static final int SOL_ALTO = 150;
    static final int BON_DOBLE = 20;
    static final int BON_MAN = 12;
    static final int BON_TAR = 8;
    static final int VAR_KLS_BAJA = 7;
    static final int VAR_KLS_MEDIA = 15;
    static final int VAR_KLS_ALTA = 30;
    static final int VAR_GRASA_BAJA = 12;
    static final int VAR_GRASA_MEDIA = 20;
    static final int VAR_GRASA_ALTA = 30;
    static final int VAR_SOL_BAJA = 18;
    static final int VAR_SOL_MEDIA = 27;
    static final int VAR_SOL_ALTA = 45;
    static final float RETENCION = 0.13F;

    @Autowired
    PagoRepository repo;

    @Autowired
    CalidadService calserv;

    @Autowired
    AcopioService acoserv;

    @Autowired
    ProveedorService proserv;

    public void save(PagoEntity pago){
        repo.save(pago);
    }

    public void preSave(PagoEntity pago){
        if(pago.getTotal() > 950000){
            pago.setV_final((int) (pago.getTotal() *(1-RETENCION)));
            pago.setRetencion((int) (pago.getTotal() * RETENCION));
            proserv.retencion(pago.getAcopio().getCodigo().getCodigo(), true);
        }else{
            pago.setV_final(pago.getTotal());
            pago.setRetencion(0);
            proserv.retencion(pago.getAcopio().getCodigo().getCodigo(), false);
        }
        save(pago);
    }
    
    public void calcularYGuardar(PagoEntity pago){
        Integer pagoacopio = pago.getPleche() + pago.getPgrasa() + pago.getPsolidos() + pago.getPleche()*pago.getBonificacion();
        pago.setDvarl(pagoacopio * getVariacionKLS(pago.getVarl()));
        pago.setDvarg(pagoacopio * getVariacionGrasa(pago.getVarg()));
        pago.setDvars(pagoacopio * getVariacionSolidos(pago.getVars()));
        Integer descuentototal = pago.getDvarl() + pago.getDvarg() + pago.getDvars();
        pago.setTotal(pagoacopio - descuentototal);
        preSave(pago);
    }

    public void calcularPagos(Integer[] fecha){
        List<AcopioAcumEntity> porpagar = acoserv.sinPagos(fecha);
        for(AcopioAcumEntity calculando:porpagar){
            datos(calculando);
        }
    }

    public void datos(AcopioAcumEntity calculando){
        PagoEntity pago = new PagoEntity();
        pago.setAcopio(calculando);
        pago.setPleche(getPagoCat(calculando) * calculando.getTotal_kls());
        pago.setPgrasa(getPagoGrasa(calculando) * calculando.getTotal_kls());
        pago.setPsolidos(getPagoSolidos(calculando) * calculando.getTotal_kls());
        pago.setBonificacion(getBonificacionFrecuencia(calculando));
        pago.setVarl(diferenciaKLS(calculando));
        pago.setVarg(diferenciaGrasa(calculando));
        pago.setVars(diferenciaSolidos(calculando));
        calcularYGuardar(pago);
    }

    public Integer getPagoCat(AcopioAcumEntity acopio){
        String categoria = acopio.getCodigo().getCategoria();
        if(Objects.equals(categoria, "A")){
            return PAGO_A;
        }else if(Objects.equals(categoria, "B")){
            return PAGO_B;
        }else if(Objects.equals(categoria, "C")){
            return PAGO_C;
        }else{
            return PAGO_D;
        }
    }

    public Integer getPagoGrasa(AcopioAcumEntity acopio){
        CalidadEntity calidad = calserv.getCalidad(acopio.getId_acopio_acum());
        if(calidad.getGrasa() <= 20){
            return GRASA_BAJA;
        }else if(calidad.getGrasa() <= 45){
            return GRASA_MEDIA;
        }else{
            return GRASA_ALTA;
        }
    }

    public Integer getPagoSolidos(AcopioAcumEntity acopio){
        CalidadEntity calidad = calserv.getCalidad(acopio.getId_acopio_acum());
        if(calidad.getSolido() <= 7){
            return SOL_BAJO;
        }else if(calidad.getSolido() <= 18){
            return SOL_MEDIO_BAJO;
        }else if(calidad.getSolido() <= 35){
            return SOL_MEDIO_ALTO;
        }else{
            return SOL_ALTO;
        }
    }

    public Integer getBonificacionFrecuencia(AcopioAcumEntity acopio){
        if(acopio.getTarde() >= 10){
            if(acopio.getManana() >= 10){
                return BON_DOBLE;
            }
            return BON_TAR;
        }else if(acopio.getManana() >= 10){
            return BON_MAN;
        }
        return 0;
    }

    public Integer diferenciaKLS(AcopioAcumEntity acopio){
        AcopioAcumEntity previo = acoserv.getPrevio(acopio);
        if(previo == null){
            return 0;
        }
        float relacion = ((float) (acopio.getTotal_kls()/previo.getTotal_kls()))*100;
        return (int) (100 - relacion);
    }

    public Integer getVariacionKLS(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_KLS_BAJA;
        }else if(diferencia <= 45){
            return VAR_KLS_MEDIA;
        }else{
            return VAR_KLS_ALTA;
        }
    }

    public Integer diferenciaGrasa(AcopioAcumEntity acopio){
        AcopioAcumEntity previo = acoserv.getPrevio(acopio);
        if(previo == null){
            return 0;
        }
        Integer id1 = previo.getId_acopio_acum();
        Integer id2 = acopio.getId_acopio_acum();
        float relacion = ((float) (calserv.getCalidad(id2).getGrasa()/calserv.getCalidad(id1).getGrasa()))*100;
        return (int) (100 - relacion);
    }

    public Integer getVariacionGrasa(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_GRASA_BAJA;
        }else if(diferencia <= 45){
            return VAR_GRASA_MEDIA;
        }else{
            return VAR_GRASA_ALTA;
        }
    }

    public Integer diferenciaSolidos(AcopioAcumEntity acopio){
        AcopioAcumEntity previo = acoserv.getPrevio(acopio);
        if(previo == null){
            return 0;
        }
        Integer id1 = previo.getId_acopio_acum();
        Integer id2 = acopio.getId_acopio_acum();
        float relacion = ((float) (calserv.getCalidad(id2).getSolido()/calserv.getCalidad(id1).getSolido()))*100;
        return (int) (100 - relacion);
    }

    public Integer getVariacionSolidos(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_SOL_BAJA;
        }else if(diferencia <= 45){
            return VAR_SOL_MEDIA;
        }else{
            return VAR_SOL_ALTA;
        }
    }

    public List<DatosPagos> getAllData(){
        Iterable<PagoEntity> pagos = repo.findAll();
        List<DatosPagos> datos = new ArrayList<>();
        for(PagoEntity pago:pagos){
            DatosPagos datopago = new DatosPagos();
            datopago.setQuincena(quincenaToString(pago));
            datopago.setCodigo(pago.getAcopio().getCodigo().getCodigo());
            datopago.setNombre(pago.getAcopio().getCodigo().getNombre());
            datopago.setKls(pago.getAcopio().getTotal_kls());
            datopago.setDiasenvio(pago.getAcopio().getTarde() + pago.getAcopio().getManana());
            datopago.setPromdiario((float) datopago.getKls() /datopago.getDiasenvio());
            datopago.setGrasa(calserv.getCalidad(pago.getAcopio().getId_acopio_acum()).getGrasa());
            datopago.setSolidos(calserv.getCalidad(pago.getAcopio().getId_acopio_acum()).getSolido());
            datopago.setVarl(pago.getVarl());
            datopago.setVarg(pago.getVarg());
            datopago.setVars(pago.getVars());
            datopago.setPagol(pago.getPleche());
            datopago.setPagog(pago.getPgrasa());
            datopago.setPagos(pago.getPsolidos());
            datopago.setBonif(pago.getBonificacion());
            datopago.setDvarl(pago.getDvarl());
            datopago.setDvarg(pago.getDvarg());
            datopago.setDvars(pago.getDvars());
            datopago.setTotal(pago.getTotal());
            datopago.setRetencion(pago.getRetencion());
            datopago.setMfinal(pago.getV_final());
            datos.add(datopago);
        }
        return datos;
    }

    public String quincenaToString(PagoEntity pago){
        return pago.getAcopio().getAnno() + "/" + pago.getAcopio().getMes() + "/" + pago.getAcopio().getQuincena();
    }
}
