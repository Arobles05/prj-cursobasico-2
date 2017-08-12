package com.example.arobles.proyecto_final_modulo_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TazadorActivity extends AppCompatActivity  {
    private Spinner mSpinner1;
    private Spinner mSpinner2;
    private String[] itemsToShow;
    private EditText mEditText;
    private TextView displayTextFinal;

                                             //    PESOS[0]--EU[1]--US[2]

    private static final double UN_DOLLAR[]   =  {47.2431,0.84757,1.0};
    private static final double UN_EURO[]  =     {55.7075,1.0,1.17984};
    private static final double UN_PESO[]   =    {1.0,0.01794,0.02119};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tazador);
        displayTextFinal = (TextView) findViewById(R.id.displayResultId);
        mSpinner1 = (Spinner)findViewById(R.id.mSpinner1Tazador);
        mSpinner2 = (Spinner)findViewById(R.id.mSpinner2Tazador);
        itemsToShow = getResources().getStringArray(R.array.Listas_Tasas);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_item,itemsToShow);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         mSpinner1.setAdapter(adapter);
         mSpinner2.setAdapter(adapter);
        mEditText =  (EditText) findViewById(R.id.editTextCantidadId);
        Button btn =  (Button) findViewById(R.id.btnConversorId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSpinner1.getSelectedItem().toString().equals(mSpinner2.getSelectedItem().toString()) && mEditText.getText() == null){
                    Toast.makeText(getBaseContext(),"Asegurese de que sea distinto el tipo de moneda y que coloque la cantidad a cambiar!",Toast.LENGTH_SHORT).show();
                }else
                {
                    double cambioResult = 0;
                    double CantidadACambiar = Double.parseDouble(mEditText.getText().toString());
                    switch (mSpinner1.getSelectedItem().toString()){
                        case "USD Dolar":
                            if(mSpinner2.getSelectedItem().toString().equals("EUR Euro")){
                               cambioResult = (UN_DOLLAR[1] * CantidadACambiar);
                            }else{
                                //peso
                                cambioResult = (UN_DOLLAR[0] * CantidadACambiar);
                            }
                            break;
                        case "EUR Euro":
                            if(mSpinner2.getSelectedItem().toString().equals("USD Dolar")){
                                cambioResult = (UN_EURO[2] * CantidadACambiar);
                            }else{
                                //peso
                                cambioResult = (UN_EURO[0] * CantidadACambiar);
                            }
                            break;
                        default:
                            if(mSpinner2.getSelectedItem().toString().equals("EUR Euro")){
                                cambioResult = (UN_PESO[1] * CantidadACambiar);
                            }else{
                                //dollar
                                cambioResult = (UN_PESO[2] * CantidadACambiar);
                            }
                            break;
                    }
                    Toast.makeText(getBaseContext(),CantidadACambiar+ mSpinner1.getSelectedItem().toString()+" ="+String.format("%.2f",cambioResult) +" "+mSpinner2.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    displayTextFinal.setText(CantidadACambiar+" "+ mSpinner1.getSelectedItem().toString()+" ="+String.format("%.2f", cambioResult) +" "+mSpinner2.getSelectedItem().toString());
                }

            }
        });

    }

}
