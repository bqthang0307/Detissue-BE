# 🧵 Detissue - Backend

Detissue is the backend service for a wholesale fashion e-commerce platform, built using Spring Boot. It handles core operations like authentication, product management, order processing, and user role-based access control.

## 📌 Features

- 🔐 JWT Authentication & Authorization
- 🧑‍💼 Role-based access (Admin, Staff, Customer)
- 🛒 Product & Category APIs
- 📥 Cart & Order Management
- 📈 Admin statistics endpoints
- 🖼️ Image Uploads for products
- ✅ Global Exception Handling
- 📂 Modular, Clean Project Structure

---

## 🧰 Tech Stack

| Layer            | Technology            |
|------------------|------------------------|
| Backend Language | Java 17                |
| Framework        | Spring Boot 3          |
| Security         | Spring Security + JWT  |
| Database         | MySQL                  |
| ORM              | Spring Data JPA (Hibernate) |
| Build Tool       | Maven                  |

---

## 📁 Project Structure

```
Detissue-BE/
├── src/
│   ├── main/
│   │   ├── java/com/DIY/Detissue/
│   │   │   ├── config/       # Security & Web config
│   │   │   ├── controller/   # API controllers
│   │   │   ├── entity/       # JPA entities
│   │   │   ├── exception/    # Custom exception handling
│   │   │   ├── filter/       # JWT filter
│   │   │   ├── payload/      # DTOs (requests/responses)
│   │   │   ├── provider/     # JWT utilities
│   │   │   ├── repository/   # JPA repositories
│   │   │   ├── service/      # Business logic layer
│   │   │   ├── utils/        # Helper utilities
│   │   │   └── DetissueApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/java/com/DIY/Detissue/
├── pom.xml
```

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/bqthang0307/Detissue-BE.git
cd Detissue-BE
```

### 2. Configure the database

In `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/detissue
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_secret_key
```

Make sure to create a database named `detissue` in your MySQL server.

---

### 3. Build and run the app

```bash
./mvnw clean install
./mvnw spring-boot:run
```

> App runs on: `http://localhost:8080/`

---

## 📡 API Overview

Here are some key endpoints (actual paths may vary):

| Method | Endpoint                 | Description               |
|--------|--------------------------|---------------------------|
| POST   | /api/auth/login          | Login and receive token   |
| POST   | /api/auth/register       | Register a new user       |
| GET    | /api/products            | Get list of products      |
| POST   | /api/admin/product       | Add new product (Admin)   |
| POST   | /api/cart/add            | Add product to cart       |
| POST   | /api/order/checkout      | Submit an order           |

> All protected routes require `Authorization: Bearer <token>` header.

---

## 🖼️ Image Handling

Product images are uploaded and stored in a static folder (`/uploads`) and are served via URL paths.

---

## 📌 Future Improvements

- ✅ Swagger/OpenAPI Documentation
- ✅ Email Notifications
- ✅ Payment Gateway Integration
- ✅ Caching with Redis
- ✅ Unit & Integration Tests

---

## 🤝 Contributing

Pull requests and issues are welcome! If you find a bug or want to propose a feature, feel free to open an issue or submit a PR.

---

## 📄 License

This project is currently for educational purposes only. Contact the author for commercial usage or licensing inquiries.

---

Made with 💙 by [bqthang0307](https://github.com/bqthang0307)
