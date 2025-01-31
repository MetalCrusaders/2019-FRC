/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Talon intakeTalon = new Talon(RobotMap.INTAKE_TALON);

	public Intake() {
	  //intakeTalon = new Talon(RobotMap.INTAKE_TALON);
    }
    
    public void intakeOp (boolean isIntakeR, boolean isIntakeL) {
		if (isIntakeR)
			intakeTalon.set(0.25);
      	else if (isIntakeL)
        	intakeTalon.set(-0.5);
		else intakeStop();
	}
	
	public void intakeStop () {
		intakeTalon.set(0.0);
	}

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}