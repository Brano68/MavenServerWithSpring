package sk.kosickaakademia.server;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

    //localhost:8083/hello
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String getHello(){
        return "HELLO. How are you doing? ";
    }


    @GetMapping(path = "/hi")
    public String getHi(){
        return "HI. How are you doing? ";
    }

    @RequestMapping(path = "/time")
    public String currentTime(){
        return new Date().toString();
    }

    @RequestMapping(path = "/hi/{username}")
    public String getHiWithName(@PathVariable String username){
        return "<h2>HI " + username + " What are you doing? </h2>";
    }



    ///POST namiesto GET chyba405
    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public String getTest(@RequestBody String userName){

        return "Your name is " + userName;
    }

}
