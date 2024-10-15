# SmallTalk
- 그냥저냥 주저리주저리들

## Run Client (ReactJS) on localhost

```sh
docker compose up reactjs
```

## Redis

- run redis with compose 

```sh
docker compose up redis
```

- run redis with redislabs(Saas)
https://app.redislabs.com/

---

## MonogoDB

- run mongodb with compose

```sh
docker compose up mongodb
```



## GraphQL

- Playground 브라우저 접속
  - http://localhost:8080/graphiql?path=/graphql&wsPath=/graphql-ws


- Authorization 

```
{"Authorization": "User {userId}"}
```

- Subscription
```
subscription {
  subscribe(topic: "ddd")
}
```

- Mutation
```
mutation producer {
  publish(topic: "ddd", message: "hello123123")
}
```

- Query
```
query {
  greeting,
  greetingMono,
  greetingsFlux
}
```
- 게시판 생성

```
mutation board {
  createBoard (input: {
		name: "게시판"
    description: "자유게시판"
  }) {
    code
    message
    data {
    	...on Board {
      	id
    	}  
    }
  }
}
```

- 게시글 쓰기

```
mutation article {
  createArticle (input: {
    boardId: "66cad594ed126a5a4d22bd36"
    title: "asdfas"
    contents: "asdfasfs"
    author: "aaa"
    tags: ["tag1"]
  }) {
    code
    message
  }
}
```


- 게시글 가져오기(페이징)
```
query Article {
  getArticles(input: {
    boardId: "66cad594ed126a5a4d22bd36",
    pageNo: 1,
    pageSize: 5
  }) {
    code
    message
    data {
      ...on PageResult {
      	pageNo
        pageSize
        totalCount
        count
        items {
          ...on Article {
            id
            boardId
            title
            contents
            createdBy
            createdAt
          }
        }
      }
    }
  }
}
```