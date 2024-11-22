// import { ApolloClient, InMemoryCache } from "@apollo/client";

// const client = new ApolloClient({
//   uri: "http://localhost:8080/graphql",
//   cache: new InMemoryCache()
// });

// export default client;

import { ApolloClient, InMemoryCache, createHttpLink } from "@apollo/client";
import { setContext } from "@apollo/client/link/context";

// GraphQL 서버 URI
const httpLink = createHttpLink({
  uri: "http://localhost:8080/graphql"
});

// Authorization 헤더를 설정하는 링크 추가
const authLink = setContext((_, { headers }) => {
  // 로컬 스토리지에서 인코딩된 유저 식별자 가져오기
  const encodedIdentifier = localStorage.getItem("userIdentifier");
  return {
    headers: {
      ...headers,
      authorization: encodedIdentifier ? `USER ${encodedIdentifier}` : ""
    }
  };
});

// Apollo Client 초기화
const client = new ApolloClient({
  link: authLink.concat(httpLink),
  cache: new InMemoryCache()
});

export default client;
