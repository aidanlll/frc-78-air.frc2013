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
import org.usfirst.frc78.FRC2013.RobotMap;
import org.usfirst.frc78.FRC2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc78.FRC2013.Robot;
/**
 *
 */
public class Tilt extends Subsystem {
    // constants
    private final static double TILT_ON_TARGET = 2.0;
//    private final static double TILT_INC_MOVE  = 0.25;
    private final static double TILT_LO_POS   = 3.895;
    private final static double TILT_HI_POS   = 0.834;
    private final static double TILT_RESOLUTION = 1.0;
    private final static double TILT_KP = 1.0/(TILT_LO_POS-TILT_HI_POS);
    private final static double TILT_LOAD_POS = 3.895;
    
    // private
    private double m_drGoal = 0.0;
    private double m_drPos = 0.0;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController motor = RobotMap.tiltMotor;
    AnalogChannel anglePot = RobotMap.tiltAnglePot;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TiltWithJoystick());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getPos() {
        m_drPos = anglePot.getAverageValue() * TILT_RESOLUTION;
        return m_drPos;
    }
    public boolean isOnTarget() {
        double d_error = m_drGoal - m_drPos;
        if (TILT_ON_TARGET > Math.abs(d_error)) {
            return true;
        }
        return false;
    }
    public double getGoal() {
        return m_drGoal;
    }
    public void setGoal(double goal) {
        m_drGoal = goal;
    }
    public void move() {
        double pos = getPos();
        double goal = m_drGoal;
        double error = goal - pos;
        double cmd = error * TILT_KP;
        if (-1.0 > cmd) {
            cmd = -1.0;
        } else if (1.0 < cmd) {
            cmd = 1.0;
        }
//TODO        motor.set(cmd);
    }
    public void setGoalToLoad() {
        setGoal(TILT_LOAD_POS);
    }
    public void JoystickDrive() {
        double goal = 0.0;
        motor.set(-Robot.oi.getShooterTiltSpeed());
        getPos();
        setGoal(m_drPos);
    }
}