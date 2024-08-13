# LdesMemberSeeder4J

## Purpose of this application

As part of the development of the [LdesServer4J](https://github.com/Informatievlaanderen/VSDS-LDESServer4J) by
the [VSDS](https://www.vlaanderen.be/digitaal-vlaanderen/onze-oplossingen/vlaamse-smart-data-space) team, we have from
time to time test our server by seeding it with a lot of LDES members. Therefore, this application has been written,
which makes the seeding simpler and more customizable.

## How to run

### Configuration properties

This application is very customizable, and here are the properties that can be used to customize the application

| Property                                             | Description                                                                                                                                                                                 | Required | Default               | Example               | Supported values                    |
|------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|-----------------------|-----------------------|-------------------------------------|
| member-seeder.member-template                        | File name or path to the template to use for seeding the members                                                                                                                            | true     | NA                    | example-member.ttl    | Relative and absolute file paths    |
| member-seeder.boundaries.inclusive-start             | Inclusive start where the seeder can start, this will be used in the id or subject of a member. This property can come in handy when the application is ran multiple times after each other | false    | 0                     | 100                   | Integer value                       |
| member-seeder.boundaries.exclusive-end               | Exclusive end where the seeder must stop seeding members                                                                                                                                    | false    | 10000                 | 2000                  | Integer value                       |
| member-seeder.ldes-server.host                       | Host where the LDES server is running                                                                                                                                                       | false    | http://localhost:8080 | http://localhost:8080 | HTTP and HTTPS URLs                 |
| member-seeder.ldes-server.collection                 | Name of the event stream collection that is running on the LDES server                                                                                                                      | true     | NA                    | event-stream          | String                              |
| member-seeder.ldes-server.rdf-format                 | Format in which the member must be sent to the LDES server                                                                                                                                  | false    | application/n-quads   | text/turtle           | Any type supported by [Apache Jena] |
| member-seeder.state-object-seeding.enabled           | Flag that indicates whether state objects or version object must be used                                                                                                                    | false    | false                 | true                  | Boolean value                       |
| member-seeder.state-object-seeding.number-of-members | Number of state members that will be sent in batch to the LDES server                                                                                                                       | false    | 10                    | 25                    | Integer value                       |

> [!TIP]
> By default, a spring application keeps running until killed or crashed. However, this application does not have any
> purpose to keep running after the seeder has completed. To let the application shut down on completion, the following
> property can be set:
> ```properties
> spring.main.web-application-type=none
> ```

#### Example config
```yaml
spring:
  main:
    web-application-type: none
member-seeder:
  member-template: examples/simple-state-object.nq
  ldes-server:
    collection: event-stream
  boundaries:
    inclusive-start: 0
    exclusive-end: 10000
  state-object-seeding:
    enabled: true
    number-of-members: 100
```

### Docker

Currently, there is no Docker image available yet.

### Run locally via Maven

Requirements:

- Java 21

If the project is not pulled on your system yet: execute:

```bash
git clone https://github.com/jobulcke/LdesMemberSeeder4J.git
cd ./LdesMemberSeeder4J
```

To run the application: execute the following command

```bash
./mvnw spring-boot:run
```

## Credits

A special thank-you goes here to [Wouter Lefever](https://github.com/Lefeverw), who wrote the very first version of this
application, where most of this code is based on.

[Apache Jena]: https://jena.apache.org/documentation/io/rdf-input.html#determining-the-rdf-syntax