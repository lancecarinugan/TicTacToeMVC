package mvc.tictactoe;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables
  private boolean whoseMove;
  private boolean gameOver;
  private String[][] board;

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
    this.board = new String[3][3];
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:changeButton", this);
    setVariable1(10);
    setVariable2(-10);
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
    int field = payload.getField();
    int direction = payload.getDirection();
    
    if (direction == Constants.UP) {
      if (field == 1) {
        setVariable1(getVariable1()+Constants.FIELD_1_INCREMENT);
      } else {
        setVariable2(getVariable2()+Constants.FIELD_2_INCREMENT);
      }
    } else {
      if (field == 1) {
        setVariable1(getVariable1()-Constants.FIELD_1_INCREMENT);
      } else {
        setVariable2(getVariable2()-Constants.FIELD_2_INCREMENT);
      }      
    }
  }

}
