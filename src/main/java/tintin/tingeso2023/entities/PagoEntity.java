package tintin.tingeso2023.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;

    @OneToOne
    @JoinColumn(name = "id_acopio_acum")
    private AcopioAcumEntity acopio;

    private Integer varl;

    private Integer varg;

    private Integer vars;

    private Integer pleche;

    private Integer pgrasa;

    private Integer psolidos;

    private Integer bonificacion;

    private Integer dvarl;

    private Integer dvarg;

    private Integer dvars;

    private Integer retencion;

    private Integer total;

    private Integer vfinal;
}