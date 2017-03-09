package org.usfirst.frc.team1306.robot.commands.drivetrain;

/**
 * Enum that stores different drive modes (TANK, ARCADE, BENT_ELBOW)
 * @author Sam Roquitte
 */
public enum DriveMode {

	TANK(0),ARCADE(1),BENT_ELBOW(2);
	
	private final double driveMode;
	
	private DriveMode(int drive) {
		this.driveMode = drive;
	}
	
	public double getDriveMode() {
		return driveMode;
	}
}