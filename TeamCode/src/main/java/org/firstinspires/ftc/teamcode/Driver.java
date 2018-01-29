package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Driver {
    public DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Telemetry telemetry;
    private double scaleFactor= 6;
    public Driver(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftDrive = hardwareMap.get(DcMotor.class, "motor_1");
        rightDrive = hardwareMap.get(DcMotor.class, "motor_2");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }
    public void driveStraight(double power) {
        leftDrive.setPower(scaleFactor*power);
        rightDrive.setPower(scaleFactor*power);
        updateTelementry();
    }
    public void turnRight(double power) {
        rightDrive.setPower(scaleFactor*power);
        leftDrive.setPower(0.0);
    }
    public void turnLeft(double power) {
        rightDrive.setPower(0.0);
        leftDrive.setPower(scaleFactor*power);
    }
    public void stop() {
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
    private void updateTelementry() {
        telemetry.addData("Position", "left (%d), right (%d)",
                leftDrive.getCurrentPosition(),
                rightDrive.getCurrentPosition());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)",
                leftDrive.getPower(),
                rightDrive.getPower());
    }
}
