/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Spark elevatorSpark = null;
	private Spark elevatorSpark2 = null;
	public int currentToggle = 0;
	public Encoder toggleEncode = Robot.elevEncode;

	public Elevator() {
		elevatorSpark = new Spark(RobotMap.ELEVATOR_SPARK);
		elevatorSpark2 = new Spark(RobotMap.ELEVATOR_SPARK2);
    	//SpeedControllerGroup elevatorMotor = new SpeedControllerGroup(elevatorTalon);
    }
    
    public void elevatorOp (boolean isLiftB, boolean isLiftY) {
		if (isLiftB && !isLiftY)
			{elevatorSpark.set(0.5);
			elevatorSpark2.set(0.5);
			}
		else if (isLiftY && !isLiftB)
			{
			elevatorSpark.set(-0.5);
			elevatorSpark2.set(-0.5);
			}
		else
			elevatorStop();
	}

	public void elevatorStop () {
		elevatorSpark.set(0.0);
		elevatorSpark2.set(0.0);
	}
	
	public void toggleOne()
	{
		if (currentToggle > 1) //if you're curretly above the base height.
			while (toggleEncode.getDistance() > 1) //1 is a placeholder value for the distance from the base of the elevator. It's a distance, based on the distancePerPulse.
				elevatorOp(false, true);
	 	else
			while (toggleEncode.getDistance() < 1)
				elevatorOp(true, false);
		elevatorStop();
		currentToggle = 1;
	}

	public void toggleTwo()
	{
		if (currentToggle > 2) //if you're curretly above the base height.
			while (toggleEncode.getDistance() > 2) //1 is a placeholder value for the distance from the base of the elevator. It's a distance, based on the distancePerPulse.
				elevatorOp(false, true);
		else
			while (toggleEncode.getDistance() < 2)
				elevatorOp(true, false);
		elevatorStop();
		currentToggle = 2;
	}

	public void toggleThree()
	{
		if (currentToggle > 3) //if you're curretly above the base height.
			while (toggleEncode.getDistance() > 3) //1 is a placeholder value for the distance from the base of the elevator. It's a distance, based on the distancePerPulse.
				elevatorOp(false, true);
		else
			while (toggleEncode.getDistance() < 3)
				elevatorOp(true, false);
		elevatorStop();
		currentToggle = 3;
	}

	public void toggleFour()
	{
		if (currentToggle > 4) //if you're curretly above the base height.
			while (toggleEncode.getDistance() > 4) //1 is a placeholder value for the distance from the base of the elevator. It's a distance, based on the distancePerPulse.
				elevatorOp(false, true);
 		else
			while (toggleEncode.getDistance() < 4)
				elevatorOp(true, false);
		elevatorStop();
		currentToggle = 4;
	}

	public void toggleFive()
	{
		while (toggleEncode.getDistance() < 5)
			elevatorOp(true, false);
		elevatorStop();
		currentToggle = 5;
	}

	

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}