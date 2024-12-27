package com.example.pro1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView eq;
    private TextView res;
    private boolean isOperatorPressed = false;
    private boolean isEqualPressed = false;
    double firstNumber;
    double secondNumber;
    char ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        res = findViewById(R.id.textViewResult);
        eq = findViewById(R.id.textVieweq);
        eq.setText(" ");
        res.setText(" ");
    }

    public void numfunc(View view) {
        Button button = (Button) view;
        eq.append(button.getText().toString());
        res.setText(" ");
        isEqualPressed = false;

    }

    public void pointfunc(View view) {
        Button button = (Button) view;
        String currentText = eq.getText().toString();
        if (!currentText.isEmpty()) {
            String[] parts = currentText.split("[+\\-*/]");
            String currentNumber = parts[parts.length - 1];

            if (!currentNumber.contains(".")) {
                eq.append(button.getText().toString());
            }
        } else {
            eq.append("0.");
        }
    }



    public void acfunc(View view) {

        if (isOperatorPressed) {
            Toast.makeText(view.getContext(), "כבר בחרת פעולה", Toast.LENGTH_SHORT).show();
            return;
        }


        String eqText = eq.getText().toString();
        if (eqText.isEmpty()) {
            Toast.makeText(view.getContext(), "אנא הזן מספר לפני ביצוע הפעולה", Toast.LENGTH_SHORT).show();
            return;
        }

        firstNumber = Double.parseDouble(eqText);
        ch = ((Button) view).getText().toString().charAt(0);
        eq.setText("");
        isOperatorPressed = true;
    }





    public void sumfunc(View view) {
        if (isEqualPressed) {
            Toast.makeText(view.getContext(), "כבר ביצעת חישוב, מתבצע אתחול (AC)", Toast.LENGTH_SHORT).show();
            res.setText("");
            eq.setText("");
            isOperatorPressed = false;
            isEqualPressed = false;
            firstNumber = 0;
            secondNumber = 0;
            ch = '\0';
            return;
        }
        String eqText = eq.getText().toString();
        if (eqText.isEmpty()) {
            Toast.makeText(view.getContext(), "תרשום מספר", Toast.LENGTH_SHORT).show();
            return;
        }

        secondNumber = Double.parseDouble(eqText);
        double reso = 0;


        if (ch == '/' && secondNumber == 0) {
            Toast.makeText(view.getContext(), "אי אפשר לחלק ב 0", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (ch) {
            case '+':
                reso = firstNumber + secondNumber;
                break;
            case '-':
                reso = firstNumber - secondNumber;
                break;
            case 'x':
                reso = firstNumber * secondNumber;
                break;
            case '/':
                reso = firstNumber / secondNumber;
                break;
            default:
                firstNumber = Double.parseDouble(eqText);
                reso = firstNumber;
                break;
        }

        res.setText(reso+" ");
         eq.setText("");
        isEqualPressed = true;
        isOperatorPressed = false;
        firstNumber = 0;
        secondNumber = 0;
        ch = '\0';
        }

    public void acacfunc(View view) {
        res.setText("");
        eq.setText("");
        isOperatorPressed = false;
        isEqualPressed = false;
        firstNumber = 0;
        secondNumber = 0;
        ch = '\0';
    }

    public void cfunc(View view) {
        String currentText = eq.getText().toString();

        if (!currentText.isEmpty()) {
            eq.setText(currentText.substring(0, currentText.length() - 1));

        }
    }
}

