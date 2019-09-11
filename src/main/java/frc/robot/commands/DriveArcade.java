/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robot;
import frc.robot.commands.HatchPopOff;
import frc.robot.subsystems.Pixy;

public class DriveArcade extends Command {

	public DriveArcade() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_drivetrain);
		requires(Robot.m_hatchpop);
		requires(Robot.m_elevator);
		requires (Robot.m_intake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		//DriveArcade Execute
		double moveSpeed = Robot.m_oi.xboxcontroller1.getY(GenericHID.Hand.kLeft);
		double rotateSpeed = Robot.m_oi.xboxcontroller1.getX(GenericHID.Hand.kRight);
		/*
			if (moveSpeed < .15 && moveSpeed > -.15)
				moveSpeed = 0;
			if (rotateSpeed <.15 && rotateSpeed > -.15)
				rotateSpeed = 0;
		*/
		Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
		
		//HatchPop Execute Original 
		/*
		if (Robot.m_oi.xboxcontroller1.getAButtonPressed()) {
			if (Robot.m_hatchpop.isOpen)
				Robot.m_hatchpop.pitchDown();
			else
				Robot.m_hatchpop.pitchUp();
		}
		*/

		//HatchPop Execute 2: calling HatchPopOff (working SOLENOID)
		if (Robot.m_oi.xboxcontroller2.getAButtonPressed())
		{
			HatchPopOff runBoy = new HatchPopOff();

		}

		//Elevator execute
		/*
		if (Robot.m_oi.xboxcontroller1.getBButton()) {
			if (Robot.m_elevator.isOn)
				Robot.m_elevator.elevatorStop();
			else 
				Robot.m_elevator.elevatorUp();
		}
		*/
		boolean elevUp = false; boolean elevDown = false;

		if (Robot.m_oi.xboxcontroller2.getY(GenericHID.Hand.kLeft) > .15)
		 	elevUp = true;
		else if (Robot.m_oi.xboxcontroller2.getY(GenericHID.Hand.kLeft) < -.15)
			elevDown = true;
		else {elevUp = false; elevDown = false;}
		Robot.m_elevator.elevatorOp(elevUp, elevDown);

		/*
		boolean elevUp = Robot.m_oi.xboxcontroller1.getBButton();
		boolean elevDown = Robot.m_oi.xboxcontroller1.getYButton();
		Robot.m_elevator.elevatorOp(elevUp, elevDown);

		boolean intakeUp = Robot.m_oi.xboxcontroller1.getBumper(Hand.kRight);
		boolean intakeDown = Robot.m_oi.xboxcontroller1.getBumper(Hand.kLeft);
		Robot.m_intake.intakeOp(intakeUp, intakeDown);
		*/
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_drivetrain.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
