package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "AutonomousRed2", group = "Linear Opmode")
public class AutonomousRed2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double s = runtime.seconds();
        int state = 0;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        Driver driver = new Driver(hardwareMap, telemetry);

        while (opModeIsActive()) {

            s = runtime.seconds();

            if (s < 2.25) {
                state = 1;
                driver.driveStraight(1.0);
            } else if (s < 4) {
                state = 2;
                driver.turnRight(1.0);
            } else if (s < 5) {
                state = 3;
                driver.driveStraight(1.0);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("state", state);
            telemetry.update();

        }
    }
}