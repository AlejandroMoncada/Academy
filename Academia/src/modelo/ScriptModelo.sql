

create table tiposPersona(
id INT PRIMARY KEY auto_increment,
nombre char(30)
);

create table personas(

id INT PRIMARY KEY ,
nombre char(30),
apellido char(30),
email char(60),
pw char(60),
tipo INT,
edad INT,
CONSTRAINT fk_tipoPersona FOREIGN KEY (tipo) REFERENCES tiposPersona(id)
);


create table estados(
id INT PRIMARY KEY,
nombre char(30)
);

create table horarios(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre char(30),
fechaEntrada date,
fechaSalida date,
horaEntrada time,
horaSalida time
);


create table cursos(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre varchar(60),
horario INT,
estado INT,
CONSTRAINT fk_Estado FOREIGN KEY (estado) REFERENCES estados(id),
CONSTRAINT fk_Horario FOREIGN KEY (horario) REFERENCES horarios(id)
);

create table personas_cursos(
id INT PRIMARY KEY AUTO_INCREMENT,
persona INT,
curso INT,
CONSTRAINT fk_personas FOREIGN KEY (persona) REFERENCES personas(id),
CONSTRAINT fk_curso FOREIGN KEY (curso) REFERENCES cursos(id)
);

