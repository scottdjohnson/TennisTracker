package scottdjohnson.android.tennistrackerapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Stats extends Activity{
	
	private TextView aWinnersView, bWinnersView, aErrorsView, bErrorsView, aAcesView, bAcesView, 
			aDoubleFaultsView, bDoubleFaultsView, aFirstServePercentageView, bFirstServePercentageView, 
			aFirstServePointsWonView, bFirstServePointsWonView, aSecondServePointsWonView, bSecondServePointsWonView,
			aPointsWonView, bPointsWonView;
	
	public Stats()
	{
		
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Player a = (Player)savedInstanceState.get("getPlayer");
        // Inflate our UI from its XML layout description.
        setContentView(R.layout.stats);
        
        Bundle bundle = getIntent().getExtras();
        
        int aWinners = bundle.getInt("aWinners");      		
        aWinnersView = (TextView)findViewById(R.id.playerAwinners);
        aWinnersView.setText(String.valueOf(aWinners));

        int bWinners = bundle.getInt("bWinners");      		
        bWinnersView = (TextView)findViewById(R.id.playerBwinners);
        bWinnersView.setText(String.valueOf(bWinners));

        int aErrors = bundle.getInt("aErrors");      		
        aErrorsView = (TextView)findViewById(R.id.playerAerrors);
        aErrorsView.setText(String.valueOf(aErrors));

        int bErrors = bundle.getInt("bErrors");      		
        bErrorsView = (TextView)findViewById(R.id.playerBerrors);
        bErrorsView.setText(String.valueOf(bErrors));

        int aAces = bundle.getInt("aAces");      		
        aAcesView = (TextView)findViewById(R.id.playerAaces);
        aAcesView.setText(String.valueOf(aAces));

        int bAces = bundle.getInt("bAces");      		
        bAcesView = (TextView)findViewById(R.id.playerBaces);
        bAcesView.setText(String.valueOf(bAces));
        
        int aDoubleFaults = bundle.getInt("aDoubleFaults");      		
        aDoubleFaultsView = (TextView)findViewById(R.id.playerAdoubleFaults);
        aDoubleFaultsView.setText(String.valueOf(aDoubleFaults));

        int bDoubleFaults = bundle.getInt("bDoubleFaults");      		
        bDoubleFaultsView = (TextView)findViewById(R.id.playerBdoubleFaults);
        bDoubleFaultsView.setText(String.valueOf(bDoubleFaults));
        
        int aFirstServePercentage = bundle.getInt("aFirstServePercentage");      		
        aFirstServePercentageView = (TextView)findViewById(R.id.playerAfirstServePercentage);
        aFirstServePercentageView.setText(String.valueOf(aFirstServePercentage) + "%");

        int bFirstServePercentage = bundle.getInt("bFirstServePercentage");      		
        bFirstServePercentageView = (TextView)findViewById(R.id.playerBfirstServePercentage);
        bFirstServePercentageView.setText(String.valueOf(bFirstServePercentage) + "%");
        
        int aFirstServePointsWon = bundle.getInt("aFirstServePointsWon");      		
        aFirstServePointsWonView = (TextView)findViewById(R.id.playerAfirstServePointsWon);
        aFirstServePointsWonView.setText(String.valueOf(aFirstServePointsWon) + "%");

        int bFirstServePointsWon = bundle.getInt("bFirstServePointsWon");      		
        bFirstServePointsWonView = (TextView)findViewById(R.id.playerBfirstServePointsWon);
        bFirstServePointsWonView.setText(String.valueOf(bFirstServePointsWon) + "%");
        
        int aSecondServePointsWon = bundle.getInt("aSecondServePointsWon");      		
        aSecondServePointsWonView = (TextView)findViewById(R.id.playerAsecondServePointsWon);
        aSecondServePointsWonView.setText(String.valueOf(aSecondServePointsWon) + "%");        

        int bSecondServePointsWon = bundle.getInt("bSecondServePointsWon");      		
        bSecondServePointsWonView = (TextView)findViewById(R.id.playerBsecondServePointsWon);
        bSecondServePointsWonView.setText(String.valueOf(bSecondServePointsWon) + "%"); 
       
        int aPointsWon = bundle.getInt("aPointsWon");      		
        aPointsWonView = (TextView)findViewById(R.id.playerApointsWon);
        aPointsWonView.setText(String.valueOf(aPointsWon)); 

        int bPointsWon = bundle.getInt("bPointsWon");      		
        bPointsWonView = (TextView)findViewById(R.id.playerBpointsWon);
        bPointsWonView.setText(String.valueOf(bPointsWon) ); 
    }
    
    public void onDestroy() {
        // Remove AccountManager callback
        super.onDestroy();
    }    
}
