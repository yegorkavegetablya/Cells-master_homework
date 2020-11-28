package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HeaderViewListAdapter;


import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 8;
    private int HEIGHT = 10;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {

        //Эту строку нужно удалить
        //Task.showMessage(this, "Добавьте код в функцию активности generate() для генерации клеточного поля");


        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setBackgroundColor(Color.WHITE);
            }
    }

    @Override
    public boolean onLongClick(View v) {
        //Эту строку нужно удалить
        //Stub.show(this, "Добавьте код в функцию активности onLongClick() - реакцию на долгое нажатие на клетку");
        return false;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        int color = ((ColorDrawable)cells[tappedY][tappedX].getBackground()).getColor();

        if (tappedX - 1 >= 0) {
            int cur_color = ((ColorDrawable)cells[tappedY][tappedX - 1].getBackground()).getColor();
            if (cur_color == Color.RED) {
                cells[tappedY][tappedX - 1].setBackgroundColor(Color.WHITE);
            }
            else {
                cells[tappedY][tappedX - 1].setBackgroundColor(Color.RED);
            }
        }
        if (tappedX + 1 < WIDTH) {
            int cur_color = ((ColorDrawable)cells[tappedY][tappedX + 1].getBackground()).getColor();
            if (cur_color == Color.RED) {
                cells[tappedY][tappedX + 1].setBackgroundColor(Color.WHITE);
            }
            else {
                cells[tappedY][tappedX + 1].setBackgroundColor(Color.RED);
            }
        }
        if (tappedY - 1 >= 0) {
            int cur_color = ((ColorDrawable)cells[tappedY - 1][tappedX].getBackground()).getColor();
            if (cur_color == Color.RED) {
                cells[tappedY - 1][tappedX].setBackgroundColor(Color.WHITE);
            }
            else {
                cells[tappedY - 1][tappedX].setBackgroundColor(Color.RED);
            }
        }
        if (tappedY + 1 < HEIGHT) {
            int cur_color = ((ColorDrawable)cells[tappedY + 1][tappedX].getBackground()).getColor();
            if (cur_color == Color.RED) {
                cells[tappedY + 1][tappedX].setBackgroundColor(Color.WHITE);
            }
            else {
                cells[tappedY + 1][tappedX].setBackgroundColor(Color.RED);
            }
        }

        boolean all_colored = true;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int cur_color = ((ColorDrawable)cells[i][j].getBackground()).getColor();
                if (cur_color == Color.WHITE) {
                    all_colored = false;
                }
            }
        }

        if (all_colored) {
            Task.showMessage(this, "Вы победили!");
        }
    }

	/*
     * NOT FOR THE BEGINNERS
	 * ==================================================
	 */

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}