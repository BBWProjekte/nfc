package bms.nfccreditcardreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button nfcOn;
    private Button nfcOff;
    private Button toReader;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcOn = (Button) findViewById(R.id.nfcOnButton);
        nfcOff = (Button) findViewById(R.id.nfcOffButton);
        toReader = (Button) findViewById(R.id.toReaderButton);

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            return true;
        }
        if (id == R.id.action_about) {
            createDialog("NFC Kreditkartenleser","\u00a9 2015\nLuka Kramer\nMatthias Koch\nJanes Thomas\n\nBMS Winterthur");
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void nfcOnPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if (!nfc.isEnabled())
        {
            Toast.makeText(getApplicationContext(), "Aktiviere NFC in den Optionen.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            //Toast.makeText(getApplicationContext(), "Klicke zurück, um wieder zur App zu gelangen.", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "NFC ist bereits aktiviert.", Toast.LENGTH_LONG).show();
        }
    }

    public void nfcOffPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if(nfc.isEnabled()){
            Toast.makeText(getApplicationContext(), "Deaktiviere NFC in den Optionen.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            //Toast.makeText(getApplicationContext(), "Klicke zurück, um wieder zur App zu gelangen.", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "NFC ist bereits deaktiviert.", Toast.LENGTH_LONG).show();
        }
    }

    public void toReaderPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if(!nfc.isEnabled()){
            Toast.makeText(getApplicationContext(),"Hast du nicht was vergessen?", Toast.LENGTH_LONG).show();
        } else{
            Intent intent = new Intent(MainActivity.this, ReaderActivity.class);
            startActivity(intent);
        }
    }

    private void createDialog(String reason, String reasonDescription){
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(reason);
        alertDialog.setMessage(reasonDescription);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Zurück zur Startseite", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
