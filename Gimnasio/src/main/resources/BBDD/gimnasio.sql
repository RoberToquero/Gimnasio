DROP DATABASE IF EXISTS gimnasio;
CREATE DATABASE gimnasio;
USE gimnasio;

-- Tabla Monitor
CREATE TABLE Monitor (
                         ID_Monitor INT PRIMARY KEY AUTO_INCREMENT,
                         Nombre VARCHAR(255) NOT NULL,
                         Email VARCHAR(255) NOT NULL,
                         Contrasena VARCHAR(255) NOT NULL
);
INSERT INTO Monitor (Nombre, Email, Contrasena)
VALUES
    ('Lucia', 'lucia@hotmail', '456'),
    ('Francisco', 'francisco@hotmail.com', '456'),
    ('Laura', 'laura@hotmail.com', '456');


-- Tabla Usuario
CREATE TABLE Usuario (
                         ID_Usuario INT PRIMARY KEY AUTO_INCREMENT,
                         Nombre_Usuario VARCHAR(255) NOT NULL,
                         Email VARCHAR(255),
                         Contrasena VARCHAR(255) NOT NULL,
                         Nombre_Apellido VARCHAR(255),
                         Tipo VARCHAR(200),
                         Acceso_Spa BOOLEAN,
                         Cuota BOOLEAN DEFAULT false,
                         Dar_de_alta BOOLEAN DEFAULT false,
                         Numero_faltas int DEFAULT 0
);
INSERT INTO Usuario (ID_Usuario, Nombre_Usuario, Email, Contrasena, Nombre_Apellido, Tipo, Acceso_Spa, Cuota, Dar_de_alta)
VALUES
    (1, 'admin', null, '123', null, null, null, null, true),
    (2, 'roberto', 'roberto@hotmail.com', '123', 'Roberto Toquero', 'Premium', true, true, false),
    (3, 'maria', 'maria@hotmail.com', '123', 'María García', 'Regular', false, true, false),
    (4, 'juan', 'juan@hotmail.com', '123', 'Juan Pérez', 'Ocasional', false, true, false),
    (5, 'luis', 'luis@hotmail.com', '123', 'Luis Martinez', 'Premium', true, false, false);

-- Tabla Sala
CREATE TABLE Sala (
    ID_Sala INT PRIMARY KEY AUTO_INCREMENT
);
INSERT INTO Sala (ID_Sala) VALUES (1), (2), (3);


-- Tabla Actividad
CREATE TABLE ActividadHorario (
                                  ID_Actividad INT PRIMARY KEY AUTO_INCREMENT,
                                  Nombre_Actividad VARCHAR(200) NOT NULL,
                                  Dia_Semana VARCHAR(200) NOT NULL,
                                  Hora_Inicio TIME NOT NULL,
                                  Hora_Fin TIME NOT NULL,
                                  Limite_Asistentes INT Default 5,
                                  Participantes INT DEFAULT 0,
                                  ID_Monitor INT,
                                  ID_Sala INT,
                                  FOREIGN KEY (ID_Monitor) REFERENCES Monitor(ID_Monitor),
                                  FOREIGN KEY (ID_Sala) REFERENCES Sala(ID_Sala)
);
INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Lunes','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Lunes','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Lunes','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Lunes','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Lunes','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Lunes','18:00:00', '19:00:00', 2, 2);

INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Martes','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Martes','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Martes','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Martes','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Martes','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Martes','18:00:00', '19:00:00', 2, 2);

INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Miércoles','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Miércoles','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Miércoles','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Miércoles','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Miércoles','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Miércoles','18:00:00', '19:00:00', 2, 2);

INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Jueves','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Jueves','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Jueves','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Jueves','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Jueves','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Jueves','18:00:00', '19:00:00', 2, 2);

INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Viernes','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Viernes','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Viernes','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Viernes','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Viernes','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Viernes','18:00:00', '19:00:00', 2, 2);

INSERT INTO ActividadHorario (Nombre_Actividad,Dia_Semana, Hora_Inicio, Hora_Fin, ID_Monitor, ID_Sala)
VALUES
    ('Cardio', 'Sábado','10:00:00', '11:00:00', 3, 1),
    ('Crossfit','Sábado','12:00:00', '13:00:00', 1, 3),
    ('Yoga','Sábado','13:00:00', '14:00:00', 2, 2),
    ('Pilates','Sábado','16:00:00', '17:00:00', 1, 3),
    ('Spinning','Sábado','17:00:00', '18:00:00', 3, 3),
    ('Zumba','Sábado','18:00:00', '19:00:00', 2, 2);


