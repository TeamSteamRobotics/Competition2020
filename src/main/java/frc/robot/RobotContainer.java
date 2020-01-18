/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Intake;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.MoveToIntake;
import frc.robot.commands.MoveToShooter;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.Drive;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  
	//private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final HopperSubsystem m_feederSubsystem = new HopperSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();



  Joystick stick = new Joystick(0);
  JoystickButton shootButton = new JoystickButton(stick, 1);
  JoystickButton intakeButton = new JoystickButton(stick, 2);
  JoystickButton climbDownButton = new JoystickButton(stick, 3);
  JoystickButton climbUpButton = new JoystickButton(stick, 5);
  //manual overrides
  JoystickButton moveToIntakeButton = new JoystickButton(stick, 7);
  JoystickButton moveToShooterButton = new JoystickButton(stick, 8);
  JoystickButton spinIntakeButton = new JoystickButton(stick, 9);
  JoystickButton spinShooterButton = new JoystickButton(stick, 10);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, stick::getY, stick::getX));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    shootButton.whileHeld(new Shoot(m_shooterSubsystem, m_feederSubsystem));
    intakeButton.whileHeld(new ConditionalCommand(
      new WaitCommand(0), 
      new Intake(m_intakeSubsystem, m_feederSubsystem, m_ballTrackingSubsystem),
      m_ballTrackingSubsystem::isHopperFull
    ));
    climbDownButton.whileHeld(new ClimbDown(m_climbSubsystem));  
    climbUpButton.whileHeld(new ClimbUp(m_climbSubsystem));
    //manual overrides
    moveToIntakeButton.whileHeld(new MoveToIntake(m_feederSubsystem));
    moveToShooterButton.whileHeld(new MoveToShooter(m_feederSubsystem));
    spinIntakeButton.whileHeld(new SpinIntake(m_intakeSubsystem));
    spinShooterButton.whileHeld(new ManualShoot(m_shooterSubsystem));
    

   
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
    return null;
  }
}
