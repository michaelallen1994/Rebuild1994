// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;

public class LauncherSubsystem extends SubsystemBase {
  private final SparkFlex m_launcherMotor;
  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {
    m_launcherMotor = new SparkFlex(LauncherConstants.kLauncherMotorCanID, MotorType.kBrushless);

    SparkFlexConfig globalConfig = new SparkFlexConfig();
    SparkFlexConfig launcherMotorConfig = new SparkFlexConfig();

    globalConfig
      .smartCurrentLimit(50).idleMode(IdleMode.kCoast);

    launcherMotorConfig
      .apply(globalConfig);

    m_launcherMotor
      .configure(globalConfig, com.revrobotics.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void Launcher() {
    m_launcherMotor.set(LauncherConstants.kLauncherMotor);
  }

  public void ClearLauncher() {
    m_launcherMotor.set(LauncherConstants.kLauncherMotorClear);
  }

  public void StopLauncher() {
    m_launcherMotor.set(LauncherConstants.kLauncherMotorStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Launcher Power", m_launcherMotor.getAppliedOutput());
  }
}
