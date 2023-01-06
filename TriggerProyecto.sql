use proyectobdd;

DELIMITER //

CREATE TRIGGER tr1 AFTER UPDATE ON Donacion
	FOR EACH ROW
    BEGIN
		INSERT INTO Revision(idEnfermero,idDonacion,fechaRevision) VALUES (new.idEnfermero,new.idDonacion, CURDATE());
	END // 
DELIMITER ;