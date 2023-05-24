package tintin.tingeso2023.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {

    @Id
    private Integer codigo;

    private String nombre;

    private String categoria;

    private boolean retencion;

    public boolean getRetencion(){
        return this.retencion;
    }
}
