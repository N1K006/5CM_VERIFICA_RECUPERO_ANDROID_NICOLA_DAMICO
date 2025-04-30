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
    float percentuale_corsa = 0;
    float percentuale_ciclismo = 0;
    float percentuale_palestra = 0;
    float percentuale_obiettivo = 0;

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

    private void evidenziaAttivita()
    {
        Button[] bottoni_attivita = { butCorsa, butCiclismo, butPalestra, butRiposo };
        for (Button b : bottoni_attivita)
        {
            b.setAlpha(0.5f);
        }
    }

    private void setAttivitaButtonsEnabled(boolean visivility)
    {
        butCorsa.setEnabled(visivility);
        butCiclismo.setEnabled(visivility);
        butPalestra.setEnabled(visivility);
        butRiposo.setEnabled(visivility);
    }

    private void mostraInputAttivita()
    {
        tDurata.setVisibility(View.VISIBLE);
        tKcalBruciate.setVisibility(View.VISIBLE);
        butInvia.setVisibility(View.VISIBLE);
    }

    public void b_obb_calorie(View v) {
        Button b = (Button) v;
        int calorie = 0;
        try
        {
            calorie = Integer.parseInt(tObbKcal.getText().toString());
        }
        catch (NumberFormatException e)
        {
            calorieObiettivoImpostato = false;
            tObbKcal.setEnabled(true);
            bObbKcal.setEnabled(true);
            setAttivitaButtonsEnabled(false);
            Toast.makeText(this, "L'obiettivo calorie deve essere maggiore di 0.", Toast.LENGTH_SHORT).show();
            return;
        }
        calorieObiettivoImpostato = true;
        // Assegna il valore dell'EditText alla variabile calorieObiettivo
        calorieObiettivo = calorie;
        tObbKcal.setEnabled(false);
        bObbKcal.setEnabled(false);
        setAttivitaButtonsEnabled(true);
    }

     private void ResetEditText_e_B()
     {
         tDurata.setText("");
         tKcalBruciate.setText("");

         b_corsa_impostato = false;
         b_riposo_impostato = false;
         b_palestra_impostato = false;
         b_ciclismo_impostato = false;
     }

    public void b_corsa(View v) {
        mostraInputAttivita();
        evidenziaAttivita();
        butCorsa.setAlpha(1f);
        ResetEditText_e_B();
        b_corsa_impostato = true;
    }

    public void b_ciclismo(View v) {
        mostraInputAttivita();
        evidenziaAttivita();
        butCiclismo.setAlpha(1f);
        ResetEditText_e_B();
        b_ciclismo_impostato = true;
    }

    public void b_palestra(View v) {
        mostraInputAttivita();
        evidenziaAttivita();
        butPalestra.setAlpha(1f);
        ResetEditText_e_B();
        b_palestra_impostato = true;
    }

    public void b_riposo(View v) {
        mostraInputAttivita();
        evidenziaAttivita();
        butRiposo.setAlpha(1f);
        ResetEditText_e_B();
        b_riposo_impostato = true;
    }

    @SuppressLint("SetTextI18n")
    public void b_invia(View v) {
        Button b = (Button) v;


        if (!tKcalBruciate.getText().toString().isEmpty() && !tDurata.getText().toString().isEmpty())
        {
            if (Integer.parseInt(tKcalBruciate.getText().toString()) <= 300 && Integer.parseInt(tKcalBruciate.getText().toString()) > 0 )
            {
                if (Integer.parseInt((tDurata.getText().toString())) < 120)
                {
                    if (calorieObiettivoImpostato)
                    {
                        if (b_corsa_impostato)
                        {
                            durata_corsa += Integer.parseInt(tDurata.getText().toString());
                            kcal_corsa += Integer.parseInt(tKcalBruciate.getText().toString());
                        }
                        else if (b_ciclismo_impostato)
                        {
                            durata_ciclismo += Integer.parseInt(tDurata.getText().toString());
                            kcal_ciclismo += Integer.parseInt(tKcalBruciate.getText().toString());
                        }
                        else if (b_palestra_impostato)
                        {
                            durata_palestra += Integer.parseInt(tDurata.getText().toString());
                            kcal_palestra += Integer.parseInt(tKcalBruciate.getText().toString());
                        }
                        else if (b_riposo_impostato)
                        {
                            durata_riposo += Integer.parseInt(tDurata.getText().toString());
                            kcal_riposo += Integer.parseInt(tKcalBruciate.getText().toString());
                        }
                        else
                        {
                            Toast.makeText(this, "Completa tutti i campi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        tDurata.setText("");
                        tKcalBruciate.setText("");

                        int tot_cal_bruciate = ((kcal_corsa + kcal_ciclismo + kcal_palestra) - kcal_riposo);

                        if (tot_cal_bruciate < 0)
                        {
                            Toast.makeText(this, "Errore: Calorie bruciate scese sotto lo zero", Toast.LENGTH_SHORT).show();
                            ResetEditText_e_B();
                            return;
                        }

                        percentuale_corsa = ((float) kcal_corsa / calorieObiettivo * 100);
                        percentuale_ciclismo = ((float) kcal_ciclismo / calorieObiettivo * 100);
                        percentuale_palestra = ((float) kcal_palestra / calorieObiettivo * 100);
                        percentuale_obiettivo = ((float) tot_cal_bruciate / calorieObiettivo * 100);


                        if (percentuale_corsa > 70 || percentuale_ciclismo > 70 || percentuale_palestra > 70)
                        {
                            Toast.makeText(this, "Percentuale di calorie bruciate troppo alte!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        tView.setText("Obiettivo Calorico: " + calorieObiettivo  + "\n\n"+
                                "Totale Attività:" +
                                "\nCorsa: " + kcal_corsa + "kcal (" + (int) percentuale_corsa + "%) - " + durata_corsa + " min" +
                                "\nCiclismo: " + kcal_ciclismo + "kcal  (" + (int) percentuale_ciclismo + "%)- " + durata_ciclismo + " min" +
                                "\nPalestra: " + kcal_palestra + "kcal  (" + (int) percentuale_palestra + "%)- " + durata_palestra + " min" +
                                "\nRiposo: " + kcal_riposo + "kcal - :" + durata_riposo + " min" +
                                "\nTotale Calorie Bruciate Nette: " + tot_cal_bruciate + " kcal" +
                                "\nHai raggiunto il (" + (int) percentuale_obiettivo + "%) dell'obiettivo");
                    }
                }
                else
                {
                    Toast.makeText(this, "Non puoi inserire più di 120 min per attività", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Non puoi inserire più di 300 kcal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}