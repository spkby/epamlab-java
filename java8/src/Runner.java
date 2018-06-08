import by.gsu.epamlab.Trial;


public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trial[] trials = {
				new Trial("Sakovich", 45, 93),
				null,
				new Trial("Zhylinsky", 51, 35),
				new Trial()
			};
		for (Trial trial : trials) {
			System.out.println(trial);
		}
	}

}
