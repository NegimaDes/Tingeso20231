package tintin.tingeso2023;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.Entities.ProveedorEntity;
import tintin.tingeso2023.Services.ProveedorService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class proveedorTest {

    @Autowired
    ProveedorService serv;

    @Test
    void testsave1(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        Integer codigo = serv.save(temp).getCodigo();

        assertEquals(1001, codigo, 0.0);

        serv.delete(1001);
    }
    @Test
    void testposible(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        boolean posible = serv.codigoCorrecto(1001);

        assertFalse(posible);

        serv.delete(1001);
    }
    @Test
    void testnext_code(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        ProveedorEntity temp2 = new ProveedorEntity();
        temp2.setCategoria("A");
        temp2.setCodigo(2001);
        temp2.setNombre("Nombre");
        temp2.setRetencion(false);

        serv.save(temp2);

        Integer codigo = serv.nextCode(1000);

        assertEquals(1002, codigo);

        serv.delete(1001);
        serv.delete(2001);
    }
    @Test
    void testobtener(){
        ProveedorEntity temp = new ProveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        Integer codigo = serv.obtenerProveedor(1001).getCodigo();

        assertEquals(1001,codigo);

        serv.delete(1001);
    }
}
