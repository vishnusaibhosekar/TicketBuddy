# Ticket Buddy
This is the backend for TicketBuddy - a bookmyshow ripoff.  
- Tech Stack Used: Spring Boot, MySQL, Docker.

## To run the demo:
- First run db: The db is exposed to the system on port `13306`. username `root`. password `root_pass`. To use adminer `http://localhost:18080`.
```
docker compose up
```
- Then run the demo backend
```
./mvnw clean install
./mvnw spring-boot:run
```
- Then open `http://localhost:8080/swagger-ui.html#/`

## To stop the demo
- Stop the backend service (ctrl-c)
- To stop docker compose
```
docker compose down
docker volume rm ticketbuddy_db_data
```

## API flow for booking tickets
Use the Ticket Booking Controller to book tickets. (Open [swagger page](http://localhost:8080/swagger-ui.html#/) to check it out).

## Notes:
- The ticket booking concurrency is handled by using the db's `SERIALIZABLE` level of Isolation. Refer [this](https://en.wikipedia.org/wiki/Isolation_(database_systems)) link. Topic: `Isolation levels`. 
- Screening overlaps are to be handled by user who creates shows.
- Tickets must be booked at least one day prior.
- No auth.
- No logging.
- No tests.