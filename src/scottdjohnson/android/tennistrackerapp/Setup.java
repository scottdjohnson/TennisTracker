package scottdjohnson.android.tennistrackerapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Setup extends Activity{
	
	
	public Setup()
	{
		
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Player a = (Player)savedInstanceState.get("getPlayer");
        // Inflate our UI from its XML layout description.
        setContentView(R.layout.setup);
        
        Bundle bundle = getIntent().getExtras();

    }
    
    public void onDestroy() {
        // Remove AccountManager callback
        super.onDestroy();
    }    
}
