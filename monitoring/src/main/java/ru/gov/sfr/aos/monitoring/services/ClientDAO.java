/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.conf.DBConnection;
import ru.gov.sfr.aos.monitoring.exceptions.DaoException;
import ru.gov.sfr.aos.monitoring.repair.RepairDto;
import ru.gov.sfr.aos.monitoring.transfer.TransferDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class ClientDAO {
   @Autowired
  protected DBConnection dBConnection;
    
    private static final String ADD_REPAIR = "INSERT INTO repair (date_repair, definition, document_number, object_buing_id) VALUES(?,?,?,?)";
    private static final String ADD_TRANSFER = "INSERT INTO transfer (date_transfer, document_number, inventary_number_new, inventary_number_old, transfer_from, transfer_to, object_buing_id) VALUES(?,?,?,?,?,?,?)";
    private static final String EDIT_REPAIR = "UPDATE repair SET date_repair = ?, definition = ?, document_number = ?, object_buing_id = ? WHERE id = ?";
    private static final String EDIT_TRANSFER = "UPDATE transfer SET date_transfer = ?, inventary_number_new = ?, inventary_number_old = ?, transfer_from = ?, transfer_to = ?,  document_number = ?, object_buing_id = ? WHERE id = ?";
    private static final String DELETE_REPAIR = "DELETE FROM repair WHERE id = ?";
    private static final String DELETE_TRANSFER = "DELETE FROM transfer WHERE id = ?";

    public static String getADD_REPAIR() {
        return ADD_REPAIR;
    }
    
     public void addRepair(RepairDto dto) throws IOException {
        try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(ADD_REPAIR)) {
            Date date = null;
            if(dto.getDateRepair() != null) {
                date = new Date(dto.getDateRepair().getTime());
                
            } else {
                date = new Date(System.currentTimeMillis());
            }
            
            preparedStatement.setDate(1, date);
            if(dto.getDefinition() != null && !dto.getDefinition().isBlank() && !dto.getDefinition().isEmpty()) {
                preparedStatement.setString(2, dto.getDefinition());
            } else {
                preparedStatement.setString(2, "отсутствует");
            }
            if(dto.getDocumentNumber() != null && !dto.getDocumentNumber().isBlank() && !dto.getDocumentNumber().isEmpty()) {
                preparedStatement.setString(3, dto.getDocumentNumber());
            } else {
                preparedStatement.setString(3, "отсутствует");
            }
            
            preparedStatement.setLong(4, dto.getIdObjectBuing());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }

    }
     
     public void deleteRepair(Long id) {
          try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(DELETE_REPAIR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
     }
     
     
      public void deleteTransfer(Long id) {
          try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(DELETE_TRANSFER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
     }
     
     public void editRepair(RepairDto dto) throws IOException {
        try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(EDIT_REPAIR)) {
             Date date = null;
            if(dto.getDateRepair() != null) {
                date = new Date(System.currentTimeMillis());
            } else {
                date = new Date(dto.getDateRepair().getTime());
            }
            
            preparedStatement.setDate(1, date);
            if(dto.getDefinition() != null && !dto.getDefinition().isBlank() && !dto.getDefinition().isEmpty()) {
                preparedStatement.setString(2, dto.getDefinition());
            } else {
                preparedStatement.setString(2, "отсутствует");
            }
            if(dto.getDocumentNumber() != null && !dto.getDocumentNumber().isBlank() && !dto.getDocumentNumber().isEmpty()) {
                preparedStatement.setString(3, dto.getDocumentNumber());
            } else {
                preparedStatement.setString(3, "отсутствует");
            }
            preparedStatement.setLong(4, dto.getIdObjectBuing());
            preparedStatement.setLong(5, dto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }

    }
     
     public void editTransfer(TransferDto dto) throws IOException {
        try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(EDIT_TRANSFER)) {
           Date date = null;
            if(dto.getDateTransfer() != null) {
            date = new Date(dto.getDateTransfer().getTime());
            } else {
                date = new Date(System.currentTimeMillis());
            }
            preparedStatement.setDate(1, date);
            if(dto.getDocumentNumber() != null && !dto.getDocumentNumber().isBlank() && !dto.getDocumentNumber().isEmpty()) {
                preparedStatement.setString(2, dto.getDocumentNumber());
            } else {
                preparedStatement.setString(2, "отсутствует");
            }
            if(dto.getInventaryNumberNew() != null && !dto.getInventaryNumberNew().isBlank() && !dto.getInventaryNumberNew().isEmpty()) {
                preparedStatement.setString(3, dto.getInventaryNumberNew());
            } else {
                preparedStatement.setString(3, "отсутствует");
            }
            if(dto.getInventaryNumberOld() != null && !dto.getInventaryNumberOld().isBlank() && !dto.getInventaryNumberOld().isEmpty()) {
                preparedStatement.setString(4, dto.getInventaryNumberOld());
            } else {
                preparedStatement.setString(4, "отсутствует");
            }
            if(dto.getTransferFrom() != null && !dto.getTransferFrom().isBlank() && !dto.getTransferFrom().isEmpty()) {
                preparedStatement.setString(5, dto.getTransferFrom());
            } else {
                preparedStatement.setString(5, "отсутствует");
            }
            if(dto.getTransferTo() != null && !dto.getTransferTo().isBlank() && !dto.getTransferTo().isEmpty()) {
                preparedStatement.setString(6, dto.getTransferTo());
            } else {
                preparedStatement.setString(6, "отсутствует");
            }
            preparedStatement.setLong(7, dto.getIdObjectBuing());
            preparedStatement.setLong(8, dto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }

    }
     
     
     public void addTransfer(TransferDto dto) throws IOException {
        try ( var connection = dBConnection.getConnection();  var preparedStatement = connection.prepareStatement(ADD_TRANSFER)) {
            Date date = null;
            if(dto.getDateTransfer() != null) {
            date = new Date(dto.getDateTransfer().getTime());
            } else {
                date = new Date(System.currentTimeMillis());
            }
            preparedStatement.setDate(1, date);
            if(dto.getDocumentNumber() != null && !dto.getDocumentNumber().isBlank() && !dto.getDocumentNumber().isEmpty()) {
                preparedStatement.setString(2, dto.getDocumentNumber());
            } else {
                preparedStatement.setString(2, "отсутствует");
            }
            if(dto.getInventaryNumberNew() != null && !dto.getInventaryNumberNew().isBlank() && !dto.getInventaryNumberNew().isEmpty()) {
                preparedStatement.setString(3, dto.getInventaryNumberNew());
            } else {
                preparedStatement.setString(3, "отсутствует");
            }
            if(dto.getInventaryNumberOld() != null && !dto.getInventaryNumberOld().isBlank() && !dto.getInventaryNumberOld().isEmpty()) {
                preparedStatement.setString(4, dto.getInventaryNumberOld());
            } else {
                preparedStatement.setString(4, "отсутствует");
            }
            if(dto.getTransferFrom() != null && !dto.getTransferFrom().isBlank() && !dto.getTransferFrom().isEmpty()) {
                preparedStatement.setString(5, dto.getTransferFrom());
            } else {
                preparedStatement.setString(5, "отсутствует");
            }
            if(dto.getTransferTo() != null && !dto.getTransferTo().isBlank() && !dto.getTransferTo().isEmpty()) {
                preparedStatement.setString(6, dto.getTransferTo());
            } else {
                preparedStatement.setString(6, "отсутствует");
            }
            
            preparedStatement.setLong(7, dto.getIdObjectBuing());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }

    }
}
