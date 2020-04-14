# SeniorDesignApp
App code for the Duckweed Trimmer Capstone Project.
Completed for Senior Design 2 for Florida Polytechnic University.

### Contributors
#### Ryan Bressette
  Main app development
#### Lauren Czech
  Documentation and reports
#### Ramon Morales
  Documentation and reports
#### Andrew Ryan
  Documentation and reports
#### William Woodham
  Team leader
  
## Project Outline
### Purpose
  Bok Tower Gardens has several ponds that are being overcrowded by Duck Weed, a small floating weed. They have requested an autonomous device that will patrol around their ponds and remove the weed. The device must support manual via a phone application and autonomous control, be able to operate for a long period of time before being recharged, and blend in with th aesthetics of Bok Tower Gardens.
  
### Scope
  Our contribution to the project is limited to the phone application while the body of the robot is being built by a group of Mechanical Engineering students and the onboard microcontroller is being programmed by a group of Computer Engineers. Due to the extenuating circumstances of Covid-19, the three components of this project will not be integrated this semester. Instead, the components will be finished separately with the hopes that following design groups will be able to finish the project and add more features.
  
### Code Outline
  The app is being developed using Android Studio. We were originally going to use Flutter so we could allow IOS devices to run our app, but we decided that the effort required to learn this tool would be too great and we would not be able to finish the app on time.
  The app consists of a login screen and a home page that contains the components necessary to connect to and control the robot. Two helper threads are created to handle receiving and sending bluetooth messages. Any status messages received are inspected to see if the robot has encountered any errors. If so, then a notification and a toast message are created.
  
### Using the App
#### Connecting to the robot.
  1. Ensure the robot is turned on and its bluetooth server is running.
  2. Press and hold the bluetooth button on the status bar to see a list of nearby bluetooth devices.
  3. Pair the phone to the robot.
  4. Open the app and enter the login credentials (any email address and "donny") for now.
  5. Tap the button labelled "Connect a device" to connect the devices.
  
#### Controlling the robot.
  1. If the app is not connected to the robot, follow the steps under "Connecting to the robot".
  2. Tap the button labelled "Control Robot" and control buttons will appear.
  3. Tap any of the four control buttons to send movement commands to the robot.
  
#### Checking the status of the robot.
  1. Connect to the robot by following the steps under "Connecting to the robot">
  2. Check for any notifications on your phone. The app will generate notifications if it receives any error messages.
