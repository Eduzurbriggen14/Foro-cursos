-- Creación de la tabla USUARIO
CREATE TABLE Usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    passw VARCHAR(255) NOT NULL,
    nombreUsuario VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);


-- Creación de la tabla USUARIO_PERFIL (relación N a N entre USUARIO y PERFIL)
CREATE TABLE UsuarioPerfil (
    id_usuario_perfil BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil ENUM('Estudiante',
                'Profesor',
                'Administrador') DEFAULT 'Estudiante',
    estadoPerfil BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);


-- Creación de la tabla CURSO
CREATE TABLE Curso (
    id_curso BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    categoria ENUM( 'PROGRAMACION',
                     'BASES_DE_DATOS',
                     'FRONTEND',
                     'BACKEND',
                     'DEVOPS',
                     'MOBILE',
                     'INTELIGENCIA_ARTIFICIAL',
                     'SEGURIDAD') NOT NULL
);

-- Creación de la tabla USUARIO_CURSO (relación N a N entre USUARIO y CURSO)
CREATE TABLE UsuarioCurso (
    id_usuario_curso BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    fechaInscripcion DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE
);


-- Creación de la tabla COMENTARIO
CREATE TABLE Comentario (
    id_comentario BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    contenido TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estadoComentario ENUM('Pendiente',
                          'Resuelto') DEFAULT 'Pendiente',
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE
);


-- Creación de la tabla RESPUESTA (relación con COMENTARIO)
CREATE TABLE RESPUESTA (
    id_respuesta BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    id_usuario INT NOT NULL,
    id_comentario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES COMENTARIO(id_comentario) ON DELETE CASCADE
);
