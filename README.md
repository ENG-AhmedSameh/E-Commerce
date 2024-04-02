# E-Commerce Web Application

This is a simple E-Commerce web application developed as part of the evaluation for Client-Side Technologies Courses (HTML5, CSS3, JavaScript), Servlets & JSP Course, Asynchronous Development Course, and ORM.

# Contributers
- @EslaM-AhMed14
- @tatashii

## DataBase Schema
![erd](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/c3b280e3-aaf7-487f-85ec-ad2461249601)
## Main Functionalities

### Home Page
The application starts with a home page Showing the products and the categories.
![homePage1](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/4f805fb1-cebf-431e-b28e-e2df1c6a3cd8)
![homePage2](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/20c32e1d-dcbe-4199-90c3-f76b7e575b47)

### User
A user can:
- Sign up for the first time and enter their profile data (name, birthday, password, job, e-mail, credit limit, address, interests, etc.)
- Sign in for the next visits with their e-mail and password.
- Edit their profile.
- View all available products.
- Categorize the products.
- Add/remove products to their shopping cart.
- Virtually buy the products in their shopping cart within their credit limit.
- After the buying process is completed, the user's credit limit and the products quantities should be updated and their cart reset.
  ![signup](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/1c82d633-8e56-421f-a79b-465b130e73c4)
  ![profile](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/cd697f6d-ade2-4dd4-9aae-10fbb2fce755)
- ![shop](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/6f2a2358-62e2-4f8b-827e-bcc222ad53d5)
  ![filter](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/a4af9ffa-ac1a-4015-95b2-74d7baf98b65)
  ![product](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/a4b280f4-9a8a-41e8-bf60-dd083330311a)
  ![cart](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/7525dcd1-a7ba-41fa-8f41-b9924ae02cc5)


### Shopping Cart
Storing the shopping cart products in database if the user signs out before buying.
![Shopping cart](https://github.com/ENG-AhmedSameh/E-Commerce/assets/101069344/9dbba836-a543-4c36-a999-d244e0fd7acf)

## Technologies Used
- Backend: Java Servlets and JSP, implementing a Layered Architecture inclusive of MVC, Front Controller, and Intercepting Filter design patterns.
- Frontend: HTML, CSS, Bootstrap, JS, and JQuery, enhanced with AJAX for seamless asynchronous operations.
- Server: Deployed on Tomcat 10 for reliable web server container management.
- Data Handling: Hibernate for Object-Relational Mapping (ORM), ensuring efficient data manipulation.
- Dependency Management: Maven, for streamlined project builds and dependencies.
- Connection Pooling: Utilized HikariCP for efficient database connection pooling.


# Getting Started
To get a local copy up and running, follow these steps:

- Clone the repository.
- Import the project into your IDE.
- Set up your Java development environment.
- Set up your database and create a user with credentials specified in the persistence.xml file for hibernate connection then import the schema.
- Set up a tomcat server and deploy the application using the command mvn clean tomcat7:redeploy.