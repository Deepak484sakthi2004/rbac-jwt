# Ride Pooling Application {RBAC-JWT}

A Spring Boot-based backend application for a ride-pooling platform (like bla bla cars). This platform provides features for users to register, log in, and choose roles (Driver or Passenger) based on their preferences. Drivers can post rides, and passengers can request rides. The system includes role-based authentication and session management using JWT tokens.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
- **MySQL**: Used as the database for storing application data.
- **Redis**: Ride cache for faster retrival.
- **Java Development Kit (JDK)**: Ensure you have Java 17 or higher installed.
- **IntelliJ IDEA**: Recommended IDE for Java-based projects.

## Setup Instructions

### Step 1: Create the Database
1. Open your MySQL client (e.g., MySQL Workbench or CLI).
2. Create a new database. The name of the database should match the `spring.datasource.name` in your `application.properties` file.
   ```sql
   CREATE DATABASE {db_name};

### Step 2: Run the Application
1. Open postman / altair to access the endpoints {I have used graphql as api}
2. Create a new Ping the endpoint `http://localhost:8080/auth/register` with the parameters:
      ```sql
             {
              "name":"deepak",
              "email": "deepak@driver.com",
              "password": "12345",
              "roles":["DRIVER"]
       `     }
      
  It responds a Bearer auth key, save it in the Bearer Authenication under Authorisation section in postman/ altair
3. Now hit the endpoint  `http://localhost:8080/graphql` and choose the body as graphql, use the below body to req/res!


   1. [Create Driver](#create-driver)
   2. [Create Passenger](#create-passenger)
   3. [Create Ride](#create-ride)
   4. [Accept Booking Request](#accept-booking-request)
   5. [Reject Booking Request](#reject-booking-request)
   6. [Create Passenger Review](#create-passenger-review)
   7. [Get Driver](#get-driver)
   
   ---
   
   ### 1. Create Driver
   Mutation to create a new Driver
   
   ```graphql
   mutation CreateDriver {
           createDriver(
             createDriverRequest: {
               userEmail: "deepak@driver.com"
               phoneNumber: "9876543210"
               licenseNumber: "DL12345678"
               vehicleDetails: "Toyota Innova, White, 2022 Model"
               vehicleRegistrationNumber: "KA01AB1234"
             }
           ) {
             driverId
             user {
               userId
               name
               email
               roles
             }
             phoneNumber
             rating
             licenseNumber
             vehicleDetails
             vehicleRegistrationNumber
             createdAt
             updatedAt
           }
         } ```



