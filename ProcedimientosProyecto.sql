use proyectobdd;


DROP PROCEDURE IF EXISTS verificarLogin;
DELIMITER /
CREATE PROCEDURE verificarLogin(tabla varchar(30),idUsuario int, contrasena varchar(20))
BEGIN
	IF tabla = "CredencialesEnfermeros" THEN
		SELECT count(1) FROM CredencialesEnfermeros
		WHERE idEnfermero = idUsuario AND contrasenaEnfermero = contrasena;
	ELSEIF tabla = "CredencialesBeneficiarios" THEN
		SELECT count(1) FROM CredencialesBeneficiarios 
        WHERE idBeneficiario = idUsuario AND contrasenaBeneficiario = contrasena;
	END IF;
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS buscarInfoUsuario;
DELIMITER /
CREATE PROCEDURE buscarInfoUsuario(tabla VARCHAR(30), idUsuario int)
BEGIN
	IF tabla = "enfermero" THEN
		SELECT nombre, apellido, cedulaE, especialidad FROM Enfermero WHERE idEnfermero = idUsuario;
	ELSEIF tabla = "beneficiario" THEN
		SELECT nombre, apellido, tipoDeSangre, tipificacionSangre FROM Beneficiario WHERE idBeneficiario = idUsuario;
	END IF;
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS buscarCantidadEspecifica;
DELIMITER /
CREATE PROCEDURE buscarCantidadEspecifica(tabla VARCHAR(30), idUsuario int)
BEGIN
	IF tabla = "enfermero" THEN
		SELECT COUNT(idEnfermero) AS cantidad FROM Donacion Where idEnfermero = idUsuario;
	ELSEIF tabla = "beneficiario" THEN
		SELECT COUNT(idBeneficiario) AS cantidad FROM Solicitud WHERE idBeneficiario = idUsuario;
	END IF;
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS obtenerDonadores;
DELIMITER /
CREATE PROCEDURE obtenerDonadores()
BEGIN
	SELECT * FROM Donador;
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS insertarDonador;
DELIMITER /
CREATE PROCEDURE insertarDonador(cedula VARCHAR(10), nombre VARCHAR(50), apellido VARCHAR(50), sexo VARCHAR(1), tipoDeSangre VARCHAR(2), tipificacionSangre VARCHAR(1))
BEGIN
	INSERT INTO Donador(cedulaD,nombre,apellido,sexo,tipoDeSangre,tipificacionSangre) VALUES ( cedula, nombre, apellido,sexo,tipoDeSangre,tipificacionSangre);
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS obtenerDonaciones;
DELIMITER /
CREATE PROCEDURE obtenerDonaciones()
BEGIN
	SELECT d.idDonador, d.cedulaD, d.nombre, d.apellido, d.tipoDeSangre, d.tipificacionSangre, do.idDonacion, do.aceptacion, do.idEnfermero, do.idDestino, do.fechaDonacion FROM Donador d JOIN Donacion do ON do.idDonador = d.idDonador;
END;
/ DELIMITER ;







DROP PROCEDURE IF EXISTS obtenerRevisiones;
DELIMITER /
CREATE PROCEDURE obtenerRevisiones()
BEGIN
	SELECT r.idRevision,r.idDonacion, d.nombre, d.apellido, e.nombre, e.apellido, r.fechaRevision 
    FROM Revision r
	JOIN Donacion dx ON r.idDonacion = dx.idDonacion 
	JOIN Donador d ON d.idDonador = dx.idDonador 
	JOIN Enfermero e ON e.idEnfermero = dx.idEnfermero;
END;
/ DELIMITER ;


DROP PROCEDURE IF EXISTS obtenerSolicitudes;
DELIMITER /
CREATE PROCEDURE obtenerSolicitudes()
BEGIN
	SELECT s.idSolicitud, s.cantidadSolicitada, s.cantidadRecibida, s.fechaSolicitud, b.nombre, b.apellido, s.realizada
	FROM Solicitud s JOIN Beneficiario b ON b.idBeneficiario = s.idBeneficiario;
END;
/ DELIMITER ;

DROP PROCEDURE IF EXISTS obtenerMisSolicitudes;
DELIMITER /
CREATE PROCEDURE obtenerMisSolicitudes(idUsuario INT)
BEGIN
	SELECT s.idSolicitud,s.cantidadSolicitada, b.nombre, b.apellido, s.cantidadRecibida, s.fechaSolicitud,s.idBeneficiario, s.realizada
	FROM Solicitud s JOIN Beneficiario b ON s.idBeneficiario = b.idBeneficiario WHERE s.idBeneficiario= idUsuario;
END;
/ DELIMITER ;

