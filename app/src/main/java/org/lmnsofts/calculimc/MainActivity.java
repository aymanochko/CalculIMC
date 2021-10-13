package org.lmnsofts.calculimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculerBtn = findViewById(R.id.btn_calculer);
        calculerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerIMC(v);
            }
        });
    }

    public void calculerIMC(View view) {
        EditText inputPoids = findViewById(R.id.input_poids);
        if(null == inputPoids.getText() || inputPoids.getText().length() == 0){
            return;
        }
        Double poidsDbl = Double.valueOf(inputPoids.getText().toString());
        EditText inputTaille = findViewById(R.id.input_taille);
        if(null == inputPoids.getText() || inputPoids.getText().length() == 0){
            return;
        }
        Integer tailleInt = Integer.parseInt(inputTaille.getText().toString());
        TextView lblResultat = findViewById(R.id.lbl_calculIMC_result);
        Double valeurImc = poidsDbl/(Math.pow((double)tailleInt/100,2));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);

        StringBuilder resultat = new StringBuilder(256);
        resultat.append(getString(R.string.accueil_calculer_result_line1, numberFormat.format(valeurImc)));
        String interpretationIMC = "Autre";
        if(valeurImc < 18.5) {
            interpretationIMC = getString(R.string.accueil_calculer_result_maigre);
        }
        else if(valeurImc < 25) {
            interpretationIMC = getString(R.string.accueil_calculer_result_normal);

        }
        else if(valeurImc < 30) {
            interpretationIMC = getString(R.string.accueil_calculer_result_surpoids);
        }
        else if(valeurImc < 35) {
            interpretationIMC = getString(R.string.accueil_calculer_result_obesite_1);
        }
        else if(valeurImc < 40) {
            interpretationIMC = getString(R.string.accueil_calculer_result_obesite_2);
        }
        else {
            interpretationIMC = getString(R.string.accueil_calculer_result_obesite_3);
        }
        resultat.append("\n");
        resultat.append(getString(R.string.accueil_calculer_result_line2,interpretationIMC));
        Double poidsIdealDebut = (Math.pow((double) tailleInt/100,2))*18.5;
        Double poidsIdealFin = (Math.pow((double) tailleInt/100,2))*25;
        resultat.append("\n");
        resultat.append(getString(R.string.accueil_calculer_result_line3,numberFormat.format(poidsIdealDebut), numberFormat.format(poidsIdealFin)));
        lblResultat.setText(resultat.toString());
    }
}