package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.*;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends CommandBase {
	
	public TankDrive() {
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		
		/*
		 * Applies deadband to trigger values to allow both joystick and trigger driving
		 */
		if(oi.getTriggerVal(controller.p, trigger.l) >= 0.1 || oi.getTriggerVal(controller.p, trigger.r) >= 0.1) {
			if(oi.getTriggerVal(controller.p, trigger.r) >= 0.1) {
				drivetrain.tankDrive(oi.getTriggerVal(controller.p, trigger.r), oi.getTriggerVal(controller.p, trigger.r));
			} else if(oi.getTriggerVal(controller.p, trigger.l) >= 0.1) {
				drivetrain.tankDrive(-oi.getTriggerVal(controller.p, trigger.l), -oi.getTriggerVal(controller.p, trigger.l));
			}
		} else {
			drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.r, axis.y), oi.getJoyVal(controller.p, joystick.l, axis.y));
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}