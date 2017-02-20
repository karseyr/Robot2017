package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that controls the turret and sets it to differen't positions
 * @author Jackson Goth
 */
public class Turret extends PIDSubsystem {

	private final CANTalon turretMotor;
	
	public Turret() {
		super("Turret PID",Constants.TURRET_P,Constants.TURRET_I,Constants.TURRET_D);
		
		setAbsoluteTolerance(2);
		setOutputRange(-0.1,0.1);
		
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		/*turretMotor.setProfile(0);
		turretMotor.configNominalOutputVoltage(+0.0f,-0.0f);
		turretMotor.configPeakOutputVoltage(+12.0f,-12.0f);
		turretMotor.setF(0.0);
		turretMotor.setP(0.0);
		turretMotor.setI(0.0);
		turretMotor.setD(0.0);
		turretMotor.setMotionMagicAcceleration(0.0);
		turretMotor.setMotionMagicCruiseVelocity(0.0);*/
		turretMotor.enableBrakeMode(true);
		turretMotor.changeControlMode(TalonControlMode.PercentVbus);
		turretMotor.enable();
		
		setSetpoint(0.0);
		
	}
	
	public double getEncPos() {
		return turretMotor.getEncPosition();
	}
	
	public void resetEncoder() {
		turretMotor.reset();
	}
	
	/**
	 * Turns the turret to a given set-point
	 * @param setpoint
	 */
	public void setPosition(double setpoint) {
		if(Constants.TURRET_ENABLED) {
			getPIDController().reset();
			SmartDashboard.putNumber("Position",getEncPos());
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			turretMotor.set(setpoint);
			turretMotor.enable();
		}
	}
	
	/**
	 * Turns the turret with a given speed
	 * @param speed
	 */
	public void set(double speed) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("Position",getEncPos());
			SmartDashboard.putNumber("Set-Speed",speed);
			SmartDashboard.putNumber("Read-Speed",turretMotor.getEncVelocity());
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	@Override
	protected double returnPIDInput() {

		return getEncPos();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("PID-T Position",getEncPos());
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(output);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}