**Final Challenge – DAMI Hotel**  
📝 **Description**  
Desktop application for hotel management that allows you to manage clients, rooms, reservations, and extra services. The project includes a login system and a main menu with graphical interfaces, connected to a relational database for data persistence.

🧰 **Technologies Used**  
- Java (Swing for graphical interfaces)  
- 🗄️ MySQL  
- 🔌 JDBC (MySQL connector)  
- Mockito (testing with JARs)  
- Tools: NetBeans / Eclipse / VS Code / IntelliJ IDEA  

📚 **Dependencies**  
This project uses the following external libraries:

- MySQL JDBC Connector  
- Mockito  

Make sure to add the .jar files to the project:

- **In Eclipse:** Right‑click the project → *Build Path* → *Configure Build Path* → *Libraries* → *Add JARs…*  
- **In NetBeans:** *Properties* → *Libraries* → *Add JAR/Folder*  
- **In IntelliJ:** *File* → *Project Structure* → *Modules* → *Dependencies*  

🗄️ **Database**  
The project includes the database script:

▶️ **How to use it**  
1. Create a database in MySQL.  
2. Import the file:  
   a. From console:  
      `mysql -u user -p database_name < bd.sql`  
   b. Or using a graphical tool such as MySQL Workbench, phpMyAdmin, or DBeaver.

📦 **Installation**

📥 **Clone the repository**  
```
git clone https://github.com/user/repository.git
```

📁 **Import the project**  
Open the project locally in your preferred IDE (Eclipse / IntelliJ / VS Code / NetBeans).

➕ **Add dependencies manually**  
Download / locate:  
a. MySQL JDBC Connector (included in the project).  
b. Mockito libraries.  

Add the .jar files to the project’s classpath/build path (see the “Dependencies” section).

▶️ **Execution**  
- Configure the MySQL database connection in the project (you can verify credentials and database settings in files such as `src/configClase.properties` or in your database model classes).  
- Run the application from the main class:  
  `src/principal/ProgramaPrincipal.java`

### **Tests**  
The project includes unit tests using Mockito (in the *test* package). To run them:

- From the IDE → Right‑click the test class or the *test* folder → *Run as JUnit Test*.
  
Make sure the Mockito (and JUnit) JARs are correctly added to the build path before running the tests.
