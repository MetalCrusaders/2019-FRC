package frc.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	public XboxController xboxcontroller1 = new XboxController(RobotMap.OI_DRIVER_CONTROLLER_MOVER);

	public XboxController xboxcontroller2 = new XboxController(RobotMap.OI_DRIVER_CONTROLLER_OPERATOR);

}
	
