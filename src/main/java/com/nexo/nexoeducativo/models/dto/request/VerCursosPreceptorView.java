package com.nexo.nexoeducativo.models.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Martina
 */
@Getter
@Setter
@NoArgsConstructor
public class VerCursosPreceptorView extends CursoDTO {

    private static final long serialVersionUID = 1L;
    private Integer idCurso;

    public VerCursosPreceptorView(Integer idCurso, int numero, Character division) {
        super(numero, division);
        this.idCurso = idCurso;
    }

    public VerCursosPreceptorView(int numero, Character division) {
        super(numero, division);
    }

}
