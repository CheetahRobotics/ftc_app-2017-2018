package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "AutonomousBlue2", group = "Linear Opmode")
public class AutonomousBlue2 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive = hardwareMap.get(DcMotor.class, "motor_1");
        rightDrive = hardwareMap.get(DcMotor.class, "motor_2");
        double s = runtime.seconds();
        int state = 0;

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower = 0;
            double rightPower = 0;
            s = runtime.seconds();

            if (s < 2.25) {
                state = 1;
                leftPower = 1;
                rightPower = 1;
            } else if (s < 4.25) {
                state = 2;
                leftPower = 1;
                rightPower = 0;
            } else if (s < 5.50) {
                state = 3;
                leftPower = 1;
                rightPower = 1;
            }

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)");
            telemetry.addData("Name", "Hi my name is Wall-E");
            telemetry.addData("state", state);
            telemetry.update();

        }
    }
}
