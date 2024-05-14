import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class Sketch extends PApplet {
  
  // Initiate the images
  PImage imgRobber;
  PImage imgVault;
  PImage imgMoney;
  PImage imgTruck;
  PImage imgPolice;
  PImage imgPrison;
  PImage imgCoins;
  
  // variables for the background image
  float fltBackgroundX = 0;
  float fltBackgroundY = 0;
  
  // money and coins location spawning
  int maxMoney = 100000000;
  int maxCoins = 100000000;
  int coinsCount = 0;
  int moneyCount = 0; 
  float[] moneyX = new float[maxMoney]; 
  float[] moneyY = new float[maxMoney]; 
  float[] coinsX = new float[maxCoins]; 
  float[] coinsY = new float[maxCoins]; 
  float[] moneySize = new float[maxMoney]; 
  Random random = new Random();  

  // Police movement variables
  float policeX = 200; 
  float policeY = 200;  
  boolean boolUp = false;
  boolean boolRight = false;
  boolean boolLeft = false;
  boolean boolDown = false;
  
  public void settings() {
    size(400, 400);
  }
  
  public void setup() {
    // Set different backgrounds and size them
    imgVault = loadImage("vault2.jpg");
    imgVault.resize(width, height);

    imgTruck = loadImage("truck.jpg");
    imgTruck.resize(width, height);

    imgPrison = loadImage("prison.jpg");
    imgPrison.resize(width, height);
    
    // load robber
    imgRobber = loadImage("Robber.png");
    imgRobber.resize(width/4, height/4);
    
    // setup money image
    imgMoney = loadImage("Money.png");
    imgMoney.resize(width / 8, height / 8);

    // setup police
    imgPolice = loadImage("police.png");
    imgPolice.resize(width/4, height/4);

    // setup coins
    imgCoins = loadImage("coins.png");
    imgCoins.resize(width/6, height/6);
  }
  
  public void draw() {
    // change background to truck if key pressed
    if(keyPressed){
      if (key == 't') {
        image(imgTruck, fltBackgroundX, fltBackgroundY);
      }
      if (key == 'p') {
        image(imgPrison, fltBackgroundX, fltBackgroundY);
      }
    }
    else{
      // default background is vault
      image(imgVault, fltBackgroundX, fltBackgroundY);
    }
    
    // Draw robber at mouse position
    image(imgRobber, mouseX, mouseY);

    // Draw police at current position
    image(imgPolice, policeX, policeY);

    // movement for police
    if (boolUp) policeY -= 2;
    if (boolDown) policeY += 2;
    if (boolLeft) policeX -= 2;
    if (boolRight) policeX += 2;
    
    // Draw all money images and resize them
    for (int i = 0; i < moneyCount; i++) {
      float scaledWidth = imgMoney.width * moneySize[i]; 
      float scaledHeight = imgMoney.height * moneySize[i]; 
      image(imgMoney, moneyX[i], moneyY[i], scaledWidth, scaledHeight);  
    }

    // spawn coins
    for (int i = 0; i < coinsCount; i++) {
      image(imgCoins, coinsX[i], coinsY[i]);
    }
  }
  
  public void mouseDragged() {
    // Add current mouse position to money arrays
    if (moneyCount < maxMoney) {
      moneyX[moneyCount] = mouseX;
      moneyY[moneyCount] = mouseY;
      moneySize[moneyCount] = random.nextFloat() * 1.0f + 0.5f; 
      moneyCount++;
    }
  }

  public void mousePressed() {
    if (coinsCount < maxCoins) {
      coinsX[coinsCount] = mouseX;
      coinsY[coinsCount] = mouseY;
      coinsCount++;
    }
  }


  public void keyPressed() {
    if (key == 'w') {
      boolUp = true;
    }
    if (key == 's') {
      boolDown = true;
    }
    if (keyCode == 65) {
      boolLeft = true;
    }
    if (key == 'd') {
      boolRight = true;
    }
  }

  public void keyReleased() {
    if (key == 'w') {
      boolUp = false;
    }
    if (key == 's') {
      boolDown = false;
    }
    if (keyCode == 65) {
      boolLeft = false;
    }
    if (key == 'd') {
      boolRight = false;
    }
  }
}