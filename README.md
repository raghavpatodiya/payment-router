# payment-router

# Payment Routing Service

A Spring Boot microservice that receives a payment request, evaluates routing rules, selects the best payment gateway (Bank A / Bank B etc.), and routes the transaction synchronously. Designed to simulate real-world fintech payment routing and decision engines.

---

## 🔥 Features
- REST API based payment initiation
- Dynamic routing using priority rules
- Strategy pattern based gateway routing
- Database-backed transactions & routing rules
- Fallback / future retry capable design
- Clean layered architecture

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
│  │   │        ├─ PaymentRoutingServiceApplication.java
│  │   │
│  │   │        ├─ controller
│  │   │        │     └─ PaymentController.java
│  │   │
│  │   │        ├─ service
│  │   │        │     ├─ PaymentService.java
│  │   │        │     └─ RoutingEngine.java
│  │   │
│  │   │        ├─ strategy
│  │   │        │     ├─ RoutingStrategy.java
│  │   │        │     ├─ BankARouter.java
│  │   │        │     └─ BankBRouter.java
│  │   │
│  │   │        ├─ model        // DTOs
│  │   │        │     ├─ PaymentRequest.java
│  │   │        │     ├─ PaymentResponse.java
│  │   │        │     └─ TransactionStatus.java
│  │   │
│  │   │        ├─ entity       // DB entities
│  │   │        │     ├─ TransactionEntity.java
│  │   │        │     └─ RoutingRuleEntity.java
│  │   │
│  │   │        ├─ repository
│  │   │        │     ├─ TransactionRepository.java
│  │   │        │     └─ RoutingRuleRepository.java
│  │   │
│  │   │        ├─ exception
│  │   │        │     ├─ PaymentException.java
│  │   │        │     └─ GlobalExceptionHandler.java
│  │   │
│  │   │        └─ config
│  │   │              └─ WebConfig.java
│  │   │
│  │   └─ resources
│  │        ├─ application.yaml
│  │        └─ data.sql
│  │
│  └─ test
│       └─ PaymentRoutingServiceApplicationTests.java
```

---

## 🛢️ Database Tables

### `transactions`
Stores each transaction + routing decision.

| field | type |
|------|------|
| id | uuid |
| reference_id | varchar |
| amount | decimal |
| currency | varchar |
| selected_gateway | varchar |
| status | varchar |
| failure_reason | varchar |
| created_on | timestamp |

### `routing_rules`
Stores routing logic dynamically.

| field | type |
|------|------|
| id | int |
| priority | int |
| condition_expression | text |
| gateway | varchar |
| active | boolean |

---

## ▶️ Run Locally
1️⃣ Configure PostgreSQL in `application.yaml`  
2️⃣ Run Spring Boot application  
3️⃣ Test API:

POST `/api/payments`

```
{
  "referenceId": "TXN12345",
  "amount": 1200,
  "currency": "INR",
  "customerId": "CUST1"
}
```

---

## ✅ Status
Project under development. Core structure ready.