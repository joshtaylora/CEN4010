package Models;
/**
 * @author Alex
 */

import java.util.Calendar;

//Timer class that has two functions to set the game timer and to check if the time limit has been reached.
public class Timer {
  //initialize endTimer which will keep track of the time that the game will end
  Calendar endTimer = Calendar.getInstance();

  //Updates endTimer to be the current time plus the time limit passed into the function in minutes. No need to return  
  public Timer(int gameTime){
    endTimer.add(Calendar.MINUTE, gameTime);
  }

  //Function to check if the time limit has been reached, returns true if the time limit is reached and false otherwise. SHOULD WE CHECK SECONDS? MAKE IT EXACT TO SECONDS
  public boolean checkTime(){
    Calendar currentTime = Calendar.getInstance();
    if(currentTime.get(Calendar.MINUTE) == endTimer.get(Calendar.MINUTE) || currentTime.get(Calendar.MINUTE) > endTimer.get(Calendar.MINUTE)){
      if(currentTime.get(Calendar.SECOND) == endTimer.get(Calendar.SECOND) || currentTime.get(Calendar.SECOND) > endTimer.get(Calendar.SECOND) ){
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }

  //MAKE GET TIME
  public void getTime(){
    Calendar currentTime = Calendar.getInstance();
    int minLeft, secLeft, hrLeft;
    minLeft = endTimer.get(Calendar.MINUTE) - currentTime.get(Calendar.MINUTE);
    secLeft = endTimer.get(Calendar.SECOND) - currentTime.get(Calendar.SECOND);
    hrLeft = endTimer.get(Calendar.HOUR) - currentTime.get(Calendar.HOUR);
    System.out.println("The time left in the game is: " + hrLeft +":" + minLeft +":"+ secLeft);
  }
}