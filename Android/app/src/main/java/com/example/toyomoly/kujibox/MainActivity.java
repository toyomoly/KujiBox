package com.example.toyomoly.kujibox;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
// import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Boolean playable = true;
    private Integer RestartCnt = 0;
    private Integer OptionCnt = 0;

    // MediaPlayer se1;
    // MediaPlayer se2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // se1 = MediaPlayer.create(getApplicationContext(), R.raw.se1);
        // se2 = MediaPlayer.create(getApplicationContext(), R.raw.se2);

        // emb
        ImageView emb = (ImageView) findViewById(R.id.emb);
        emb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OptionCnt++;
                if (OptionCnt > 9) {
                    // ConfigDialog Open
                    init();
                    showDialog();
                }
            }
        });

        // logo
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RestartCnt++;
                if (RestartCnt > 4) {
                    // Restart
                    init();
                }
            }
        });

        // box
        ImageView box = (ImageView) findViewById(R.id.imageView2);
        box.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play();
            }
        });

        init();

    }

    private void init() {
        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setVisibility(View.INVISIBLE);

        ImageView ticket = (ImageView) findViewById(R.id.imageView1);
        ticket.setVisibility(View.INVISIBLE);

        ImageView box = (ImageView) findViewById(R.id.imageView2);
        box.setImageResource(R.drawable.box1);

        playable = true;
        RestartCnt = 0;
        OptionCnt = 0;
    }

    private void play() {
        if (playable) {


            ImageView box = (ImageView) findViewById(R.id.imageView2);
            box.setImageResource(R.drawable.box2);

            AnimationSet set = new AnimationSet(true);

            ScaleAnimation scale = new ScaleAnimation(1, 0.9f, 1, 0.9f);
            set.addAnimation(scale);

            TranslateAnimation translate = new TranslateAnimation(0, 40, 0, 50);
            set.addAnimation(translate);

            set.setDuration(2400);

            set.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    SharedPreferences pref;
                    pref = getSharedPreferences("config", MODE_PRIVATE);
                    Editor ed = pref.edit();

                    // 景品決定
                    String name = "";
                    Integer n0 = pref.getInt("num0", 0);
                    Integer n1 = pref.getInt("num1", 0);
                    Integer n2 = pref.getInt("num2", 0);
                    Integer n3 = pref.getInt("num3", 0);
                    Integer n4 = pref.getInt("num4", 0);
                    Integer n5 = pref.getInt("num5", 0);
                    Integer n6 = pref.getInt("num6", 0);
                    Integer n7 = pref.getInt("num7", 0);
                    Integer n8 = pref.getInt("num8", 0);
                    Integer n9 = pref.getInt("num9", 0);
                    Integer sum = n0 + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9;
                    if (sum < 1) {
                        name = "景品準備中";
                    } else {
                        //Randomクラスのインスタンス化
                        Random rnd = new Random();
                        int ran = rnd.nextInt(sum);

                        if (ran < n0) {
                            name = pref.getString("name0", "");
                            ed.putInt("num0", n0 - 1);
                        } else if (ran < n0 + n1) {
                            name = pref.getString("name1", "");
                            ed.putInt("num1", n1 - 1);
                        } else if (ran < n0 + n1 + n2) {
                            name = pref.getString("name2", "");
                            ed.putInt("num2", n2 - 1);
                        } else if (ran < n0 + n1 + n2 + n3) {
                            name = pref.getString("name3", "");
                            ed.putInt("num3", n3 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4) {
                            name = pref.getString("name4", "");
                            ed.putInt("num4", n4 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4 + n5) {
                            name = pref.getString("name5", "");
                            ed.putInt("num5", n5 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4 + n5 + n6) {
                            name = pref.getString("name6", "");
                            ed.putInt("num6", n6 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4 + n5 + n6 + n7) {
                            name = pref.getString("name7", "");
                            ed.putInt("num7", n7 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8) {
                            name = pref.getString("name8", "");
                            ed.putInt("num8", n8 - 1);
                        } else if (ran < n0 + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9) {
                            name = pref.getString("name9", "");
                            ed.putInt("num9", n9 - 1);
                        } else {
                            name = "ハズレ";
                        }
                        ed.commit();
                    }

                    TextView text1 = (TextView) findViewById(R.id.textView1);
                    text1.setText(name);
                    text1.setVisibility(View.VISIBLE);

                    ImageView ticket = (ImageView) findViewById(R.id.imageView1);
                    ticket.setVisibility(View.VISIBLE);

                    ImageView box = (ImageView) findViewById(R.id.imageView2);
                    box.setImageResource(R.drawable.box3);

                    // se2.start();
                }
            });

            box.startAnimation(set);

            // se1.start();

            playable = false;
            RestartCnt = 0;
            OptionCnt = 0;
        }
    }

    private void showDialog() {
        ConfigFragment d = new ConfigFragment();
        d.show(getFragmentManager(), "dialog");
    }

    public static class ConfigFragment extends DialogFragment {

        private EditText name0;
        private EditText name1;
        private EditText name2;
        private EditText name3;
        private EditText name4;
        private EditText name5;
        private EditText name6;
        private EditText name7;
        private EditText name8;
        private EditText name9;

        private EditText num0;
        private EditText num1;
        private EditText num2;
        private EditText num3;
        private EditText num4;
        private EditText num5;
        private EditText num6;
        private EditText num7;
        private EditText num8;
        private EditText num9;

        private SharedPreferences pref;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);

            Dialog dialog = new Dialog(getActivity());

            dialog.getWindow().setTitle("景品設定 ver2");

            dialog.setContentView(R.layout.dialog_config);

            name0 = (EditText) dialog.findViewById(R.id.dialog_name0);
            name1 = (EditText) dialog.findViewById(R.id.dialog_name1);
            name2 = (EditText) dialog.findViewById(R.id.dialog_name2);
            name3 = (EditText) dialog.findViewById(R.id.dialog_name3);
            name4 = (EditText) dialog.findViewById(R.id.dialog_name4);
            name5 = (EditText) dialog.findViewById(R.id.dialog_name5);
            name6 = (EditText) dialog.findViewById(R.id.dialog_name6);
            name7 = (EditText) dialog.findViewById(R.id.dialog_name7);
            name8 = (EditText) dialog.findViewById(R.id.dialog_name8);
            name9 = (EditText) dialog.findViewById(R.id.dialog_name9);

            num0 = (EditText) dialog.findViewById(R.id.dialog_num0);
            num1 = (EditText) dialog.findViewById(R.id.dialog_num1);
            num2 = (EditText) dialog.findViewById(R.id.dialog_num2);
            num3 = (EditText) dialog.findViewById(R.id.dialog_num3);
            num4 = (EditText) dialog.findViewById(R.id.dialog_num4);
            num5 = (EditText) dialog.findViewById(R.id.dialog_num5);
            num6 = (EditText) dialog.findViewById(R.id.dialog_num6);
            num7 = (EditText) dialog.findViewById(R.id.dialog_num7);
            num8 = (EditText) dialog.findViewById(R.id.dialog_num8);
            num9 = (EditText) dialog.findViewById(R.id.dialog_num9);

            pref = this.getActivity().getSharedPreferences("config", MODE_PRIVATE);

            name0.setText(pref.getString("name0", "景品Ａ"));
            name1.setText(pref.getString("name1", "景品Ｂ"));
            name2.setText(pref.getString("name2", "景品Ｃ"));
            name3.setText(pref.getString("name3", "景品Ｄ"));
            name4.setText(pref.getString("name4", "景品Ｅ"));
            name5.setText(pref.getString("name5", "景品Ｆ"));
            name6.setText(pref.getString("name6", "景品Ｇ"));
            name7.setText(pref.getString("name7", "景品Ｈ"));
            name8.setText(pref.getString("name8", "景品Ｉ"));
            name9.setText(pref.getString("name9", "景品Ｊ"));

            num0.setText("" + pref.getInt("num0", 0));
            num1.setText("" + pref.getInt("num1", 0));
            num2.setText("" + pref.getInt("num2", 0));
            num3.setText("" + pref.getInt("num3", 0));
            num4.setText("" + pref.getInt("num4", 0));
            num5.setText("" + pref.getInt("num5", 0));
            num6.setText("" + pref.getInt("num6", 0));
            num7.setText("" + pref.getInt("num7", 0));
            num8.setText("" + pref.getInt("num8", 0));
            num9.setText("" + pref.getInt("num9", 0));

            dialog.findViewById(R.id.dialog_ok).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editor ed = pref.edit();

                    ed.putString("name0", name0.getText().toString());
                    ed.putString("name1", name1.getText().toString());
                    ed.putString("name2", name2.getText().toString());
                    ed.putString("name3", name3.getText().toString());
                    ed.putString("name4", name4.getText().toString());
                    ed.putString("name5", name5.getText().toString());
                    ed.putString("name6", name6.getText().toString());
                    ed.putString("name7", name7.getText().toString());
                    ed.putString("name8", name8.getText().toString());
                    ed.putString("name9", name9.getText().toString());

                    ed.putInt("num0", getNum(num0));
                    ed.putInt("num1", getNum(num1));
                    ed.putInt("num2", getNum(num2));
                    ed.putInt("num3", getNum(num3));
                    ed.putInt("num4", getNum(num4));
                    ed.putInt("num5", getNum(num5));
                    ed.putInt("num6", getNum(num6));
                    ed.putInt("num7", getNum(num7));
                    ed.putInt("num8", getNum(num8));
                    ed.putInt("num9", getNum(num9));

                    ed.commit();

                    dismiss();
                }
            });

            dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            return dialog;
        }

        private Integer getNum(EditText e) {
            try {
                System.out.println(Integer.parseInt(e.getText().toString()));
                return Integer.parseInt(e.getText().toString());
            } catch (Exception ex) {
                System.out.println(ex);
                return 0;
            }
        }

    }
}