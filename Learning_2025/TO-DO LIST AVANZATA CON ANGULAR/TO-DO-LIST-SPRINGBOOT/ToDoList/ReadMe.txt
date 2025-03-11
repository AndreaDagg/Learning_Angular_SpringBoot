----- --- INSTALLARE H2 SQL DB --- -----

creiamo un db che si salva alla path presente in url ed è accessibile da console 
all'interno del file application.properties: 

spring.application.name=ToDoList
spring.datasource.url=jdbc:h2:file:C:/h2db/testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=s2pring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true



- Tasto destro su ToDoList e Run as -> Maven clear
- Run as -> Maven install
- Rus as -> Java Application 


su vs code o da terminale si può avviare tramite mvn spring-boot:run

navigare: http://localhost:8080/h2-console




---------------------------------
---- ----  QUERY AL DB  ---- ----
---------------------------------

--- Tabella degli elementi 

CREATE TABLE ITEMS (
    ID INT PRIMARY KEY AUTO_INCREMENT, 
    DONE BOOLEAN NOT NULL, 
    CATEGORY VARCHAR(50) NOT NULL, 
    DESCRIPTION TEXT NOT NULL, 
    TITLE VARCHAR(100) NOT NULL
);


*********************************

INSERT INTO ITEMS (ID, DONE, CATEGORY, DESCRIPTION, TITLE) VALUES
(1, FALSE, 'Lavoro', 'Completare il report', 'Report Mensile'),
(2, TRUE, 'Casa', 'Fare la spesa', 'Lista della Spesa'),
(3, FALSE, 'Fitness', 'Allenamento in palestra', 'Workout'),
(4, TRUE, 'Studio', 'Studiare per l''esame', 'Esame di Matematica'),
(5, FALSE, 'Personale', 'Chiamare il medico', 'Visita di controllo'),
(6, TRUE, 'Casa', 'Pulire la casa', 'Pulizie'),
(7, FALSE, 'Hobby', 'Leggere un libro', 'Romanzo giallo'),
(8, TRUE, 'Lavoro', 'Rispondere alle email', 'Email Urgenti'),
(9, FALSE, 'Viaggi', 'Prenotare un volo', 'Biglietto Aereo'),
(10, TRUE, 'Cucina', 'Provare una nuova ricetta', 'Ricetta Italiana');



--- Tabella degli elementi tradotti


CREATE TABLE TODO_ITEMS_TRANSLATED (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_ITA INT NOT NULL,
    DESCRIPTION_EN VARCHAR(255) NOT NULL,
    TITLE_EN VARCHAR(255) NOT NULL,
    FOREIGN KEY (ID_ITA) REFERENCES ITEMS(ID)
);

*********************************************

INSERT INTO TODO_ITEMS_TRANSLATED (ID, ID_ITA, DESCRIPTION_EN, TITLE_EN) VALUES
(1, 2, 'Do the grocery shopping', 'Shopping List'),
(2, 4, 'Study for the exam', 'Math Exam'),
(3, 7, 'Read a book', 'Mystery Novel'),
(4, 10, 'Try a new recipe', 'Italian Recipe');


--- CREAZIONE DELLA VISTA 

CREATE VIEW VIEW_item_translated AS SELECT  I.ID AS ID, T.TITLE_EN AS TITLE, T.DESCRIPTION_EN AS DESCRIPTION , I.CATEGORY AS CATEGORY, I.DONE AS DONE FROM ITEMS I JOIN TODO_ITEMS_TRANSLATED T ON I.ID = T.ID_ITA
    
