/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package scottdjohnson.android.tennistrackerapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
import android.widget.TextView;

/**
 * This class provides a basic demonstration of how to write an Android
 * activity. Inside of its window, it places a single view: an EditText that
 * displays and edits some internal text.
 */
public class TennisTrackerActivity extends Activity {
    
	static final private int SETUP_ID = Menu.FIRST;
    static final private int STATS_ID = Menu.FIRST + 1;
    
    private Match m;
    private Player a;
    private Player b;
    private Set s;

    private boolean firstServe;
    
    Player server;

    private TextView playerAservesView, playerBservesView, victoryView, winnerView;

    public TennisTrackerActivity() {
    }

    /** Called with the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate our UI from its XML layout description.
        setContentView(R.layout.tennistracker_activity);

        // Hook up button presses to the appropriate event handler.
        ((Button) findViewById(R.id.ace)).setOnClickListener(mAceListener);
        ((Button) findViewById(R.id.se)).setOnClickListener(mSEListener);
        ((Button) findViewById(R.id.aw)).setOnClickListener(mAWListener);
        ((Button) findViewById(R.id.ae)).setOnClickListener(mAEListener);
        ((Button) findViewById(R.id.bw)).setOnClickListener(mBWListener);
        ((Button) findViewById(R.id.be)).setOnClickListener(mBEListener);

        a = new Player("A");
        b = new Player("B");
        server = null;
        
		playerAservesView = (TextView)findViewById(getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerAserves","", ""));
		playerBservesView = (TextView)findViewById(getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerBserves","", ""));
	       
		victoryView = (TextView)findViewById(getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/victory","", ""));
		winnerView = (TextView)findViewById(getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/winner","", ""));

		m = new Match(a,b,this);
        
        firstServe = true;
    }
        
    /**
     * Called when the activity is about to start interacting with the user.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Called when your activity's options menu needs to be created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // We are going to create two menus. Note that we assign them
        // unique integer IDs, labels from our string resources, and
        // given them shortcuts.
        //menu.add(0, SETUP_ID, 0, R.string.setup).setShortcut('0', 'b');
        menu.add(0, STATS_ID, 0, R.string.stats).setShortcut('1', 'c');

        return true;
    }

    /**
     * Called right before your activity's option menu is displayed.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // Before showing the menu, we need to decide whether the clear
        // item is enabled depending on whether there is text to clear.
        // menu.findItem(CLEAR_ID).setVisible(mEditor.getText().length() > 0);

        return true;
    }

    /**
     * Called when a menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	StatisticsCollection sc;    	        	
    	final Intent i;
    	Bundle bundle;
    	int player;
   	
        switch (item.getItemId()) {
        	
        case SETUP_ID:
        	sc = new StatisticsCollection(a,b);
        	m.getStats(sc);
        	        	
        	i = new Intent(this, Setup.class);
        	bundle = new Bundle();
        	        	
        	i.putExtras(bundle);
        	startActivity(i);
            return true;
        	
        case STATS_ID:
        	
        	sc = new StatisticsCollection(a,b);
        	m.getStats(sc);
        	        	
        	i = new Intent(this, Stats.class);
        	bundle = new Bundle();
        	        	
        	if (sc.p[0] == a)
        		player = 0;
        	else
        		player = 1;
        	
        	bundle.putInt("aWinners", sc.forehandWinners[player] + sc.backhandWinners[player]);
        	bundle.putInt("aErrors", sc.forehandErrors[player] + sc.backhandErrors[player]);
        	bundle.putInt("bWinners", sc.forehandWinners[(player+1)%2] + sc.backhandWinners[(player+1)%2]);
        	bundle.putInt("bErrors", sc.forehandErrors[(player+1)%2] + sc.backhandErrors[(player+1)%2]);
        	
        	// aces and double faults
           	bundle.putInt("aAces", sc.aces[player]);
        	bundle.putInt("bAces", sc.aces[(player+1)%2]);
           	bundle.putInt("aDoubleFaults", sc.doubleFaults[player]);
        	bundle.putInt("bDoubleFaults", sc.doubleFaults[(player+1)%2]);        	
        	
        	// First serve percentage
        	bundle.putInt("aFirstServePercentage", getPercentage( sc.firstServePoints[player], (sc.firstServePoints[player] + sc.secondServePoints[player])));
        	bundle.putInt("bFirstServePercentage", getPercentage(sc.firstServePoints[(player+1)%2], (sc.firstServePoints[(player+1)%2] + sc.secondServePoints[(player+1)%2])));
        	
        	// First serve points won
        	bundle.putInt("aFirstServePointsWon", getPercentage(sc.firstServePointsWon[player], sc.firstServePoints[player]));
        	bundle.putInt("bFirstServePointsWon", getPercentage(sc.firstServePointsWon[(player+1)%2], sc.firstServePoints[(player+1)%2]));        	
        	
        	// Second serve points won
        	bundle.putInt("aSecondServePointsWon", getPercentage(sc.secondServePointsWon[player], sc.secondServePoints[player]));
        	bundle.putInt("bSecondServePointsWon",getPercentage(sc.secondServePointsWon[(player+1)%2], sc.secondServePoints[(player+1)%2]));     
        	
        	// Points won
        	bundle.putInt("aPointsWon", sc.pointsWon[player]);
        	bundle.putInt("bPointsWon",sc.pointsWon[(player+1)%2]);     
        	
        	
        	i.putExtras(bundle);
        	startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getPercentage (int numerator, int denominator)
    {
    	int retVal;
    	
    	try {
    		retVal = 100 * numerator/denominator;
    	}
    	catch(Exception e)
    	{
    		retVal = 0;
    	}
    	
    	return retVal;
    	
    }
    
    /**
     * A call-back for when the user presses the back button.
     */
    OnClickListener mAceListener = new OnClickListener() {
        public void onClick(View v) {        	
        	Point p 		= new Point(server,server);
        	p.ace			= true;
        	p.winner 		= true;
        	p.servePoint 	= true;
        	p.firstServe	= firstServe;

        	resetServe();

        	if (server == a)
        		m.point(a, b, p);
        	else
        		m.point(b, a, p);
        }
    };

