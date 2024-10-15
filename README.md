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

- check url
```shell
gcloud run services describe smalltalk-api --region=asia-northeast3 --format 'value(status.url)'
```

- 403오류시 
```
gcloud run services set-iam-policy smalltalk-api policy.yaml
```

## Browser
```
https://smalltalk-api-ax6fykyr2a-du.a.run.app/graphiql?path=/graphql&wsPath=/graphql-ws
```

# References
 - [컨테이너 빌드](https://cloud.google.com/run/docs/building/containers?hl=ko)
 - [Cloud Run에 컨테이너 이미지 배포](https://cloud.google.com/run/docs/deploying?hl=ko)
 - [Cloud Run Troubleshooting](https://cloud.google.com/run/docs/troubleshooting?hl=ko)

 gcloud run services describe smalltalk-api --format export > service.yaml
