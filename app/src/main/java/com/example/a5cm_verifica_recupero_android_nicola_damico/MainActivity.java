package com.example.a5cm_verifica_recupero_android_nicola_damico;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText tObbKcal;
    private Button bObbKcal;
    private Button butCorsa;
    private Button butCiclismo;
    private Button butPalestra;
    private Button butRiposo;

    private TextView tView;
    private boolean b_corsa_impostato = false;
    private boolean b_ciclismo_impostato = false;
    private boolean b_palestra_impostato = false;
    private boolean b_riposo_impostato = false;

    private int perc_corsa = 0;
    private int perc_ciclismo = 0;
    private int perc_palestra = 0;

    private int durata_corsa = 0;
    private int kcal_corsa = 0;
    private int durata_ciclismo = 0;
    private int kcal_ciclismo = 0;
    private int durata_palestra = 0;
    private int kcal_palestra = 0;
    private int durata_riposo = 0;
    private int kcal_riposo = 0;

    private EditText tDurata;
    private EditText tKcalBruciate;
    private Button butInvia;

    private boolean calorieObiettivoImpostato = false;
    private int calorieObiettivo = 0;

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

        tView = findViewById(R.id.textView);
        tObbKcal = findViewById(R.id.t_obb_kcal);
        bObbKcal = findViewById(R.id.b_obb_kcal);
        butCorsa = findViewById(R.id.but_corsa);
        butCiclismo = findViewById(R.id.but_ciclismo);
        butPalestra = findViewById(R.id.but_palestra);
        butRiposo = findViewById(R.id.but_riposo);
        tDurata = findViewById(R.id.t_durata);
        tKcalBruciate = findViewById(R.id.t_kcal_bruciate);
        butInvia = findViewById(R.id.but_invia);

        // Inizialmente disabilito i pulsanti attività
        setAttivitaButtonsEnabled(false);
    }

    private void setAttivitaButtonsEnabled(boolean visivility) {
        butCorsa.setEnabled(visivility);
        butCiclismo.setEnabled(visivility);
        butPalestra.setEnabled(visivility);
        butRiposo.setEnabled(visivility);
    }

    private void mostraInputAttivita() {
        tDurata.setVisibility(View.VISIBLE);
        tKcalBruciate.setVisibility(View.VISIBLE);
        butInvia.setVisibility(View.VISIBLE);
    }


    public void b_obb_calorie(View v) {
        Button b = (Button) v;
        Integer calorie = null;
        try {
            calorie = Integer.parseInt(tObbKcal.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }
        if (calorie != null && calorie > 0) {
            calorieObiettivoImpostato = true;
            // Assegna il valore dell'EditText alla variabile calorieObiettivo
            calorieObiettivo = calorie;
            tObbKcal.setEnabled(false);
            bObbKcal.setEnabled(false);
            setAttivitaButtonsEnabled(true);
        } else {
            calorieObiettivoImpostato = false;
            tObbKcal.setEnabled(true);
            bObbKcal.setEnabled(true);
            setAttivitaButtonsEnabled(false);
            Toast.makeText(getApplicationContext(), "L'obiettivo calorie deve essere maggiore di 0.", Toast.LENGTH_SHORT).show();
        }
    }

    public void b_corsa(View v) {
        mostraInputAttivita();
        b_corsa_impostato = true;
        b_ciclismo_impostato = false;
        b_palestra_impostato = false;
        b_riposo_impostato = false;
    }

    public void b_ciclismo(View v) {
        mostraInputAttivita();
        b_corsa_impostato = false;
        b_ciclismo_impostato = true;
        b_palestra_impostato = false;
        b_riposo_impostato = false;
    }

    public void b_palestra(View v) {
        mostraInputAttivita();
        b_corsa_impostato = false;
        b_ciclismo_impostato = false;
        b_palestra_impostato = true;
        b_riposo_impostato = false;
    }

    public void b_riposo(View v) {
        mostraInputAttivita();
        b_corsa_impostato = false;
        b_ciclismo_impostato = false;
        b_palestra_impostato = false;
        b_riposo_impostato = true;
    }

    @SuppressLint("SetTextI18n")
    public void b_invia(View v) {
        Button b = (Button) v;

        if (calorieObiettivoImpostato) {
            if (b_corsa_impostato) {
                if (!tDurata.getText().toString().isEmpty() && !tKcalBruciate.getText().toString().isEmpty()) {
                    durata_corsa = Integer.parseInt(tDurata.getText().toString());
                    kcal_corsa = Integer.parseInt(tKcalBruciate.getText().toString());
                }
            } else if (b_ciclismo_impostato) {
                if (!tDurata.getText().toString().isEmpty() && !tKcalBruciate.getText().toString().isEmpty()) {
                    durata_ciclismo = Integer.parseInt(tDurata.getText().toString());
                    kcal_ciclismo = Integer.parseInt(tKcalBruciate.getText().toString());
                }
            } else if (b_palestra_impostato) {
                if (!tDurata.getText().toString().isEmpty() && !tKcalBruciate.getText().toString().isEmpty()) {
                    durata_palestra = Integer.parseInt(tDurata.getText().toString());
                    kcal_palestra = Integer.parseInt(tKcalBruciate.getText().toString());
                }
            } else if (b_riposo_impostato) {
                if (!tDurata.getText().toString().isEmpty() && !tKcalBruciate.getText().toString().isEmpty()) {
                    durata_riposo = Integer.parseInt(tDurata.getText().toString());
                    kcal_riposo = Integer.parseInt(tKcalBruciate.getText().toString());
                }
            }

            tView.setText("Obiettivo Calorico:\n " + calorieObiettivo +
                    "Totale Attività: \n" +
                    "\nCorsa: " + kcal_corsa + "kcal - :" + durata_corsa + " min" +
                    "\nCiclismo: " + kcal_ciclismo + "kcal - :" + durata_ciclismo + " min" +
                    "\nPalestra: " + kcal_palestra + "kcal - :" + durata_palestra + " min" +
                    "\nRiposo: " + kcal_riposo + "kcal - :" + durata_riposo + " min" +
                    "\nTotale Calorie Bruciate Nette: " + (kcal_corsa + kcal_ciclismo + kcal_palestra - kcal_riposo) + " kcal" +
                    "\nHai raggiunto il 70% dell'obiettivo");
        }
    }
}