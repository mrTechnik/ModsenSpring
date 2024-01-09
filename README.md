# ModsenSpring

## ModsenSpring is a CRUD Web API for a library imitation with using [Java](https://www.java.com/ru/) [Spring](https://spring.io) framework.

## Schema:
```code
ModsenSpring
│   .gitignore
│
└───app
    │   .gitignore
    │   HELP.md
    │   mvnw
    │   mvnw.cmd
    │   pom.xml
    │
    ├───.mvn
    │   └───wrapper
    │           maven-wrapper.jar
    │           maven-wrapper.properties
    │
    ├───.vscode
    │       NEWLY_CREATED_BY_SPRING_INITIALIZR
    │
    ├───src
    │   ├───main
    │   │   ├───java
    │   │   │   └───library
    │   │   │       └───app
    │   │   │           │   AppApplication.java
    │   │   │           │
    │   │   │           ├───domain
    │   │   │           │       Book.java
    │   │   │           │       BookOrderID.java
    │   │   │           │       Usr.java
    │   │   │           │
    │   │   │           ├───jwtutilities
    │   │   │           │       JwtUtil.java
    │   │   │           │
    │   │   │           ├───repos
    │   │   │           │       BookRepo.java
    │   │   │           │       UsrRepo.java
    │   │   │           │
    │   │   │           ├───requests
    │   │   │           │       HttpPostJSONRequest.java
    │   │   │           │
    │   │   │           ├───secureconfig
    │   │   │           │       SecurityConfiguration.java
    │   │   │           │
    │   │   │           └───webservice
    │   │   │                   MainController.java
    │   │   │
    │   │   └───resources
    │   │       │   application.properties
    │   │       │
    │   │       ├───static
    │   │       └───templates
    │   └───test
    │       └───java
    │           └───library
    │               └───app
    │                       AppApplicationTests.java
    │
    └───target
        ├───classes
        │   │   application.properties
        │   │
        │   └───library
        │       └───app
        │           │   AppApplication.class
        │           │
        │           ├───domain
        │           │       Book.class
        │           │       BookOrderID.class
        │           │       Usr.class
        │           │
        │           ├───jwtutilities
        │           │       JwtUtil.class
        │           │
        │           ├───repos
        │           │       BookRepo.class
        │           │       UsrRepo.class
        │           │
        │           ├───requests
        │           │       HttpPostJSONRequest.class
        │           │
        │           ├───secureconfig
        │           │       SecurityConfiguration.class
        │           │
        │           └───webservice
        │                   MainController.class
        │
        ├───generated-sources
        │   └───annotations
        ├───generated-test-sources
        │   └───test-annotations
        └───test-classes
            └───library
                └───app
                        AppApplicationTests.class

```


Main Functionaliti:
1) getting a list of all books;
2) getting a book by is's ID;
3) getting a book by is's ISBN;
4) adding new book;
5) updating any book;
6) deleting any book.

A book information:
1) ID;
2) ISBN;
3) name;
4) jenre;
5) description;
6) author.

Endponts description:
1) ```addNewBook``` endpoint:
   It process incoming HTTP POST requests:
   example:
   ```bash
    curl -X POST http://localhost:8080/service/add --header "Content-Type: application/json"
    --data '{"isbn": <isbn>, "name": <name>, "jenre": <jenre>, "description": <descripton>, "author": <author>}'
    --header "Authorization: <Bearer token>"
   ```
      
   ```bash
    Saved
   ```
   1) It create a new book and save a book entry in PostgreSQL.
   2) It make outcomming POST HTTP request to the [LibraryService](https://github.com/mrTechnik/ModsenSpringOrder) microservice.
   3) Return a status of response.

2) ```updateBook``` endpoint:
   It process incoming HTTP PUT requests:
   example:
   ```bash
    curl -X PUT http://localhost:8080/service/update/1003 --header "Content-Type: application/json"
    --data '{"isbn": "111111", "name": "Fairy Tail", "jenre": "Simple", "description": "Nice book", "author": "Denchik"}'
    --header "Authorization: <Bearer token>"
   ```
      
   ```bash
    Updated
   ```
   1) It update containded book data in PostgerSQL.
   2) Return a status of response.

## Endpoints

3) ```getAllBooks``` endpoint:
   It process incoming HTTP GET requests:
   example:
   ```bash
    curl -X GET http://localhost:8080/service/get/all
    --header "Authorization: <Bearer token>"
   ```
      
   ```bash
   {ID: 1003, ISBN: 111111, Name: Fairy Tail, Jenre: Simple, Description: Nice book, Author: Denchik}{ID: 1004, ISBN: 113111, Name: Screw Tail, Jenre: Hard Rock, Description: Hard book, Author: Melisia}
   ```
   1) It get all book entries from the PostgerSQL and create a response.
   2) Return a status of response.

4) ```getBookById``` endpoint:
   It process incoming HTTP GET requests:
   example:
   ```bash
    curl -X GET http://localhost:8080/service/get/1003 
    --header "Authorization: <Bearer token>"
   ```
      
   ```bash
   {ID: 1003, ISBN: 111111, Name: Fairy Tail, Jenre: Simple, Description: Nice book, Author: Denchik}
   ```
   1) It get a book entry from the PostgerSQL by book's ID and create a response.
   2) Return a status of response.

5) ```getBookByIsbn``` endpoint:
   It process incoming HTTP GET requests:
   example:
   ```bash
    curl -X GET http://localhost:8080/service/get/isbn/111111 
    --header "Authorization: <Bearer token>"
   ```
      
   ```bash
   {ID: 1003, ISBN: 111111, Name: Fairy Tail, Jenre: Simple, Description: Nice book, Author: Denchik}
   ```
   1) It get a book entry from the PostgerSQL by book's ISBN and create a response.
   2) Return a status of response.

6) ```deleteBook``` endpoint:
   It process incoming HTTP DELETE requests:
   example:
   ```bash
    curl -X DELETE http://localhost:8080/service/delete/1004
   --header "Authorization: <Bearer token>"
   ```
      
   ```bash
   Deleted
   ```
   1) It delete a book entry from the PostgerSQL by book's ID.
   2) Return a status of response.

## Swagger

After after starting the application the ```Swagger``` and ```API-Docs``` are available on the (http://localhost:8080/swagger-ui/index.html) and (http://localhost:8080/api-docs) links.

## Running
For running clone repository:
```bash
https://github.com/mrTechnik/ModsenSpring.git
```
or download a zip file and extract it.
## 
Run the following command in a terminal window (in the ```app```) directory ([Resource](https://spring.io/guides/gs/spring-boot/)):
```bash
./mvnw spring-boot:run
```
OR
```bash
cd <path_to_directory>; & <path_to_java.exe>\java.exe @<path_to_argfile>\cp_boe4eo3ov2138n3wwqekuz5uc.argfile library.app.AppApplication
```
Example:
```bash
F:\>f:; cd f:\Just Work\Tests\ModsenSpring; & C:\Users\Администратор\.vscode\extensions\redhat.java-1.15.0-win32-x64\jre\17.0.6-win32-x86_64\bin\java.exe @C:\Users\836D~1\AppData\Local\Temp\cp_boe4eo3ov2138n3wwqekuz5uc.argfile library.app.AppApplication
```
