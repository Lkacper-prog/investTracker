🚀 InvestTrack
InvestTrack is a web application designed to manage investment portfolios. It allows users to track their assets (stocks, cryptocurrencies) in real-time through integrations with external market APIs and features an AI-powered assistant for analysis and queries.

✨ Features
Portfolio Management (CRUD): Add and manage purchased assets with specific types like Cryptocurrencies and Stocks.

Live Valuation: Automatically fetches current prices for assets:

Cryptocurrencies via the CoinGecko API.

Stocks via the FinnHub API.

Security: User registration and login secured with Spring Security and JWT (JSON Web Tokens).

AI Assistant: Integrated chat functionality using Google Gemini 2.5 Flash with Google Search tool capabilities.

Simple Web Interface: A minimalist frontend built with Vanilla JS, HTML, and CSS to display asset tables and total portfolio value.

🛠️ Technologies
Backend:

Java 21.

Spring Boot 3.4.0 (Web, Data JPA, Security, Validation).

Spring Security & JJWT for stateless authentication.

Google GenAI SDK for AI features.

MySQL 8.0 database.

Frontend:

HTML5, CSS3, Vanilla JavaScript.

DevOps & Tools:

Docker & Docker Compose for database containerization.

Testcontainers for integration testing with a real MySQL instance.

Maven build tool.

⚙️ Prerequisites
To run the project locally, ensure you have the following installed:

Java 21 (JDK).

Docker (for MySQL and Testcontainers).

Maven (or use the provided mvnw wrapper).

You will also need API keys from:

FinnHub API.

Google Gemini API.

🚀 Getting Started
Clone the repository:

Bash
git clone <repository-url>
cd investTracker
Configure environment variables:
Set your API keys in src/main/resources/application.properties or as environment variables:

Properties
GEMINI_API_KEY=your_gemini_key
FINHUB_API_KEY=your_finnhub_key
JWT_SECRET=YourVerySecretKeyForJWTGeneration12345!
Start the database:
Use Docker Compose to launch the MySQL container:

Bash
docker-compose up -d
Run the application:

Bash
./mvnw spring-boot:run
(Windows: mvnw.cmd spring-boot:run)

Access the App:
Open your browser and navigate to: http://localhost:8080/.

📡 Key API Endpoints
The REST API is available at http://localhost:8080. Most endpoints require an Authorization: Bearer <jwt_token> header.

🔐 Authentication (/auth)

POST /auth/register - Register a new user.

POST /auth/login - Login and receive a JWT.

💼 Portfolio (/assets)

GET /assets/ - Retrieve all assets for the logged-in user.

POST /assets - Add a new asset.

GET /assets/price - Get the portfolio with real-time market valuations.

🤖 AI Assistant (/ai)

POST /ai/ask - Send a prompt to the Gemini model and receive an answer.

🧪 Testing
The project uses Testcontainers to spin up a real MySQL database for integration tests. Run tests with:

Bash
./mvnw test