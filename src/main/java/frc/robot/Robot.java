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
  int kFrontLeftMotorID = 0;
  int kFrontRightMotorID = -1;
  int kBackRightMotorID = 2;
  int kBackLeftMotorID = 3;

  int joystickPortID = 0;
  
  double deadBand = 0.05;



  CANSparkMax frontLeftMotor = new CANSparkMax(kFrontLeftMotorID, MotorType.kBrushless);
  CANSparkMax frontRightMotor = new CANSparkMax(kFrontRightMotorID, MotorType.kBrushless);
  CANSparkMax backRightMotor = new CANSparkMax(kBackRightMotorID, MotorType.kBrushless);
  CANSparkMax backLeftMotor = new CANSparkMax(kBackLeftMotorID, MotorType.kBrushless);


  MotorControllerGroup leftSide = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
  MotorControllerGroup rightSide = new MotorControllerGroup(frontRightMotor, backRightMotor);


  DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);

  Joystick driveController = new Joystick(joystickPortID);

  double forwardSpeed;
  double turnSpeed;

  @Override
  public void robotInit() {
    rightSide.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
    forwardSpeed = driveController.getRawAxis(1);
    turnSpeed = driveController.getRawAxis(4);

    if(driveController.getRawAxis(1) < deadBand){
       forwardSpeed = 0;
    }

    if(driveController.getRawAxis(4) < deadBand){
      turnSpeed = 0;
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
