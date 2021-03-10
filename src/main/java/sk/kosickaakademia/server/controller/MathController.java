package sk.kosickaakademia.server.controller;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
/*
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String getAdd(@RequestBody String input){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            System.out.println(jsonObject.get("a"));
            System.out.println(jsonObject.get("b"));
            long a = Integer.parseInt(jsonObject.get("a").toString());
            long b = Integer.parseInt(jsonObject.get("b").toString());
            long result = a + b;
            System.out.println(result);
            JSONObject js = new JSONObject();
            js.put("result", result);
            return js.toJSONString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> getAdd(@RequestBody String input){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            //System.out.println(jsonObject.get("a"));
            //System.out.println(jsonObject.get("b"));
            int a = Integer.parseInt(String.valueOf(jsonObject.get("a")));
            int b = Integer.parseInt(String.valueOf(jsonObject.get("b")));
            int result = a + b;
            //System.out.println(result);
            JSONObject js = new JSONObject();
            js.put("result", result);
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


    @RequestMapping(path = "/mul", method = RequestMethod.GET)
    public ResponseEntity<String> getMul(@RequestParam(value="a") int value1, @RequestParam(value="b") int value2){

        int result = value1 * value2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

}
