# p2-Team-5
Guidelines and requirements for Project 2

> Create a full-stack application to develop associate skills and create familiarity with using testing and behavior-driven development cycles.

## High Concept
A resource-management focused adoptable pets app using multiple databases to track items and pet states, as well as enable inter-user communication. 

### Presentation date: July 18, 2022

## Requirements
1. Must be a full-stack web application
2. Development process must be Agile/Scrum utilizing a GitHub Project Board
3. Application code must be on GitHub so that all team members can contribute
    - Create a repository in our GitHub Organization
    - naming convention -> `p2-team-<team number>` or `p2-<team name>`
4. Some reasonably complex data should be persisted (more than 2-3 tables)
5. Must follow TDD and BDD Methodologies  
    - Cucumber Feature Files and Selenium Step Implementations should be written first (at least 5 Feature Files)
    - Unit Tests are written first (at least 20 to start)
    - After tests are written, coding implementations can be worked on.
6. Test Documentation is used to guide development, including (but not limited to):
    - Test Plan
    - Test Cases
    - Test Reporting
 
 ## Technical Requirements
1. Back end must be as RESTful as possible (following REST constraints)
2. Back end uses the following technologies: Java 8+ with JDBC and Javalin
3. Front end is created with HTML, CSS/Bootstrap, and JavaScript
4. Back end is deployed on an AWS EC2
5. Front end is deployed on AWS S3
6. Database is PostgreSQL on AWS RDS
7. Can use external APIs if desired

### Application Development Flow
Use a kanban board to keep track of the following categories of feature/story:
- Sprint Backlog: all of the stories to be completed for the current sprint  
- In Progress: the stories currently being worked on; this should be ONE per developer (aside from setup tasks like building database schema, models, etc.)  
- Testing: the stories currently being tested by the person who worked on the story; if a developer has a story here, they should not have one in the other sections (unless it's complete).  
- Peer Review: the stories which are being tested and whose code quality is being reviewed by another group member (a developer who did not work on the story)
- Complete: the stories which have been completed, tested, and reviewed; once a story gets here, the developer working on it can begin a new story

## MVPs
- Users can login and logout
- Users can edit data fields of specifically their account 
- Users can create pets
- Users can change certain pet values (ex: hunger) with items
- Items can be assigned to the inventory of a user or one of their pets
- Item assignment drop-down auto-populates with all of a user's pets
- Items can be used on pets

## Stretch Goals
- Users can add comments to user pages
- Users can edit/delete their own comments
- Pets can be adopted from a pound
- Pets can be sent to the pound 
- Pet values can change over time 
- Users can edit pet data fields (ex: name)
- Users can acquire new items 
- Some items disappear/change on use 

## Tech Stack
- Java 8
- Apache Maven
- Selenium
- Cucumber
- Apache Log4j 2
- PostgreSQL
- AWS RDS
- AWS S3
- AWS EC2
- Javalin
- JUnit
- JDBC 
- JavaScript
- AJAX / Fetch API
- HTML
- CSS / Bootstrap
