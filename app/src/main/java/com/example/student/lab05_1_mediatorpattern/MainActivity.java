package com.example.student.lab02_interactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.student.lab05_1_mediatorpattern.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int mQuantity = 0;//初始值
    private int mPrice = 50;//初始值
    private  String mName= "鳴人";
    private StringBuilder mPriceMessage = new StringBuilder("NT$" +  mPrice);
    private StringBuilder mQuantityMessage = new StringBuilder(mQuantity);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPriceMessage();
    }


    //按下按鈕執行
    public void submitOrder(View view) {
        clearPriceMessageString();
        concatPriceMessageString();
        displayPriceMessage();
    }

    private void displayPriceMessage() {
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);//透過ID尋找TextView
        priceTextView.setText(String.valueOf(mPriceMessage));//重設
    }

    public void displayQuantityMessage() {
        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        int start = 0;
        int end = mQuantityMessage.length();
        mQuantityMessage.delete(start, end).append(mQuantity);
        quantityTextView.setText(mQuantityMessage);
    }

    private void clearPriceMessageString(){
        int start = 0;
        int end = mPriceMessage.length();
        mPriceMessage.delete(start, end);
    }

    public void cilckTopping(View view){
        resetPriceMessageString();
        displayPriceMessage();
    }

    private void resetPriceMessageString(){
        clearPriceMessageString();
        mPriceMessage.append("臭豆腐")
                .append("NT$:")
                .append(mPrice);
    }
    private void concatPriceMessageString(){
        CheckBox checkBox = (CheckBox)findViewById(R.id.toppings_checkbox);
        mPriceMessage.append("Name: ")
                .append(mName)
                .append("\n")
                .append("臭豆腐")
                .append("\n")
                .append("加泡菜")
                .append(checkBox.isChecked())
                .append("\n");
        if(mQuantity == 0){
            mPriceMessage.append("Free");
        }else {
            mPriceMessage.append("Quantity: ")
                    .append(mQuantity)
                    .append("\n")
                    .append("Total: ")
                    .append("NT$: ")
                    .append(mPrice * mQuantity)
                    .append("\n")
                    .append("Thank you!")
                    .append("\n");
        }
    }

    private void displayTotalPrice() {
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        int total = mQuantity * mPrice;
        String myString = NumberFormat.getCurrencyInstance().format(total);
        String message = myString + (mQuantity == 0 ? "\nFree" : "\nThankyou");
        priceTextView.setText(message);
    }

    public void resetTotalPrice() {
        TextView PriceTextView = (TextView)findViewById((R.id.price_text_view));
        PriceTextView.setText("");
    }

    public void increment(View view) {
        //從TextView取得初始值 +1個數量
        ++mQuantity;
        displayQuantityMessage();
        resetTotalPrice();
    }

    private int getQuantity() {
        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);//用ID尋找TextView
        String quantitystring = quantityTextView.getText().toString();//轉字串
        return Integer.parseInt(quantitystring);
    }

    public void decrement(View view) {
        //從TextView取得初始值 -1個數量
        int quantity = getQuantity();
        if(quantity > 0){
            --mQuantity;
        }
        displayQuantityMessage();
        resetTotalPrice();
    }

}
