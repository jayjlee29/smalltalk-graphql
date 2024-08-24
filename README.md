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



## GroupQL

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

