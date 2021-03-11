package sk.kosickaakademia.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sk.kosickaakademia.server.controller.JokeController;

import java.util.Collections;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "sk.kosickaakademia.server.controller")
public class App 
{

    public static void main( String[] args )
    {

        //naplnenie arrayu
        JokeController jokeController = new JokeController();
        jokeController.fillTheArray();


        //default8080
        //SpringApplication.run(App.class, args);

        //iny port 8083
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", "8083"));
        app.run(args);

    }

}
