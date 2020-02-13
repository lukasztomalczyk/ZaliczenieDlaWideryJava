package na.zaliczenie.lukasz.demo.controllers;

import org.javatuples.Triplet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculateController {

    @GetMapping
    public List<String> Oblicz() {
        String connectionUrl =
                "jdbc:sqlserver://develop_server:1433;"
                        + "database=master;"
                        + "user=sa;"
                        + "password=yourStrong(!)Password;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;

        try {
            List<Triplet<Double, Double, String>> lista = new ArrayList();
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver).newInstance();

            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();

            String selectSql = "SELECT * FROM Obliczenia";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                lista.add(new Triplet<Double, Double, String>(
                        resultSet.getDouble(0), resultSet.getDouble(1), resultSet.getString(2)));
            }

            return GetResult(lista);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> GetResult(List<Triplet<Double, Double, String>> values)
    {
        List<String> result = new ArrayList<String>();

        for (Triplet<Double, Double, String> x : values) {
            if (x.getValue2() == "dodaj")
                result.add(Dodaj(x.getValue0(), x.getValue1()));
            if (x.getValue2() == "podziel")
                result.add(Podziel(x.getValue0(), x.getValue1()));
            if (x.getValue2() == "odejmij")
                result.add(Odejmij(x.getValue0(), x.getValue1()));
            if (x.getValue2() == "pomnoz")
                result.add(Pomnoz(x.getValue0(), x.getValue1()));
        }
        return result;
    }

    private String Dodaj(Double value1, Double value2)
    {
        return value1 + " + " + value2 + " = " + (value1 + value2);
    }

    private String Podziel(Double value1, Double value2)
    {
        return value1 + " / " + value2 + " = " + (value1 / value2);
    }

    private String Odejmij(Double value1, Double value2)
    {
        return value1 + " - " + value2 + " = " + (value1 - value2);
    }
    private String Pomnoz(Double value1, Double value2)
    {
        return value1 + " * " + value2 + " = " + (value1 * value2);
    }
}
