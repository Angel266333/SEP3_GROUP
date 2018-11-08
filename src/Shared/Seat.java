package Shared;

import java.io.Serializable;
import java.util.Map;

import javax.json.Json;

import Utils.JsonMapper;

public class Seat implements Serializable
{  
   //fields are public so you don't need getters and setters;
   public int id;
   public boolean isOccupied;
   
   public Seat() {
	   
   }
   
   public Seat(int id, boolean isOccupied)
   {
      this.id = id;
      this.isOccupied = isOccupied;
   }
 
   @Override
   public String toString()
   {
      return Json.createObjectBuilder()
            .add("id",id)
            .add("isOccupied", isOccupied)
            .build().toString();
 
   }
   
   
   public static Seat fromString(String serial)
   {
      Map<String,String> map = JsonMapper.parse(serial);
      
      int id = Integer.parseInt(map.get("id"));
      
      boolean isO = map.get("isOccupied").equals("true");
      
      return new Seat(id, isO);
      
      
   }
   
   
   
}
