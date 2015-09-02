package bms.nfccreditcardreader;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ReaderActivity extends Activity{

    private ImageView nfcImage;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        nfcImage = (ImageView) findViewById(R.id.nfcImage);

        setContentView(R.layout.activity_reader);

        IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
                final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE,
                        NfcAdapter.STATE_OFF);
                switch (state) {
                    case NfcAdapter.STATE_OFF:
                        createDialog("No NFC","test");
                        alertDialog.show();
                        break;
                    case NfcAdapter.STATE_TURNING_OFF:
                        break;
                    case NfcAdapter.STATE_ON:
                        break;
                    case NfcAdapter.STATE_TURNING_ON:
                        break;
                }
            }
        }
    };

    private void createDialog(String reason, String reasonDescription){
        alertDialog = new AlertDialog.Builder(ReaderActivity.this).create();
        alertDialog.setTitle(reason);
        alertDialog.setMessage(reasonDescription);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Zurück zur Startseite", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent intent = new Intent(ReaderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
