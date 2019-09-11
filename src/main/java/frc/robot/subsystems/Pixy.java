/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.DriveArcade;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

/**
 * Add your docs here.
 */
public class Pixy extends Subsystem {
    public static Pixy2 navi = Robot.greenCam;
    public static Encoder encode = Robot.leftEncoder;
    private final double degCord = .19; //degress/1 x coordinate on pixy (from 0 to 319)
    private final double turnDeg = 0; // turns/1 degree on robot (need to calculate)

    public static void tapeAlign() {//command to automatically center on a hatch for scoring 
        Pixy2CCC naviCCC = navi.getCCC();
        encode.reset();
        ArrayList<Block> blockNums = naviCCC.getBlocks(); //creates an ArrayList of blocks, each with its own x, y, signature, etc.
        if (blockNums.size() > 0){
            Robot.m_drivetrain.arcadeDrive(1,0);
            
            int avgX = (blockNums.get(0).getX() + blockNums.get(1).getX()) / 2;
            int goalX = avgX - 160;
            double goalDeg = goalX * .19;

            if (goalX > 0)  //Turn left/right depending on distance from desired value.
                Robot.m_drivetrain.arcadeDrive(0, 1);
            if (goalX < 0)
                Robot.m_drivetrain.arcadeDrive(0, -1);
        }
    }
    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveArcade());
    }
}
