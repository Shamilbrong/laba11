package com.example.laba10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText arrayInput;
    TextView resultTextView;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayInput = findViewById(R.id.arrayInput);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        String input = arrayInput.getText().toString().trim();
        String[] elements = input.split(",");

        double[] array = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            try {
                array[i] = Double.parseDouble(elements[i]);
            } catch (NumberFormatException e) {
                resultTextView.setText("Ошибка: введите числа, разделенные запятой");
                return;
            }
        }

        // Вычисляем номер максимального по модулю элемента массива
        int maxIndex = findMaxMagnitudeIndex(array);

        // Вычисляем сумму элементов после первого положительного элемента
        double sumAfterPositive = sumAfterFirstPositive(array);

        // Отображаем результаты на экране
        resultTextView.setText("Номер максимального по модулю элемента: " + (maxIndex+1) +
                "\nСумма элементов после первого положительного элемента: " + sumAfterPositive);
    }

    // Метод для вычисления номера максимального по модулю элемента массива
    private int findMaxMagnitudeIndex(double[] array) {
        double maxMagnitude = Math.abs(array[0]);
        int maxIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (Math.abs(array[i]) > maxMagnitude) {
                maxMagnitude = Math.abs(array[i]);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    // Метод для вычисления суммы элементов массива после первого положительного элемента
    private double sumAfterFirstPositive(double[] array) {
        boolean foundPositive = false;
        double sum = 0;

        for (double num : array) {
            if (foundPositive) {
                sum += num;
            } else if (num > 0) {
                foundPositive = true;
            }
        }

        return sum;
    }
}