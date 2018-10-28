package com.example.tofgi.topquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tofgi.topquiz.R;
import com.example.tofgi.topquiz.model.Question;
import com.example.tofgi.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {
    private TextView gQuestionText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        gQuestionText = findViewById(R.id.activity_game_question_text);
        button1 = findViewById(R.id.activity_game_answer1_btn);
        button2 = findViewById(R.id.activity_game_answer2_btn);
        button3 = findViewById(R.id.activity_game_answer3_btn);
        button4 = findViewById(R.id.activity_game_answer4_btn);

        button1.setTag(0);
        button2.setTag(1);
        button3.setTag(2);
        button4.setTag(3);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
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
                                            3);

        Question question2 = new Question("Qui a été le premier président des Etats-Unis ?",
                                            Arrays.asList("Georges Washington", "Abraham Lincoln", "Benjamin Harrison", "Theodore Roosevelt"),
                                            1);

        Question question3 =  new Question("Quelle ville est devenue la capitale de l'Allemagne de l'ouest en 1949 ?",
                                            Arrays.asList("Berlin", "Stuttgart", "Bonn", "Munich"),
                                            3);

        Question question4 = new Question("Quelle guerre prit fin à la signature de l'armistice du 27 juillet 1953 ?",
                                            Arrays.asList("La guerre du Vietnam", "La guerre de Corée", "La guerre d'Algérie", "La guerre d'Indochine"),
                                            2);

        Question question5 = new Question("Dans quelle ville fut contruit le Titanic ?",
                                            Arrays.asList("Belfast", "Anvers", "Stockholm", "Dublin"),
                                            1);

        Question question6 = new Question("Quel roi français était surnommé 'Le Roi Soleil' ?",
                                            Arrays.asList("Louis XVI", "Louis XIV", "Louis XIII", "Louis XVIII"),
                                            2);

        Question question7 = new Question("Quel était le nom initial de la ville de New-York ?",
                                            Arrays.asList("New-Yorkshire", "New-Yorkwire", "New-Yorked", "New-Amsterdam"),
                                            4);

        Question question8 = new Question("Quel était le nom latin de Lyon au temps des Romains ?",
                                            Arrays.asList("Lutetia", "Limonum", "Ligora", "Lugdunum"),
                                            4);

        Question question9 = new Question("Quel était le prénom de Robespierre ?",
                                            Arrays.asList("Jean", "Albert", "Auguste", "Maximilien"),
                                            4);

        Question question10 = new Question("Quand Louis XVI fût-il guillotiné ?",
                                            Arrays.asList("21 janvier 1790", "21 janvier 1792", "21 janvier 1793", "21 janvier 1795"),
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
                question10));
    }
}
