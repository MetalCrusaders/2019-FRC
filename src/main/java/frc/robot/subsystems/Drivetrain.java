/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Talon leftFrontTalon = null;
	Talon leftBackTalon = null;
	Talon rightFrontTalon = null;
	Talon rightBackTalon = null;

	DifferentialDrive differentialDrive = null;

	public Drivetrain() {
		leftFrontTalon = new Talon(RobotMap.DRIVETRAIN_LEFT_FRONT_TALON);
		leftBackTalon = new Talon(RobotMap.DRIVETRAIN_LEFT_BACK_TALON);
		rightFrontTalon = new Talon(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
		rightBackTalon = new Talon(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON);
		//bigBoy = new Encoder(RobotMap.ENCODER_LEFT);

		SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFrontTalon, leftBackTalon);
		SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFrontTalon, rightBackTalon);

		differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
	}

	public void arcadeDrive(double moveSpeed, double rotateSpeed) {
		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}
