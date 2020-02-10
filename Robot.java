/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  AHRS ahrs;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(Map.m_leftMotor1);
  private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(Map.m_leftMotor2);
  private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(Map.m_rightMotor1);
  private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(Map.m_rightMotor2);

  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(rightMotor1, rightMotor2);

  private final DifferentialDrive m_driveTrain = new DifferentialDrive(m_leftMotors, m_rightMotors);
  private final Timer m_timer = new Timer();
    //contorller definitions
    private final XboxController m_driverController = new XboxController(Map.DRIVER_CONTROLLER);
    private final XboxController m_operatorController = new XboxController(Map.OPERATOR_CONTROLLER);



// creating a NavX port, and setting up the ability to give Error Reports
    public Robot() {
      try {
        ahrs = new AHRS(SPI.Port.kMXP);


      } catch (RuntimeException ex) {

        DriverStation.reportError ("Error instantiating naxX-MXP: " + ex.getMessage(), true);

      }



    }


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    resetGyro();
   


    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    m_timer.reset();
    m_timer.start();
    ahrs.reset();


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
   while (m_timer.get() < 8){

    m_driveTrain.setSafetyEnabled(false);
    //delay for shooting the balls into powerport
    Timer.delay(2); 
    //drive back for 2 seconds
    m_driveTrain.arcadeDrive(-.6, 0); 
    Timer.delay(2);
    // turn right forward for .5 seconds
    m_rightMotors.set(-.5); 
    Timer.delay(.2);
    //Drive back for 2.5 seconds
    m_driveTrain.arcadeDrive(-.5, 0); 
    Timer.delay(3.5);
    //Left motors move forward to reposition Robot
    m_leftMotors.set(.5);
    Timer.delay(.3);
    //Robot moves back a bit
    m_driveTrain.arcadeDrive(-0.6, 0);
    Timer.delay(2.5);
    m_driveTrain.arcadeDrive(0.6, 0);
    Timer.delay(3);
    m_driveTrain.arcadeDrive(0, 0);


    //More Code to be added



}

}

  
   
   
   
   
   
   
   
   
   
    /*if (m_timer.get() < 5) {

      m_driveTrain.arcadeDrive(-.5,0);

    } else if (m_timer.get() < 6.4) {

      m_leftMotors.set(.5);

    } else if (m_timer.get() < 8) {

      m_driveTrain.arcadeDrive(.5,0);

    } else if (m_timer.get() < 8.5) {

      m_leftMotors.set(.5);

    } 
*/    

    /*{
    while (m_timer.get() < 3) {
      m_driveTrain.setExpiration(3);
      m_driveTrain.arcadeDrive(.6,0);
    }*/
        /*switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
    }*/
   

private void bruhControls(){
  if (m_operatorController.getAButton()){

    System.out.println("A-bruh");
    
  }else if (m_operatorController.getBButton()){
  
    System.out.println("B-bruh");
    
  }else if (m_operatorController.getXButton()){
  
    System.out.println("X-bruh");
    
  }else if (m_operatorController.getYButton()){
  
    System.out.println("Y-bruh");
      
  }

}

private void driverBruh(){

  if (m_driverController.getAButton()){

    System.out.println("A-bruh");
    
  }else if (m_driverController.getBButton()) {
  
    System.out.println("B-bruh");
    
  }else if (m_driverController.getXButton()){
  
    System.out.println("X-bruh");
    
  }else if (m_driverController.getYButton()){
  
    System.out.println("Y-bruh");
      
  }


}


private void resetGyro() {

System.out.println("bruh");

}



  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {


final double triggerVal = (m_driverController.getTriggerAxis(Hand.kRight) - m_driverController.getTriggerAxis(Hand.kLeft)) * Map.DRIVING_SPEED;
final double stick = (m_driverController.getX(Hand.kLeft)) * Map.TURNING_RATE;

double left_command = (triggerVal + stick) * Map.DRIVING_SPEED;
double right_command = (triggerVal - stick) * Map.DRIVING_SPEED;

m_driveTrain.tankDrive(left_command, right_command);

bruhControls();


while (m_driverController.getPOV() == 0  && m_operatorController.getPOV() == 0){
  System.out.println("Double Bruh");
}






}























// Straight Driving 
/*double gyroAngle = ahrs.getAngle();
SmartDashboard.putNumber("Gyro Bruh", gyroAngle/175);
//SmartDashboard.putNumber("Gyro Bruh", gyroAngle);

if (m_driverController.getAButton()){

  ahrs.reset();

}

if  (m_driverController.getBButton() && ahrs.isMoving()) {
if (Math.abs(gyroAngle) <= 3) {
  m_driveTrain.tankDrive(Map.leftSlow-(gyroAngle)/15, Map.rightSlow-(gyroAngle)/15);
 } else if (Math.abs(gyroAngle) < 10){
  m_driveTrain.tankDrive(Map.leftSlow, 1.1 * Map.rightSlow);
 }else if (gyroAngle < 0){
  m_driveTrain.tankDrive(1.1 * Map.leftSlow, Map.rightSlow);
} else {

if (gyroAngle > 0) {
while (gyroAngle > 10 && isAutonomous()){
m_driveTrain.tankDrive(-Map.rotateSpeed, -Map.rotateSpeed);
} while (gyroAngle > 0 && isAutonomous()) {
m_driveTrain.tankDrive(-Map.rotateSpeedSlow, -Map.rotateSpeedSlow);
} while (gyroAngle < 0 && isAutonomous()) {
m_driveTrain.tankDrive(Map.rotateSpeedSlow, Map.rotateSpeedSlow);
} while (gyroAngle <-10 && isAutonomous()){
m_driveTrain.tankDrive(Map.rotateSpeed, Map.rotateSpeed);
}
}
}
}

// Operator controller is the Bruh Controller, any button that it presses will print Bruh*/ 


  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

 bruhControls();
 driverBruh();

  }
}