    OnClickListener mSEListener = new OnClickListener() {
        public void onClick(View v) {        	
        	
        	if (true == firstServe)
        	{
                ((Button) findViewById(R.id.se)).setText("Double Fault ");	
        		firstServe = false;
        	}
        	else
        	{
	        	Point p 		= new Point(server,server);
	        	p.winner 		= false;
	        	p.servePoint 	= true;
	        	p.firstServe 	= false;
	        	
	        	resetServe();

	        	if (server == a)
	        		m.point(b, a, p);
	        	else
	        		m.point(a, b, p);	        	
        	}
        }
    };

    OnClickListener mAWListener = new OnClickListener() {
        public void onClick(View v) {        	
        	Point p 	= new Point(server,a);
        	p.winner 	= true;
        	p.firstServe	= firstServe;
	
        	resetServe();

        	m.point(a, b, p);
        }
    };

    /**
     * A call-back for when the user presses the clear button.
     */
    OnClickListener mAEListener = new OnClickListener() {
        public void onClick(View v) {
        	Point p 	= new Point(server,a);
        	p.winner 	= false;
        	p.firstServe	= firstServe;

        	resetServe();
        	m.point(b, a, p);
        }
    };
    
    OnClickListener mBWListener = new OnClickListener() {
        public void onClick(View v) {
        	Point p = new Point(server,b);
        	p.winner 	= true;
        	p.firstServe	= firstServe;

        	resetServe();
        	m.point(b, a, p);      	
        }
    };
    
    OnClickListener mBEListener = new OnClickListener() {
        public void onClick(View v) {
        	Point p = new Point(server,b);
        	p.winner = false;
        	p.firstServe	= firstServe;

        	resetServe();
        	m.point(a, b, p);
        }
    };
    
    /* On click, do nothing */ 
    OnClickListener nullListener = new OnClickListener() {
        public void onClick(View v) {
        }
    };
    
    private void resetServe()
    {
        ((Button) findViewById(R.id.se)).setText("Second Serve ");	
    	firstServe = true;
    	victoryView.setText("");
    	winnerView.setText("");
    }
    
    public void won(Player p, String victory)
    {
    	victoryView.setText(victory);
    	winnerView.setText(p.getName());
    }
    
    public void newGame()
    {	
    	if (server == null || server == b)
    	{
    		server = a;
    		playerAservesView.setText("* ");
    		playerBservesView.setText("   ");
    	}
    	else
    	{
    		server = b;
    		playerBservesView.setText("* ");
    		playerAservesView.setText("   ");
    	}
    }
    
    public void matchOver(Player p)
    {
        ((Button) findViewById(R.id.ace)).setOnClickListener(nullListener);
        ((Button) findViewById(R.id.se)).setOnClickListener(nullListener);
        ((Button) findViewById(R.id.aw)).setOnClickListener(nullListener);
        ((Button) findViewById(R.id.ae)).setOnClickListener(nullListener);
        ((Button) findViewById(R.id.bw)).setOnClickListener(nullListener);
        ((Button) findViewById(R.id.be)).setOnClickListener(nullListener);    	
        victoryView.setText("Game, Set, Match: ");
        winnerView.setText(p.getName());
    }
}
