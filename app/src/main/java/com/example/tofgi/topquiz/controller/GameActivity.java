package com.example.tofgi.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tofgi.topquiz.R;
import com.example.tofgi.topquiz.model.Question;
import com.example.tofgi.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView gQuestionText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private int nbrQuestions;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int score;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private boolean mEnabledTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        nbrQuestions = 8;

        score = 0;

        mEnabledTouchEvents = true;

        gQuestionText = findViewById(R.id.activity_game_question_text);
        button1 = findViewById(R.id.activity_game_answer1_btn);
        button2 = findViewById(R.id.activity_game_answer2_btn);
        button3 = findViewById(R.id.activity_game_answer3_btn);
        button4 = findViewById(R.id.activity_game_answer4_btn);

        button1.setTag(0);
        button2.setTag(1);
        button3.setTag(2);
        button4.setTag(3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    public void onClick(View v) {
        int reponseIndex = (int) v.getTag();
        if (reponseIndex == mCurrentQuestion.getAnswerIndex()) {
            score++;
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Réponse incorrecte !", Toast.LENGTH_SHORT).show();
        }
        mEnabledTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnabledTouchEvents = true;
                if (--nbrQuestions == 0) {
                    endGame();
                }else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnabledTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder bteDialog = new AlertDialog.Builder(this);

        bteDialog.setTitle("Fin de la partie !")
                .setMessage("Votre score est " + score)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void displayQuestion(final Question question) {
        gQuestionText.setText(question.getQuestion());
        button1.setText(question.getChoiceList().get(0));
        button2.setText(question.getChoiceList().get(1));
        button3.setText(question.getChoiceList().get(2));
        button4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("Quelle grande invention marque le début de l'antiquité ?",
                Arrays.asList("L'élevage", "L'architecture", "L'écriture", "La roue"),
                2);

        Question question2 = new Question("Qui a été le premier président des Etats-Unis ?",
                Arrays.asList("Georges Washington", "Abraham Lincoln", "Benjamin Harrison", "Theodore Roosevelt"),
                0);

        Question question3 = new Question("Quelle ville est devenue la capitale de l'Allemagne de l'ouest en 1949 ?",
                Arrays.asList("Berlin", "Stuttgart", "Bonn", "Munich"),
                2);

        Question question4 = new Question("Quelle guerre prit fin à la signature de l'armistice du 27 juillet 1953 ?",
                Arrays.asList("La guerre du Vietnam", "La guerre de Corée", "La guerre d'Algérie", "La guerre d'Indochine"),
                1);

        Question question5 = new Question("Dans quelle ville fut contruit le Titanic ?",
                Arrays.asList("Belfast", "Anvers", "Stockholm", "Dublin"),
                0);

        Question question6 = new Question("Quel roi français était surnommé 'Le Roi Soleil' ?",
                Arrays.asList("Louis XVI", "Louis XIV", "Louis XIII", "Louis XVIII"),
                1);

        Question question7 = new Question("Quel était le nom initial de la ville de New-York ?",
                Arrays.asList("New-Yorkshire", "New-Yorkwire", "New-Yorked", "New-Amsterdam"),
                3);

        Question question8 = new Question("Quel était le nom latin de Lyon au temps des Romains ?",
                Arrays.asList("Lutetia", "Limonum", "Ligora", "Lugdunum"),
                3);

        Question question9 = new Question("Quel était le prénom de Robespierre ?",
                Arrays.asList("Jean", "Albert", "Auguste", "Maximilien"),
                3);

        Question question10 = new Question("Quand Louis XVI fût-il guillotiné ?",
                Arrays.asList("21 janvier 1790", "21 janvier 1792", "21 janvier 1793", "21 janvier 1795"),
                2);

        Question question11 = new Question("Combien de pays font partie de l'Union Européenne ?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question12 = new Question("En quelle année l'homme à-t-il fait son premier pas sur la lune ?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question13 = new Question("Quelle est la capitale de la Roumanie ?",
                Arrays.asList("Bucarest", "Varsovie", "Budapest", "Berlin"),
                0);

        Question question14 = new Question("Dans quelle ville le compositeur Frédéric Chopin est-t-il enterré ?",
                Arrays.asList("Strasbourg", "Varsovie", "Paris", "Moscou"),
                2);

        Question question15 = new Question("Quel est le numéro de la maison des Simpson ?",
                Arrays.asList("42", "101", "666", "742"),
                2);

        Question question16 = new Question("Où situez-vous les glandes sudoripares d'un chien ?",
                Arrays.asList("Sous ses pattes", "Sur sa truffe", "Sur sa langue", "A la base des poils"),
                0);

        Question question17 = new Question("Quel animal Jean de La Fontaine employa-t-il pour représenter la rapidité",
                Arrays.asList("Le lévrier", "Le Lièvre", "Le guépard", "La cigale"),
                1);

        Question question18 = new Question("Quel poisson d'eau douce de nos lacs et rivières, dont la bouche contient 700 dents, peut vivre plusieurs dizaines d'années ?",
                Arrays.asList("Le poisson-chat", "Le brochet", "La silure", "La truite"),
                1);

        Question question19 = new Question("Quel oiseau est considéré comme l'oiseau rapace le plus rapide du monde ?",
                Arrays.asList("Le faucon pèlerin", "L'aigle royal", "Le vautour", "La buse noire"),
                0);

        Question question20 = new Question("Lequel de ces acteurs n'a jamais incarné James Bond ?",
                Arrays.asList("Sean Connery", "Piers Brosnan", "Roger Moore", "Burt Reynolds" ),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11,
                question12,
                question13,
                question14,
                question15,
                question16,
                question17,
                question18,
                question19,
                question20));
    }
}
