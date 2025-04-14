CREATE DATABASE coderhouse;
USE coderhouse;

CREATE TABLE alumno(
	nombre VARCHAR(20),
    apellido VARCHAR(20)
);

SELECT * FROM alumno;

SELECT id, COUNT(*) 
FROM alumno 
GROUP BY id 
HAVING COUNT(*) > 1;

SELECT * FROM alumno WHERE id = 1;

INSERT INTO alumno VALUES
	("Alejandro" , "Di Stefano"),
    ("Alejandro" , "Perez"),
    ("Lionel" , "Messi"),
    ("Gabriel" , "Bertella");
    
    ALTER TABLE alumno ADD COLUMN id INT AUTO_INCREMENT NOT NULL PRIMARY KEY;
    ALTER TABLE alumno ADD COLUMN rut INT NULL;
    
    INSERT INTO alumno VALUES
	(5, "Susana" , "Gimenez", 22233355);
    
-- ALTER TABLE alumno 
-- MODIFY COLUMN id INT PRIMARY KEY FIRST;
-- ALTER TABLE alumno DROP PRIMARY KEY;
ALTER TABLE alumno MODIFY COLUMN id INT NOT NULL FIRST;
ALTER TABLE alumno MODIFY COLUMN id INT AUTO_INCREMENT NOT NULL;
-- ALTER TABLE alumno ADD PRIMARY KEY (id);

UPDATE alumno SET rut = 333666999 WHERE id = 1;
UPDATE alumno SET rut = 55222666 WHERE id = 2;
UPDATE alumno SET rut = 99555775 WHERE id = 3;
UPDATE alumno SET rut = 234556897 WHERE id = 4;

DESC alumno;
DELETE FROM alumno WHERE id = 0;

-- DROP TABLE alumno; elimina toda la tabla.-