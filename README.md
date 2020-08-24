# Accounting notebook - Backend test

# Get started

This project requires Java 11 or higher to run.
To run the project, make that your JAVA_HOME environment variable is pointing to a JDK 11+ and run

`./mvnw spring-boot:run`

or build it with

`./mvnw clean package`

and then run it using java like

`java -jar target/accountingNotebook-0.0.1-SNAPSHOT.jar`

The web service will start in localhost:8081

To test the application you can use Postman with the collection provided in `account-balance-test.postman_collection.json`

# Requirements

We are looking to build a money accounting system. The application should be a web service. It should not do any real “transactional” work, just emulate the financial transactions logic (debit and credit).

We emulate debit and credit operations for a single user, so we always have just one financial account.

No security is required. So don't worry about authentication.

No real persistence is expected. Please don't invest time into DB integration.

Please avoid wasting time for complex project configuration. Use configuration from an existing project, if you have one, or use project skeleton generation tools for your technologies. Default configuration would be completely enough.

## Must have

- Service must store the account value of the single user.
- Service must be able to accept credit and debit financial transactions and update the account value correspondingly.
- Any transaction, which leads to negative amount within the system, should be refused. Please provide http response code, which you think suits best for this case.
- Application must store transactions history. Use in-memory storage. Pay attention that several transactions can be sent at the same time. The storage should be able to handle several transactions at the same time with concurrent access, where read transactions should not lock the storage and write transactions should lock both read and write operations.
- It is necessary to design REST API by your vision in the scope of this task. There are some API recommendations. Please use these recommendations as the minimal scope, to avoid wasting time for not-needed operations.
- In general, the service will be used programmatically via its RESTful API. For testing purposes Postman or any similar app can be used.
- It should be possible to launch project/projects by a single-line-command. Please provide README.md
- Target completion time is 3 hours. We would rather see what you were able to do in 3 hours than a full-blown application you’ve spent days implementing. Note that in addition to quality, time used is also factored into scoring the task.

## UX/UI requirements:

- We need a simple UI application for this web service.
- Please don't spend time for making it beautiful. Use a standard CSS library, like Bootstrap with a theme (or any other).
- UI application should display the transactions history list only. No other operation is required.
- Transactions list should be done in accordion manner. By default the list shows short summary (type and amount) for each transaction. Full info for a transaction should be shown on user's click.
- It would be good to have some coloring for credit and debit transactions.

## Expected Deliverables

- Source code.
- Binary versions of your applications that are ready to run. No build should be required.
- Readme.
