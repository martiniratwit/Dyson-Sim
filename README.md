# Dyson Swarm Simulation
Software Engineering Project

## Introduction

This simulation is meant to roughly mimick the form that a Dyson Swarm surrounding the sun would take.


## Functions

1. Display the simulation
  * Display the graphics of the required objects (sun, mercury, space)
  * Show any satellites added to the simulation
  * Display the buttons meant to alter the simulation's state

2. Manage user inputs
  * Alter the simulation's speed when a speed button is clicked
  * Add a satellite to the simulation when the satellite button is clicked
  * Reset the simulation when the reset simulation button is clicked
  * Change the satellite variables when text box value is changed
  
## Getting Started
### Installation and Setup
- Download and install the Eclipse IDE from https://www.eclipse.org/downloads/packages/.
- Download, extract, and place the 'javafx-sdk-16' into the C:/ directory from 'https://gluonhq.com/products/javafx/'.

Import this repo into Eclipse using the link 'https://github.com/martiniratwit/Dyson-Sim.git'.
- In Eclipse, click the 'File' tab and click 'Import'.
- Click 'Git', followed by 'Projects from Git (with smart import)'.
- Click 'Clone URI', paste the .git link into the 'URI' line.
- Enter your git Username and Password and click next.
- Click 'Next' twice, and click 'Finish'.
After the repo has been cloned, change the buildpath to include the JavaFX JARs.
- Click the 'Window' tab at the top and click 'Preferences'.
- Click 'New' and type in 'JavaFX'.
- Click on 'JavaFX' and click 'Add External JARs'.
- Highlight all of the JAR files in 'C:\javafx-sdk-16\lib' and open them with the Eclipse file explorer tab.
- Click 'Apply and Close'.
- Right click the project, hover over 'Build Path' and click 'Add Libraries'.
- Click 'User Library' and check 'JavaFX'. Click 'Finish'.
- Right click the project, hover over 'Build Path' and click 'Configure Build Path'.
- Click 'Java Build Path' on the left and click 'Order and Export' on the top. Check all boxes.
- Click 'Run/Debug Settings' on the left, click 'View' and click 'Edit'.
- Click 'Arguments' and paste '--module-path C:\javafx-sdk-16\lib --add-modules=javafx.controls' into the 'VM arguments' box. 
- Click 'Apply', 'OK', and 'Apply and Close'

### Run
Open Eclipse to the workspace where you cloned the repo.
Open the project you created for the repo.
Open the 'src' folder and then the 'Sample' folder.
Open the 'View.java' file and run it.

## Demo video

https://www.youtube.com/watch?v=J0muFPGH8Kg

## Contributors
* Robert Martini (martinir@wit.edu), Project Lead
* Devin Salter (salterd@wit.edu), Developer
* Seamus Nauton (nautons@wit.edu), Research and Documentation
