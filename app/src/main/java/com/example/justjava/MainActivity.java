
//Librerias
package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

//clase principal
public class MainActivity extends AppCompatActivity {

    int quantity=1;//varible para saber la orden total

    @Override

    //conexion entre java y xml
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //metodo para incrementar la orden
    public void increment(View view){
        //Define un limite que es de 10 cafes
        if (quantity == 10) {
            Toast.makeText(this, "You cannot have more than 10 coffes ", Toast.LENGTH_SHORT).show();// Si el usuario pide mas de 10 cafes se le mostrara este mensaje
            return;
        }
        quantity = quantity +1;//Incrementa el valor de  quantity
        displayQuantity(quantity);//Muestra el valor
    }
    //Metodo para decremetar en la orden
    public void decrement(View view){
        //Determina el valor minimo a pedir (orden)
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffes", Toast.LENGTH_SHORT).show();// Si el usuario pide mas de 10 cafes se le mostrara este mensaje
            return;
        }
        quantity = quantity -1;//Decrementa el valor de la variable quantity
        displayQuantity(quantity);//Muestra el valor
    }
    /**
     * Este método muestra el valor de la cantidad dada en la pantalla.
     */
    //Metodo para inivar orden
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);//texto dado por el Cliente (nombre)
        String name = nameField.getText().toString();//Variable que guarda el el texto dado por el usurio
        // Figure out if the user wants whipped cream topping (Averigua si el usuario quiere crema batida)
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);// se obtine valores boolean del CheckBoox
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping (Averiguar si el usuario quiere cobertura de chocolate)
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);// se obtine valores boolean del CheckBoox
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price (Calcule el precio)
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen (Mostrar el resumen del pedido en la pantalla)
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(message);
    }

    //Metodo para calcular el precio
    private int calculatePrice (boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;// se define el precio base

        if (addWhippedCream){//Si el usuario a elegido el topping de crema se suma al precio base
            basePrice = basePrice + 1;
        }

        if (addChocolate){//Si el usuario a elegido el topping de chocloate se suma al precio base
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;//Se multiplica la cantida de cafes  por el precio base
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name){//metodo para crear la orden
        //Guarda los valores
        String priceMessage = "Name " + name;
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd whipped chocolate?" + addChocolate;
        priceMessage = priceMessage + "\nQuantity: "+ quantity;
        priceMessage = priceMessage + "\nTotal: $ "+ price;
        priceMessage = priceMessage + "\nthank you!";
        return priceMessage;
    }



    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}

