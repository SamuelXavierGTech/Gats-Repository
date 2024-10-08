package com.mobile.educaeco.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mobile.educaeco.Database;
import com.mobile.educaeco.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ActivityVideo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        YouTubePlayerView video = findViewById(R.id.video);
        ImageView btnExpand = findViewById(R.id.btnExpand);

        Bundle bundle = getIntent().getExtras();
        String video_url = bundle.getString("video_url");
        video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(video_url, 0);
            }
        });

        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Database db = new Database();
                SharedPreferences sharedPreferences = getSharedPreferences("aluno", MODE_PRIVATE);
                String id_aluno = sharedPreferences.getString("id_aluno", "");
                db.updateStatus(id_aluno, "2");
                bundle.putString("tela", "jogos");
                Intent intent = new Intent(ActivityVideo.this, Main.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
