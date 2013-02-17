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
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc78.FRC2013.Robot;
import org.usfirst.frc78.FRC2013.RobotMap;
import org.usfirst.frc78.FRC2013.commands.TiltWithJoystick;
/**
 *
 */
public class Tilt extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogChannel tiltPot = RobotMap.tiltTiltPot;
    SpeedController tiltMotor = RobotMap.tiltTiltMotor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Initialize your subsystem here
    public Tilt() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Tilt", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Tilt", "PIDSubsystem Controller", getPIDController());
        getPIDController().setInputRange(-1.0, 1.0);
        getPIDController().setOutputRange(-1.0, 1.0);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TiltWithJoystick());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return tiltPot.getAverageVoltage();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        tiltMotor.pidWrite(output);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    }
    public void move(double goal) {
        setSetpoint(goal);
    }
    public void JoystickDrive() {
        double goal = getSetpoint();
        if (0.5 < Robot.oi.getShooterTiltSpeed()) {
            goal += 0.25;
        } else if (-0.5 > Robot.oi.getShooterTiltSpeed()) {
            goal -= 0.25;
        }
//        setSetpoint(goal);
        disable();
        tiltMotor.set(-Robot.oi.getShooterTiltSpeed());
    }
}
