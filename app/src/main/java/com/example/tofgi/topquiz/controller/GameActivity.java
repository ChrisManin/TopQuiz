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

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int nbrQuestions;
    private int score;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean mEnabledTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null){
            score = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            nbrQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else{
            score = 0;
            nbrQuestions = 10;
        }

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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, score);
        outState.putInt(BUNDLE_STATE_QUESTION, nbrQuestions);
        super.onSaveInstanceState(outState);
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
                .setCancelable(false)
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

        Question question21 = new Question("Suite à une décision de ses éditeurs, quel héros, dont les aventures débutèrent en 1938, est mort en 1992 dans les bras de sa bien-aimée ?",
                Arrays.asList("Spiderman", "Flash", "Superman", "Captain America"),
                2);

        Question question22 = new Question("Dans quelle aventure Tintin voit-il un Yeti ?",
                Arrays.asList("Tintin au pays des Soviets", "Tintin au Tibet", "Le Lotus Bleu", "Le crabe aux pince d'or"),
                1);

        Question question23 = new Question("William et Averell sont deux des quatre frères Dalton. Le prénom de chacun des deux autres commence par la lettre « J ». Quels sont leurs prénoms ?",
                Arrays.asList("Joe et Jack", "Joe et John",  "John et Jack", "Joe et Jim"),
                0);

        Question question24 = new Question("Sur la page couverture de quel album Tintin est-il sur une île devant un immense champignon ?",
                Arrays.asList("L'étoile mystérieuse", "Le crabe aux pinces d'or", "Le trésor de Rackham le Rouge", "L'île mystérieuse"),
                3);

        Question question25 = new Question("Dans une aventure de Gaston Lagaffe, Fantasio commet une bourde en acceptant le nouvel animal gagné par Gaston. De quel animal s'agit-il ?",
                Arrays.asList("Un chien", "Un vautour", "Une vache", "Un écureuil"),
                2);

        Question question26 = new Question("En quelle année Lucky Luke a-t-il été publié la première fois ?",
                Arrays.asList("1947", "1957", "1967", "1977"),
                0);

        Question question27 = new Question("A quelle race appartient le chien Milou ?",
                Arrays.asList("Caniche", "Coker", "Fox-terrier", "Yorkshare"),
                2);

        Question question28 = new Question("Odie et Jon sont des personnages de quelle bande dessinée?",
                Arrays.asList("Les aristochats", "Garfield", "Mafalda", "Titeuf"),
                1);

        Question question29 = new Question("Quel compositeur allemand, devenu sourd continua d'écrire des œuvres célèbres ?",
                Arrays.asList("Chopin", "Mozart", "Sully", "Beethoven"),
                3);

        Question question30 = new Question("De quelle nationalité était Frédéric Chopin ?",
                Arrays.asList("Français", "Allemand", "Polonais", "Autrichien"),
                2);

        Question question31 = new Question("Qui a composé le Lac des Cygnes ?",
                Arrays.asList("Beethoven", "Bach", "Berlioz", "Tchaïkovski"),
                3);

        Question question32 = new Question("De quel pays sont natifs les compositeurs suivants : Glück, Haydn, Strauss et Mozart ?",
                Arrays.asList("Autriche", "Allemagne", "Roumanie", "Pologne"),
                0);

        Question question33 = new Question("Dans quel domaine Auguste Renoir s'est-il illustré?",
                Arrays.asList("Sculpture", "Musique", "Peinture", "Littérature"),
                2);

        Question question34 = new Question("Dans quelle ville est situé le célèbre Carnegie Hall ?",
                Arrays.asList("Washington", "New-York", "Londres", "Dublin"),
                1);

        Question question35 = new Question("Qui a peint La Dernière Cène ?",
                Arrays.asList("Léonard de Vinci", "Michel-Ange", "Rembrandt", "Vincent Van Gogh"),
                0);

        Question question36 = new Question("En superficie, quel est le plus grand État des États-Unis d'Amérique ?",
                Arrays.asList("Texas", "Alaska", "Nevada", "Californie"),
                1);

        Question question37 = new Question("Quel État africain a la plus petite superficie ?",
                Arrays.asList("Congo", "Guinée", "Gambie", "Namibie"),
                2);

        Question question38 = new Question("La Perse a changé de nom. Quel est-il actuellement ?",
                Arrays.asList("Iran", "Irak", "Pakistan", "Turquie"),
                0);

        Question question39 = new Question("A quel pays appartient l'archipel des Galápagos ?",
                Arrays.asList("Le Guatemala", "La Colombie", "L'Uruguay", "L'Equateur"),
                3);

        Question question40 = new Question("Quel est l'océan le plus vaste du monde ?",
                Arrays.asList("Le Pacifique", "L'Atlantique", "l'Arctique", "L'océan Indien"),
                0);

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
                question20,
                question21,
                question22,
                question23,
                question24,
                question25,
                question26,
                question27,
                question28,
                question29,
                question30,
                question31,
                question32,
                question33,
                question34,
                question35,
                question36,
                question37,
                question38,
                question39,
                question40));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
