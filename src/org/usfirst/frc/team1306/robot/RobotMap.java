package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class RobotMap {

	//OI Ports
	public static final int PRIMARY_PORT = 0;
	public static final int SECONDARY_PORT = 1;

	//Drivetrain Ports
	public static final int LEFT_TALON_1_PORT = 1;
	public static final int LEFT_TALON_2_PORT = 3;
	public static final int RIGHT_TALON_1_PORT = 2;
	public static final int RIGHT_TALON_2_PORT = 4; 

	//Shooter Ports
	public static final int LEFT_SHOOTER_PORT = 5;
	public static final int RIGHT_SHOOTER_PORT = 6;
	public static final int INDEXER_TALON_PORT = 7;
	
	//Turret Ports
	public static final int TURRET_TALON_PORT = 8;
	
	//Hopper Ports
	public static final int HOPPER_TALON_PORT = 0;
	
	//Intake Ports
	public static final int INTAKE_TALON_PORT = 1;
	
	//Climber Ports
	public static final int CLIMBER_TALON_1_PORT = 2;
	public static final int CLIMBER_TALON_2_PORT = 3;
	
	//Gearmech Ports
	public static final int GEAR_SPARK_PORT = 4;
	public static final int GEAR_SOLENOID_PORT = 4;
}
