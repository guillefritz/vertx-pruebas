package ar.gfritz.pruebas.vertx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.gfritz.pruebas.vertx.verticle.StaticServer;
import io.vertx.core.Vertx;

@SpringBootApplication
public class Application {

	@Autowired
	StaticServer staticServer;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void deployVerticle() {
		Vertx.vertx().deployVerticle(staticServer);
	}
}
