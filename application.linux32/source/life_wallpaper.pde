color red = color(192, 0, 24);
//color bg = color(154, 201, 254);
//color red = color(0);
color white = color(180);
color bg = color(255);


int dotCount = 0;
int totalWeeks, weeksLived, x, y, padding, marginx, marginy, radius;

void setup() {
  size(2560, 1600);  //Change values to match your screen resolution
  background(bg);
  ellipseMode(RADIUS);
  noStroke();
  frameRate(1000);
 

  totalWeeks = totalWeeks(82);
  weeksLived = weeksLived(31, 8, 1995);
  
  marginx = floor(0.15*width);
  marginy = floor(0.15*height);
  int p = floor(sqrt((0.7*width*0.7*height)/(totalWeeks*25)));
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

void draw() {
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

void reset() {
  x = marginx;
  y = marginy;
  fill(red);
}

void writeText() {
  fill(white);
  textAlign(CENTER);
  String word = "LIFE LEFT : " + round((1.0-float(weeksLived)/float(totalWeeks))*10000.0f)/100.0f + "%"; 
  text(word, width/2, floor(y+height*0.05));
}

void drawDot() {
  ellipse(x, y, radius, radius);
  x += padding;
  if (x > (width-marginx)) {
    y += padding;
    x = marginx;
  }
}

int totalWeeks(int lifespan) {
  return floor(lifespan*(52*7+1)/7);
}

int weeksLived(int day, int month, int year) {
  int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  int bMonth = 0;
  for (int i = 0; i < month; i++) {
    bMonth += daysInMonth[i];
  }
  int bYear = floor(364.25*year);
  int birthDays = day + bMonth + bYear;

  bMonth = 0;
  for (int i = 0; i < month(); i++) {
    bMonth += daysInMonth[i];
  }
  bYear = floor(364.25*year());
  int currentDays = day() + bMonth + bYear;
  return floor((currentDays-birthDays)/7);
}