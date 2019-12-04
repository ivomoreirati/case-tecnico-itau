# case-tecnico-itau
Case técnico para renegociação e cobrança itaú.

To run it locally:
- Create Mongodb container:
```docker-compose up --no-start mongodb```

- Start container if not started already:
```docker-compose start```

- Run project with local profile:
```mvn spring-boot:run -Dspring.profiles.active=dev```
