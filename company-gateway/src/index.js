const { ApolloServer } = require('apollo-server');
const { ApolloGateway, IntrospectAndCompose } = require('@apollo/gateway');

const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: 'employees', url: 'http://localhost:8001/graphql' },
      { name: 'teams', url: 'http://localhost:8002/graphql' },
    ],
  }),
});

const server = new ApolloServer({ gateway });

server.listen({ port: 8000 }).then(({ url }) => {
  console.log(`Server ready at ${url}`);
});
