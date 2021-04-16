package com.example.jogocmlibras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.VideoView;

public class ComoJogar extends AppCompatActivity {
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_jogar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Como Jogar");

        scrollView = findViewById(R.id.ScrollView);
    }

    public void bntSinal (View view){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        intent.putExtra("videoNome", "sinal");
        startActivity(intent);
    }

    public void bntFimDeJogo (View view){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        intent.putExtra("videoNome", "fimdejogo");
        startActivity(intent);
    }

    public void bntNovaCM(View view){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        intent.putExtra("videoNome", "novacm");
        startActivity(intent);
    }

    public void bntPontos(View view){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        intent.putExtra("videoNome", "pontos");
        startActivity(intent);
    }

    public void bntTempo(View view){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        intent.putExtra("videoNome", "tempo");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
}