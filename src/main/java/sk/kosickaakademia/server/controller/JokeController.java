package sk.kosickaakademia.server.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {

    String joke1 = "joke one";
    String joke2 = "joke two";
    String joke3 = "joke three";
    public static List<String> list = new ArrayList<>();

    public void fillTheArray(){
        list.add(joke1);
        list.add(joke2);
        list.add(joke3);
    }

    @GetMapping("/jokes")
    public ResponseEntity<String> getJokes(){

        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();

            for(int i = 0; i < list.size(); i++){
                array.add(list.get(i));
            }

            jsonObject.put("jokes", array);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/joke")
    public ResponseEntity<String> getRandomJoke(){
        Random random = new Random();
        int number = random.nextInt(list.size()); //(B-A+1) + A
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("joke", list.get(number));
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id){
        JSONObject jsonObject = new JSONObject();
        int status = 0;
        if(id<1 || id>list.size()){
            jsonObject.put("error","Invalid ID");
            status = 404;
        }else{
            jsonObject.put("joke", list.get(id-1));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    //pridanie vtipu cez POST
    @RequestMapping(path = "/joke/add", method = RequestMethod.POST)
    public ResponseEntity<String> addJoke(@RequestBody String input){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            String joke = (String)jsonObject.get("joke");
            list.add(joke);

            JSONObject js = new JSONObject();
            JSONArray array = new JSONArray();

            for(int i = 0; i < list.size(); i++){
                array.add(list.get(i));
            }

            js.put("jokes", array);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(js.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(NumberFormatException e){
            JSONObject obj = new JSONObject();
            obj.put("error", "INCORRECT INPUT");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        } catch(Exception e){
            JSONObject obj = new JSONObject();
            obj.put("error", "INCORRECT INPUT");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }

}
