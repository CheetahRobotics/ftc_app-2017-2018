package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Driver {
    public DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Telemetry telemetry;
    public Driver(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftDrive = hardwareMap.get(DcMotor.class, "motor_1");
        rightDrive = hardwareMap.get(DcMotor.class, "motor_2");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }
    public void driveStraight(double power) {
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        updateTelementry();
    }
    public void turnRight(double power) {
        leftDrive.setPower(power);
        rightDrive.setPower(0.0);
    }
    public void turnLeft(double power) {
        leftDrive.setPower(0.0);
        rightDrive.setPower(power);
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
