package de.br.aff.springbootrabbitmqconsumer.domain;

import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Setter
public class Profile {
    UUID id;
    String firstName;
    String lastName;
    String email;
}

    //Example of the JSON file that would be consumed from RabbitMQ
    // {"id":"d6706324-086c-44b9-8ecb-20d5cf6df5b7","firstName":"John","lastName":"Doe","email":"john.doe@aol.com"}

