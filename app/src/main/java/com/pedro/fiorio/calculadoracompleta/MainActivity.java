package com.pedro.fiorio.calculadoracompleta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    protected EditText display;
    protected String numberA = "";
    protected String numberB = "";
    protected String operator = "";

    protected boolean justUsedEqual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.display = (EditText) findViewById(R.id.display);
    }

    public void updateDisplay() {
        this.display.setText(this.numberA + this.operator + this.numberB);
    }

    public void handleDigitClick(View view) {
        String tag = view.getTag().toString();

        if (this.justUsedEqual) {
            this.numberA = "";
        }

        if (operator.isEmpty()) {
            this.numberA = this.numberA + tag;
            this.numberB = "";

        } else {
            this.numberB = this.numberB + tag;
        }
        this.justUsedEqual = false;
        this.updateDisplay();
    }

    public void handleOperatorClick(View view) {
        if (!this.numberA.isEmpty()) {
            this.operator = view.getTag().toString();
        }
        this.justUsedEqual = false;
        this.updateDisplay();
    }

    public void handleEraseClick(View view) {
        if (!this.numberB.isEmpty()) {
            this.numberB = this.numberB.substring(0, this.numberB.length()-1);
        } else if (!this.operator.isEmpty()) {
            this.operator = "";
        } else if (!this.numberA.isEmpty()) {
            this.numberA = this.numberA.substring(0, this.numberA.length()-1);
        }
        this.justUsedEqual = false;
        this.updateDisplay();
    }

    public void handleClearClick(View view) {
        this.justUsedEqual = false;
        this.clear();
        this.updateDisplay();
    }

    public void clear() {
        this.numberA = this.operator = this.numberB = "";
    }

    public void handleDotClick(View view) {
        if (!this.numberB.isEmpty() && !this.numberB.contains(".")) {
            this.numberB = this.numberB + ".";
        } else if (!this.numberA.isEmpty() && !this.numberA.contains(".") && this.operator.isEmpty()) {
            this.numberA = this.numberA + ".";
        }
        this.justUsedEqual = false;
        this.updateDisplay();
    }

    public void handleEqualsClick(View view) {
        if (this.numberB.isEmpty()) return;

        double vA = Double.parseDouble(this.numberA);
        double vB = Double.parseDouble(this.numberB);
        double result = 0.0;

        if (this.operator.equals("/") && vB != 0.0) {
            result = vA / vB;
        } else if (this.operator.equals("*")) {
            result = vA * vB;
        } else if (this.operator.equals("-")) {
            result = vA - vB;
        } else if (this.operator.equals("+")) {
            result = vA + vB;
        } else {
            this.clear();
            this.display.setText("ERROR");
            return;
        }

        this.clear();
        this.numberA = Double.toString(result);
        this.justUsedEqual = true;
        this.updateDisplay();
    }
}