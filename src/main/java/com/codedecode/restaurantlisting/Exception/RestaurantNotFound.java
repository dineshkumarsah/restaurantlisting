package com.codedecode.restaurantlisting.Exception;

public class RestaurantNotFound extends RuntimeException{
   public RestaurantNotFound(){
        super();
    }
    public RestaurantNotFound(String message){
       super(message);
    }
    public RestaurantNotFound(String message, Throwable cause){
       super(message,cause);
    }
    public  RestaurantNotFound(Throwable cause){
      super(cause);
    }
}
