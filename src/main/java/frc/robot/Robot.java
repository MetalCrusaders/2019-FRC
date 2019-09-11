/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousDrive;
import io.github.pseudoresonance.pixy2api.links.Link;
import io.github.pseudoresonance.pixy2api.links.SPILink;
import io.github.pseudoresonance.pixy2api.Pixy2.Checksum;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Line;
import io.github.pseudoresonance.pixy2api.Pixy2Video;
import frc.robot.commands.HatchPopOff;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchPop;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pixy;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Drivetrain m_drivetrain = null;
	public static Elevator m_elevator = null;
	public static OI m_oi;
	public static HatchPop m_hatchpop = null;
	public static Intake m_intake = null;
	public static Pixy2 greenCam = Pixy2.createInstance(new SPILink());

	//public static PixyObject pixyobject = null;

	public static InstantCommand m_hatchpopoff;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */

	//Constants
	public static final double kDistancePerRevolution = 12*Math.PI;  // guestimate from your code
    public static final double kPulsesPerRevolution = 2048;     // for an AS5145B Magnetic Encoder
	public static final double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;
	public static final Encoder leftEncoder = new Encoder(0, 1, false, EncodingType.k1X); //10 Degrees/pulse. Changing encoding type appears to do nothing.
	public static final Encoder elevEncode = new Encoder(2, 3, false, EncodingType.k1X);
	@Override
	public void robotInit() {
		m_drivetrain = new Drivetrain();
		m_autonomousCommand = new AutonomousDrive();
		m_elevator = new Elevator();
		m_oi = new OI();
		//m_hatchpopoff = new HatchPopOff();
		m_hatchpop = new HatchPop();
		m_intake = new Intake();
		///pixyobject = new PixyObject();
		//m_intake = new Intake();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		leftEncoder.setDistancePerPulse(kDistancePerPulse);
		leftEncoder.reset();
		elevEncode.setDistancePerPulse(kDistancePerPulse);
		elevEncode.reset();
		greenCam.init();
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */
		
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	/*
	private double getAverageEncoderPosition() {
		return (leftEncoder.getDistance());
	}
	*/
	public void resetEncoders() {
		leftEncoder.reset();

	}
}
