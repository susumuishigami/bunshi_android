package info.susumuis.app.bunshi.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

import info.susumuis.app.bunshi.Atom;
import info.susumuis.app.bunshi.Bunshi;
import info.susumuis.app.bunshi.Molecule;
import info.susumuis.app.bunshi.PlacedAtom;

/**
 * Created by susumuis on 2015/06/07.
 */
public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startTimer();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }


    private Paint paint = new Paint();
    private SurfaceHolder holder;

    private Bunshi game = Global.startNewGame();
    private Timer dropTimer;	// 落下用タイマー
    private boolean isPausing   = false; // ポーズ中は立つ。
    private boolean isGameOver  = false; // ゲームオーバーの時を知らせる。
    private boolean checkingFlag = false;

    private String message;

    public MainView(Context context) {
        super(context);
        initializeView();

    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    private void initializeView() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setFixedSize(getWidth(), getHeight());
        repaint();
        startTimer();
    }

    public void startNewGame() {
        stopTimer();
        game = Global.startNewGame();
        isPausing   = false;
        isGameOver  = false;
        checkingFlag = false;
        message = null;
        startTimer();
    }

    public void startTimer() {
        if (dropTimer != null) {
            dropTimer.cancel();
        }
        dropTimer = new Timer();
        dropTimer.schedule(new MyTimerTask(), 0, game.fallwait);
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            fall();
        }
    };

    public void stopTimer() {
        if (dropTimer != null) {
            dropTimer.cancel();
        }
    }

    private void fall() {
        synchronized (game) {
            game.fall();
            endCheck();
            repaint();
        }
    }

    private void endCheck() {
        checkingFlag = true;
        if (game.isGenerating()) {
            Molecule m = null;

            while ((m = game.check()) != null) {
                if (m != Molecule.NUCLEAR)
                    message = m.getName() + "発生";
                else
                    message = m.getName();
                repaint();
                stopTimer();
                delay(1000);
            }
            startTimer();
        }

        if (game.isEnd()) {
            stopTimer();
            isGameOver = true;
        }
        checkingFlag = false;

//        dropTimer.cancel();
//        dropTimer = new Timer();
//        dropTimer.schedule(timerTask, 0, game.fallwait);
    }

    private void delay(long milliSeconds) {
        try {
            Thread.currentThread().sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void repaint() {
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            try {
                paint(canvas);
            } finally {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void paint(Canvas g) {
        int width = getWidth();
        int height = getHeight();
        paint.setTextSize(width / 18);

//            setSoftLabel(SOFT_KEY_1, "休憩");
        paint.setARGB(255, 160, 160, 160);
        g.drawRect(new Rect(0, 0, width, height), paint);

        // 枠の描画
        paint.setColor(Color.BLACK);
        int[] px = {width * 1/40,  width * 1/40,   width * 23/40, width * 23/40, width * 26/40};
        int[] py = {height * 5/40, height * 39/40, height * 39/40, height * 8/40, height * 5/40};

        //g.drawPolyline(px, py, 5);

        paint.setARGB(255, 128, 128, 128);
        g.drawLine(width * 1/40,  height * 5/40, width * 26/40, height * 5/40, paint);

        paintInformation(g);

//            g.setFont(Font.getFont(Font.SIZE_MEDIUM));

        // フィールド上の原子を表示
        if (!checkingFlag)
            paintAtom(g, game.movingAtom, game.movingAtom.getX(), game.movingAtom.getY());
        for (int x = 0; x < game.field.getWidth(); x++) {
            for (int y = 0; y < game.field.amount(x); y++) {
                paintAtom(g, game.field.getMatter(x,y), x, y);
            }
        }

        // 次の原子を表示
        paint.setColor(getColor(game.nextAtom));
        g.drawText(game.nextAtom.toString(), width * 33 / 40, height * 5 / 20, paint);


        // 発生表示
        if (game.isGenerating()) {
            paint.setColor(Color.BLACK);
            g.drawText(message, 0, height / 10, paint);
        }

        // ゲームオーバー表示
        if (isGameOver) {
            paintInformation(g);

//            setSoftLabel(SOFT_KEY_1, "次へ");
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            g.drawRect(new Rect(width * 3/40, height * 16/40, width * 21/40, height * 20/40), paint);
            g.drawRect(new Rect(width * 3/40, height * 21/40, width * 21/40, height * 22/40), paint);
            paint.setColor(Color.BLACK);
            g.drawText("Game Over", width * 3/40, height * 19/40, paint);
            g.drawText("Swipe left", width * 3/40, height * 21/40, paint);
        }

        // ポーズ画面
        if (isPausing) {
            paint.setARGB(255, 160, 160, 160);
            g.drawRect(new Rect(width * 1/40 + 1, height * 5/40 + 1, width * 23/40 - 1, height * 39/40 - 1), paint);

            paint.setColor(Color.BLACK);
            g.drawText("エネ  → 得点", width * 2 / 40, height * 19 / 40, paint);
            g.drawText("C/◆/◇→ N", width * 2 / 40, height * 22 / 40, paint);
            g.drawText("N      → O", width * 2 / 40, height * 25 / 40, paint);
            g.drawText("S      →Cl", width * 2 / 40, height * 28 / 40, paint);
            g.drawText("C/◆/◇→ O", width * 2 / 40, height * 31 / 40, paint);
            g.drawText("◆/◇  → C", width * 2 / 40, height * 34 / 40, paint);
            g.drawText("抑制度復活", width * 2 / 40, height * 37 / 40, paint);
        }
    }

    private void paintInformation(Canvas g) {

        String missionStr = game.showMission();
        int p =missionStr.indexOf("\n");

        int width = getWidth();
        int height = getHeight();

        paint.setARGB(255, 160, 160, 160);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        g.drawRect(new Rect(width * 24/40, height * 13/40, width * 40/40, height * 40/40), paint);
        g.drawRect(new Rect(width * 27/40, height * 0/40, width * 37/40, height * 4/40), paint);
        paint.setColor(Color.BLACK);
//        g.setFont(Font.getFont(Font.SIZE_SMALL));

        if (game.gameResult.getMode() == Bunshi.TIMEATTACK) {
            long rest = game.gameResult.getStartTime() - System.currentTimeMillis() + Bunshi.GAMETIME;
            rest = rest / 1000;
            g.drawText("あと" + Long.toString(rest) + "秒", width * 28 / 40, height * 2 / 20, paint);
        }
        g.drawText("指令:", width * 25/40, height * 15/40, paint);

        if (p != -1) {
            g.drawText(missionStr.substring(0, p), width * 24/40, height * 18/40, paint);
            g.drawText(missionStr.substring(p), width * 25/40, height * 22/40, paint);
        } else {
            g.drawText(missionStr, width * 25/40, height * 18/40, paint);
        }

        g.drawText(Integer.toString(game.seriesCount) + "連鎖", width * 31 / 40, height * 14 / 20, paint);
        g.drawText("エネ: " + Integer.toString(game.energy), width * 25 / 40, height * 16 / 20, paint);
        g.drawText("抑: " + Integer.toString(game.fallwait), width * 25 / 40, height * 35 / 40, paint);
        g.drawText("Sc: " + Integer.toString(game.gameResult.getScore()), width * 25 / 40, height * 19 / 20, paint);

        g.drawText("Next: ", width * 25 / 40, height * 5 / 20, paint);


    }

    private static int getColor(Atom atom) {
        switch (atom.getMatter()) {
            case Atom.HYDROGEN: return Color.WHITE;
            case Atom.CARBON:   return Color.BLACK;
            case Atom.NITROGEN: return Color.BLUE;
            case Atom.OXYGEN:   return Color.RED;
            case Atom.FLORIDE:  return Color.GREEN;
            case Atom.SULFUR:   return Color.CYAN;
            case Atom.CHLORINE: return Color.LTGRAY;
            case Atom.GRAPHITE: return Color.BLACK;
            case Atom.DIAMOND:  return Color.BLACK;
            default:            return Color.BLACK;
        }
    }

    private void paintAtom(Canvas g, PlacedAtom atom, int x, int y) {
        int width = getWidth();
        int height = getHeight();
        Rect rect = new Rect(0, 0, width / 10, height / 10);
        if (atom.isMarkedErace()) {
            paint.setColor(Color.YELLOW);
            rect.offset((2 * x + 1) *  width / 20, (2 * (10 - y) - 3) * height / 20 + height/80);
            g.drawRect(rect, paint);
        }
        paintAtom(g, (Atom)atom, x, y);
    }

    private void paintAtom(Canvas g, Atom atom, int x, int y) {
        int width = getWidth();
        int height = getHeight();
        if (atom != null) {
            // g.setColor(Graphics.getColorOfName(Graphics.BLACK));
            paint.setColor(getColor(atom));
            g.drawText(
                    atom.toString(),
                    (2 * x + 1) *  width / 20,
                    (2 * (10 - y) - 1) * height / 20,
                    paint
            );
        }
    }

    private void eraceAtom(Canvas g, Atom atom, int x, int y) {
        int width = getWidth();
        int height = getHeight();
        if (atom != null) {
            paint.setARGB(255, 160, 160, 160);
            g.drawText(
                    atom.toString(),
                    (2 * x + 1) *  width / 20,
                    (2 * (10 - y) - 1) * height / 20,
                    paint
            );
        }
    }

    public void moveLeft() {
        if (isGameOver) {
            startNewGame();
        } else {
            game.moveLeft();
            repaint();
        }
    }

    public void moveRight() {
        game.moveRight();
        repaint();
    }

    public void land() {
        game.gameResult.addScore(30 + (1000 - game.fallwait) / 500);
        game.fallwait++;
        game.land();
        endCheck();
        repaint();
    }

    public void togglePause() {
        if (isPausing) {
            pauseEnd();
        } else {
            pauseStart();
        }
    }

    public void pauseStart() {
        isPausing = true;
        if (dropTimer != null) {
            dropTimer.cancel();
        }
        repaint();
    }

    public void pauseEnd() {
        isPausing = false;
        startTimer();
        repaint();
    }
}
