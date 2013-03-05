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
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc78.FRC2013.commands.*;
import org.usfirst.frc78.FRC2013.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//    Preferences pref;
    Command autonomousCommand;
    SendableChooser autoChooser;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Shooter shooter;
    public static Stacker stacker;
    public static Target target;
    public static Tilt tilt;;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
//    public double stackUpperGateOpen;
//    public double stackUpperGateClose;
//    public double stackLowerGateOpen;
//    public double stackLowerGateClose;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
//        stackUpperGateOpen = pref.getDouble("StackUpperGateOpen", 0.3);
//        stackUpperGateClose = pref.getDouble("StackUpperGateClose", 0.0);
//        stackLowerGateOpen = pref.getDouble("StackLowerGateOpen", 0.6);
//        stackLowerGateClose = pref.getDouble("StackLowerGateClose", 1.0);
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        shooter = new Shooter();
        stacker = new Stacker();
        target = new Target();
        tilt = new Tilt();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Do Nothing", new AutoDoNothing(0.0));
//        autoChooser.addObject("Front 3pt Center", new AutoFront3ptCenter());
        autoChooser.addObject("Front 3pt Corner", new AutoFront3ptCorner());
        autoChooser.addObject("Front 2pt Corner", new AutoFront2ptCorner());
       // autoChooser.addObject("Rear 3pt Center", new AutoRear3ptCenter());   //old non-fully working auto
       // autoChooser.addObject("Rear 2pt Corner", new AutoRear2ptCorner());   //old non-working auto
        autoChooser.addObject("Rear2pt Corner", new AutoNewRear2());
        autoChooser.addObject("Rear3pt Corner", new AutoNewRear3());
        autoChooser.addObject("Back Pyramid", new LoadAndShootFour());
        SmartDashboard.putData("Autonomous Chooser", autoChooser);
        // preset servos to prevent frisbee jams
        stacker.init();
    }
    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
        // preset servos to prevent frisbee jams
        stacker.init();
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        // preset servos to prevent frisbee jams
        stacker.init();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
