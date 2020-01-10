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
import frc.robot.commands.FeederCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ReverseFeederCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.ClimbDownCommand;
import frc.robot.commands.ClimbUpCommand;
import frc.robot.subsystems.ClimbSubsystem;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
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
    private final FeederSubsystem m_feederSubsystem = new FeederSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();



    private final Joystick joystick = new Joystick(0);
    private final JoystickButton climbUpButton = new JoystickButton(joystick, 6);
    private final JoystickButton climbDownButton = new JoystickButton(joystick, 4);

    Joystick stick = new Joystick(0);
    JoystickButton shootButton = new JoystickButton(stick, 1);
    JoystickButton intakeButton = new JoystickButton(stick, 2);
    JoystickButton feedButton = new JoystickButton(stick, 5);
    JoystickButton reverseFeedButton = new JoystickButton(stick, 3);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    shootButton.whileHeld(new ShooterCommand(m_shooterSubsystem));
    intakeButton.whileActiveOnce(new IntakeCommand(m_intakeSubsystem));
    feedButton.whileHeld(new FeederCommand(m_feederSubsystem));
    reverseFeedButton.whileHeld(new ReverseFeederCommand(m_feederSubsystem));
    
      climbUpButton.whileHeld(new ClimbUpCommand(m_climbSubsystem));
      climbDownButton.whileHeld(new ClimbDownCommand(m_climbSubsystem));
      
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

