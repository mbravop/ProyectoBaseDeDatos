CREATE DATABASE proyectobdd;
use proyectobdd;

CREATE TABLE Donador(
	idDonador INT PRIMARY KEY AUTO_INCREMENT,
    cedulaD VARCHAR(10) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    tipoDeSangre VARCHAR(2) NOT NULL,
    tipificacionSangre VARCHAR(1) NOT NULL
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
    aceptacion VARCHAR(1) NOT NULL,
    idDonador INT,
    idEnfermero INT,
    fechaDonacion DATE NOT NULL,
    idDestino INT NOT NULL,
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
    tipoDeSangre VARCHAR(2) NOT NULL,
    tipificacionSangre VARCHAR(1) NOT NULL
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

CREATE TABLE CredencialesEnfermeros(
	idEnfermero INT,
    contrasenaEnfermero VARCHAR(20) NOT NULL,
    FOREIGN KEY (idEnfermero) REFERENCES Enfermero(idEnfermero)
);

CREATE TABLE CredencialesBeneficiarios(
	idBeneficiario INT,
    contrasenaBeneficiario VARCHAR(20) NOT NULL,
    FOREIGN KEY (idBeneficiario) REFERENCES Beneficiario(idBeneficiario)
);

INSERT INTO Donador(cedulaD,nombre,apellido,sexo,tipoDeSangre,tipificacionSangre) VALUES ("0987654321","Mauricio","Bravo","M","O","+"),
("1234567890","Dereck","Santander","M","O","+"),
("1029384756","Fernanda","Mawyin","F","AB","-"),
("7894561230","Juanito","Coque","M","A","+"),
("1472580369","Rosa","Flores","F","B","-");

INSERT INTO Enfermero(cedulaE,nombre,apellido,especialidad) VALUES ("5709665994","Antonio","Garcia","Pediatrica"),
("4978470317","Isabel","Sanchez","Familiar"),
("4683347097","Manuel","Gonzalez","Salud mental"),
("3408649249","Cristina","Perez","Cuidados medicos"),
("2086059294","Lionel","Messi","Ginecologo");

INSERT INTO Donacion(idDonador,idEnfermero,fechaDonacion,idDestino, aceptacion) VALUES (1,4,DATE ("2022-12-20"),0,"-"),
(2,5,DATE ("2022-12-18"),1,"-"),
(3,3,DATE ("2022-11-28"),0,"-"),
(4,1,DATE ("2022-10-15"),0,"-"),
(4,2,DATE ("2022-09-30"),2,"-"),
(5,4,DATE ("2022-02-19"),3,"-");

INSERT INTO CredencialesEnfermeros VALUES (1,"salchipapa"),
(2,"comidaRica"),
(3,"cargador"),
(4,"elcrack"),
(5,"password123");

INSERT INTO Beneficiario(nombre, apellido, tipoDeSangre, tipificacionSangre) VALUES ("Karim","Benzema","A","+"),
("Cristiano","Ronaldo","AB","+"),
("Guillermo","Lasso","B","+"),
("Joaquin","Castillo","O","+"),
("Fernando","Alonso","A","-");

INSERT INTO Solicitud(cantidadSolicitada,cantidadRecibida,fechaSolicitud,idBeneficiario,realizada) VALUES
(4,1,DATE("2022-10-16"),1,FALSE),
(1,1,DATE("2022-10-01"),2,TRUE),
(3,1,DATE("2022-02-20"),3,FALSE);

INSERT INTO CredencialesBeneficiarios VALUES (1,"mejorproyecto"),
(2,"televisor"),
(3,"celular"),
(4,"espol"),
(5,"contraseña");

DELIMITER //

CREATE TRIGGER tr1 AFTER UPDATE ON Donacion
	FOR EACH ROW
    BEGIN
		INSERT INTO Revision(idEnfermero,idDonacion,fechaRevision) VALUES (new.idEnfermero,new.idDonacion, CURDATE());
	END 
// 
DELIMITER ;

SELECT d.idDonador, d.cedulaD, d.tipoDeSangre, d.tipificacionSangre, do.idDonacion, do.aceptacion, do.idEnfermero, do.idDestino, do.fechaDonacion FROM Donador d JOIN Donacion do ON do.idDonador = d.idDonador;
