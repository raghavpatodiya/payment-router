# Payment Router

## Payment Routing Service

A Spring Boot microservice that receives a payment request, evaluates routing rules, selects the best payment gateway (Bank A / Bank B etc.), and routes the transaction synchronously. Designed to simulate real-world fintech payment routing and decision engines.

---

## 🔥 Features
- REST API based payment initiation
- Dynamic routing using priority rules
- Strategy pattern based gateway routing
- Database-backed transactions & routing rules (to be enabled next)
- Fallback / future retry capable design
- Clean layered architecture

---

## ✅ Prerequisites
- Java 17+
- Maven
- (Later) PostgreSQL

Verify:
```
java -version
mvn -v
```

---

## 🏗️ Project Structure

```
payment-routing-service
│
├─ pom.xml
├─ src
│  ├─ main
│  │   ├─ java
│  │   │   └─ com.raghav.paymentrouting
│  │   │        ├─ DemoApplication.java
│  │   │        ├─ controller
│  │   │        │     └─ PaymentController.java
│  │   │        ├─ service
│  │   │        │     ├─ PaymentService.java
│  │   │        │     └─ RoutingEngine.java
│  │   │        ├─ strategy
│  │   │        │     ├─ RoutingStrategy.java
│  │   │        │     ├─ BankARouter.java
│  │   │        │     └─ BankBRouter.java
│  │   │        ├─ model
│  │   │        │     ├─ PaymentRequest.java
│  │   │        │     ├─ PaymentResponse.java
│  │   │        │     └─ TransactionStatus.java
│  │   │        ├─ entity
│  │   │        │     ├─ TransactionEntity.java
│  │   │        │     └─ RoutingRuleEntity.java
│  │   │        ├─ repository
│  │   │        │     ├─ TransactionRepository.java
│  │   │        │     └─ RoutingRuleRepository.java
│  │   │        ├─ exception
│  │   │        │     ├─ PaymentException.java
│  │   │        │     └─ GlobalExceptionHandler.java
│  │   │        └─ config
│  │   │              └─ WebConfig.java
│  │   └─ resources
│  │        ├─ application.yaml
│  │        └─ data.sql
│  └─ test
│       └─ DemoApplicationTests.java
```

---

## ▶️ Run Locally

### 1️⃣ Build the project
```
mvn clean package
```

If tests block build:
```
mvn clean package -DskipTests
```

---

### 2️⃣ Start the Application
```
mvn spring-boot:run
```

Expected:
- App starts on port **8080**
- No DB required yet

---

## ✅ Test API

### Endpoint
POST `/api/payments`

### Sample Request
```
{
  "referenceId": "TXN12345",
  "amount": 1200,
  "currency": "INR",
  "customerId": "CUST1"
}
```

### Sample Response
```
{
  "status": "SUCCESS",
  "gateway": "BANK_A",
  "message": "Payment processed successfully"
}
```

### Curl
```
curl -X POST http://localhost:8080/api/payments \
-H "Content-Type: application/json" \
-d '{"referenceId":"TXN12345","amount":1200,"currency":"INR","customerId":"CUST1"}'
```

---

## 🛢️ Database (Coming Next)
- PostgreSQL configuration
- Transactions table
- Routing rules table
- Persistence + retrieval
- Real routing engine enablement

---

## ✅ Status
Application setup completed, API working. Next milestone → enable PostgreSQL + JPA routing storage.