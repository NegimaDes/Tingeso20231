package tintin.tingeso2023.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acopio_acum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class acopio_acumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_acopio_acum;

    @ManyToOne
    @JoinColumn(name="codigo")
    private proveedorEntity codigo;

    private Integer total_kls;

    private Integer manana;

    private Integer tarde;

    private Date fecha;
}