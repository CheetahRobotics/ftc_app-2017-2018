package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "AutonomousBlue", group = "Linear Opmode")
public class AutonomousBlue extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Servo sensor_arm;
    double sensor_armPower;

    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Driver driver = new Driver(hardwareMap, telemetry);
        AllyBallEliminator allyBallEliminator = new AllyBallEliminator(hardwareMap, telemetry);
        double s = runtime.seconds();
        int state = 0;

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            // double leftPower = 0;
            //double rightPower = 0;
            sensor_armPower = 0;
            s = runtime.seconds();

            if (s > 0) {
                state = 0;
                sensor_armPower = .65;
            }
            if (s > 2) {
                state = 1;
                sensor_armPower = 0;
                double power = allyBallEliminator.checkSensor();
                driver.driveStraight(power);
            }
            if (s > 3) {
                state = 2;
                sensor_armPower = -1;
            }
            if (s < 3.05) {
                state = 3;
                driver.driveStraight(1.0);
            } else if (s < 4.80) {
                state = 4;
                driver.turnLeft(1.0);
            } else if (s < 5.75) {
                state = 5;
                driver.driveStraight(1.0);
            }

            sensor_arm.setPosition(sensor_armPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("state", state);
            telemetry.update();
        }
    }
}
