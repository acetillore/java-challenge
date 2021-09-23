### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years


#### What I did in this project
- Added Junit test classes for EmployeeController, Employee, EmployeeServiceImpl, ApiDemoApplication classes
and performed unit testing of those classes with 100% coverage
- Added cache processing for database calls. Also, I added a thread of 4sec just for the cache testing.
- Added error handling process when fetching the data from the database. 
- Added constraint (Not empty) condition to the Name parameter
- Added EmployeeErrorController class for the /error response
- Added SecurityConfig class for controller endpoints protection
  username : user
  password : password

#### My Java experience
I have around almost a year of work experience in Java, so I know some basics of Java language and Junit testing.
But I am a beginner of Java and new to Spring boot framework, and I just learned it at least the basics with this coding exercises. 
And very interested to learn more about it and to implement/develop more implementations with it.
