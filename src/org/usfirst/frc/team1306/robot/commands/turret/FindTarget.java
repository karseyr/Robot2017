package org.usfirst.frc.team1306.robot.commands.turret;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @FindTarget
 * 
 * 
 * 
 * @author Jackson Goth
 */
public class FindTarget extends CommandBase {

	private double averagedYaw, accumulator, visionAdjustment;
	private boolean scanning;
	private ScanDirection scanDir;
	private ArrayList<Double> yawList;
	private Timer timer;
//	DigitalOutput leds;
	
	public FindTarget(ScanDirection direction) {
		requires(turret);
		
		yawList = new ArrayList<Double>(); //Array used for storing and averaging yaw values from the jetson
//		leds = new DigitalOutput(0); //Vision LEDS
		scanning = true; //If the turret should scan in initialize()
		scanDir = direction; //The direction the turret should scan in
		timer = new Timer();
	}
	
	/**
	 * Starts looking for the target without scanning
	 */
	public FindTarget() {
		requires(turret);
		
		yawList = new ArrayList<Double>(); //Array used for storing and averaging yaw values from the jetson
//		leds = new DigitalOutput(0); //Vision LEDS
		scanning = false; //If the turret should scan in initialize()
		timer = new Timer();
	}
	
	/**
	 * Starts the scan if the command was started via driver controls
	 */
	@Override
	protected void initialize() {
		if(scanning) {
			if(scanDir.equals(ScanDirection.LEFT)) { //If we want to scan left...
				turret.moveRot(-Constants.TURRET_LEFT_ROT_LIMIT * Constants.TURRET_GEAR_CONVERSION); //1/4 of a rotation to the left (middle gear) * conversion factor from middle to outer gears
			} else { //Otherwise we scan right...
				turret.moveRot(-Constants.TURRET_RIGHT_ROT_LIMIT * Constants.TURRET_GEAR_CONVERSION); //1/4 of a rotation to the right (middle gear) * conversion factor from middle to outer gears
			}
		}
		
		timer.reset();
		timer.start();
	}

	/**
	 * If target is visible it will track it, if scanning it will continue scanning, otherwise reset to middle
	 */
	@Override
	protected void execute() {
		
		if(yawList.size() < 8) { //Fills up initial array
			yawList.add(vision.getYaw());
		} else {
			yawList.remove(0); //Removes oldest data from list
			yawList.add(vision.getYaw()); //Adds newest data to the top of the list
		}
		
		//Finds the average of yawList and puts it in averagedYaw
		accumulator = 0;
		for(int i = 0; i < yawList.size(); i++) {
			accumulator += yawList.get(i);
		}
		averagedYaw = accumulator / yawList.size();
		
		//Puts the target yaw in rotations and then multiplies the gear conversion to it
		visionAdjustment = (averagedYaw/360) * Constants.TURRET_GEAR_CONVERSION; 
		SmartDashboard.putNumber("vision adjustment",visionAdjustment);
		SmartDashboard.putNumber("new position",visionAdjustment + turret.getPosition());
		SmartDashboard.putNumber("Turning",0);
		SmartDashboard.putNumber("seeTarget",1);
		//If the desired rotation is ever above or below 90 degrees it won't track
		
		if(visionAdjustment + turret.getPosition() < Constants.TURRET_RIGHT_ROT_LIMIT && visionAdjustment + turret.getPosition() > Constants.TURRET_LEFT_ROT_LIMIT) {
//			if(timer.hasPeriodPassed(Constants.TURRET_RECOVERY_TIME)) {
//				timer.reset();
//				timer.start();
//				turret.moveRot(visionAdjustment + turret.getPosition());
//			}
			SmartDashboard.putNumber("Turning",1);
			if(Math.abs(vision.getYaw()) < 4) { //TODO use deadband
				
			} else {
				turret.moveRot(visionAdjustment + turret.getPosition());
			}
			
		}
		
		
		
//		if(vision.seeTarget()) { //If target visible...
//			
//			if(yawList.size() < 8) { //Fills up initial array
//				yawList.add(vision.getYaw());
//			} else {
//				yawList.remove(0); //Removes oldest data from list
//				yawList.add(vision.getYaw()); //Adds newest data to the top of the list
//			}
//			
//			//Finds the average of yawList and puts it in averagedYaw
//			accumulator = 0;
//			for(int i = 0; i < yawList.size(); i++) {
//				accumulator += yawList.get(i);
//			}
//			averagedYaw = accumulator / yawList.size();
//			
//			//Puts the target yaw in rotations and then multiplies the gear conversion to it
//			visionAdjustment = (averagedYaw/360) * Constants.TURRET_GEAR_CONVERSION; 
//			SmartDashboard.putNumber("vision adjustment",visionAdjustment);
//			SmartDashboard.putNumber("new position",visionAdjustment + turret.getPosition());
//			SmartDashboard.putNumber("Turning",0);
//			SmartDashboard.putNumber("seeTarget",1);
//			//If the desired rotation is ever above or below 90 degrees it won't track
//			if(visionAdjustment + turret.getPosition() < Constants.TURRET_RIGHT_ROT_LIMIT && visionAdjustment + turret.getPosition() > Constants.TURRET_LEFT_ROT_LIMIT) {
////				if(timer.hasPeriodPassed(Constants.TURRET_RECOVERY_TIME)) {
////					timer.reset();
////					timer.start();
////					turret.moveRot(visionAdjustment + turret.getPosition());
////				}
//				SmartDashboard.putNumber("Turning",1);
//				if(Math.abs(vision.getYaw()) < 4) {
//					
//				} else {
//					turret.moveRot(visionAdjustment + turret.getPosition());
//				}
//				
//			}
//			
//		} else {
//			SmartDashboard.putNumber("seeTarget",0);
//		}	
			/* else if(scanning) { //If scanning, do nothing...
		}
			Sm
		} else { //Snapping back to the center.
			//turret.moveRot(0); TODO: Decide if useful or not
			
		}*/
		
		SmartDashboard.putNumber("Averaged Yaw",averagedYaw);
		SmartDashboard.putNumber("Vision Adjustment",visionAdjustment);
		SmartDashboard.putNumber("Turret Position",turret.getPosition());
		
//		leds.set(true); //Light up Vision LEDS
	}

	/**
	 * Always want to be tracking, so doesn't end without interruption
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		turret.stop();
//		leds.set(false); 
	}

	@Override
	protected void interrupted() {
		end();
	}
}
