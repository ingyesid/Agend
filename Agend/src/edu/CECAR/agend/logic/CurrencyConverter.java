
package edu.CECAR.agend.logic;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class CurrencyConverter {

    final static double pesos = 1900;
    final static double euro = 2480;
    final static double dolar_euro = 0.7704;
    final static double euro_dolar = 1.2980;

    public static double USDToCOP(double nUSD) {
        return (nUSD * pesos);
    }

    public static double COPToUSD(double nCOP) {
        return (nCOP / pesos);
    }

    public static double EUROToCOP(double nEURO) {
        return (nEURO * euro);
    }

    public static double COPToEURO(double nCOP) {
        return (nCOP / euro);
    }

    public static double USDToEURO(double nUSD) {
        return (nUSD * dolar_euro);
    }

    public static double EUROToUSD(double nEURO) {

        return (nEURO * 1.2980);

    }
}
