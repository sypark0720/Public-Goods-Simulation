package publicGoods;

import java.util.ArrayList;
import java.util.List;

import actors.Contributor;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
//		System.out.println("Phase 1.. all permutations of 3 different types of contributors.");
//		driver.runWithPermutations(3, 2, 0f, false, 0f, 0f);
//		System.out.println("Phase 2...............");
		driver.phase2();
//		driver.phase3();
	}
	
	private void runWithPermutations(int actorCount, float potMultiplier, float flatReward, boolean divyPotByPercent, float punishment, float minEntry, float learningRate) {
		float bigSpender = 1f;
		float middleMan = 0.5f;
		float miser = 0.0f;
		int pot = 1000;
		float[] types = new float[] { bigSpender, middleMan, miser };	
		List<List<Float>> permutationList = makeSets(types, new ArrayList<Float>(), 0, actorCount);
		
		for(List<Float> list : permutationList) {
			GameController gc = new GameController(100, potMultiplier, divyPotByPercent, flatReward, punishment, minEntry, learningRate);
			for(Float type : list) {
				gc.addContributor(new Contributor(type, pot));
			}
			gc.run();
			System.out.println();
		}
	}
	
	private void phase2() {
//		System.out.println("more actors..........................................");
//		runWithPermutations(5, 2, 0, false, 0f, 0f, .1f);
//		System.out.println("Variable multiplier ..................................");
//		runWithPermutations(3, 3, 0, false, 0f, 0f, .1f);
//		System.out.println("Flat reward added to the one given most resources.....");
//		runWithPermutations(3, 2, 100, false, 0f, 0f, .1f);
//		System.out.println("More personalities introduced.........................");
//		GameController gc = new GameController(1, 2, false, 0, 0, 0, 0);
//		gc.addContributor(new Contributor(.25f, 500));
//		gc.addContributor(new Contributor(.75f, 500));
//		gc.addContributor(new Contributor(.1f, 500));
//		gc.run();
//		System.out.println("percentage of pot split between actors ...............");
//		runWithPermutations(3, 2, 0, true, 0f, 0f, .1f);
//		System.out.println("place a punishment on everyone at end of round .......");
//		runWithPermutations(3, 2, 0, false, 20f, 0f, .1f);
		System.out.println("Minimum entry to receive payout........................");
		runWithPermutations(3, 2, 0, false, 0f, 10f, .1f);
	}
	
	private void phase3() {
		System.out.println("simulation changed to update contribution for best returns...");
		GameController gc = new GameController(100, 1.1f, false, 0, 0, 0, .1f);
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.addContributor(new Contributor((float)Math.random(), 100));
		gc.run();
	}
	
	private static List<List<Float>> makeSets(float[] types, List<Float> beginning, int location, int numberPlayers) {
		ArrayList<List<Float>> toReturn = new ArrayList<>();
		for(int i = location; i < types.length; i++) {
			List<Float> nextOnes = new ArrayList<Float>(beginning);
			while(nextOnes.size() < numberPlayers - 1) {
				nextOnes.add(types[i]);
				toReturn.addAll(makeSets(types, nextOnes, i + 1, numberPlayers));
			}
			nextOnes.add(types[i]);
			toReturn.add(new ArrayList<Float>(nextOnes));
		}
		return toReturn;
	}
}