# EAS
Enterprise Application Stack(EAS) is a Java 21 Spring Boot 4 web application built with JSF/PrimeFaces via JoinFaces. It is packaged as a JAR and uses Maven for build and dependency management. Default user and password is admin/abcd1234

## Key Features

- Java 21
- Spring Boot 4
- JSF / PrimeFaces UI via JoinFaces
- Spring Data JPA persistence
- Spring Security
- Quartz scheduling
- OSS security tooling and OWASP ESAPI configuration

## Repository Structure

- `pom.xml` — Maven build file and dependency configuration
- `src/main/java` — application source code
- `src/main/resources` — runtime resources and Spring Boot properties
- `src/main/native2ascii` — localization resource sources for native2ascii processing
- `src/main/resources/META-INF/resources` — JSF/PrimeFaces web assets
- `src/test/java` — unit and integration tests

## Build & Run

### Recommended commands

```bash
mvn clean package
mvn test
mvn -DskipTests package
```

### Build profiles

- `tomcat-db2` (JAR)
- `tomcat-mysql` (JAR)
- `tomcat-oracle` (JAR)
- `tomcat-postgresql` (JAR)
- `tomcat-sqlserver` (JAR)

Activate a profile with `-P{profile}`.

## Important Notes

- Unit tests are skipped by default in `pom.xml` via `maven-surefire-plugin`; run `mvn test` explicitly for tests.
- Update localization source files in [src/main/native2ascii](../src/main/native2ascii) when changing user-facing text; the build will convert them into generated resources under [src/main/resources](../src/main/resources).
- Avoid editing generated files under `target/`.

## Security & Configuration

- The repository uses OWASP ESAPI policies under `src/main/resources/esapi/`.
- Sensitive credentials should not be committed in source control.

## Planning

This repository currently does not include feature planning artifacts like `spec.md` or `plan.md` in the root.

## Contact

For contribution or support, refer to internal EAS team practices and the `.github/copilot-instructions.md` guidance.