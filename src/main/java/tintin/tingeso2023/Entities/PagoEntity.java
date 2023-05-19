package tintin.tingeso2023.Entities;

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
    private Integer id_pago;

    @OneToOne
    @JoinColumn(name = "id_acopio_acum")
    private AcopioAcumEntity acopio;

    private Integer total;

    private Integer v_final;
}