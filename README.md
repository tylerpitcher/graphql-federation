# GraphQL Federation
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Netflix](https://img.shields.io/badge/Netflix-E50914?style=for-the-badge&logo=netflix&logoColor=white)
![GraphQL](https://img.shields.io/badge/-GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white)
![Apollo-GraphQL](https://img.shields.io/badge/-ApolloGraphQL-311C87?style=for-the-badge&logo=apollo-graphql)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Testing Netflix's DGS & Apollo to create a federated GraphQL API for enrolling students in courses. This setup uses Apollo Federation to compose multiple GraphQL subgraphs into a single unified schema. Each subgraph defines its own types and resolvers. The student API handles student data, while course API handles course data and enrollment logic. The gateway service acts as the central GraphQL endpoint and routes queries to the appropriate subgraph. By isolating functionality into separate subgraphs, the architecture promotes modular design and scalability.

Both subgraphs connect to a shared PostgreSQL database and expose federated types using the `@key`, `@external`, and `@extends` directives to support cross-service references. For example, resolving a student's enrolled courses or listing student enrollment in a course requires the gateway to coordinate requests between both services.

All client requests are funneled through the gateway at `http://localhost:8080/graphql`, which orchestrates the requests as shown:
![School API Components](federation-architecture.svg)

## How to Run
1. Build Jars
```bash
mvn clean package -DskipTests
```

2. Build Docker Images
```bash
docker compose build
```

3. Run Containers
```bash
docker compose up
```

4. Access in Browser
```text
http://localhost:8080/graphql
```
