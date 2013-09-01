/*
 * 	0 = 0
 * 	1 = 15
 *  2 = 30
 *  3 = 40/deuce
 *  4 = AD
 *  
 */

package scottdjohnson.android.tennistrackerapp;

import java.util.*;

import android.widget.TextView;

public class Game {
	
	private TextView pointsViewA, pointsViewB;
	private Player a, b;
	private List points;
	
	public Game(Player a, Player b, TennisTrackerActivity app)
	{
		this.a = a;
		this.b = b;
				
		a.newGame();
		b.newGame();
		
		points = new ArrayList();
		
		pointsViewA = (TextView)app.findViewById(app.getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerApoints","", ""));
		pointsViewB = (TextView)app.findViewById(app.getResources().getIdentifier(
        		"scottdjohnson.android.tennistrackerapp:id/playerBpoints","", ""));
		
      	pointsViewA.setText(a.getGameScore());
      	pointsViewB.setText(b.getGameScore());
      	
      	app.newGame();

	}
	
	public void point (Player wonPoint, Player lostPoint, Point p)
	{				
      	points.add(p);

      	pointsViewA.setText(a.getGameScore());
      	pointsViewB.setText(b.getGameScore());
      	
	}
	
	public void getStats(StatisticsCollection sc)
	{
		for (int i = 0; i < points.size(); i++)
		{
			((Point)points.get(i)).getStats(sc);
		}
	}

}
