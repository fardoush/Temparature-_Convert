package sample.android.com.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Spinner inputUnit, outputUnit;
    private EditText inputValue;
    private TextView result;
    private Button convertButton;

    private String[] units = {"Kelvin", "Celsius", "Fahrenheit"};

    private double value;

    // User selected unit
    private String input, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        intitVariable();
        initFunctionality();

        String title = "<font color = '#000000'>Temperature</font>";

        setTitle(Html.fromHtml(title));
    }

    private void initView() {
        inputUnit = findViewById(R.id.inputUnitId);
        outputUnit = findViewById(R.id.outputUnitId);
        result = findViewById(R.id.resultId);
        inputValue = findViewById(R.id.inputValueId);
        convertButton = findViewById(R.id.convertButtonId);



    }

    private void intitVariable() {

    }

    private void initFunctionality() {

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, units);

        inputUnit.setAdapter(adapter);
        outputUnit.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculate();
            }
        });


        //  get input unit
        inputUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                input = units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // get output unit
        outputUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                output = units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void calculate() {

        value = Double.parseDouble(inputValue.getText().toString());


        double res = convert(input, output, value);

        result.setText(String.valueOf(res));

    }

    private double convert(String input, String output, double value) {


        double outputValue = 10;


        if (input.equalsIgnoreCase("Fahrenheit")) {

            /*

                From Fahrenheit to others
             */


            if (output.equalsIgnoreCase("Celsius")) {

                /*
                ((°F-32)x(5/9))=°C
                 */

                outputValue = (value - 32) * (5 / 9.0);

            } else if (output.equalsIgnoreCase("kelvin")) {
                /*
                (F - 32) * 5/9 + 273.15
                 */

                outputValue = (value - 32) * 5 / 9.0 + 273.15;

            } else {
                outputValue = value;
            }

        } else if (input.equalsIgnoreCase("kelvin")) {
            /*

            From Kelvin to others
             */
            if (output.equalsIgnoreCase("fahrenheit")) {

                /*
                (K - 273.15) * 9/5 + 32
                 */
                outputValue = (value - 273.15) * 9 / 5.0 + 32;
            } else if (output.equalsIgnoreCase("celsius")) {

                /*
                K - 273.15
                 */

                outputValue = value - 273.15;
            } else {
                outputValue = value;
            }

        } else if (input.equalsIgnoreCase("celsius")) {

            /*
               From Celsius to Others
             */

            if (output.equalsIgnoreCase("fahrenheit")) {

                /*
                  (C * 9/5) + 32
                 */

                outputValue = (value * 9 / 5.0) + 32;
            } else if (output.equalsIgnoreCase("kelvin")) {

                /*
                C + 273.15
                 */

                outputValue = value + 273.15;
            } else {
                outputValue = value;
            }

        }


        return outputValue;
    }


}
