package tintin.tingeso2023;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.entities.ProveedorEntity;
import tintin.tingeso2023.services.ProveedorService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ProveedorTest {

    @Autowired
    ProveedorService serv;

    @Test
    void testsave1(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(20001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        Integer codigo = serv.save(temp).getCodigo();

        assertEquals(20001, codigo, 0.0);

        serv.delete(20001);
    }
    @Test
    void testposible(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(20001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        boolean posible = serv.codigoCorrecto(20001);

        assertFalse(posible);

        serv.delete(20001);
    }
    @Test
    void testnext_code(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(20001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        ProveedorEntity temp2 = new ProveedorEntity();
        temp2.setCategoria("A");
        temp2.setCodigo(21001);
        temp2.setNombre("Nombre");
        temp2.setRetencion(false);

        serv.save(temp2);

        Integer codigo = serv.nextCode(20000);

        assertEquals(20002, codigo);

        serv.delete(20001);
        serv.delete(21001);
    }
    @Test
    void testobtener(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(20001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        Integer codigo = serv.obtenerProveedor(20001).getCodigo();

        assertEquals(20001,codigo);

        serv.delete(20001);
    }

    @Test
    void testmanejardatos1(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(20001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);


    }
}
