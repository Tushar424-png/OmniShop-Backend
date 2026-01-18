# 🛒 Microservices-Based E-Commerce Backend System

This project is a **scalable, secure, microservices-based e-commerce backend** built using **Spring Boot, Spring Cloud, JWT Authentication, Redis, and API Gateway**.

The system follows **real-world architecture practices** like service discovery, centralized authentication, role-based authorization, caching, rate limiting, and fault tolerance using circuit breakers.

---

## 🚀 Architecture Overview

All client requests go through **API Gateway**.  
Services communicate with each other using **REST + Service Discovery (Eureka)**.

### 🔹 Services Included

- **API Gateway**
- **Service Registry (Eureka Server)**
- **Auth Service**
- **Product Service**
- **Inventory Service**
- **User Service**
- **Order Service**

---

## 🔐 Authentication & Security

- JWT-based authentication
- Role-based authorization (**ADMIN / USER**)
- Centralized security at API Gateway
- Token validation handled only by API Gateway
- Rate limiting applied on login APIs

---

## 🧩 Service Responsibilities

### 🟢 API Gateway
- Single entry point for all APIs
- JWT validation
- Role-based access control
- Rate limiting for login requests
- Circuit breaker with fallback methods
- Routes requests to respective services

---

### 🔑 Auth Service
- User registration
- User login
- Generates JWT token
- On registration:
  - Stores authentication data
  - Sends user profile data (name, email, address) to User Service

---

### 📦 Product Service
- Add products (ADMIN)
- Get product list (public)
- Product activation / deactivation
- Uses Redis caching for product listing

---

### 🏬 Inventory Service
- Stock restock (ADMIN)
- Reduce stock on order
- Check stock availability
- Automatically marks product **OUT_OF_STOCK**
- Calls Product Service to deactivate product when stock is zero

---

### 👤 User Service
- Manages user profile data
- Cart management
- Wishlist management
- Provides cart details to Order Service
- Uses Redis cache for user profile data

---

### 🛍️ Order Service
- Create order (USER)
- Order status tracking
- Order validation flow:
  1. Fetch cart items from User Service
  2. Check stock via Inventory Service
  3. Create order
  4. Reduce inventory
- Uses Redis for order status caching

---

## 🔁 Circuit Breaker

- Implemented between inter-service communication
- If any dependent service is down:
  - Fallback method is executed
  - System remains stable and responsive

---

## ⚡ Redis Usage

- Product listing cache
- User profile cache
- Order status cache
- API Gateway rate limiting

---

## 🔐 Role-Based Access Control

### 👑 ADMIN
- Restock inventory
- Add products
- View all orders
- View all users
- View all carts & wishlists

### 👤 USER
- Add/remove cart items
- Add/remove wishlist items
- Create and check orders
- View own profile

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Cloud Gateway
- Spring Security (JWT)
- Eureka Service Discovery
- Redis
- MySQL
- Resilience4j (Circuit Breaker)
- Maven
- Git & GitHub

---

## 📌 Key Features

- Microservices architecture
- Secure JWT authentication
- API Gateway with centralized security
- Redis caching
- Rate limiting
- Circuit breaker & fallback handling
- Role-based authorization
- Clean and scalable code structure

---

## 📂 Project Structure
api-gateway/
auth-service/
product-service/
inventory-service/
user-service/
order-service/
service-registry/


---

## 📬 Future Enhancements

- Docker & Kubernetes deployment
- PostgreSQL support
- React frontend
- Spring AI integration
- Payment gateway integration

---

## 👨‍💻 Author

**Tushar Handa**  
Java Backend / Full Stack Developer  
Spring Boot | Microservices | JWT | Redis  

GitHub: (https://github.com/Tushar424-png/)
