// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LoaderConstants;

public class LoaderSubsystem extends SubsystemBase {
  private final SparkMax m_loaderMotor;
  /** Creates a new LoaderSubsystem. */
  public LoaderSubsystem() {
    m_loaderMotor = new SparkMax(LoaderConstants.kLoaderMotorCanID, MotorType.kBrushless);

    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig loaderMotorConfig = new SparkMaxConfig();

    globalConfig
      .smartCurrentLimit(50).idleMode(IdleMode.kBrake);

    loaderMotorConfig
      .apply(globalConfig);

    m_loaderMotor
      .configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void Load() {
    m_loaderMotor.set(LoaderConstants.kLoaderMotor);

  }

  public void ClearLoader() {
    m_loaderMotor.set(LoaderConstants.kLoaderMotorClear);

  }

  public void StopLoader() {
    m_loaderMotor.set(LoaderConstants.kLoaderMotorStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Loader Power", m_loaderMotor.getAppliedOutput());
  }
}
