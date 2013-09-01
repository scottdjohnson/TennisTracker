package scottdjohnson.android.tennistrackerapp;

public class Point {

	public boolean ace;
	public boolean firstServe;
	public boolean servePoint;
	/*
	 * service winner (unreturnable, not an ace): winner=true, servePoint=true
	 * double fault: 1st serve = false, winner=false, servePoint=true
	 */

	public boolean winner;
	public boolean net;
	public boolean forehand;
	public boolean importantPoint;
	public String notes;
	
	/*
	 * How do we know who won point, and who was server?
	 * point functions retrieve players based on order, maybe not necessary?
	 * 		
	 */
	Player server;		// server for this point
	Player pointEnder; 	// player who hit the winner or error
	
	public Point(Player server, Player pointEnder)
	{
		this.server = server;
		this.pointEnder = pointEnder;
		
		ace = false;
		firstServe = false;
		servePoint = false;
		winner = false;
		net = false;
		forehand = false;
		importantPoint = false;
		notes = new String("");
	}

	public void getStats(StatisticsCollection sc)
	{
		int player;
		int statServer;
		
		/*
		 * Determine which player this point refers to
		 */
		if (pointEnder == sc.p[0])
		{
			player = 0;
		}
		else
		{
			player = 1;
		}

		if (server == sc.p[0])
			statServer = 0;
		else
			statServer = 1;
		
		// is first or second serve point?
		// is first/second serve point won?
		/*
		 * Attach these stats to this player
		 */
		if (firstServe)
		{
			sc.firstServePoints[statServer]++;
			if (server == pointEnder && winner == true)
				sc.firstServePointsWon[statServer]++;
		}
		else
		{
			sc.secondServePoints[statServer]++;
			if (server == pointEnder && winner == true)
				sc.secondServePointsWon[statServer]++;
			
			if (!winner && servePoint)
				sc.doubleFaults[player]++;
		}
		
		if (winner)
		{
			if (forehand)
				sc.forehandWinners[player]++;
			else
				sc.backhandWinners[player]++;

			sc.pointsWon[player]++;
		}
		else
		{
			if (forehand)
				sc.forehandErrors[player]++;
			else
				sc.backhandErrors[player] ++;

			sc.pointsWon[(player+1) %2]++;
		}
		
		if (ace)
			sc.aces[player]++;
	}	
}
