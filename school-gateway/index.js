const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const { ApolloServer } = require("@apollo/server");
const { expressMiddleware } = require("@apollo/server/express4");
const { ApolloGateway, IntrospectAndCompose } = require("@apollo/gateway");

const app = express();

const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: "courses", url: "http://localhost:8081/graphql" },
      { name: "students", url: "http://localhost:8082/graphql" },
    ],
  }),
});

async function start() {
  const server = new ApolloServer({
    gateway,
    introspection: true,
  });

  await server.start();

  app.use(
    "/graphql",
    cors(),
    bodyParser.json(),
    expressMiddleware(server)
  );

  app.listen(8080, () => {
    console.log("Gateway running at 8080");
  });
}

start();
