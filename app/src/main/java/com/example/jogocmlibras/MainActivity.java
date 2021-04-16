package com.example.jogocmlibras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mostrarResultado, exibirPontuacao, cronometro;
    private Button escolher, fimJogo;
    private int totalEscolhas = 62;
    private Random gerador = new Random();
    private int num;
    private int totalPontos = 0;
    private String imagem;
    private ImageView imagemDados, capaInicio;
    private CheckBox pessoa, lugar, animal, comida, objeto, outros;
    private CountDownTimer tempoContagemRegressiva;
    private Boolean ativarCronometro = true;
    private Switch timerLigaDesliga;
    private SharedPreferences salvarDados;
    ArrayList<Integer> mSelectedItems;

    List<Integer> naoRepetir = new ArrayList<>();
    private String[] imagemNome = {"zero" ,
            "um" , "dois" , "tres" , "quatro" , "cinco" , "seis" , "sete" , "oito" , "nove" , "dez" ,
            "onze" , "doze" , "treze" , "catorze" , "quinze" , "dezesseis" , "dezessete" , "dezoito" ,
            "dezenove" , "vinte" , "vinteum" , "vintedois" , "vintetres" , "vintequatro" , "vintecinco" ,
            "vinteseis" , "vintesete" , "vinteoito" , "vintenove" , "trinta" , "trintaum" , "trintadois" ,
            "trintatres" , "trintaquatro" , "trintacinco" , "trintaseis" , "trintasete" , "trintaoito" ,
            "trintanove" , "quarenta" , "quarentaum" , "quarentadois" , "quarentatres" , "quarentaquatro" ,
            "quarentacinco" , "quarentaseis" , "quarentasete" , "quarentaoito" , "quarentanove" , "cinquenta" ,
            "cinquentaum" , "cinquentadois" , "cinquentatres" , "cinquentaquatro" , "cinquentacinco" ,
            "cinquentaseis" , "cinquentasete" , "cinquentaoito" , "cinquentanove" , "sessenta" , "sessentaum" ,
            "sessentadois" , "sessentatres" , "sessentaquatro" , "sessentacinco" , "sessentaseis" ,
            "sessentasete" , "sessentaoito" , "sessentanove" , "setenta"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrarResultado = findViewById(R.id.mostrarNumero);
        escolher = findViewById(R.id.botaoEscolher);
        fimJogo = findViewById(R.id.botaoFim);
        imagemDados = findViewById(R.id.imageViewPrincipal);
        exibirPontuacao = findViewById(R.id.mostrarTotalPontos);
        cronometro = findViewById(R.id.textViewTempo);

        pessoa = findViewById(R.id.checkBoxPessoas);
        lugar = findViewById(R.id.checkBoxLugar);
        animal = findViewById(R.id.checkBoxAnimal);
        outros = findViewById(R.id.checkBoxOutro);
        objeto = findViewById(R.id.checkBoxObjeto);
        comida = findViewById(R.id.checkBoxComida);
        timerLigaDesliga = findViewById(R.id.switchTempo);
        salvarDados = getSharedPreferences("dados", Context.MODE_PRIVATE);

        desabilitarCheckBoxTodos(true);

        recuperarDadosActivity ();

        pessoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 20;
                }else{
                    totalPontos -= 20;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        lugar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 20;
                    exibirPontuacao.setText(Integer.toString(totalPontos));
                }else{
                    totalPontos -= 20;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        animal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 20;
                    exibirPontuacao.setText(Integer.toString(totalPontos));
                }else{
                    totalPontos -= 20;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        outros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 10;
                }else{
                    totalPontos -= 10;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        objeto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 10;
                }else{
                    totalPontos -= 10;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        comida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    totalPontos += 20;
                }else{
                    totalPontos -= 20;
                }
                exibirPontuacao.setText(Integer.toString(totalPontos));

                salvarDadosActivity (false);
            }
        });

        timerLigaDesliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerLigaDesliga.isChecked()){
                    ativarCronometro = true;
                    cronometro.setVisibility(View.VISIBLE);
                }else {
                    ativarCronometro = false;
                    cronometro.setVisibility(View.INVISIBLE);

                    if (tempoContagemRegressiva != null){
                        tempoContagemRegressiva.cancel();
                    }
                    escolher.setVisibility(View.VISIBLE);
                    cronometro.setText("01:00");

                    salvarDadosActivity (false);
                }
            }
        });

    }


    //menus superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuComoJogar:
                startActivity(new Intent(this, ComoJogar.class));
                break;
            case R.id.menuCM:
                startActivity(new Intent(this, ConfiguracaoMao.class));
                break;
            case R.id.menuPontuacao:
            startActivity(new Intent(this, Pontuacao.class));
            break;
            case R.id.menuSobre:
            startActivity(new Intent(this, SobreJogo.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botaoEscolher(View view){
        desabilitarCheckBoxTodos(false);
        sorteioImagem();
        desmarcarCheckBoxTodos();
        if(ativarCronometro){
            contagemRegressiva();
        }

        mostrarResultado.setVisibility(View.VISIBLE);

        salvarDadosActivity (false);

    }

    public void botaoFimJogo(View view){
        reiniciarJogo();
        num = 0;
        salvarDadosActivity(true);


    }

    public void sorteioImagem (){
        num = gerador.nextInt(totalEscolhas + 1);
        if ((num != 0) && (!naoRepetir.contains(num))){// no array temos o zero, mas a imagem zero não existe.
            naoRepetir.add(num);
            mostrarResultado.setText(Integer.toString(num));
            Log.i("teste1",Integer.toString(num) );
            mostrarImagemEscolhida();
        }else{
            if (naoRepetir.size() >= totalEscolhas){
                escolher.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),
                        "FIM DO JOGO \ntodas as Configurações de Mão(CM) foram escolhidas" ,
                        Toast.LENGTH_LONG).show();
                        desabilitarCheckBoxTodos(true);
            }else{
                sorteioImagem();
            }
        }
    }

    public void mostrarImagemEscolhida (){ //Mostrar a configuaçõa de mão sorteada.
        String imagemCaminho;
        if (num != 0){
            imagemCaminho = "@drawable/" + imagemNome[num];
        }else{
            imagemCaminho = "@android:drawable/ic_menu_gallery";
            Log.i("testeImagemCaminhoIgua0" , imagemCaminho);
        }

        Log.i("testeImagemCaminho" , imagemCaminho);
        int imageResource = getResources().getIdentifier(imagemCaminho, null, getPackageName());
        Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imagemDados.setImageDrawable(res);


    }

    public void reiniciarJogo (){
        naoRepetir.clear();
        escolher.setVisibility(View.VISIBLE);

        int imageResource = getResources().getIdentifier("@android:drawable/ic_menu_gallery", null, getPackageName());
        Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imagemDados.setImageDrawable(res);

        mostrarResultado.setVisibility(View.INVISIBLE);
        exibirPontuacao.setText("00");
        desmarcarCheckBoxTodos();
        totalPontos = 0;
        exibirPontuacao.setText(Integer.toString(totalPontos));

        Toast.makeText(getApplicationContext(),
                "JOGO REINICIADO" ,
                Toast.LENGTH_LONG).show();

        cronometro.setText("01:00");
        if(tempoContagemRegressiva != null){
            tempoContagemRegressiva.cancel();
        }
    }

    public void desmarcarCheckBoxTodos(){
        int valor = totalPontos;

        pessoa.setChecked(false);
        lugar.setChecked(false);
        animal.setChecked(false);
        outros.setChecked(false);
        objeto.setChecked(false);
        comida.setChecked(false);

        totalPontos = valor;
        exibirPontuacao.setText(Integer.toString(totalPontos));
    }

    public void contagemRegressiva (){
        escolher.setVisibility(View.INVISIBLE);
        tempoContagemRegressiva = new CountDownTimer(60000, 1000){

            public void onTick(long millisUntilFinished) {
                int time = (int) (millisUntilFinished / 1000);
                if (time == 10){
                    cronometro.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.vermelhoapp));
                }
                if(time > 9){
                    cronometro.setText("00:" + time);
                }else{
                    cronometro.setText("00:0" + time);
                    if(time % 2 == 0){
                        cronometro.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.vermelhoapp));
                    }else {
                        cronometro.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.preto));
                    }
                }
            }

            public void onFinish() {
                cronometro.setText("00:00");
                escolher.setVisibility(View.VISIBLE);
                salvarDadosActivity(false);
                desabilitarCheckBoxTodos(true);
            }
        }.start();



    }


    public void caixaMSG(){
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Are you sure you want to change the language?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog, int which){

                    }
                }).show();
    }

    public void salvarDadosActivity (Boolean apagar){
        SharedPreferences.Editor editor = salvarDados.edit();

        editor.putInt("pontos", totalPontos);
        editor.putBoolean("pessoa",pessoa.isChecked());
        editor.putBoolean("animal",animal.isChecked());
        editor.putBoolean("lugar",lugar.isChecked());
        editor.putBoolean("comida",comida.isChecked());
        editor.putBoolean("objeto",objeto.isChecked());
        editor.putBoolean("outros",outros.isChecked());

        editor.putInt("botaoEscolher" , escolher.getVisibility()); //  visible = 0 & invisible =  4

        editor.putInt("imagemNum" , num);

        editor.commit();

        if(apagar){
            editor.clear();
        }


    }

    public void recuperarDadosActivity (){
        int auxValor = salvarDados.getInt("pontos",totalPontos);

        pessoa.setChecked(salvarDados.getBoolean("pessoa",pessoa.isChecked()));
        animal.setChecked(salvarDados.getBoolean("animal",animal.isChecked()));
        lugar.setChecked(salvarDados.getBoolean("lugar",lugar.isChecked()));
        comida.setChecked(salvarDados.getBoolean("comida",comida.isChecked()));
        objeto.setChecked(salvarDados.getBoolean("objeto",objeto.isChecked()));
        outros.setChecked(salvarDados.getBoolean("outros",outros.isChecked()));
        num = salvarDados.getInt("imagemNum" , num);

        mostrarResultado.setText(Integer.toString(num));

        mostrarImagemEscolhida();

        //Configuração do botão e cronometro
        escolher.setVisibility(salvarDados.getInt("botaoEscolher" , escolher.getVisibility()));
        if (escolher.getVisibility() == View.INVISIBLE) {
            contagemRegressiva();
            desabilitarCheckBoxTodos(false);
        }

        totalPontos = auxValor;
        exibirPontuacao.setText(Integer.toString(totalPontos));

    }

    public void desabilitarCheckBoxTodos (Boolean desativar){
        if (desativar){
            pessoa.setEnabled(false);
            lugar.setEnabled(false);
            animal.setEnabled(false);
            outros.setEnabled(false);
            objeto.setEnabled(false);
            comida.setEnabled(false);
        }else {
            pessoa.setEnabled(true);
            lugar.setEnabled(true);
            animal.setEnabled(true);
            outros.setEnabled(true);
            objeto.setEnabled(true);
            comida.setEnabled(true);
        }


    }

}