// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   // Constant fields
  int kFrontLeftMotorID = 4;
  int kFrontRightMotorID = 2;
  int kBackRightMotorID = 1;
  int kBackLeftMotorID = 3;

  int kTopRollerMotorID = 6;
  int kLowerRollerMotorID = 7;

  double topRollerSpeed = 1;

  int joystickPortID = 0;
  
  double deadBand = 0.05;



  CANSparkMax frontLeftMotor = new CANSparkMax(kFrontLeftMotorID, MotorType.kBrushed);
  CANSparkMax frontRightMotor = new CANSparkMax(kFrontRightMotorID, MotorType.kBrushed);
  CANSparkMax backRightMotor = new CANSparkMax(kBackRightMotorID, MotorType.kBrushed);
  CANSparkMax backLeftMotor = new CANSparkMax(kBackLeftMotorID, MotorType.kBrushed);


  CANSparkMax rollerTopMotor = new CANSparkMax(kTopRollerMotorID, MotorType.kBrushed);
  CANSparkMax rollerBottomMotor = new CANSparkMax(kLowerRollerMotorID, MotorType.kBrushed);

  MotorControllerGroup leftSide = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
  MotorControllerGroup rightSide = new MotorControllerGroup(frontRightMotor, backRightMotor);


  DifferentialDrive drive = new DifferentialDrive(rightSide, leftSide);

  Joystick driveController = new Joystick(joystickPortID);

  double forwardSpeed;
  double turnSpeed;

  @Override
  public void robotInit() {
    rightSide.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
    forwardSpeed = driveController.getRawAxis(4);
    turnSpeed = driveController.getRawAxis(1);

    if(Math.abs(driveController.getRawAxis(4) ) < deadBand){
       forwardSpeed = 0;
    }

    if(Math.abs(driveController.getRawAxis(1)) < deadBand){
      turnSpeed = 0;
    }


    if(driveController.getRawButton(0)){
      rollerTopMotor.set(topRollerSpeed);
    }

    if(driveController.getRawButton(0)){
      rollerBottomMotor.set(topRollerSpeed);
    }

    drive.arcadeDrive(forwardSpeed, turnSpeed);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
