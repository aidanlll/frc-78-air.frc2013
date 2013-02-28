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
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc78.FRC2013.Robot;
/**
 *
 */
public class Tilt extends PIDSubsystem {
    private final static boolean TILT_USING_PID = true;
    private final static double TILT_MAX_POS = 45.0;
    private final static double TILT_MIN_POS = -5.0;
    private final static double TILT_MOVE_TO_LOAD = 0.0;
    private final static double TILT_MOVE_TO_START = TILT_MOVE_TO_LOAD;
    private final static double TILT_MOVE_TO_BALANCE = 46.388;
    private final static double TILT_2PT_WALL = 42.8;
    private final static double TILT_3PT_WALL = 53.4;
    private final static double TILT_5PT_MOVE = 30.051;
    private final static double TILT_FRONT_2PT_CORNER = 10.65;
    private final static double TILT_FRONT_3PT_CORNER = 12.78;
    private final static double TILT_REAR_2PT_CORNER = 4.29;
    private final static double TILT_REAR_3PT_CORNER = 6.45;
   
    // private
    private boolean m_bFastSpeed = false;
    private double m_drGoal = 0.0;
    private double m_drCmd = 0.0;
    private double m_drPos = 0.0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController motor = RobotMap.tiltMotor;
    Encoder encoder = RobotMap.tiltEncoder;
    AnalogChannel pot = RobotMap.tiltPot;
    DigitalInput upperLimit = RobotMap.tiltUpperLimit;
    DigitalInput lowerLimit = RobotMap.tiltLowerLimit;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Initialize your subsystem here
    public Tilt() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Tilt", 0.045, 0.0, 0.0);
        setAbsoluteTolerance(2.0);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Tilt", "PIDSubsystem Controller", getPIDController());
        getPIDController().setOutputRange(-0.25, 0.25);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        getPIDController().setInputRange(TILT_MIN_POS, TILT_MAX_POS);
//        disable();
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
        m_drPos = encoder.pidGet();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return encoder.pidGet();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    }
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        output = CheckAndLimitMotorSpeed(output);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        motor.pidWrite(output);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    }
    public boolean onTarget() {
        if (true == TILT_USING_PID) {
            return onTarget();
        }
        return true;
    }
    public void enablePID() {
        if (true == TILT_USING_PID) {
            enable();
        }
    }
    public void disablePID() {
        if (true == TILT_USING_PID) {
            disable();
        }
    }
    public void resetEncoder() {
        encoder.reset();
    }
    public void setFastSpeed() {
        m_bFastSpeed = true;
    }
    public void setSlowSpeed() {
        m_bFastSpeed = false;
    }
    public void setTiltMoveToLoad() {
        m_drGoal = TILT_MOVE_TO_LOAD;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltMoveToStart() {
        m_drGoal = TILT_MOVE_TO_START;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltMoveToBalance() {
        m_drGoal = TILT_MOVE_TO_BALANCE;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTilt2ptWall() {
        m_drGoal = TILT_2PT_WALL;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTilt3ptWall() {
        m_drGoal = TILT_3PT_WALL;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltFront2ptCorner() {
        m_drGoal = TILT_FRONT_2PT_CORNER;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltFront3ptCorner() {
        m_drGoal = TILT_FRONT_3PT_CORNER;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltRear2ptCorner() {
        m_drGoal = TILT_REAR_2PT_CORNER;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTiltRear3ptCorner() {
        m_drGoal = TILT_REAR_3PT_CORNER;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    public void setTilt5ptMove() {
        m_drGoal = TILT_5PT_MOVE;
        if (true == TILT_USING_PID) {
            this.setSetpoint(m_drGoal);
        }
    }
    /**
     * Each time called, will read the joystick and move the tilt arm 
     * motor directly.  It first disables any PID pending.
     * It uses the shooter joystick and implements a pitch command where
     * forward motion of the stick is nose down and rearwards motion of 
     * the stick is nose up. Slow speed is supported if user selected.
     *
     * @param  n/a
     * @return none
     * @see    n/a
     */
    public void JoystickDrive() {
        disable();
        double speed = -Robot.oi.getShooterTiltSpeed();
        if (false == m_bFastSpeed) {
            if (0.0 > speed) {
                speed *= 0.2;
            } else if (0.0 < speed) {
                speed *= 0.4;
            }
        }
        if (0.05 > Math.abs(speed)) {
            speed = 0.0;
        }
        if (TILT_MIN_POS > encoder.pidGet() &&  0.0 > speed) {
            speed = 0.0;
        } else if (TILT_MAX_POS < encoder.pidGet() &&  0.0 < speed) {
            speed = 0.0;
        }
        motor.set(CheckAndLimitMotorSpeed(speed));
    }
    private double CheckAndLimitMotorSpeed(double speed) {
        if(0.0 < speed && true == upperLimit.get()) {
            speed = 0.0;
        } else if (0.0 > speed && true == lowerLimit.get()) {
            speed = 0.0;
        }
        SmartDashboard.putNumber("TiltMotorPosEnc", encoder.pidGet());
        SmartDashboard.putNumber("TiltMotorPosPot", pot.getAverageVoltage());
        SmartDashboard.putNumber("TiltMotorOut", speed);
        return speed;
    }
}
