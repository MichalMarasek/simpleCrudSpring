Simple CRUD application using Java, Spring Boot, MySQL, Docker, REST API.

This is example of how to create Docker database and REST Controller 
which lets us perform CRUD (create, read, update, delete) operations on the database.

Docker info:
To pull docker mysql image use: 
docker pull mysql
To start docker mysql container use: 
docker run --name booklibrary-mysql -e MYSQL_ROOT_PASSWORD=passw -d -p 3306:3306 mysql 

To check if docker image started correctly use: 
docker ps

In case of any problems you can delete container using: 
docker rm --force booklibrary-mysql

To stop your container use:
docker stop <container-id> 

or more brutal way:
docker kill <container-id>


To manage database you can use any MySQL compatible client eg. MySQL Workbench
https://dev.mysql.com/downloads/workbench/

Database info:
Schema name: booklibrary
Table:
CREATE TABLE `book` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(200) DEFAULT NULL,
`rating` int DEFAULT NULL,
PRIMARY KEY (`id`)
)

To use correct schema in your query:
use booklibrary;

To insert data manually:
INSERT INTO book(name, rating) VALUES('Kings Quest', 8);

To select all:
SELECT * FROM book;

To delete records:
DELETE FROM book WHERE id=1;

You can test your API using postman:
https://www.postman.com/downloads/


Sample payload for post operation to add books:
[
{
"name" : "Amber Chronicles",
"rating" : 7
},
{
"name" : "fun stuff",
"rating" : 5
}
]

