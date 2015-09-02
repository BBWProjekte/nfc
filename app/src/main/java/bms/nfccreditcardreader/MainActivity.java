package bms.nfccreditcardreader;

import android.app.Activity;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nfcOnPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if (!nfc.isEnabled())
        {
            Toast.makeText(getApplicationContext(), "Aktiviere NFC in den Optionen.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        } else{
            Toast.makeText(getApplicationContext(), "NFC ist bereits aktiviert.", Toast.LENGTH_LONG).show();
        }
    }

    public void nfcOffPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if(nfc.isEnabled()){
            Toast.makeText(getApplicationContext(), "Deaktiviere NFC in den Optionen.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        } else{
            Toast.makeText(getApplicationContext(), "NFC ist bereits deaktiviert.", Toast.LENGTH_LONG).show();
        }
    }

    public void toReaderPressed(View view){

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(view.getContext());

        if(!nfc.isEnabled()){
            Toast.makeText(getApplicationContext(),"Hast du nicht was vergessen?", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(),"Working...", Toast.LENGTH_LONG).show();
        }
    }
}
