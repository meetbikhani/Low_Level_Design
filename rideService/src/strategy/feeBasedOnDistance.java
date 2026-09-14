package rideService.src.strategy;

public class feeBasedOnDistance implements feeStrategy {
	int fixedfee = 10;
	int variablefee = 20;

	@Override
	public double calculateFee(double start, double end) {
		double distance = end - start;
		return fixedfee + variablefee * distance;
	}
}