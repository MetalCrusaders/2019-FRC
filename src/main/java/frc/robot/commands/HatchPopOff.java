/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class HatchPopOff extends InstantCommand {
  public HatchPopOff() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_hatchpop);
    initialize();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
			if (Robot.m_hatchpop.isOpen)
				Robot.m_hatchpop.pitchDown();
			else
				Robot.m_hatchpop.pitchUp();
		}

}
