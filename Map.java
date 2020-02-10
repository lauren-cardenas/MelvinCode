/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Map {
    //remote values
        //driving variables 
        public static final double TURNING_RATE = 0.85;
        public static final double DRIVING_SPEED = 0.85;
        public static final double SHOOTING_SPEED = 1.0;

        //controller USB ports
        public static final int DRIVER_CONTROLLER = 1;
        public static final int OPERATOR_CONTROLLER = 0;

        //auto variables 
        public static final double AUTO_DISTANCE = 20;
        public static final double AUTO_SPEED = 0.8;
        public static final int timer = 8;

        //Robot Stabilization Variables
        public static final double leftSlow = 0.34;
        public static final double rightSlow = -0.34;
        public static final double rotateSpeed = 0.45;
        public static final double rotateSpeedSlow = 0.35;


        //motors 
        public static final int m_leftMotor1 = 0;
        public static final int m_leftMotor2 = 1;
        public static final int m_rightMotor1 = 2;
        public static final int m_rightMotor2 = 3;

        public static final int m_shooterLeft = 0;
        public static final int m_shooterRight = 1;

        //encoder 
        public static final int m_leftEnc1 = 0;
        public static final int m_leftEnc2 = 1;
        public static final int m_rightEnc1 = 2;
        public static final int m_rightEnc2 = 3;
        public static final double wheelDiameter = 6;
        public static final double encoderCPR = 360;

    

    
}
