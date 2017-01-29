package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls drivetrain motors with the joysticks from OI.java, and can limit intake speed
 * @author Jackson Goth and Sam Roquitte
 */

public class Drivetrain extends Subsystem {
	
	private final CANTalon[] motors;	
	private final CANTalon leftmotor1;
	private final CANTalon rightmotor1;
	private final CANTalon leftmotor2;
	private final CANTalon rightmotor2;
	//private final CANTalon leftmotor3;
	//private final CANTalon rightmotor3;
	
	public Drivetrain() {
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		leftmotor2 = new CANTalon(RobotMap.LEFT_TALON_2_PORT);
		rightmotor2 = new CANTalon(RobotMap.RIGHT_TALON_2_PORT);
		//leftmotor3 = new CANTalon(RobotMap.LEFT_TALON_3_PORT);
		//rightmotor3 = new CANTalon(RobotMap.RIGHT_TALON_3_PORT);
		
		motors = new CANTalon[] {leftmotor1, rightmotor1};
		setupMotors(leftmotor1,leftmotor2);
		setupMotors(rightmotor1,rightmotor2);
	}
	
	/**
	 * Sets up the motors, master in pvb mode and sets the slave motor to a follower
	 * @param master
	 * 		The talon that will be the master, folower will follow this talon
	 * @param slave
	 * 		The follower talon
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(0.0);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
		slave.enable();
	}
	
	/**
	 * Sets up motors, master in pvb mode and sets 2 slaves as followers
	 * @param master
	 * 		The talon that will be the master, folower will follow this talon
	 * @param slave1
	 * 		First follower talon
	 * @param slave2
	 * 		Second follower talon
	 */
	private void setupMotors(CANTalon master, CANTalon slave1, CANTalon slave2) {
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(0.0);
		master.enable();
		
		slave1.changeControlMode(TalonControlMode.Follower);
		slave1.set(master.getDeviceID());
		slave1.enable();
		
		slave2.changeControlMode(TalonControlMode.Follower);
		slave2.set(master.getDeviceID());
		slave2.enable();
	}
	
	/**
	 * Takes 2 params to control the motors
	 * 
	 * @param leftVal
	 * 		Speed for the left side (from -1 to 1)
	 * @param rightVal
	 * 		Speed for the right side (from -1 to 1)
	 */
	public void tankDrive(double leftVal, double rightVal) {
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);
		if(Constants.DRIVETRAIN_ENABLED) {
			SmartDashboard.putNumber("leftvelocity",leftmotor1.getEncVelocity());
			SmartDashboard.putNumber("rightvelocity",rightmotor1.getEncVelocity());
			
			leftmotor1.set(leftVal*Constants.SPEED_MODIFIER);
			rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER);
			leftmotor1.set(leftVal*Constants.SPEED_MODIFIER);
			rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER);
		}
			
		/**
		 * Currently lowers speed of intake motor when drivetrain speed is above 0.5
		 */
		if(rightVal*Constants.SPEED_MODIFIER > .5) {
			Intake.lowerSpeed();
		} 
		else {
			Intake.raiseSpeed();
		}

	}
	
	/**
	 * Sets the motor speed to 0
	 * 
	 * @param motor
	 * 		The motor that you would like to stop 0,1:(left1, right1), also stopps follower motors
	 */
	
	public void stopMotor(int motor) {
		motors[motor].set(0.0);
	}
	
	public void stopAll() {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(0.0);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}
}