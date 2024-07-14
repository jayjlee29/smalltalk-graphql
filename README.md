# graphql

## run redis 
```
docker run --rm -d -p 6379:6379 --name my-redis redis
```

## run compose
```
docker compose up
```

---

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