/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

public class AutonomousDrive extends Command{

  private boolean endFlag = false;
  public Encoder leftEncoder = Robot.leftEncoder;

  public AutonomousDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {leftEncoder.reset();
  leftEncoder.setDistancePerPulse(1);
  }

  // Called repeatedly when this Command is scheduled to run
  // One rotation is 36 pulses for 0.5 secs
  @Override
  protected void execute() {
  if (leftEncoder.getDistance() < 36.0)
      Robot.m_drivetrain.arcadeDrive(0.5, 0);
  else
    endFlag = true;
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return endFlag;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0, 0);
    Robot.leftEncoder.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  
}
