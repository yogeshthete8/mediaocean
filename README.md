# mediaocean
This repository for mediaocean's assignment

Problem Statement :

Build a RESTful service that implments a check out counter for an online retail store that scans products and generates an itemized bill.
The bill should also total the cost of all the products and the applicable sales tax for each product.
The total cost and total sales tax should be printed
Sales tax varies based on the type of products
1. category A products carry a levy of 10%
2. category B products carry a levy of 20%
3. category C products carry no levy
############################################################################################################
Technologies : 
1. Java
2. Spring Boot 
3. Spring Security
4. Spring JPA
5. Maven 
6. Swagger2
7. H2 Database
8. JUnit

#############################################################################################################

Build & Run Commands:
1. Clone Repo  - git clone https://github.com/yogeshthete8/mediaocean.git
2. Build project - mvn clean install
3. Run Project using jar - 
    a. cd target
    b. java -jar OnlineRetailStore-0.0.1-SNAPSHOT.jar
    c. Default port - 8080
4. Accecc API using Swagger - http://localhost:8080/swagger-ui.html

5. credentials for authentication
	1. 	username: yogesh
		passwod: password
	2.	username: admin
		password: admin
