// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FunLauncherConstants;

public class FunLauncherSubsystem extends SubsystemBase {
  private final SparkFlex m_launcherMotor;
  /** Creates a new LauncherSubsystem. */
  public FunLauncherSubsystem() {
    m_launcherMotor = new SparkFlex(FunLauncherConstants.kLauncherMotorCanID, MotorType.kBrushless);

    SparkFlexConfig globalConfig = new SparkFlexConfig();
    SparkFlexConfig launcherMotorConfig = new SparkFlexConfig();

    globalConfig
      .smartCurrentLimit(50).idleMode(IdleMode.kCoast);

    launcherMotorConfig
      .apply(globalConfig);

    m_launcherMotor
      .configure(globalConfig, com.revrobotics.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void FunLauncher() {
    m_launcherMotor.set(FunLauncherConstants.kLauncherMotor);
  }

  public void FunClearLauncher() {
    m_launcherMotor.set(FunLauncherConstants.kLauncherMotorClear);
  }

  public void FunStopLauncher() {
    m_launcherMotor.set(FunLauncherConstants.kLauncherMotorStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Launcher Power", m_launcherMotor.getAppliedOutput());
  }
}
