package com.vesmer.familytestbackend;

import com.vesmer.familytestbackend.dto.RegisterRequest;
import com.vesmer.familytestbackend.model.Person;
import com.vesmer.familytestbackend.model.Relatives;
import com.vesmer.familytestbackend.model.enumeration.Gender;
import com.vesmer.familytestbackend.repository.PersonRepository;
import com.vesmer.familytestbackend.repository.RelativesRepository;
import com.vesmer.familytestbackend.repository.UserRepository;
import com.vesmer.familytestbackend.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FamilyTestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTestBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthService authService, UserRepository userRepository,
						  PersonRepository personRepository,
						  RelativesRepository relativesRepository) {
		return args -> {
			initialUserRegistration(authService, userRepository);
			initializePersonsAndRelatives(personRepository, relativesRepository);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	private void initialUserRegistration(AuthService authService, UserRepository userRepository){
		RegisterRequest registerRequest = new RegisterRequest("user-email@mail.com",
				"user", "1111");
		authService.signup(registerRequest);
		authService.enableAccount("user");
	}

	private void initializePersonsAndRelatives(PersonRepository personRepository,
											   RelativesRepository relativesRepository) {

		Person father = new Person(
				null,
				"bob",
				LocalDate.of(2001, 4, 5),
				Gender.MALE
		);

		Person mother = new Person(
				null,
				"anna",
				LocalDate.of(1998, 5, 6),
				Gender.FEMALE
		);

		Person son = new Person(
				null,
				"jack",
				LocalDate.of(2010, 7, 8),
				Gender.MALE
		);

		Person daughter = new Person(
				null,
				"maria",
				LocalDate.of(2013, 9, 10),
				Gender.FEMALE
		);

		Person me = new Person(
				null,
				"vasiliy",
				LocalDate.of(2005, 10, 12),
				Gender.MALE
		);

		personRepository.save(father);
		personRepository.save(mother);
		personRepository.save(son);
		personRepository.save(daughter);
		personRepository.save(me);

		Set<Person> parents = new HashSet<>();
		parents.add(father);
		parents.add(mother);

		Set<Person> children = new HashSet<>();
		children.add(son);
		children.add(daughter);

		Relatives relatives = new Relatives(
				null,
				me,
				parents,
				children
		);
		relativesRepository.save(relatives);

		children = new HashSet<>();
		children.add(me);
		relatives = new Relatives(
				null,
				father,
				new HashSet<>(),
				children
		);
		relativesRepository.save(relatives);

		relatives = new Relatives(
				null,
				mother,
				new HashSet<>(),
				children
		);
		relativesRepository.save(relatives);

		parents = new HashSet<>();
		parents.add(me);
		relatives = new Relatives(
				null,
				son,
				parents,
				new HashSet<>()
		);
		relativesRepository.save(relatives);

		relatives = new Relatives(
				null,
				daughter,
				parents,
				new HashSet<>()
		);
		relativesRepository.save(relatives);

	}

}
