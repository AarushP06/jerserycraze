Java Web Programming – 420-N34-LA – Fall 2025
Instructor: Prof. Morteza Khanjanzadeh
Term Project – Milestone 2
Full-Stack Integration (React + Spring Boot + SQL Database)
### Overview

Jersey Craze lets you:

View jerseys and customers

Search

Add

Edit

Delete

Use pagination (5 per page)

Store data in a real SQL database

Work with a clean dark UI

Both pages use card layouts, modal forms, and API calls.

### Project Structure
```
project-root/
├── frontend/
│   ├── src/
│   │   ├── pages/          # Jersey + Customer pages
│   │   ├── components/     # Cards, modals, forms
│   │   ├── api/            # Axios API helpers
│   │   ├── styles.css      # Main styles
│   │   └── App.jsx         # Layout + routes
│   └── package.json
│
└── backend/
    ├── src/main/java/com/champsoft/jerserycrazedatabase/
    │   ├── bootstrap/      # Data seeding
    │   ├── business/       # Services + mappers
    │   ├── config/         # CORS + Swagger
    │   ├── dataaccess/     # Entities + repositories
    │   ├── exceptions_utilities/ # Error handling
    │   └── presentation/   # REST controllers
    └── src/main/resources/
        └── application.properties
```
### Tech Stack
## Frontend

React

Vite

Axios

Custom modal system

Dark UI with glass-style cards

## Backend

Spring Boot

Spring Data JPA

MySQL or MariaDB

Swagger

CORS setup

### Database Requirements

Your database must include:

At least 10 customers

At least 50 jerseys

Valid links between data

All CRUD working in Postman

Tables created by JPA

### Backend Setup
1. Move into the backend folder
cd backend

2. Open the project in IntelliJ
3. Update database settings

Edit:

src/main/resources/application.properties


Example:
```
spring.datasource.url=jdbc:mysql://localhost:3306/jerseycraze
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
4. Run the Spring Boot backend

From IntelliJ or terminal:
```bash
./mvnw spring-boot:run
```

API runs at:

http://localhost:8080

### Frontend Setup
1. Move into the frontend folder
```bash
cd frontend
```
2. Install dependencies
 ```bash
npm install
```
3. Create a .env file
```bash
VITE_API_URL=http://localhost:8080
```
4. Start the frontend
```bash
npm run dev
```

Frontend runs at:
```
http://localhost:5173
```
### API Examples
Get all jerseys (paginated)
GET /api/jerseys?page=0&size=5

# Add a jersey
POST /api/jerseys

```
Body:

{
  "team": "Real Madrid",
  "size": "M",
  "price": 99.99,
  "imageUrl": "https://..."
}
```
# Delete a jersey
```
DELETE /api/jerseys/{id}
```
### Current Status
Done

Backend with SQL

Swagger

Full CRUD

Pagination

Search

React pages for both sections

Add modal

Edit modal

Delete actions

Clean dark UI

Database integration

App works end-to-end.

### Team

Aarush Patel
Hadrian Gosset
Liautaud Ryan Kaleb
