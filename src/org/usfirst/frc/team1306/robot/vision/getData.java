package org.usfirst.frc.team1306.robot.vision;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetData extends CommandBase {

	Timer timer;
	NetworkTable table;
	
	public GetData() {
		timer = new Timer();
		NetworkTable.setClientMode();
		NetworkTable.initialize();
		//NetworkTable.setIPAddress(Constants.JETSON_IP);
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		table.putNumber("roboValue",5);
		SmartDashboard.getNumber("roboValue",0);
		SmartDashboard.putBoolean("Connected?: ",table.isConnected());
		if(timer.hasPeriodPassed(Constants.DATA_REFRESH_RATE)) {
			int defaultValue = 0;
			double newValue = table.getNumber("newValue",defaultValue);
			SmartDashboard.putNumber("NetworkTables Output: ",newValue);
			SmartDashboard.putString("Getting Data: ","True");
			timer.reset();
			timer.start();
		} else {
			SmartDashboard.putString("Getting Data: ","False");
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
