package Part2;

public interface TariffPolicy {

	String evaluateTrade(double proposedTariff, double minimumTariff);
}
