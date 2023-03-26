package tintin.tingeso2023;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tintin.tingeso2023.Entities.proveedorEntity;
import tintin.tingeso2023.Services.proveedorService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class proveedorTest {

    @Autowired
    proveedorService serv;

    @Test
    void testsave1(){
        proveedorEntity temp = new proveedorEntity();
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
        proveedorEntity temp = new proveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        boolean posible = serv.codigo_correcto(1001);

        assertFalse(posible);

        serv.delete(1001);
    }
    @Test
    void testnext_code(){
        proveedorEntity temp = new proveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        proveedorEntity temp2 = new proveedorEntity();
        temp2.setCategoria("A");
        temp2.setCodigo(2001);
        temp2.setNombre("Nombre");
        temp2.setRetencion(false);

        serv.save(temp2);

        Integer codigo = serv.next_code(1000);

        assertEquals(1002, codigo);

        serv.delete(1001);
        serv.delete(2001);
    }
    @Test
    void testobtener(){
        proveedorEntity temp = new proveedorEntity();
        temp.setCategoria("A");
        temp.setCodigo(1001);
        temp.setNombre("Nombre");
        temp.setRetencion(false);

        serv.save(temp);

        Integer codigo = serv.obtener_proveedor(1001).getCodigo();

        assertEquals(1001,codigo);

        serv.delete(1001);
    }
}
