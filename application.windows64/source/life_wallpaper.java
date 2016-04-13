import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class life_wallpaper extends PApplet {

int red = color(192, 0, 24);
//color bg = color(154, 201, 254);
//color red = color(0);
int white = color(180);
int bg = color(255);


int dotCount = 0;
int totalWeeks, weeksLived, x, y, padding, marginx, marginy, radius;

public void setup() {
    //Change values to match your screen resolution
  background(bg);
  ellipseMode(RADIUS);
  noStroke();
  frameRate(1000);
 

  totalWeeks = totalWeeks(82);
  weeksLived = weeksLived(31, 8, 1995);
  
  marginx = floor(0.15f*width);
  marginy = floor(0.15f*height);
  int p = floor(sqrt((0.7f*width*0.7f*height)/(totalWeeks*25)));
  padding = 5*p;
  radius = 2*p;
  println(totalWeeks);
  println(weeksLived);
  println(p);

  reset();
  PFont font;
  font = createFont("BebasNeue Bold.otf", 30);
  textFont(font, 30);
}

public void draw() {
  if (dotCount == weeksLived) {
    fill(white);
  }
  if (dotCount < totalWeeks) {
    drawDot();
  } 
  else {
    writeText();
    save("life3.jpg");   
    noLoop();
  }
  dotCount++;
}

public void reset() {
  x = marginx;
  y = marginy;
  fill(red);
}

public void writeText() {
  fill(white);
  textAlign(CENTER);
  String word = "LIFE LEFT : " + round((1.0f-PApplet.parseFloat(weeksLived)/PApplet.parseFloat(totalWeeks))*10000.0f)/100.0f + "%"; 
  text(word, width/2, floor(y+height*0.05f));
}

public void drawDot() {
  ellipse(x, y, radius, radius);
  x += padding;
  if (x > (width-marginx)) {
    y += padding;
    x = marginx;
  }
}

public int totalWeeks(int lifespan) {
  return floor(lifespan*(52*7+1)/7);
}

public int weeksLived(int day, int month, int year) {
  int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  int bMonth = 0;
  for (int i = 0; i < month; i++) {
    bMonth += daysInMonth[i];
  }
  int bYear = floor(364.25f*year);
  int birthDays = day + bMonth + bYear;

  bMonth = 0;
  for (int i = 0; i < month(); i++) {
    bMonth += daysInMonth[i];
  }
  bYear = floor(364.25f*year());
  int currentDays = day() + bMonth + bYear;
  return floor((currentDays-birthDays)/7);
}
  public void settings() {  size(2560, 1600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "life_wallpaper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
