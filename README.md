# SmallTalk


## Redis
- run redis 
```
docker run --rm -d -p 6379:6379 --name my-redis redis
```

- run redis with compose 
```
docker compose up
```

- run redis with redislabs(Saas)
https://app.redislabs.com/

---

## MonogoDB

- run mongodb with compose

```
docker compose up
```



## GraphQL

- Playground 브라우저 접속
  - http://localhost:8080/graphiql?path=/graphql&wsPath=/graphql-ws


- Authorization 

```
{"Authorization": "{userId}"}
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
