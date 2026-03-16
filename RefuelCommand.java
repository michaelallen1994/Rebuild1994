// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DeploySubsystem;
import frc.robot.subsystems.LoaderSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RefuelCommand extends SequentialCommandGroup {
  /** Creates a new RefuelCommand1. */
  public RefuelCommand(DeploySubsystem deploy, LoaderSubsystem load) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DeployCommand(deploy),
      new WaitCommand(2),
      new LoadCommand(load)
    );
  }
}
