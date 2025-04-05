package com.example.spring_maven_demo;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMavenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMavenDemoApplication.class, args);
		Map<String, String> env_var = System.getenv();

        // Loop through all environment variables
        for (String envName : env_var.keySet()) {
            // Print environment variable name and value to console
            System.out.format("%s=%s", envName, env_var.get(envName));
            System.out.println();
        }
	}

}
