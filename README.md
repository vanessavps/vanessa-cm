# CM assignment

## Open API
You can find Swagger API on http://localhost:8080/cm/swagger-ui/index.html

## Assignment requisites

    A client has requested the implementation of an application that provides:
    Ability to manage a list of contacts and update their details,
        Each contact has a name, email and phone number,
        Each contact can work for one or more organisations,
        There will potentially be thousands of customers.
    Ability to manage a list of organisations and update their details,
        Each organisation has a name, address and a url,
        There will potentially be hundreds of customers.
    Ability to get a list of organisations.
    Ability to get a list of contacts by organisation.

    For the assignment, we ask that you create the API (e.g. RESTful Web Services) that would be needed by this application. 
    No frontend/user interface is required for the assignment.
    The implementation should:
    - Be developed in Java 8+ (and ideally make use of Spring Boot (but not mandatory)),
    - Include interaction with, and data persistence in, a database (which you should design based on the requirements).

## Database

### Schema
Schema can be found in the [ddl.sql](database/ddl.sql) file

### Application User
During the implementation I created a user to be used by the application. It has limited permissions. 
Is not necessary, but I will leave the code here.

    #Create new user to be used by the application
    CREATE USER 'CM'@'localhost' IDENTIFIED BY 'centricminds';
    GRANT INSERT, UPDATE, DELETE, SELECT on *.* TO 'CM'@'localhost' WITH GRANT OPTION;

## Completion time
I implemented this within the given 3 hours.

