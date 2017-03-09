package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearMech extends Subsystem {
	
	private final Solenoid gearSolenoid;
	private final Timer timer;
	private Spark gearMotor;
	
	public GearMech() {
		gearSolenoid = new Solenoid(RobotMap.GEAR_SOLENOID_PORT);
		timer = new Timer();
		gearMotor = new Spark(RobotMap.GEAR_SPARK_PORT);
	}
	
	/**
	 * Sets the solenoid to true and pushes out the gear
	 */
	public void deployGear() {
		gearSolenoid.set(true);
		//timer.delay(1);
//		timer.reset();
//		timer.start();
//		while(!timer.hasPeriodPassed(Constants.GEAR_DEPLOY_TIME)) {
//		}
		//gearSolenoid.set(false);
	}
	
	/**
	 * Sets the solenoid to false and pulls gear back
	 */
	public void reverseGear() {
		gearSolenoid.set(false);
		//timer.delay(1);
	}
	
	public void spinMotor(double speed) {
		gearMotor.set(speed);
	}
	
	public void stopAll() {
		gearMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
