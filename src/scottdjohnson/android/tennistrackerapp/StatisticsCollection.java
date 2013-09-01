package scottdjohnson.android.tennistrackerapp;

public class StatisticsCollection {
	public Player p[];

	public int aces[];
	public int doubleFaults[];
	public int firstServePoints[];
	public int secondServePoints[];
	public int firstServePointsWon[];
	public int secondServePointsWon[];
	
	public int pointsWon[];
	public int forehandWinners[];
	public int forehandErrors[];
	public int backhandWinners[];
	public int backhandErrors[];
	public int netPointsWon[];
	public int netPointsLost[];
	public int breakPoints[];
	public int breakPointsWon[];
	
	public StatisticsCollection(Player a, Player b)
	/*
	 * 	This is arbitrary, but the first player passed to the function will be index 0,
	 * 	the second will be index 1
	 */
	{
		p = new Player[2];
		p[0] = a;
		p[1] = b;
		
		aces  			= new int[2];
		doubleFaults 	= new int[2];
		pointsWon 			= new int[2];		
		
		firstServePoints		= new int[2];
		secondServePoints		= new int[2];
		firstServePointsWon		= new int[2];
		secondServePointsWon	= new int[2];

		/* forget winners/errors, just forehand winner, f errors, b winners, b errors*/
		forehandWinners	= new int[2];
		forehandErrors 	= new int[2];
		backhandWinners = new int[2];
		backhandErrors	= new int[2];
		netPointsWon	= new int[2];
		netPointsLost 	= new int[2];
		breakPoints 	= new int[2];
		breakPointsWon 	= new int[2];
		
		for (int i = 0; i < 2; i++)
		{
			aces[i] 				= 0;
			doubleFaults[i] 		= 0;
			pointsWon[i] 			= 0;
			firstServePoints[i] 	= 0;
			secondServePoints[i] 	= 0;
			firstServePointsWon[i] 	= 0;
			secondServePointsWon[i]	= 0;			
			forehandWinners[i] 		= 0;
			forehandErrors[i] 		= 0;
			backhandWinners[i] 		= 0;
			backhandErrors[i] 		= 0;
			netPointsWon[i] 		= 0;
			netPointsLost[i] 		= 0;
			breakPoints[i] 			= 0;
			breakPointsWon[i] 		= 0;
		}
	}

}
