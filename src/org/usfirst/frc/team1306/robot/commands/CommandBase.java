package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Intake;
import org.usfirst.frc.team1306.robot.subsystems.PIDDrivetrain;
import org.usfirst.frc.team1306.robot.subsystems.PIDLeftDrive;
import org.usfirst.frc.team1306.robot.subsystems.PIDRightDrive;
import org.usfirst.frc.team1306.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author James Tautges
 */

public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	//protected static PIDDrivetrain piddrivetrain;
	protected static PIDLeftDrive leftdrive;
	protected static PIDRightDrive rightdrive;
	protected static Shooter shooter;
	protected static Intake intake;

	public static void init() {
		drivetrain = new Drivetrain();
		//piddrivetrain = new PIDDrivetrain();
		leftdrive = new PIDLeftDrive();
		rightdrive = new PIDRightDrive();
		shooter = new Shooter();
		intake = new Intake();
		oi = new OI();
	}

}
