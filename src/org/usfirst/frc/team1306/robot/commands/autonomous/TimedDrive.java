package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * A command that drives the robot straight forward for a certain amount of
 * time.
 * 
 * @author Jackson Goth
 */
public class TimedDrive extends CommandBase {

	/** The speed we wish the drivetrain to go */
	private final double desiredSpeed;
	/** The power for the motors, on a scale from -1.0 to 1.0. */
	private final double speed;
	/** The amount of time to drive before stopping, in seconds. */
	private final double time;
	/** The timer that controls how long the robot drives before stopping */
	private final Timer timer;

	/**
	 * Constructs a new TimedDrive command. Initializes the timer and requires
	 * the drivetrain.
	 */
	public TimedDrive(double desiredSpeed, double time) {
		this.desiredSpeed = desiredSpeed;
		this.time = time;
		this.speed = Constants.SPEED_DEFAULT;
		timer = new Timer();
		requires(drivetrain);
	}

	/**
	 * Called just before this Command runs the first time. Here, the timer is
	 * started.
	 */
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. It runs both
	 * motors at the designated throttle.
	 */
	@Override
	protected void execute() {
		
		drivetrain.drivePID(speed, speed, desiredSpeed);
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command ends after a designated amount of time.
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

	/**
	 * Called once after isFinished returns true. Stops the drivetrain.
	 */
	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Stops the drivetrain so that whatever is
	 * interrupting can use it.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}
