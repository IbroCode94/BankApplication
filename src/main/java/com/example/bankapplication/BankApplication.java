package com.example.bankapplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "The Bank App",
                description = "Backend Rest ApI for Bank",
                version = "v1.0",
                contact  = @Contact(
                        name = "Ibrahim Ozigis",
                        email = "ibrahimozigis0@gmail.com",
                        url = ""

                ),
                license = @License(
                        name = "The Backend Java App"
                )

        ),
        externalDocs = @ExternalDocumentation(
                description = "The Bank App Documentation",
                url = ""
        )
)
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}
