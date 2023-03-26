package tintin.tingeso2023.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class calidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_calidad;

    @OneToOne
    @JoinColumn(name = "id_acopio_acum")
    private acopio_acumEntity acopio;

    private Integer grasa;

    private Integer solido;
}