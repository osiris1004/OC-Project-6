 ![Telesport](/MDD.png)
 # MDD APP (Monde de Dév)

This is the README file for the DDM application. The application is a web application built using **sprint boot** and **angular**, with a **MySQL Server** database. The application allows users looking for job opportunities to consult the list of opportunities, and allows those offering opportunities to make them available to the public.

# Built With

This section show the list of any major frameworks/libraries used in the project. Here are a few examples.
 - Angular
 - spring boot
 - material ui
 - spring security

## Requirements
Before installing and running the MDD app, make sure you have the following requirements installed on your machine:

 - JAVA 17
 - NodeJS 16
 - MySQL Server 8


## Installation

To install and run the app, follow these steps:

Clone  the entire project : >`https://github.com/osiris1004/OC-Project-2.git`

A) ___IN THE BACKEND DIRECTORY___

1. Set up a MySQL Server and create a database named: `mddapi`.

2. data-credential:

    a. Edit the file "back/src/main/application.properties" with your database credentials.
        
        spring.datasource.username=YOUR USERNAME
        spring.datasource.password=YOUR PASSWORD
        
    b. Run the following command:

        mvn spring-boot:run
       
A) ___IN THE FRONTEND DIRECTORY___

2. Install NPM packages

        npm install

3. Start or run the project 
        
        npm start



## Git Flow
Git Flow is a branching model for Git, for managing the development and release of software. The Git Flow model consists of two main branches, that is the main and develop, as well as several supporting branches for feature development, bug fixes, and releases.

Here is a brief overview of the main branches and supporting branches in the Git Flow model:

1. **main branch(master branch)**: This is the main branch that contains the stable and production-ready code. The main(master) branch is always kept in a deployable state and represents the latest release version of the software.

2. **develop branch**: This branch is used for ongoing development and is the main integration branch for feature branches. All new development is merged into the develop branch, which is periodically merged into the master branch when a new release is ready.

3. **Feature branches**: These are branches created off the develop branch for developing new features or enhancements. Feature branches are short-lived and are typically deleted once the feature is completed and merged back into the develop branch.

4. **Release branches**: These are branches created off the develop branch for preparing a new release. Release branches are used to stabilize the code and perform final testing and bug fixing. Once the release is ready, the changes are merged into the master branch and tagged with a release version.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.


## Contact
Mailto : <a href="mailto:email@example.com, secondemail@example.com">Send Email</a>
