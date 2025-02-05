
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "escuela_cuota")
@Data
@NoArgsConstructor
public class EscuelaCuota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escuela_cuota")
    private Integer idEscuelaCuota;

    @ManyToOne
    @JoinColumn(name = "escuela_id", referencedColumnName = "id_escuela", nullable = false)
    private Escuela escuela;

    @ManyToOne
    @JoinColumn(name = "cuota_id", referencedColumnName = "id_cuota", nullable = false)
    private Cuota cuota;
}

