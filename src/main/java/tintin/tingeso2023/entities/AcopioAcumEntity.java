package tintin.tingeso2023.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "acopio_acum")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcopioAcumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idacopio;

    @ManyToOne
    @JoinColumn(name = "codigo")
    private ProveedorEntity codigo;

    private Integer totalkls;

    private Integer manana;

    private Integer tarde;

    private Integer anno;

    private Integer mes;

    private Integer quincena;
}