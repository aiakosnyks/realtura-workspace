# Real Estate Application

This project is a real estate application where users can create, edit, delete, and view listings. Additionally, users can activate or deactivate their listings and purchase packages for publishing rights.

## Project Overview

### Backend

#### Project Description:
The backend system allows users to manage their listings. Users can:
- Create, edit, delete, and view listings
- Activate or deactivate their listings
- Purchase packages to gain publishing rights for their listings

#### Product: Listing Publishing Rights
- Each package includes the right to publish 10 listings and is valid for one month.
- Users without a valid package cannot publish listings.
- Users can purchase a new package before the current one expires, extending the validity by one month from the remaining validity period.

#### Requirements:
- Users must log in to publish listings.
- Users can view active and inactive listings.
- Users can view purchased packages.
- Users can update listings status to ACTIVE or PASSIVE.
- Purchased products should be assigned asynchronously after a successful payment.

#### Technologies Used:
- Java 8
- JUnit 5
- Spring Boot
- RESTful API
- MySQL / PostgreSQL / MongoDB
- RabbitMQ
- Microservice architecture

#### System Assumptions:
- Products are predefined in the system; no need for a new service for product creation.
- Products are sold in units of 10.
- Product validity is limited to 1 month (30 days).
- Payment process requires necessary records to be written to the system.
- Payment process should be synchronous.
- Listings are saved with a default status of IN_REVIEW and should be changed to ACTIVE asynchronously.
- Users can update listings status to only ACTIVE or PASSIVE.

#### Listing Statuses:
- ACTIVE
- PASSIVE
- IN_REVIEW

#### Project Evaluation Criteria:
1. Backend project must function correctly as per the specified rules (20 points).
2. Correct implementation of microservice architecture and practices (20 points).
3. Unit Test coverage must be at least 90% (excluding Model and DTO) (15 points).
4. Usage of NoSQL, RDBMS (ORM), and Hibernate (JPA) technologies (10 points).
5. Implementation of logging and exception handling mechanisms (10 points).
6. Services should be accessible only by authorized users (10 points).
7. Code quality (Clean Code), structure, extensibility for new features, and understandability, including the use of design patterns (10 points).
8. Documentation (UML diagram, Readme, Postman queries, Swagger) (5 points).
9. Logging system should be polymorphic, meeting different logging needs (DB, text file, RabbitMQ) (Bonus 10 points).

### Frontend

#### Project Description:
The frontend application is developed using Next.js. Users can manage their listings and view existing ones.

#### Requirements:
1. Login page for user authentication with necessary validations (no empty username/password, toast notification for incorrect password).
2. Dashboard showing listings in a table or card layout with pagination or infinite scroll. Includes a button for creating new listings which opens a modal for the new listing creation.
3. Each listing should have edit and delete buttons. Listings can be edited, deleted, and toggled between active or passive status.
4. Users can filter and view active and passive listings.
5. Detailed view page for listings where users can see the details of each listing.
6. A page for users to view their package information.

#### Total Pages:
1. Login page
2. Listing dashboard
3. Listing edit/create page
4. Users own listing dashboard

## Getting Started

### Prerequisites
- Java 8
- Maven
- Node.js
- PostgreSQL / MongoDB
- RabbitMQ

### Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/aiakosnyks/realtura.git

2. **Run backend:**
   run each services jar files   

3. **Run frontend:**
   cd realtura-frontend
   npm run dev
