# Student Management System   

## Features of this Project
- Save
- Show all
- Find by ID
- Find by Email
- Find by Index number
- Find between two dates of birth
- Delete by ID
- Delete by Email

## Environment
- Eclipse IDE
- Java 11
- Spring
- Spring initializr
- H2 Embedded Database

## Operating Instructions
- Download source code and import into your code editor (Eclipse, IntelliJ..).
- Update Maven in project.
- If there is no errors, run program.
- Use [Postman](https://www.postman.com) to check app.

## URL : http://localhost:8080/api/v1/student-ms + 
- ### /add
Add student with this in body: <br/>
{ <br/>
    "firstName": "Ognjen", <br/>
    "lastName": "Andjelic", <br/>
    "dateOfBirth": "1999-12-01", <br/>
    "email": "andjelicb.ognjen@gmail.com", <br/>
    "indexNumber": 183, <br/>
    "onBudget": true <br/>
}
- ### /all
List all students from DB.

- ### /find/{id}
Find student in DB by ID.

- ### /email/{email}
Find student in DB by Email.

- ### /index/{index}
Find student in DB by Index.

- ### /date-of-birth
Find students (list) between two dates of birth. <br/>
Put this as example parameters: <br/>
KEY: dob1 <br/>
VALUE: 1999-12-01 <br/>
KEY: dob2 <br/>
VALUE: 2004-10-12

- ### /delete/{id}
Delete student with ID.

- ### /delete-with-email/{email}
Delete student with Email.





