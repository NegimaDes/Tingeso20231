package tintin.tingeso2023.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcalidad;

    @OneToOne
    @JoinColumn(name = "id_acopio_acum")
    private AcopioAcumEntity acopio;

    private Integer grasa;

    private Integer solido;
}