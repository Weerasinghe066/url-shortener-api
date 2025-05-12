# URL Shortener API

A simple Spring Boot-based URL shortener application. It converts long URLs into short hashes and redirects short URLs
to their original destinations.

## 🚀 Features

- Shorten any valid URL
- Retrieve original URL from short URL
- In-memory storage for simplicity
- Basic collision handling with hash suffixing
- Redirect support

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.4.5
- SLF4J for logging
- JUnit 5 for testing
- In-memory `ConcurrentHashMap` as the URL store
- MurmurHash3 via Guava for generating hashes

---

## 📦 Getting Started

### Prerequisites

- Java 17+
- Maven

### Running Locally

```bash
# Clone the project
git clone https://github.com/Weerasinghe066/url-shortener-api.git
cd url-shortener-api
```

```bash
# Build the project
mvn clean install
```
```bash
# Run the application
mvn spring-boot:run
```


The application will start on:  
`http://localhost:8080`
> **Note:** The application is configured to run on port **8080**. Please ensure that this port is **not in use by any other
> application** before starting the project.
---

## 📮 API Endpoints

### 1. Shorten a URL

**POST** `/shortenerUrl`  
**Headers:** `Content-Type: application/json`  
**Request Body:** (raw string)

```
"https://www.example.com"
```

**Response:**
```
http://localhost:8080/abc12345
```

---

### 2. Redirect to Original URL

**GET** `/{shortenCode}`  
Redirects to the long/original URL.

Example:  
`GET http://localhost:8080/abc12345`  
⟶ redirect to `https://www.example.com`

---

### 3. Retrieve Original URL (optional lookup without redirection)

**POST** `/getOriginalUrl`  
**Headers:** `Content-Type: application/json`  
**Request Body:** (raw string)

```
"abc12345"
```

**Response:**
```
"https://www.example.com"
```

---

## 🧪 Running Tests

To run unit tests:

```bash
mvn test
```

Test classes include:
- `UrlServiceTest` — verifies shortening, retrieval, and exception handling


---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/assetment/urlshortenerapi/
│   │   ├── controller/
|   |   ├── exception/
│   │   ├── service/
|   |   ├── support/
│   │   ├── util/
│   └── resources/
│       └── application.properties
├── test/
│   └── java/com/assetment/urlshortenerapi/
│       └── service/
```

---

## 🔒 Notes

- This project uses **in-memory storage** and does **not persist data** across restarts.


---

## 📧 Author

Developed by Thilina Weerasinghe  

