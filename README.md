# Sigece

## Overview

**Sigece** is a comprehensive communications system tailored for enterprise environments. It offers robust functionality for managing user roles, enabling real-time chats, sending mass emails, and ensuring secure access through SOAP and REST web services. The system provides a customizable and user-friendly interface for optimal user experience.

## Features

- **Role Management**: Easily administer user roles and permissions to ensure secure access control.
- **Messaging**: Real-time chat feature to streamline communication within the organization.
- **Mass Email**: Efficiently manage and send bulk emails for large-scale communication.
- **Customizable Interface**: Personalize the system layout to fit specific organizational needs.

## Technologies Used

The project is built using a modern tech stack to ensure performance, reliability, and scalability:

- **Java 21**: Core programming language.
- **JavaFX 21**: For a rich graphical user interface.
- **Payara Server 6**: Application server for Java EE applications.
- **Oracle 21c XE (Docker)**: Database management, can also be installed directly on the local machine.
- **IDE**: VSCode and NetBeans for development.
- **SQL**: Database queries and operations.
- **JPA**: Java Persistence API for ORM.
- **Apache POI**: To generate and handle Excel files.
- **Assembly Jar Maker**: For compiling and packaging the project.
- **REST and SOAP Web Services**:
  - **SOAP**: Ensures secure access for authentication and user authorization.
  - **REST**: Used for email sending and real-time messaging.
- **Java Enterprise 10**: Guarantees enterprise-level security and performance.

## Installation and Setup

Follow these steps to set up and run **Sigece**:

1. **Install Java JDK 21**  
   Ensure that JDK 21 is installed on your machine.

2. **Install and Configure Payara Server**

   - Download and set up Payara Server 6.
   - Configure a connection pool named `SigecePool`.
   - Set up a JDBC resource named `jdbc/Sigece`.

3. **Set Up Oracle Database**

   - Install Oracle 21c XE, either locally or using Docker.
   - Make sure it is accessible by Payara Server for database operations.

4. **Run the Application**
   - Build the project and package it as a JAR file.
   - Run the JAR files directly to start the application.

## Contributors and Instructor

- **Developers**:
  - Alejandro León Marín
  - Kendall Fonseca
- **Instructor**:
  - MSc. Carlos Carranza Blanco

---

This README provides a clear overview of **Sigece**, the technologies used, and the installation steps, making it straightforward for new users and contributors to set up and run the project.
