# SmallTalk on CloudRun
- 윈도우는 WSL에서 실행해주세요~

## gcloud
```shell
gcloud init
gcloud config list
gcloud projects list
gcloud compontents update
```

```
export PROJECT_ID=$(gcloud config get-value project)
export REGION=asia-northeast3
export IMAGE_URL=asia-northeast3-docker.pkg.dev/${PROJECT_ID}/smalltalk/smalltalk-api:latest
```

## Artifacts registry

- create

```shell
gcloud artifacts repositories create smalltalk --repository-format=docker \
    --location=asia-northeast3 --description="Smalltalk Docker repository" \
    --project=$PROJECT_ID
```

- list

```shell
gcloud artifacts repositories list --project $PROJECT_ID
```

- auth

```shell
gcloud auth configure-docker asia-northeast3-docker.pkg.dev
```



## build smalltalk-api & push to gcr.io repo

- build & push
```shell
docker build -t $IMAGE_URL .
docker push $IMAGE_URL
```

- test run on local machine
```
docker run --rm $IMAGE_URL
```

---
## Run smalltalk-api on CloudRun

```shell
gcloud run services replace --region=asia-northeast3  cloudrun-smalltalk-api.yaml
```

- 403오류시 외부접속이 가능하도록 정책을 allusers대상으로 허용한다.
```
gcloud run services set-iam-policy --region=asia-northeast3 smalltalk-api policy.yaml
```


- check url
```shell
export GRAPHQL_PLAYGROUND_URL=$(gcloud run services describe smalltalk-api --region=asia-northeast3 --format 'value(status.url)')
echo $GRAPHQL_PLAYGROUND_URL"/graphiql?path=/graphql&wsPath=/graphql-ws"
```

- url 확인
```shell
https://smalltalk-api-ax6fykyr2a-du.a.run.app/graphiql?path=/graphql&wsPath=/graphql-ws
```

- export cloudrun template
```
gcloud run services describe smalltalk-api --format export > service.yaml
```

# References
 - [컨테이너 빌드](https://cloud.google.com/run/docs/building/containers?hl=ko)
 - [Cloud Run에 컨테이너 이미지 배포](https://cloud.google.com/run/docs/deploying?hl=ko)
 - [Cloud Run Troubleshooting](https://cloud.google.com/run/docs/troubleshooting?hl=ko)
 
