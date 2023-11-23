package com.laca.service;

import com.laca.BL.FactoryUnitTransport.FactoryUnitTransporter;
import com.laca.entity.PackageUnitAbstract.UnitTransporterAbstract;
import jakarta.transaction.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitTransportService {
    private final DataSource dataSource;

    public UnitTransportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<UnitTransporterAbstract> getAllUnitTransporters() {
        List<UnitTransporterAbstract> UnitTransport = new ArrayList<UnitTransporterAbstract>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM transporters";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UnitTransporterAbstract unitTransporterAbstract = new UnitTransporterAbstract();
                unitTransporterAbstract.setName(resultSet.getString("name"));
                unitTransporterAbstract.setPlate(resultSet.getString("plate"));
                unitTransporterAbstract.setHigh(resultSet.getLong("high"));
                unitTransporterAbstract.setWidth(resultSet.getLong("width"));
                unitTransporterAbstract.setType(resultSet.getString("type"));
                unitTransporterAbstract.setMaxWeight(resultSet.getLong("name"));
                unitTransporterAbstract.setIsActive(resultSet.getBoolean("isActive"));
                unitTransporterAbstract =FactoryUnitTransporter.createUnitTransport(unitTransporterAbstract);

                UnitTransport.add(unitTransporterAbstract);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        return UnitTransport;
    }

    @Transactional
    public UnitTransporterAbstract saveTransporter(UnitTransporterAbstract unitTransporterAbstract) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO transporters (name, company) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, unitTransporterAbstract.getType());
            statement.setString(2, unitTransporterAbstract.getCompany());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    transporter.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new transporter");
        }
        return transporter;
    }

    @Transactional
    public Transporter updateTransporter(Long transporterId, Transporter updatedTransporter) {
        try (Connection connection = dataSource.getConnection()) {
            String storedProcedureCall = "{call update_transporter(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storedProcedureCall);

            statement.setLong(1, transporterId);
            statement.setString(2, updatedTransporter.getName());
            statement.setString(3, updatedTransporter.getCompany());

            boolean hasResults = statement.execute();

            if (!hasResults) {
                throw new RuntimeException("Error updating transporter: No results from the stored procedure.");
            }

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int updatedId = resultSet.getInt("id");
                String updatedName = resultSet.getString("name");
                String updatedCompany = resultSet.getString("company");

                // Crea un nuevo Transporter con los datos actualizados y devuélvelo
                updatedTransporter.setId((long) updatedId);
                updatedTransporter.setName(updatedName);
                updatedTransporter.setCompany(updatedCompany);

                return updatedTransporter;
            } else {
                throw new RuntimeException("Transporter not found by ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating transporter: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Transporter getTransporterById(Long transporterId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT id, name, company FROM transporters WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, transporterId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Transporter transporter = new Transporter();
                transporter.setId(resultSet.getLong("id"));
                transporter.setName(resultSet.getString("name"));
                transporter.setCompany(resultSet.getString("company"));
                return transporter;
            } else {
                throw new RuntimeException("Transporter not found with ID: " + transporterId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving transporter: " + e.getMessage(), e);
        }
    }


    @Transactional
    public Boolean deleteTransporter(Long transporterId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM transporters where transporters.id  = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, transporterId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transporter: " + e.getMessage(), e);
        }
    }
}
