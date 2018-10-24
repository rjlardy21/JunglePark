//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: JunglePark
// Files: CS300JunglePark.jar, background.png, tiger.png
// Course: CS300 Fall 2018
//
// Author: Reece Lardy
// Email: RLardy@wisc.edu
// Lecturer's Name: Alexander Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nick Hayden
// Partner Email: nhayden@wisc.edu
// Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (NONE)
// Online Sources: (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * JunglePark is a class that allows users to place and remove up to 8 tigers in a colorful GUI
 * called the jungle park
 * 
 * @author Reece Lardy & Nick Hayden
 *
 */
import java.util.Random;

public class JunglePark {
    private static PApplet processing; // PApplet object that represents the graphic
    // interface of the JunglePark application
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Tiger[] tigers; // array storing the current tigers present
    // in the Jungle Park

    private static Random randGen; // Generator of random numbers

    /**
     * Defines the initial environment properties of the application
     * 
     * @param processingObj represents a reference to the graphical interface of the application
     */
    public static void setup(PApplet processingObj) {
        randGen = new Random();
        processing = processingObj; // initialize the processing field to the one passed into
        // the input argument parameter
        // Set the color used for the background of the Processing window
        processing.background(245, 255, 250); // Mint cream color
        // initialize and load the image of the background
        backgroundImage = processing.loadImage("images/background.png");
        // Draw the background image at the center of the screen
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);
        // width [resp. height]: System variable of the processing library that stores the width
        // [resp. height] of the display window.
        tigers = new Tiger[8];
    }

    /**
     * Updates the environment properties of the application
     */
    public static void update() {
        // Set the color used for the background of the Processing window
        processing.background(245, 255, 250); // Mint cream color
        // initialize and load the image of the background
        backgroundImage = processing.loadImage("images/background.png");
        // Draw the background image at the center of the screen
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);
        // width [resp. height]: System variable of the processing library that stores the width
        // [resp. height] of the display window.
        for (int i = 0; i < tigers.length; i++) {
            if (tigers[i] != null) {
                tigers[i].draw();
            }
        }
    }

    /**
     * Determines whether or not the user's mouse is over the image of a tiger in the tigers array
     * 
     * @param Tiger represents a reference to a tiger in the tigers array
     */
    public static boolean isMouseOver(Tiger tiger) {
        int lowerlimitx = (int) tiger.getPositionX() - tiger.getImage().width / 2;
        // calculation to find leftmost point of GUI where the tiger image is
        int upperlimitx = (int) tiger.getPositionX() + tiger.getImage().width / 2;
        // calculation to find rightmost point of GUI where the tiger image is
        int lowerlimity = (int) tiger.getPositionY() - tiger.getImage().height / 2;
        // calculation to find lowest point of GUI where the tiger image is
        int upperlimity = (int) tiger.getPositionY() + tiger.getImage().height / 2;
        // calculation to find highest point of GUI where the tiger image is
        if ((lowerlimitx < processing.mouseX) && (processing.mouseX < upperlimitx)
            && (processing.mouseY > lowerlimity) && (processing.mouseY < upperlimity))
        // if mouse position is within tiger image, then returns true
        {
            return true;
        }
        // if mouse position is not within tiger image, return false
        return false;
    }

    /**
     * Sets dragging property of tiger object in tigers array to true if mouse is over a tiger that
     * exists in the park
     */
    public static void mouseDown() {
        for (int i = 0; i < tigers.length; i++) { // for loop to iterate through tigers array
            if (tigers[i] != null && isMouseOver(tigers[i])) { // if object in tiger array exists in
                                                               // GUI and mouse is over that tiger
                                                               // object
                tigers[i].setDragging(true); // setDragging property of tiger is set to true for the
                                             // tigers array object
                break; // stop iterating through tiger array
            }
        }
    }

    /**
     * Sets dragging property of every tiger object in GUI in tigers array to false
     */
    public static void mouseUp() {
        for (int i = 0; i < tigers.length; i++) { // for loop to iterate through tigers array
            if (tigers[i] != null) { // makes sure tiger object is not null
                tigers[i].setDragging(false); // set tiger object setDragging to false
            }
        }
    }

    /**
     * Each time the user presses a key, this method is called. If user presses t or T, a new tiger
     * is added to the jungle park (provided the park is not full of 8 tigers). If user presses r or
     * R, the tiger object highlighted by the user's mouse is removed from the jungle park
     */
    public static void keyPressed() {
        if (processing.key == 't' || processing.key == 'T') { // checks to see if user keyed t or T
            for (int i = 0; i < tigers.length; i++) {
                // for loop that iterates through tigers in tigers array
                if (tigers[i] == null) { // if there is an empty spot for a tiger object in tigers
                                         // array
                    tigers[i] = new Tiger(processing, (float) randGen.nextInt(processing.width),
                        (float) randGen.nextInt(processing.height));
                    // create new tiger object in tigers array
                    break;
                }
            }
        }
        if (processing.key == 'r' || processing.key == 'R') { // checks to see if user keyed r or R
            for (int i = 0; i < tigers.length; i++) {
                // for loop that iterates through tigers in tigers array
                if (tigers[i] != null && isMouseOver(tigers[i])) {
                    // if there is an object in tigers array and mouse is hovering over it
                    tigers[i] = null;
                    // remove that tiger object from tigers array
                    break;
                    // break out of for loop
                }
            }
        }
    }

    /**
     * main method of JunglePark. It calls startapplication() in utility which launches the GUI
     * where the user interacts with the program
     */
    public static void main(String[] args) {
        Utility.startApplication(); // launches GUI
    }

}
