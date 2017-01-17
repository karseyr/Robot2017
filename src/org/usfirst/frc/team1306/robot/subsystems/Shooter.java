package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Jackson Goth
 *
 */
public class Shooter extends Subsystem {

	private final Talon shooterMotor;
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {
		shooterMotor = new Talon(0);
	}
	
	/*
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		
		if(Constants.SHOOTER_ENABLED) {
			shooterMotor.set(shooterSpeed);
		}
	}
	
	/*
	 * Stops the shooter when done shooting
	 */
	public void stopMotor() {
		shooterMotor.set(0.0);
	}
	
	public void stopAll() {
		shooterMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
