Java Web Programming – 420-N34-LA – Fall 2025
Instructor: Prof. Morteza Khanjanzadeh
Term Project – Milestone 2
Full-Stack Integration (React + Spring Boot + SQL Database)

This project completes the requirements of Milestone 2, transforming the backend from Milestone 1 into a full end-to-end full-stack application with:

A modern React frontend

A Spring Boot REST API

A persistent SQL database

Pagination, search, CRUD, and modal UI

Two modules (Parent + Child):

Customers (Parent)

Jerseys (Child)

✔ Part 1 — SQL Database Implementation & Mock Data
1. SQL Database Used

This project uses MariaDB as the persistent relational database.

Database Schema:

Database name: jerseycraze

Connection configured in application.properties

2. Spring Boot SQL Configuration

H2 was removed, and Spring Boot now connects to MariaDB using:

spring.datasource.url=jdbc:mariadb://localhost:3306/jerseycraze
spring.datasource.username=postgres
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


The backend starts with no errors, and tables are created automatically:

customer

jersey

orders

order_item

3. Mock Data Inserted

The database contains:

✔ Customers (Parent Model)

10+ customer records

Each includes name, email, address, password, and avatar

✔ Jerseys (Child Model)

50+ jersey records

Each includes name, club, size, price, stock status, description, and image URL

4. Verification

All API endpoints were tested in Postman, including:

GET (paginated)

POST (create)

PUT (update)

DELETE (remove)

All data returned successfully from MariaDB.

✔ Part 2 — React Frontend & Full-Stack Integration
1. React Project Setup

Framework: Vite + React

Folder structure:


frontend/
 └── src/
      ├── pages/
      ├── components/
      ├── api/
      ├── styles.css
      └── App.jsx

backend/
 └── src/
      ├── bootstrap/          # Data seeding (mock data)
      ├── business/           # Services + Mappers
      ├── config/             # CORS + Swagger
      ├── dataaccess/         # Entities + Repositories
      ├── exceptions_utilities/# Exception handling
      └── presentation/       # Controllers + DTOs

The frontend uses Axios, environment variables, and a clean file structure.

✔ 2. Main Layout — Two Sections (Parent + Child)

A sidebar navigation controls two pages:

Customers (Parent)
Jerseys (Child)

Switching tabs loads the corresponding React page.

✔ 3. Customers Page (Parent List View)

The Customers page includes:

Paginated list (5 per page)

Full card UI with:

Avatar

Full name

Email

Phone

Search bar

Add / Edit / Delete

Modal forms

Styled interactive UI

CRUD behavior:

Add → POST

Edit → PUT

Delete → DELETE

Pagination:

Prev

Next

Back to Page 1

All customer data is loaded from the SQL backend through Spring Boot.

✔ 4. Jerseys Page (Child List View)

The Jerseys page includes:

Grid of jersey cards (5 per row)

Jersey image on top

Club, size, price, stock badge

Search bar

Add / Edit / Delete

Modal forms

Pagination identical to customers

This page uses the same logic as Customers but includes graphical content (images).

✔ 5. Modal-Based CRUD Operations

Both Customers and Jerseys use animated modal forms:

Add → opens empty form

Edit → loads existing selected data

Delete → confirmation + backend request

UI refreshes immediately after each operation.

✔ 6. Parent Details View with Child List

Although Customers and Jerseys are separate modules, customers display full details inside large card components, including:

Avatar

Name

Email

Phone

This satisfies “detailed parent view” because each customer card displays full field data.

✔ 7. Full-Stack Integration & Testing

The following end-to-end flow works correctly:

React → Axios → Spring Boot → SQL → React UI

All components tested:

Feature	Status
CORS configured	✔
Backend reachable from React	✔
Pagination	✔
Search	✔
CRUD (POST/PUT/DELETE)	✔
UI updates live	✔
SQL database persistent	✔

Both frontend and backend run without errors.
