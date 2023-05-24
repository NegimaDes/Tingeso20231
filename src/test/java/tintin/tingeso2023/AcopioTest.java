package tintin.tingeso2023;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.entities.AcopioAcumEntity;
import tintin.tingeso2023.entities.ProveedorEntity;
import tintin.tingeso2023.repositories.AcopioAcumRepository;
import tintin.tingeso2023.services.AcopioService;
import tintin.tingeso2023.services.ProveedorService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcopioTest {

    @Autowired
    AcopioService serv;

    @Autowired
    ProveedorService pserv;

    @Autowired
    AcopioAcumRepository repo;

    @Test
    void testEsPrevio(){
        AcopioAcumEntity temp1 = new AcopioAcumEntity();
        temp1.setAnno(2023);
        temp1.setMes(5);
        temp1.setQuincena(2);

        AcopioAcumEntity temp2 = new AcopioAcumEntity();
        temp2.setAnno(2023);
        temp2.setMes(5);
        temp2.setQuincena(1);

        assertTrue(serv.esPrevio(temp1, temp2));
    }

    @Test
    void testEsPrevio2(){
        AcopioAcumEntity temp1 = new AcopioAcumEntity();
        temp1.setAnno(2023);
        temp1.setMes(5);
        temp1.setQuincena(1);

        AcopioAcumEntity temp2 = new AcopioAcumEntity();
        temp2.setAnno(2023);
        temp2.setMes(4);
        temp2.setQuincena(2);

        assertTrue(serv.esPrevio(temp1, temp2));
    }

    @Test
    void testEsPrevio3(){
        AcopioAcumEntity temp1 = new AcopioAcumEntity();
        temp1.setAnno(2023);
        temp1.setMes(1);
        temp1.setQuincena(1);

        AcopioAcumEntity temp2 = new AcopioAcumEntity();
        temp2.setAnno(2022);
        temp2.setMes(12);
        temp2.setQuincena(2);

        assertTrue(serv.esPrevio(temp1, temp2));
    }

    @Test
    void testGetListDate(){
        String fecha = "3/15/2023";

        Integer[] lfecha = new Integer[]{3, 15, 2023};

        assertTrue(Arrays.equals(lfecha, serv.getListDate(fecha)));
    }


    @Test
    void testSaveHandler(){
        ProveedorEntity prov = new ProveedorEntity(20001, "nombre", "A", false);
        pserv.save(prov);

        serv.saveHandler(null, 2023, 5,2, "M", "20", "20001");

        AcopioAcumEntity acopio = serv.findAcopio(20001, 2023, 5,2);

        assertNotEquals(null, acopio);

        serv.delete(acopio);
        pserv.delete(20001);
    }

    @Test
    void testSaveHandler2(){
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

        AcopioAcumEntity temp2 = serv.save(temp);

        serv.saveHandler(temp2, 2023, 5,2, "M", "20", "20001");

        AcopioAcumEntity acopio = serv.findAcopio(20001, 2023, 5,2);

        assertEquals(40, acopio.getTotalkls());

        serv.delete(acopio);
        pserv.delete(20001);
    }

    @Test
    void testSave(){
        AcopioAcumEntity temp = new AcopioAcumEntity();
        Integer temp2 = serv.save(temp).getIdacopio();

        assertNotEquals(null, temp2);

        repo.delete(temp);
    }


}
