
# Ride Pooling Application {RBAC-JWT}

A Spring Boot-based backend application for a ride-pooling platform (like bla bla cars). This platform provides features for users to register, log in, and choose roles (Driver or Passenger or Admin) based on their preferences. Drivers can post rides, and passengers can request rides. The system includes role-based authentication and session management using JWT tokens.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
- **MySQL**: Used as the database for storing application data.
- **Redis**: Ride cache for faster retrieval.
- **Java Development Kit (JDK)**: Ensure you have Java 17 or higher installed.
- **IntelliJ IDEA**: Recommended IDE for Java-based projects.

## Setup Instructions

### Step 1: Create the Database
1. Open your MySQL client (e.g., MySQL Workbench or CLI).
2. Create a new database. The name of the database should match the `spring.datasource.name` in your `application.properties` file.
   ```sql
   CREATE DATABASE {db_name};
   ```

### Step 2: Run the Application
1. Open Postman/Altair to access the endpoints (GraphQL is used as the API format).
2. Create a new POST request to the endpoint `http://localhost:8080/auth/register` with the following body:
   ```json
   {
     "name": "deepak",
     "email": "deepak@driver.com",
     "password": "12345",
     "roles": ["DRIVER"]
   }
   ```
   It responds with a Bearer authentication key. Save this key in the Bearer Authentication section under the Authorization tab in Postman/Altair.
3. Now hit the endpoint `http://localhost:8080/graphql`, set the body type to GraphQL, and use the provided mutations and queries for various operations.

---

## GraphQL API Endpoints

### 1. Create Driver
Mutation to create a new Driver.

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
}
```

### 2. Create Passenger
Mutation to register a user as a passenger.

```graphql
mutation CreatePassenger($email: String!, $phoneNumber: String!) {
  createPassenger(email: $email, phoneNumber: $phoneNumber) {
    passengerId
    user {
      userId
      name
      email
    }
    phoneNumber
    rating
  }
}
```
Pass the email and phonenumber as the Qraphql parameters!

### 3. Create Ride {ROLE : DRIVER}
Mutation to post a new ride by a driver.

```graphql
mutation CreateRide($createRideRequest: CreateRideRequest!) {
  createRide(createRideRequest: $createRideRequest) {
    rideId
    origin
    destination
    departureTime
    arrivalTime
    availableSeats
    totalSeats
    vehicleType
    rideStatus
    createdAt
  }
}
```
pass these below as GRAPHQL PARAMETERS:
```json
{
  "createRideRequest": {
    "driverUserId": 123,
    "origin": "Chennai",
    "destination": "Bangalore",
    "departureTime": "2024-12-01T9:00:00Z",
    "arrivalTime": "2024-12-01T14:00:00Z",
    "availableSeats": 3,
    "totalSeats": 4,
    "vehicleType": "Sedan",
    "rideStatus": "PENDING",
    "passengerIds": []
  }
}
```

### 4. Accept Booking Request
Mutation to accept a passenger's booking request.

```graphql
mutation AcceptBookingRequest($bookingRequestId: Int!) {
  acceptBookingRequest(bookingRequestId: $bookingRequestId) {
    bookingRequestId
    status
    updatedAt
  }
}
```

```json
{
  "bookingRequestId": 1
}

```

### 5. Reject Booking Request
Mutation to reject a passenger's booking request.

```graphql
mutation RejectBookingRequest($bookingRequestId: Int!) {
  rejectBookingRequest(bookingRequestId: $bookingRequestId) {
    bookingRequestId
    status
    updatedAt
  }
}
```

### 6. Create Passenger Review
Mutation to submit a review for a passenger.

```graphql
mutation CreatePassengerReview($passengerId: ID!, $rating: Float!) {
  createPassengerReview(passengerId: $passengerId, rating: $rating) {
    id
    passenger {
      passengerId
      name
      email
    }
    rating
  }
}
```

### 7. Get Driver
Query to fetch driver details by email.

```graphql
query GetDriver($email: String!) {
  getDriver(email: $email) {
    driverId
    user {
      userId
      name
      email
    }
    phoneNumber
    rating
    licenseNumber
    vehicleDetails
    vehicleRegistrationNumber
    createdAt
    updatedAt
  }
}
```

---

## Notes
- **Authorization:** All secured endpoints require a JWT token to be included in the `Authorization` header.
- Use tools like **Postman** or **GraphQL-Altair Playground** to test the API endpoints.
