# Spring AI + Ollama Streaming Chat Application

## Overview

This project is a full-stack AI chat application built using the following technologies:

* **Spring Boot (Java 21)** with **Spring AI (Ollama)**
* **React** for the frontend
* **Ollama** running locally as the LLM runtime

The application demonstrates **real-time streaming of AI responses** from a locally running Ollama model to a React-based user interface.

---

## Required Software

Ensure the following software is installed on your system:

* **Java 21**
* **Node.js 18+**
* **Ollama (installed locally)**

---

## Ollama Requirement

Ollama must be installed on the system for this application to function.

Download and install Ollama from:
[https://ollama.com](https://ollama.com)

Once installed, Ollama runs locally and listens on its default port.

The application is configured to **automatically pull the required model** if it is not already present on the system.

---

## Backend Configuration

### `application.properties`

```properties
spring.application.name=SpringOllamaAi
spring.ai.ollama.chat.options.model=llama3.2:3b
spring.ai.ollama.init.pull-model-strategy=when_missing
```

### Configuration Behavior

* The model **`llama3.2:3b`** (can be changed to any supported model):

  * Is automatically pulled **only if it is missing**
  * Does **not** need to be downloaded manually

---

## Backend API

### Streaming Chat Endpoint

**Endpoint**

```
POST /api/chat/stream
```

**Consumes**

```json
{
  "message": "User input text"
}
```

**Produces**

```
text/plain (streamed)
```

### Controller Behavior

* Accepts a simple JSON request body
* Uses **Spring AI `ChatClient`**
* Streams AI-generated text incrementally using `Flux<String>`

---

## Frontend Overview

### Technology Stack

* **React**
* **Fetch API** with streaming support (`ReadableStream`)
* **Markdown rendering** with syntax highlighting

### Core Features

* Displays AI responses as they stream in real time
* Automatically scrolls to the latest message
* Disables user input while a response is being generated
* Supports Markdown formatting and code blocks in AI output

---

## Application Flow

1. The user sends a message from the React UI
2. React sends a POST request to the backend streaming endpoint
3. Spring AI forwards the prompt to Ollama
4. Ollama generates tokens incrementally
5. The backend streams tokens as plain text
6. The frontend appends each chunk to the active AI message

---

## Running the Application

### Start the Backend

```bash
./mvnw spring-boot:run
```

The backend runs at:
[http://localhost:8080](http://localhost:8080)

---

### Start the Frontend

```bash
npm install
npm start
```

The frontend runs at:
[http://localhost:3000](http://localhost:3000)

---
