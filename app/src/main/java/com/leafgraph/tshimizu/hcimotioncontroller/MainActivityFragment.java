package com.leafgraph.tshimizu.hcimotioncontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnTouchListener {

    String TAG = "MainActivityFrag";
    View[] mButtons;
    boolean[] mHoverState;
    boolean[] mPrevHoverState;

    ImageView mAvaterImageView;
    ImageView mSendImageView;

    public static final char[] SEND_CHARACTER = {'s', 'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    final int[] AVATER_RES = {
            R.drawable.avatar0,
            R.drawable.avatara,
            R.drawable.avatarb,
            R.drawable.avatarc,
            R.drawable.avatard,
            R.drawable.avatare,
            R.drawable.avatarf,
            R.drawable.avatarg,
    };
    final int DEFAULT_AVATER_DRAWABLE = R.drawable.avatar0;
    public static final int BUTTON_CENTER = 0;
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_C = 3;
    public static final int BUTTON_D = 4;
    public static final int BUTTON_E = 5;
    public static final int BUTTON_F = 6;
    public static final int BUTTON_SWING = 7;

    public static final int BUTTONS_SIZE = 7;

    private DatagramSocket ds;

    private int touchX;
    private int touchY;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //CircleLayoutにリスナを追加
        v.setOnTouchListener(this);

        //hover状態を全解除
        mHoverState = new boolean[BUTTONS_SIZE];
        mPrevHoverState = new boolean[BUTTONS_SIZE];
        for (int i = 0; i < mHoverState.length; i++) {
            mHoverState[i] = false;
            mPrevHoverState[i] = false;
        }

        //findViewById
        mButtons = new View[BUTTONS_SIZE];
        mButtons[BUTTON_CENTER] = v.findViewById(R.id.centerView);
        mButtons[BUTTON_A] = v.findViewById(R.id.a_button);
        mButtons[BUTTON_B] = v.findViewById(R.id.b_button);
        mButtons[BUTTON_C] = v.findViewById(R.id.c_button);
        mButtons[BUTTON_D] = v.findViewById(R.id.d_button);
        mButtons[BUTTON_E] = v.findViewById(R.id.e_button);
        mButtons[BUTTON_F] = v.findViewById(R.id.f_button);

        mAvaterImageView = (ImageView) (v.findViewById(R.id.avaterimageview));
        mSendImageView = (ImageView) (v.findViewById(R.id.sendimageview));

        mButtons[BUTTON_CENTER].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    //このボタンを起点にスタートした場合
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_CENTER);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });
        mButtons[BUTTON_A].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_A);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });
        mButtons[BUTTON_B].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_B);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });

        mButtons[BUTTON_C].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_C);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });

        mButtons[BUTTON_D].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_D);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });

        mButtons[BUTTON_E].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_E);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });

        mButtons[BUTTON_F].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: lis:hoveraction");
                        hoverAction(BUTTON_F);
                        touchX = (int) (motionEvent.getRawX());
                        touchY = (int) (motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchMoveAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        touchUpAction((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        break;

                    default:
                }
                return true;
            }
        });

        //チュートリアル表示


        return v;
    }

    //ViewGroupに対するonTouchリスナ(重なってるかを調べて処理を行う）
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //ステータスバーを含まないRawスクリーン座標系で考える
        int x = (int) motionEvent.getRawX();
        int y = (int) motionEvent.getRawY();

        //ホバー状態のチェック
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //座標の更新
                touchX = x;
                touchY = y;
                touchMoveAction(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("hco", "onTouch: actionup");
                touchUpAction(touchX, touchY);
                break;
            default:
        }

        return true;
    }


    private boolean isHover(View v, int x, int y) {
        Rect outRect = new Rect();
        v.getDrawingRect(outRect);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

    private boolean isHover(View[] v, int x, int y) {
        for (int i = 0; i < v.length; i++) {
            Rect outRect = new Rect();
            v[i].getDrawingRect(outRect);
            int[] location = new int[2];
            v[i].getLocationOnScreen(location);
            outRect.offset(location[0], location[1]);
            if (outRect.contains(x, y)) return true;
        }
        return false;
    }

    private void hoverAction(int hoverViewId) {
        //ほかにhover状態だったものがある場合
        for (int i = 0; i < mHoverState.length; i++) {

            if (i != hoverViewId) {
                //元に戻すアクション
                mButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_button));
            }
        }

        //ホバーの当該Viewの色を塗り直す
        mButtons[hoverViewId].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_button_hover));
        //アバタを当該ボタンのアバタに切り替える
        mAvaterImageView.setImageDrawable(getResources().getDrawable(AVATER_RES[hoverViewId]));

    }

    private void unhoverAction() {
        //通常状態に塗り直す
        for (int i = 0; i < mButtons.length; i++)
            mButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_button));
        //アバタも初期状態に戻す
        mAvaterImageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_dialog_info));
    }

    private void touchMoveAction(int touchX, int touchY) {
        unhoverAction();
        for (int i = 0; i < mButtons.length; i++) {
            if (isHover(mButtons[i], touchX, touchY)) {
                Log.d(TAG, "onTouch: hoveraction");
                hoverAction(i);
            }
        }
    }

    private void touchUpAction(int touchX, int touchY) {
        for (int i = 0; i < mButtons.length; i++) {
            if (isHover(mButtons[i], touchX, touchY)) {
                Log.d(TAG, "onTouch: touchUpAction");
                sendCommand(loadServerAddress(getContext()), loadServerPort(getContext()), String.valueOf(SEND_CHARACTER[i]), i);
                Snackbar.make(getActivity().getWindow().getDecorView(), "Command sent. " + SEND_CHARACTER[i], Snackbar.LENGTH_SHORT)
                        .show();
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(40);
            }
        }
        unhoverAction();
    }

    public void sendCommand(String ip, String port, String mes, int drawableId) {

        /*
        * AsyncTask<型1, 型2,型3>
        *
        *   型1 … Activityからスレッド処理へ渡したい変数の型
        *          ※ Activityから呼び出すexecute()の引数の型
        *          ※ doInBackground()の引数の型
        *
        *   型2 … 進捗度合を表示する時に利用したい型
        *          ※ onProgressUpdate()の引数の型
        *
        *   型3 … バックグラウンド処理完了時に受け取る型
        *          ※ doInBackground()の戻り値の型
        *          ※ onPostExecute()の引数の型
        *
        *   ※ それぞれ不要な場合は、Voidを設定すれば良い
        */
        new AsyncTask<String, Void, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                try {
                    //ソケット
                    InetAddress host = InetAddress.getByName(params[0]);
                    if (ds == null) {
                        ds = new DatagramSocket();  //DatagramSocket 作成
                    }
                    byte[] data = params[2].getBytes();
                    DatagramPacket dp = new DatagramPacket(data, data.length, host, Integer.parseInt(params[1]));  //DatagramPacket 作成
                    ds.send(dp);
                    Log.d("Send", "send finished." + params[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(ip, port, mes);
        mSendImageView.setImageDrawable(getResources().getDrawable(AVATER_RES[drawableId]));
    }

    public String loadServerAddress(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString("server_address", "");
    }

    public String loadServerPort(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString("server_port", "");
    }


}
