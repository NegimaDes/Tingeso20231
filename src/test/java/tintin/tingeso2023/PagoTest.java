package tintin.tingeso2023;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.entities.AcopioAcumEntity;
import tintin.tingeso2023.entities.CalidadEntity;
import tintin.tingeso2023.entities.PagoEntity;
import tintin.tingeso2023.entities.ProveedorEntity;
import tintin.tingeso2023.services.AcopioService;
import tintin.tingeso2023.services.CalidadService;
import tintin.tingeso2023.services.PagoService;
import tintin.tingeso2023.services.ProveedorService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PagoTest {
    @Autowired
    PagoService serv;

    @Autowired
    AcopioService aserv;

    @Autowired
    CalidadService cserv;

    @Autowired
    ProveedorService pserv;

    @Test
    void testGetPagoCat(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setCodigo(proveedor);

        Integer multiplicador = serv.getPagoCat(acopio);

        assertEquals(700, multiplicador);
    }

    @Test
    void testGetPagoCat2(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("B");
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setCodigo(proveedor);

        Integer multiplicador = serv.getPagoCat(acopio);

        assertEquals(550, multiplicador);
    }

    @Test
    void testGetPagoCat3(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("C");
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setCodigo(proveedor);

        Integer multiplicador = serv.getPagoCat(acopio);

        assertEquals(400, multiplicador);
    }

    @Test
    void testGetPagoCat4(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("D");
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setCodigo(proveedor);

        Integer multiplicador = serv.getPagoCat(acopio);

        assertEquals(250, multiplicador);
    }

    @Test
    void testGetPagoGrasa1(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setGrasa(15);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(30, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoGrasa2(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setGrasa(30);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(80, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoGrasa3(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setGrasa(50);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(120, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoSolidos1(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setSolido(4);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(-130, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoSolidos2(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setSolido(10);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(-90, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoSolidos3(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setSolido(20);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(95, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetPagoSolidos4(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        AcopioAcumEntity acopio = aserv.save(temp);

        CalidadEntity temp2 = new CalidadEntity();
        temp2.setAcopio(acopio);
        temp2.setSolido(50);
        CalidadEntity calidad = cserv.save(temp2);

        Integer grasa = serv.getPagoGrasa(acopio);

        assertEquals(150, grasa);

        cserv.delete(calidad);
        aserv.delete(acopio);
    }

    @Test
    void testGetBonificacionFrecuencia1(){
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setTarde(5);
        acopio.setManana(5);

        Integer bonificacion = serv.getBonificacionFrecuencia(acopio);

        assertEquals(0, bonificacion);
    }
    @Test
    void testGetBonificacionFrecuencia2(){
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setTarde(12);
        acopio.setManana(5);

        Integer bonificacion = serv.getBonificacionFrecuencia(acopio);

        assertEquals(8, bonificacion);
    }
    @Test
    void testGetBonificacionFrecuencia3(){
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setTarde(5);
        acopio.setManana(12);

        Integer bonificacion = serv.getBonificacionFrecuencia(acopio);

        assertEquals(12, bonificacion);
    }
    @Test
    void testGetBonificacionFrecuencia4(){
        AcopioAcumEntity acopio = new AcopioAcumEntity();
        acopio.setTarde(12);
        acopio.setManana(12);

        Integer bonificacion = serv.getBonificacionFrecuencia(acopio);

        assertEquals(20, bonificacion);
    }

    @Test
    void testGuardar(){
        ProveedorEntity prov = new ProveedorEntity(20001, "nombre", "A", false);
        pserv.save(prov);

        AcopioAcumEntity temp = new AcopioAcumEntity();
        temp.setQuincena(2);
        temp.setMes(5);
        temp.setAnno(2023);
        temp.setManana(2);
        temp.setTarde(1);
        temp.setTotalkls(20);
        temp.setCodigo(prov);

        AcopioAcumEntity temp2 = aserv.save(temp);

        Integer[] fecha = new Integer[]{2023, 5, 2};

        CalidadEntity calidad = cserv.saveHandler(20001, 15, 15, fecha);

        PagoEntity pago =  serv.calcularPagos(fecha);

        assertNotEquals(null, pago);

        serv.delete(pago);
        cserv.delete(calidad);
        aserv.delete(temp2);
        pserv.delete(20001);
    }
}
