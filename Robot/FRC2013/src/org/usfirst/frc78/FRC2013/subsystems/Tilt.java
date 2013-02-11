// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc78.FRC2013.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc78.FRC2013.RobotMap;
/**
 *
 */
public class Tilt extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalInput cwLimit = RobotMap.tiltcwLimit;
    DigitalInput ccwLimit = RobotMap.tiltccwLimit;
    SpeedController motor = RobotMap.tiltMotor;
    AnalogChannel anglePot = RobotMap.tiltAnglePot;
    PIDController movePID = RobotMap.tiltMovePID;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    boolean isCWLimit() {
	boolean bLim = cwLimit.get();
	return bLim;
    }
    boolean isCCWLimit() {
	boolean bLim = ccwLimit.get();
	return bLim;
    }
    void stop() {
        motor.set(0.0);
    }
    void moveUp() {
	if (!isCCWLimit()) {
            motor.set(-1.0);
	} else {
            stop();
	}
    }
}
