CREATE DATABASE proyectobdd;
use proyectobdd;

CREATE TABLE Donador(
	idDonador INT PRIMARY KEY,
    cedulaD VARCHAR(10) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    tipoDeSangre ENUM("A","B","O","AB") NOT NULL,
    tipificacionSangre ENUM("+", "-") NOT NULL
);

CREATE TABLE Enfermero(
	idEnfermero INT PRIMARY KEY AUTO_INCREMENT,
    cedulaE VARCHAR(10) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL
);

CREATE TABLE Donacion(
	idDonacion INT PRIMARY KEY AUTO_INCREMENT,
    aceptacion BOOLEAN,
    idDonador INT,
    idEnfermero INT,
    fechaDonacion DATE NOT NULL,
    idDestino INT,
    FOREIGN KEY (idDonador) REFERENCES Donador(idDonador),
    FOREIGN KEY (idEnfermero) REFERENCES Enfermero(idEnfermero)
);

CREATE TABLE Revision(
	idRevision INT PRIMARY KEY AUTO_INCREMENT,
    idEnfermero INT,
    idDonacion INT,
    fechaRevision DATE NOT NULL,
    FOREIGN KEY (idEnfermero) REFERENCES Enfermero(idEnfermero),
    FOREIGN KEY (idDonacion) REFERENCES Donacion(idDonacion)
);

CREATE TABLE Beneficiario(
	idBeneficiario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    tipoDeSangre ENUM("A","B","O","AB") NOT NULL,
    tipificacionSangre ENUM("+", "-") NOT NULL
);

CREATE TABLE Solicitud(
	idSolicitud INT PRIMARY KEY AUTO_INCREMENT,
    cantidadSolicitada INT NOT NULL,
    cantidadRecibida INT,
    fechaSolicitud DATE NOT NULL,
    idBeneficiario INT,
    realizada BOOLEAN NOT NULL,
    FOREIGN KEY (idBeneficiario) REFERENCES Beneficiario(idBeneficiario)
);

