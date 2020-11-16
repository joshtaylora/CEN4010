import java.util.Calendar;

//Timer class that has two functions to set the game timer and to check if the time limit has been reached.
public class Timer{
  //initialize endTimer which will keep track of the time that the game will end
  Calendar endTimer;
  public Timer(int gameTime){
    Calendar endTimer = Calendar.getInstance();
    endTimer.add(Calendar.MINUTE, gameTime);

  } 
  //Updates endTimer to be the current time plus the time limit passed into the function in minutes. No need to return  

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
  public Calendar getTimer(){
    return endTimer;
  }
  public int getMin(){
    Calendar currentTime = Calendar.getInstance();
    return currentTime.get(Calendar.MINUTE) - endTimer.get(Calendar.MINUTE);
  }
  public int getSec(){
    Calendar currentTime = Calendar.getInstance();
    return currentTime.get(Calendar.SECOND) - endTimer.get(Calendar.SECOND);
  }
  public int getHour(){
    Calendar currentTime = Calendar.getInstance();
    return currentTime.get(Calendar.HOUR) - endTimer.get(Calendar.HOUR);
  }
}