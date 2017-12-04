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

        Driver driver = new Driver(hardwareMap, telemetry);
        double s = runtime.seconds();
        int state = 0;

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            s = runtime.seconds();

            if (s < 2.25) {
                state = 1;
                driver.driveStraight(1.0);
            } else if (s < 4.25) {
                state = 2;
                driver.turnRight(1.0);
            } else if (s < 5.50) {
                state = 3;
                driver.driveStraight(1.0);
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("state", state);
            telemetry.update();
        }
    }
}
