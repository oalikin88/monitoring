/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.conf.DBConnection;
import ru.gov.sfr.aos.monitoring.exceptions.DaoException;
import ru.gov.sfr.aos.monitoring.models.PrinterAndCartridgeCountByLocationTable;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class PrinterAndCartridgeCountByLocationTableDAO {

  @Autowired
  DBConnection dBConnection;
    
    private final static String  SELECTPRINTERSANDCARTRIDGE = "SELECT loc.name as location, loc.id as location_id, CONCAT(manufacturer.name, ' ', m.name) as model_printer," 
                + " cart_prin.model_cartridges_id, m.id as model_id, "
                + "(SELECT count(*) "
                + "FROM printer "
                + "JOIN object_buing ob "
                + "ON printer.printer_id = ob.id  "
                + "WHERE ob.location_id = loc.id "
                + "AND printer.model_id = m.id "
                + "AND (printer_status = 'OK' "
                + "OR printer_status = 'REPAIR' "
                + "OR printer_status = 'DEFECTIVE') ) as count_printer, "
                + "(SELECT count(*) "
                + "FROM cartridge "
                + "JOIN object_buing ob "
                + "ON cartridge.cartridge_id = ob.id "
                + "WHERE ob.location_id = loc.id "
                + "AND cartridge.model_id = cart_prin.model_cartridges_id "
                + "AND util = FALSE "
                + "AND use_in_printer = FALSE) as count_cartridge "
                + "FROM location as loc, model as m "
                + "LEFT JOIN cartridge_model_models_printers cart_prin "
                + "ON m.id = cart_prin.models_printers_id "
                + "LEFT JOIN manufacturer manufacturer "
                + "ON m.manufacturer_id = manufacturer.id ";
    

    

    public List<PrinterAndCartridgeCountByLocationTable> getData() throws SQLException {
        
         List<PrinterAndCartridgeCountByLocationTable> list = new ArrayList<>();
          try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(SELECTPRINTERSANDCARTRIDGE)){
        var resultSet = preparedStatement.executeQuery();
       
        while (resultSet.next()) {
            PrinterAndCartridgeCountByLocationTable value = new PrinterAndCartridgeCountByLocationTable();
            value.setLocation(resultSet.getString("location"));
            value.setLocationId(resultSet.getLong("location_id"));
            value.setModelPrinter(resultSet.getString("model_printer"));
            value.setModelId(resultSet.getLong("model_id"));
            value.setCountPrinter(resultSet.getInt("count_printer"));
            value.setCountCartridge(resultSet.getInt("count_cartridge"));
            value.setModelCartridgeId(resultSet.getLong("model_cartridges_id"));
            list.add(value);
        }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        } finally {
              dBConnection.closeConnection();
          }
        
        return list;
    }

}
