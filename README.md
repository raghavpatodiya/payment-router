# Payment Router

## Payment Routing Service

A Spring Boot microservice that receives a payment request, evaluates routing logic, selects the best payment gateway (Bank A / Bank B), and processes the transaction synchronously.  
Designed to closely simulate a real-world fintech **payment routing and transaction persistence** service.

---

## 🔥 Features
- REST-based synchronous payment processing
- Strategy pattern based gateway routing
- PostgreSQL-backed transaction persistence
- Spring Data JPA + Hibernate integration
- Fetch payment status by referenceId
- Clean layered architecture (Controller → Service → Repository)

---

## ✅ Prerequisites

- Java 17+
- Maven
- PostgreSQL (local)

Verify:
```
java -version
mvn -v
psql --version
```

---

## 🛢️ Database Setup (Local)

Create DB:
```
createdb payment_router_db
```

Verify:
```
psql payment_router_db
```

Tables are auto-created by Hibernate on app startup.

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
│  │        └─ application.yaml
│  └─ test
│       └─ DemoApplicationTests.java
```

---

## ▶️ Build & Run

### Build
```
mvn clean package
```

Skip tests if required:
```
mvn clean package -DskipTests
```

### Run
```
mvn spring-boot:run
```

Expected:
- App starts on **http://localhost:8080**
- PostgreSQL connection established
- `transactions` table auto-created

---

## ✅ API Usage

### 1️⃣ Create Payment

**POST** `/api/payments`

```
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "referenceId": "TXN1001",
    "amount": 1500,
    "currency": "INR",
    "customerId": "CUST01"
  }'
```

Response:
```
{
  "status": "SUCCESS",
  "gateway": "BANK_A",
  "message": "Payment processed and saved successfully"
}
```

---

### 2️⃣ Get Payment Status

**GET** `/api/payments/{referenceId}`

```
curl -X GET http://localhost:8080/api/payments/TXN1001
```

Response:
```
{
  "status": "SUCCESS",
  "gateway": "BANK_A",
  "message": "Payment status fetched successfully"
}
```

---

### 3️⃣ Not Found Case

```
curl -X GET http://localhost:8080/api/payments/UNKNOWN_REF
```

Response:
```
{
  "status": "NOT_FOUND",
  "gateway": null,
  "message": "No transaction found for given referenceId"
}
```

---

## 🔎 Verify via DB

```
psql payment_router_db
```

```
SELECT reference_id, status, selected_gateway FROM transactions;
```

---

## 🚀 Current Status

- End-to-end payment routing working
- Transactions persisted in PostgreSQL
- Read-after-write consistency verified
- Stable base ready for enhancements

---

## 🧭 Next Enhancements (Planned)

- Routing rules table usage
- Idempotency (duplicate referenceId handling)
- Validation & error codes
- Swagger / OpenAPI
- Retry & fallback logic
