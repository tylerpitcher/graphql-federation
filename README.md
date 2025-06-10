# GraphQL Federation
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![GraphQL](https://img.shields.io/badge/-GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white)
![Apollo-GraphQL](https://img.shields.io/badge/-ApolloGraphQL-311C87?style=for-the-badge&logo=apollo-graphql)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

A demonstration of federated GraphQL using Netflix's DGS for backend subgraphs and an Angular client for interacting with the API. This setup uses Apollo Federation to compose multiple GraphQL subgraphs into a single unified schema. Each subgraph defines its own types and resolvers. The student API handles student data, while course API handles course data and enrollment logic. The gateway service acts as the central GraphQL endpoint and routes queries to the appropriate subgraph. By isolating functionality into separate subgraphs, the architecture promotes modular design and scalability.

Both subgraphs connect to a shared PostgreSQL database and expose federated types using the `@key`, `@external`, and `@extends` directives to support cross-service references. For example, resolving a student's enrolled courses or listing student enrollment in a course requires the gateway to coordinate requests between both services.

### Components:
- **school-client**: Angular frontend app (served via NGINX)
- **school-gateway**: Apollo Gateway service
- **school-course-api**: Handles courses and enrollment
- **school-student-api**: Handles student data
- **PostgreSQL**: Shared database for both subgraphs

All client requests are funneled through the gateway at `http://localhost:8080/graphql`, which orchestrates the requests as shown:
![School API Components](federation-architecture.svg)

## How to Run
1. Create a .env
```text
DB_URL=jdbc:postgresql://postgres:5432/school
POSTGRES_DB=school
POSTGRES_USER=
POSTGRES_PASSWORD=
```

2. Build Jars
```bash
mvn clean package -DskipTests
```

3. Build Docker Images
```bash
docker compose build
```

4. Run Containers
```bash
docker compose up
```

5. Access GraphQL Playground
```text
http://localhost:8080/graphql
```

6. Access Angular Client
```text
http://localhost
```
