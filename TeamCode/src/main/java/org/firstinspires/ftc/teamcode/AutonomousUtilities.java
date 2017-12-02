package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.qualcomm.robotcore.hardware.ColorSensor;

public class AutonomousUtilities {
    // returns the number of inches to move.
    // positive number means move forward, negative backwards.
    public static double eliminateAllyBall(
            ColorSensor colorSensor
    ) {

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        String message = "";
        if (colorSensor.blue() >= 1) {
            message = "back 3";
            return -3;
        } else if (colorSensor.red() >= 1) {
            message = "forward 2";
            return 2;
        } else {
            message = "foward half 1";
            return .5;
        }
    }
}
// for future ref:
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(hardwareMap.appContext.getApplicationContext(), notification);
//            r.play();
