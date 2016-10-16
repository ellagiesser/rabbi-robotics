package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nathan on 10/16/2016.
 */
public class TeleOpButtonPusher extends LinearOpMode {

    DcMotor l, r, lb, rb;
    Servo buttonpusher;

    public TeleOpButtonPusher() {}

    public void runOpMode() {
        gamepad1.setJoystickDeadzone((float) 0.05);

        
    }

    float scale_motor_power_redundant(double p_power, double deadzone) { // DcMotor.setPower needs a float
        // Simpler method of controlling the motor range
        p_power = Range.clip(p_power, -1, 1);
        double negative_power = 0, positive_power = 0;
        // differentiate between negative and positive power - required to implement deadzones
        if (p_power > 0) {
            positive_power = Range.clip(p_power, deadzone, 1); // implement the deadzone
            positive_power = Range.scale(positive_power, deadzone, 1, 0, 1); // and bring it back to 0<n<1
        } else {
            negative_power = Range.clip(p_power, -deadzone, -1); // implement the deadzone
            negative_power = Range.scale(negative_power, -deadzone, -1, 0, -1); // and bring it back to 0>n>-1
        }
        // return the final power
        if (positive_power > 0) {
            return (float) positive_power;
        }
        else if (negative_power < 0) {
            return (float) negative_power;
        }
        else {
            return 0;
        }
    }

}
