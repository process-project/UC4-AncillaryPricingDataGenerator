package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.starter.GeneratorStarter;
import com.lhsystems.module.datageneratorancillary.service.sqlite.SqliteConnection;
import com.lhsystems.module.datageneratorancillary.service.sqlite.read.SqliteReader;
import com.lhsystems.module.datageneratorancillary.service.sqlite.write.SqliteWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Starts generator with options read from yml file.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Component
public class MainBean {

    /**
     * Starts next generator classes in proper waz.
     */
    private final GeneratorStarter generatorStarter;

    /**
     * Reads options from CommandLine.
     */
    private final CommandLineOptionsReader commandLineReader;

    /**
     * Reads options from Yaml files.
     */
    private final YamlOptionReader optionReader;

    /**
     * Reads ssim file for generate airports and routes.
     */
    private final SSIMFileReader ssimReader;

    /**
     * Reads data from a SQLite database.
     */
    private SqliteReader sqliteReader;

    /**
     * Used to write the generated flights into the database.
     */
    private SqliteWriter sqliteWriter;


    /**
     * Instantiates a new Main bean.
     *
     * @param generatorStarterParam    the generator starter
     * @param commandLineOptionsReader the command line options reader
     * @param yamlOptionReader         the yaml option reader
     * @param ssimFileReader           the ssim file reader
     */
    @Autowired
    public MainBean(final GeneratorStarter generatorStarterParam,
                    final CommandLineOptionsReader commandLineOptionsReader,
                    final YamlOptionReader yamlOptionReader,
                    final SSIMFileReader ssimFileReader) {
        this.generatorStarter = generatorStarterParam;
        this.commandLineReader = commandLineOptionsReader;
        this.optionReader = yamlOptionReader;
        this.ssimReader = ssimFileReader;
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * SQLite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object. Afterwards saves the
     * generated flights in a SQLite dataBase.
     *
     * @param args default parameter for main method. No use for now.
     * @throws ClassNotFoundException if the class "org.sqlite.JDBC" can't found.
     * @throws SQLException           if something went wrong during communication to the SQLite             database
     */
// CHECKSTYLE:OFF
    public void start(final String[] args)
            throws ClassNotFoundException, SQLException {
        // CHECKSTYLE:ON
        initializeSqlLiteConnection();
        final String yamlPath = commandLineReader.getYamlPathFromCommandLine(args);
        final long maxId = sqliteReader.getMaxId() + 1;

        generateAirlines(maxId, yamlPath);
    }

    /**
     * Start airline generator with options read from Yaml file.
     *
     * @param maxId
     *            an upper bound for ids in the tables
     *
     * @param yamlOptionsPath
     *             path to .yml file where are generator options
     */
    private void generateAirlines(final long maxId, final String yamlOptionsPath) {
        final GeneratorConfiguration generatorConfiguration = optionReader.readGeneratorOptions(yamlOptionsPath);
        final List<String> ssimLines = ssimReader.getSsimFileLines("/ssim-mini.ssim");
        generatorStarter.generateData(maxId, generatorConfiguration, ssimLines);
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
