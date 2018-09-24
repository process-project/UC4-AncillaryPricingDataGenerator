package com.lhsystems.module.data.generator.ancillary;

import com.lhsystems.module.data.generator.ancillary.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.data.generator.ancillary.generator.starter.GeneratorStarter;
import com.lhsystems.module.data.generator.ancillary.sqlite.SqliteConnection;
import com.lhsystems.module.data.generator.ancillary.sqlite.read.SqliteReader;
import com.lhsystems.module.data.generator.ancillary.sqlite.write.SqliteWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class MainBean {

    private final GeneratorStarter generatorStarter;
    private final CommandLineOptionsReader commandLineOptionsReader;

    /**
     * Reads data from a SQLite database.
     */
    private SqliteReader sqliteReader;

    /**
     * Used to write the generated flights into the database.
     */
    private SqliteWriter sqliteWriter;

    private final YamlOptionReader optionReader;

    @Autowired
    public MainBean(GeneratorStarter generatorStarter,
                    CommandLineOptionsReader commandLineOptionsReader,
                    YamlOptionReader yamlOptionReader) {
        this.generatorStarter = generatorStarter;
        this.commandLineOptionsReader = commandLineOptionsReader;
        this.optionReader = yamlOptionReader;
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * SQLite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object. Afterwards saves the
     * generated flights in a SQLite dataBase.
     *
     * @param args
     *            default parameter for main method. No use for now.
     * @throws SQLException
     *             if something went wrong during communication to the SQLite
     *             database
     * @throws ClassNotFoundException
     *             if the class "org.sqlite.JDBC" can't found.
     */
    // CHECKSTYLE:OFF
    public void start(final String[] args)
            throws ClassNotFoundException, SQLException {
        // CHECKSTYLE:ON
        initializeSqlLiteConnection();
        final String yamlPath = commandLineOptionsReader.getYamlPathFromCommandLine(args);
        final long maxId = sqliteReader.getMaxId() + 1;

        generateAirlines(maxId, yamlPath);
    }

    /**
     * Create all the generators and read and generate data necessary for this.
     *
     * @param maxId
     *            an upper bound for ids in the tables
     *
     * @param yamlOptionsPath
     *             path to .yml file where are generator options
     */
    private void generateAirlines(final long maxId, String yamlOptionsPath) {
        GeneratorConfiguration generatorConfiguration = optionReader.readGeneratorOptions(yamlOptionsPath);
        generatorStarter.generateData(maxId, generatorConfiguration);
    }

    /**
     * Initialize readers and writers necessary for communication with the sqlLite
     * database.
     *
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws SQLException
     *             the SQL exception
     */
    private void initializeSqlLiteConnection() throws ClassNotFoundException, SQLException {
        final SqliteConnection connection = new SqliteConnection(optionReader.getDatabasePath());
        sqliteWriter = new SqliteWriter(connection.getConnection());
        sqliteReader = new SqliteReader(connection.getConnection());
    }

}
