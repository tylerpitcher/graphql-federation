const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const { ApolloServer } = require("@apollo/server");
const { expressMiddleware } = require("@apollo/server/express4");
const { ApolloGateway, IntrospectAndCompose } = require("@apollo/gateway");
const env = require("./env");

const app = express();

const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: "courses", url: env.COURSES_ENDPOINT },
      { name: "students", url: env.STUDENTS_ENDPOINT },
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
