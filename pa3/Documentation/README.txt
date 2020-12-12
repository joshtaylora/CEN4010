===============================================================================================================
========================================== External JAR dependencies ==========================================



===============================================================================================================
======================================== How to Run from Command Line =========================================


===============================================================================================================
============================================= Project Overview ================================================

                                              Strong Features
                                              ---------------
   As a whole, our Monopoly Application has been designed such that the user is not exposed to any internal
low-level components of the program. Upon beginning the PA3 project and analyzing the various requirements and
use cases involved with the tasks associated with the project, we decided that the Model/View/Controller design
pattern would best fit the needs and specifications for the project.
    Our implementation of the MVC design pattern allowed us to separate the front-end user-facing elements from
the game logic, and the game logic from the underlying application logic. This meant that instead of having to
manage all of the objects involved with implementing a GUI Monopoly game individually, we could divide the
necessary functionality into Models that "modeled" the behavior of the Objects that comprise the components of
the game, without having to immediately focus on their interoperability with each other. This allowed for
decreased coupling between our logical game components.
    Once we had modeled how the game components operate and interact, we moved on to focusing on how to best
represent them visually through the abstraction provided by FXML "View" files. Using FXML for the design
of the visual elements of our application allowed us to make use of the excellent "SceneBuilder" tool which
helped us create skeleton FXML files that we could populate with images and controls that we felt best
represented the visual forms of the game models. This is most evident in the use of the TileView fxml file
which gets shows a visual representation of the Tile objects that are used to represent the spaces on the
board. This View also contains controls which allow for user input.
    In order to process user input and control the flow of the game and application logic, we implemented 5
Controller classes that were controlled and managed by 1 main facade; the MainController class. The
MainController class is first loaded by the FXMLLoader when the driver class, Monopoly.java, sets the Stage
and Scene and calls the load method on an instance of FXMLLoader using the TabbedView fxml View file as the
target. This causes the JavaFX graphics engine to load the TabbedView View file and instantiate an instance
of it's Controller: MainController.
    MainController is the primary facade for our application as it is only through the MainController
instance that the various Tabs representing the active View components can be interacted with by the user
in order to cause the game to progress. The MainController is used to initialize all other controller files
which in turn perform various control operations on the Models that make up the Game application. The
Controllers are responsible for communicating information between models and between controllers, allowing
for the models themselves to not care about how they receive the information necessary for their function.
An example of this can be seen through the TradeController class which has Event Listeners waiting for
player's to perform input on the number and kind of property deed's they want to trade, as well as the
money they want to include in the trade. Once the TradeController has detected the events corresponding to
both Player's indicating they wish to perform the trade, and then subsequently clicking the "Complete Trade"
Button, it is then that the controller takes the Deeds and monetary amounts that both players involved in
the trade are offering, and performs the necessary operations on the Player objects to result in the trade
being successful.
   Overall, the MVC pattern we utilized during project 3 ensured that project 4's focus on the facade
structural pattern was a very easy transition for us once we got all of the PA3 components up and running.
Because the interactions between

