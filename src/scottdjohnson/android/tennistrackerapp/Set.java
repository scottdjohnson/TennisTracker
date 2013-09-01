package scottdjohnson.android.tennistrackerapp;


import java.util.*;

import android.widget.TextView;

public class Set {
	
	private TennisTrackerActivity app;
	private Player a;
	private Player b;
	
	private Game currentGame;
	private List games;

	private TextView gamesViewA, gamesViewB;
	
	public Set(Player a, Player b, TennisTrackerActivity app, int numSets)
	//public void onCreate(Bundle savedInstanceState)
	{		
		games = new ArrayList();
		currentGame = new Game(a,b,app);
		games.add(currentGame);

		this.a = a;
		this.b = b;
		this.app = app;
		
        gamesViewA = (TextView)app.findViewById(app.getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerAgames" + numSets,"", ""));
        gamesViewB = (TextView)app.findViewById(app.getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerBgames" + numSets,"", ""));

	}
	
	public void point(Player wonPoint, Player lostPoint, Point p)
	{		
		if (3 == wonPoint.getPoints() && 4 == lostPoint.getPoints())
			lostPoint.subtractPoint(); // From AD back to deuce
		else 
			wonPoint.addPoint();
					
		currentGame.point(wonPoint,lostPoint,p);

		if  (5 == wonPoint.getPoints () ||
				(4 == wonPoint.getPoints() && 2 >= lostPoint.getPoints()) )
				wonGame(wonPoint);//won game, add to set */
		
		gamesViewA.setText(String.valueOf(a.getGames()));
		gamesViewB.setText(String.valueOf(b.getGames()));

	}
	
	private void wonGame(Player wonGame)
	{
		wonGame.addGame();		
		
		/* Don't do this if the set is now over */
		if (!isSetOver())
		{
			currentGame = new Game(a,b,app);
			games.add(currentGame);
			app.won(wonGame,"Game: ");
		}
	}
	
	public void wonSet(Player wonSet, Player lostSet)
	{
		// add 
		wonSet.addSet();
		wonSet.newSet();
		lostSet.newSet();
		
		app.won(wonSet,"Game, Set: ");
	}
	
	public void getStats(StatisticsCollection sc)
	{
		for (int i = 0; i < games.size() ; i++)
		{
			((Game)games.get(i)).getStats(sc);
		}	
	}
	
	public boolean isSetOver()
	{
		/* Forget tie breaks for now */
		if (a.getGames() >= 6 || b.getGames() >= 6)
			return true;
		else
			return false;
	}
}
