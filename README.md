# HealthTrack

## Description

- No spring
- Only maven
- PostgreSQL
- OpenJDK 11
- Tomcat 9.0.54

## Requirements

- Docker
- Docker Compose

## Usage

- **Run**:

```sh
docker-compose up
```

- **Bring down with volumes**:

```sh
docker-compose down -v
```

- **Recompile war package and deploy to tomcat**:

```sh
docker-compose stop tomcat-server &&\
docker-compose build tomcat-server &&\
docker-compose up -d tomcat-server
```
