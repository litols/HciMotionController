package com.leafgraph.tshimizu.hcimotioncontroller;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.squareup.seismic.ShakeDetector;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {

    ShakeDetector mShakeDetector;

    MainActivityFragment mFragment;
    Context mContext;

    boolean mHandSwingFlag = true;

    /**
     * onCreate Activityの作成
     * シェイクジェスチャの認識リスナの作成
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragment = (MainActivityFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment));
        mContext = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTutorial();
            }
        });

        /**
         * シェイクジェスチャのリスナ設定
         * シェイクが実施されたらバイブレーションとコマンドの送信、アバタの反映を行う
         */
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mShakeDetector = new ShakeDetector(new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                //SnackBarの表示
                Snackbar.make(getWindow().getDecorView(), "Detected Gesture! : " + "Shake!Shake!", Snackbar.LENGTH_SHORT)
                        .show();
                //バイブレーション実施
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(80);
                //コマンド送信
                mFragment.sendCommand(mFragment.loadServerAddress(mContext), mFragment.loadServerPort(mContext),
                        String.valueOf(MainActivityFragment.SEND_CHARACTER[MainActivityFragment.BUTTON_SWING]), 0);

                //リソースの切り替え
                if (mHandSwingFlag) {
                    //送信したアバタの表示
                    ((ImageView) findViewById(R.id.sendimageview)).setImageDrawable(getResources().getDrawable(R.drawable.avatarg));
                    mHandSwingFlag = false;
                } else {
                    //送信したアバタの表示
                    ((ImageView) findViewById(R.id.sendimageview)).setImageDrawable(getResources().getDrawable(R.drawable.avatarh));
                    mHandSwingFlag = true;
                }

            }
        });

        //シェイクジェスチャ認識開始
        mShakeDetector.start(sensorManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * onResume()
     * アクティビティの復帰時
     */
    @Override
    protected void onResume() {
        super.onResume();
        //シェイクジェスチャ認識再スタート
        mShakeDetector.start((SensorManager) getSystemService(SENSOR_SERVICE));
    }

    /**
     * onPause()
     * ユーザがアプリから離れたとき
     */
    @Override
    protected void onPause() {
        super.onPause();
        //シェイクジェスチャの認識ストップ
        mShakeDetector.stop();
    }

    //チュートリアルの作成
    private void runTutorial() {
        //チュートリアルが再実行できるようにする
        MaterialShowcaseView.resetSingleUse(this, "Tutorial SEQ");
        //チュートリアル
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500);
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "Tutorial SEQ");
        //sequence.setConfig(config);
        sequence.addSequenceItem(findViewById(R.id.centerView),
                "(1/7)円形に配置されたアイコンに指をスライドさせ、アイコンの上で指を離すと確定します。", "次へ");
        sequence.addSequenceItem(findViewById(R.id.a_button),
                "(2/7)たとえば、ここに指をスライドさせて離せば、「楽しい」ことを示すアバターを送信します。\n" +
                        "もちろん、アイコンはタッチしても動作します。", "次へ");
        sequence.addSequenceItem(findViewById(R.id.centerView),
                "(3/7)一度タップしてから、アバターの送信をキャンセルする場合は、アイコンのないところまで指をスライドさせ、指を離してください。", "次へ");
        sequence.addSequenceItem(findViewById(R.id.avaterimageview),
                "(4/7)選択中のアバターの画像は、ここに表示されるので、指を離す前に確認してください。", "次へ");
        sequence.addSequenceItem(findViewById(R.id.sendimageview),
                "(5/7)相手側に見えているアバターの画像はここに表示されます。", "次へ");
        sequence.addSequenceItem(findViewById(R.id.centerView),
                "(6/7)このほかにも、端末をシェイクすると、「手を振るアバター」を送信します。", "次へ");

        sequence.addSequenceItem(findViewById(R.id.centerView),
                "(7/7)チュートリアルは以上です。それでは、実際に触ってみてください。", "スタート");
        sequence.start();
    }
}
