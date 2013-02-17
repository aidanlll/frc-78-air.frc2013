// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc78.FRC2013;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc78.FRC2013.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shoot;
    public JoystickButton shooterOn;
    public JoystickButton shooterOff;
    public JoystickButton chamberFrisbee;
    public Joystick driverJoystick;
    public JoystickButton shootOne;
    public JoystickButton shootFour;
    public JoystickButton spotLightOn;
    public JoystickButton spotLightOff;
    public Joystick shooterJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shooterJoystick = new Joystick(2);
        
        spotLightOff = new JoystickButton(shooterJoystick, 5);
        spotLightOff.whenReleased(new TargetSpotOff());
        spotLightOn = new JoystickButton(shooterJoystick, 5);
        spotLightOn.whenPressed(new TargetSpotOn());
        shootFour = new JoystickButton(shooterJoystick, 10);
        shootFour.whenPressed(new LoadAndShootFour());
        shootOne = new JoystickButton(shooterJoystick, 9);
        shootOne.whenPressed(new LoadAndShootOne());
        driverJoystick = new Joystick(1);
        
        chamberFrisbee = new JoystickButton(driverJoystick, 3);
        chamberFrisbee.whenPressed(new StackChamberFrisbee());
        shooterOff = new JoystickButton(driverJoystick, 1);
        shooterOff.whenReleased(new ShooterStopWheels());
        shooterOn = new JoystickButton(driverJoystick, 1);
        shooterOn.whenPressed(new ShooterStartWheels());
        shoot = new JoystickButton(driverJoystick, 7);
        shoot.whenPressed(new ShooterPullTrigger());
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("DriveWithJoystick", new DriveWithJoystick());
        SmartDashboard.putData("TiltWithJoystick", new TiltWithJoystick());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ShooterStartWheels", new ShooterStartWheels());
        SmartDashboard.putData("ShooterStopWheels", new ShooterStopWheels());
        SmartDashboard.putData("LoadAndShootOne", new LoadAndShootOne());
        SmartDashboard.putData("LoadAndShootFour", new LoadAndShootFour());
        SmartDashboard.putData("StackUpperGateOpen", new StackUpperGateOpen());
        SmartDashboard.putData("StackUpperGateClose", new StackUpperGateClose());
        SmartDashboard.putData("StackLowerGateOpen", new StackLowerGateOpen());
        SmartDashboard.putData("StackLowerGateClose", new StackLowerGateClose());
        SmartDashboard.putData("StackChamberFrisbee", new StackChamberFrisbee());
        SmartDashboard.putData("ShooterPullTrigger", new ShooterPullTrigger());
        SmartDashboard.putData("TargetSpotOn", new TargetSpotOn());
        SmartDashboard.putData("TargetSpotOff", new TargetSpotOff());
        SmartDashboard.putData("TiltMoveToLoad", new TiltMoveToLoad());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        SmartDashboard.putData("ChassisMove", new AutoChassisMove(18.8));
        SmartDashboard.putData("ChassisTurn", new AutoChassisTurn(90.0));
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverJoystick() {
        return driverJoystick;
    }
    public Joystick getShooterJoystick() {
        return shooterJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public double getDriverLeftSpeed() {
        return -driverJoystick.getY();
    }
    public double getDriverRightSpeed() {
        return -driverJoystick.getThrottle();
    }
    public double getShooterTiltSpeed() {
        return -shooterJoystick.getY();
    }
    public boolean getShooterMoveSw() {
        return !shooterOn.get();
    }
}
