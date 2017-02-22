package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the climber subsystem.
 * @author Jackson Goth
 */
public class Climber extends Subsystem{

	private final Talon climberMotor;
	
	public Climber() {
		climberMotor = new Talon(RobotMap.CLIMBER_TALON_PORT);
	}
	
	public void spinClimber() {
		if(Constants.CLIMBER_ENABLED) {
			climberMotor.set(Constants.CLIMBER_SPEED);	
		}
	}
	
	public void spinClimberBack() {
		if (Constants.CLIMBER_ENABLED) {
			climberMotor.set(-Constants.CLIMBER_SPEED);
		}
	}
	
	public void stopAll() {
		climberMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
