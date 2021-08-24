CREATE TABLE matriculas ( 
    alumno INT NOT NULL,
    asignatura INT NOT NULL,
    fecha YEAR NOT NULL,
    nota INT,
    PRIMARY KEY (alumno, asignatura, fecha),
    FOREIGN KEY alumno_matriculado(alumno) REFERENCES alumnos(id_alumno),
    FOREIGN KEY asignatura_matriculada(asignatura) REFERENCES asignaturas(id_asignatura)
    );