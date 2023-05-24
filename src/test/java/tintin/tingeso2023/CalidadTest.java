package tintin.tingeso2023;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.entities.AcopioAcumEntity;
import tintin.tingeso2023.entities.CalidadEntity;
import tintin.tingeso2023.entities.ProveedorEntity;
import tintin.tingeso2023.services.AcopioService;
import tintin.tingeso2023.services.CalidadService;
import tintin.tingeso2023.services.ProveedorService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalidadTest {

    @Autowired
    CalidadService serv;

    @Autowired
    AcopioService aserv;

    @Autowired
    ProveedorService pserv;

    @Test
    void testSave(){
        CalidadEntity calidad = new CalidadEntity();
        Integer temp2 = serv.save(calidad).getIdcalidad();

        assertNotEquals(null, temp2);

        serv.delete(calidad);
    }

    @Test
    void testSaveHandler(){
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

        CalidadEntity calidad = serv.saveHandler(20001, 15, 15, fecha);

        assertNotEquals(null, calidad);

        serv.delete(calidad);
        aserv.delete(temp2);
        pserv.delete(20001);
    }

    @Test
    void testGetCalidad(){
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

        CalidadEntity calidad = serv.saveHandler(20001, 15, 15, fecha);

        CalidadEntity temp3 = serv.getCalidad(temp2.getIdacopio());
        assertEquals(temp3.getIdcalidad(), calidad.getIdcalidad());

        serv.delete(calidad);
        aserv.delete(temp2);
        pserv.delete(20001);
    }
}
